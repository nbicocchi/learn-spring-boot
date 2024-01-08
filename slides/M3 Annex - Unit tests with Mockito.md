# Unit tests with Mockito

This tutorial introduces the usage of Mockito for JUnit tests. If you are not familiar with JUnit, please read first the [this tutorial](https://www.vogella.com/tutorials/JUnit/article.html).

## Adding Mockito to a project

*Mockito* is a popular open source framework for mocking objects in software test. Using Mockito greatly simplifies the development of tests for classes with external dependencies.

A *mock object* is a dummy implementation for an interface or a class. It allows to define the output of certain method calls. They typically record the interaction with the system and tests can validate that.

This allows you to simplify the test setup.

![](images/mockito-usage-visualization.png)

Recent versions of Mockito can also mock static methods and final classes. See [Mockito limitations](https://github.com/mockito/mockito/wiki/FAQ#what-are-the-limitations-of-mockito) for an overview of the remaining limitations. Also private methods are not visible for tests, they can also not be mocked. See [private methods](https://github.com/mockito/mockito/wiki/Mockito-And-Private-Methods) for details.

Mockito records the interaction with mock and allows you to check if the mock object was used correct, e.g. if a certain method has been called on the mock. This allows you to implement behavior testing instead of only testing the result of method calls.

Using the Mockito libraries should be done with a modern dependency system like Maven or Gradle. All modern IDEs (Eclipse, Visual Studio Code, IntelliJ) support both Maven and Gradle.

Mockito provides several methods to create mock objects:

-   Using the `@ExtendWith(MockitoExtension.class)` extension for JUnit 5 in combination with the `@Mock` annotation on fields

-   Using the static `mock()` method.

-   Using the `@Mock`, `@InjectMocks` annotations.

If you use the `@Mock` annotation, you must trigger the initialization of the annotated fields. The `MockitoExtension` does this by calling the static method `MockitoAnnotations.initMocks(this)`.

For example, consider the following data model.

```
package com.vogella.junit5;

public class Database {

    public boolean isAvailable() {
        // currently not implemented, as this is just demo used in a software test
        return false;
    }
    public int getUniqueId() {
        return 42;
    }
}
```

```
package com.vogella.junit5;

public class Service {

    private Database database;

    public Service(Database database) {
        this.database = database;
    }

    public boolean query(String query) {
        return database.isAvailable();
    }

    @Override
    public String toString() {
        return "Using database with id: " + String.valueOf(database.getUniqueId());
    }
}
```

A unit test using Mockito which mocks the `Database` object could look like the following.

```
package com.vogella.junit5;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

    @Mock
    Database databaseMock;

    @Test
    public void testQuery()  {
        assertNotNull(databaseMock);
        when(databaseMock.isAvailable()).thenReturn(true);
        Service t  = new Service(databaseMock);
        boolean check = t.query("* from t");
        assertTrue(check);
    }
}
```
* Tells Mockito to create the mocks based on the @Mock annotation, this requires JUnit 5.
* Tells Mockito to mock the databaseMock instance
* Configure the Mock to return true when its isAvailable method is called, see later for more options
* Executes some code of the class under test
* Asserts that the method call returned true

>Static imports
>
>Mockito provides a lot of static methods for mock and asserts. By adding the `org.mockito.Mockito.*;` static import, you can use these methods directly in your tests. Static imports allow you to call static members, i.e., methods and fields of a class directly without specifying the class.
>
>Using static imports also greatly improves the readability of your test code.

## Configuring the return values of methods calls on the mock objects

Mockito allows to configure the return values of methods which are called on the mock via a fluent API. Unspecified method calls return "empty" values:

* null for objects
* 0 for numbers
* false for boolean
* empty collections for collections
* etc...

## Using `when().thenReturn()` and `when().thenThrow()`

Mocks can return different values depending on arguments passed into a method. The `when(...​.).thenReturn(...​.)` method chain is used to specify a return value for a method call with pre-defined parameters.

![](images/whenThenReturn.png)

You also can use methods like `anyString` or `anyInt` to define that dependent on the input type a certain value should be returned.

If you specify more than one value, they are returned in the order of specification, until the last one is used. Afterward the last specified value is returned.

The following demonstrates the usage of `when().thenReturn()`.

```
package com.vogella.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ServiceDatabaseIdTest {

    @Mock
    Database databaseMock;

    @Test
    void ensureMockitoReturnsTheConfiguredValue() {

        // define return value for method getUniqueId()
        when(databaseMock.getUniqueId()).thenReturn(42);

        Service service = new Service(databaseMock);
        // use mock in test....
        assertEquals(service.toString(), "Using database with id: 42");
    }
}
```

Other examples which demonstrates the configuration of Mockito are in the following listing. These are not real test, the test only validating the Mockito configuration.

```
package com.vogella.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MockitoWhenExampleTest {

    @Mock
    Iterator<String> i;

    Comparable<String> c;

    // demonstrates the return of multiple values
    @Test
    void testMoreThanOneReturnValue() {
        when(i.next()).thenReturn("Mockito").thenReturn("rocks");
        String result = i.next() + " " + i.next();
        // assert
        assertEquals("Mockito rocks", result);
    }

    // this test demonstrates how to return values based on the input
    // and that @Mock can also be used for a method parameter
    @Test
    void testReturnValueDependentOnMethodParameter(@Mock Comparable<String> c)  {
            when(c.compareTo("Mockito")).thenReturn(1);
            when(c.compareTo("Eclipse")).thenReturn(2);
            //assert
            assertEquals(1, c.compareTo("Mockito"));
            assertEquals(2, c.compareTo("Eclipse"));
    }

    // return a value based on the type of the provide parameter
    @Test
    void testReturnValueInDependentOnMethodParameter2(@Mock Comparable<Integer> c )  {
            when(c.compareTo(isA(Integer.class))).thenReturn(0);
            //assert
            assertEquals(0, c.compareTo(Integer.valueOf(4)));
    }

}
```

The `when().thenThrow()` method chain can be used to throw an exception.

```
package com.vogella.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MockitoThrowsTest {

    // demonstrates the configuration of a throws with Mockito
    // not a read test, just "testing" Mockito behavior
    @Test
    void testMockitoThrows() {
        Properties properties = Mockito.mock(Properties.class);

        when(properties.get(Mockito.anyString())).thenThrow(new IllegalArgumentException("Stuff"));

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> properties.get("A"));

        assertEquals("Stuff", exception.getMessage());
    }
}
```

## Using `doReturn().when()` and `doThrow when`

The `doReturn().when()` method configuration can be used to configure the reply of a mocked method call. This is similar to `when().thenReturn()`.

As `when().thenReturn()` is easier to read, you should prefer using it. But `doReturn` can be useful if you are using spies, e.g. with @Spy, in Mockito. In case of a spy Mockito uses the underlying object and `when().thenReturn()` will call the underlying method which may create side effects. If these side effects are not desired, use `doReturn`, e.g. if you spy on a list and a certain operation would result in an undesired exception from the original object, like requesting the element in position 2 of an empty list.

```
package com.vogella.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.Properties;

import org.junit.jupiter.api.Test;

class MockitoSpyWithListTest {

    // demonstrates of the spy function
    @Test
    void ensureSpyForListWorks() {
        var list = new ArrayList<String>();
        var spiedList = spy(list);

        doReturn("42").when(spiedList).get(99);
        String value = (String) spiedList.get(99);

        assertEquals("42", value);
    }
}
```

The `doThrow` variant can be used for methods which return `void` to throw an exception. This usage is demonstrated by the following code snippet.

```
package com.vogella.junit5;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.junit.jupiter.api.Test;

class MockitoThrowsTest {

    @Test
    public void testForIOException() throws IOException {
        // create an configure mock
        OutputStream mockStream = mock(OutputStream.class);
        doThrow(new IOException()).when(mockStream).close();

        // use mock
        OutputStreamWriter streamWriter = new OutputStreamWriter(mockStream);

        assertThrows(IOException.class, () -> streamWriter.close());
    }

}
```

## Wrapping Java objects with Spy

@Spy or the `spy()` method can be used to wrap a real object. Every call, unless specified otherwise, is delegated to the object.

```
package com.vogella.mockito.spy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MockitoSpyTest {

    @Spy
    List<String> spy = new LinkedList<>();

    @BeforeEach
    void setup() {
        // Alternative way of creating a spy
        // List<String> list = new LinkedList<>();
        // List<String> spy = spy(list);
    }

    @Test
    void testLinkedListSpyCorrect() {

        // when(spy.get(0)).thenReturn("foo");
        // would not work as the delegate it called so spy.get(0)
        // throws IndexOutOfBoundsException (list is still empty)

        // you have to use doReturn() for stubbing
        doReturn("foo").when(spy).get(0);

        assertEquals("foo", spy.get(0));
    }

}
```

## Verify the calls on the mock objects

Mockito keeps track of all the method calls and their parameters to the mock object. You can use the `verify()` method on the mock object to verify that the specified conditions are met. For example, you can verify that a method has been called with certain parameters. This kind of testing is sometimes called *behavior testing*. Behavior testing does not check the result of a method call, but it checks that a method is called with the right parameters.

```
package com.vogella.junit5;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MockitoVerifyTest {

    @Test
    public void testVerify(@Mock Database database)  {
        // create and configure mock
        when(database.getUniqueId()).thenReturn(43);

        // call method testing on the mock with parameter 12
        database.setUniqueId(12);
        database.getUniqueId();
        database.getUniqueId();

        // now check if method testing was called with the parameter 12
        verify(database).setUniqueId(ArgumentMatchers.eq(12));

        // was the method called twice?
        verify(database, times(2)).getUniqueId();

        // other alternatives for verifiying the number of method calls for a method
        verify(database, never()).isAvailable();
        verify(database, never()).setUniqueId(13);
        verify(database, atLeastOnce()).setUniqueId(12);
        verify(database, atLeast(2)).getUniqueId();

        // more options are
        // times(numberOfTimes)
        // atMost(numberOfTimes)
        // This let's you check that no other methods where called on this object.
        // You call it after you have verified the expected method calls.
        verifyNoMoreInteractions(database);
    }

}
```

In case you do not care about the value, use the `anyX`, e.g., `anyInt`, `anyString()`, or `any(YourClass.class)` methods.

## Using @InjectMocks for dependency injection

You also have the `@InjectMocks` annotation which tries to do constructor, method or field dependency injection of mock objects in to other type. It does not require `@Inject` to be present and also does not try to be a full features dependency injection framework but is helpful to wire up objects to be tested.

For example, assume you have a class named `ArticleManager` with the following constructor:

```
public ArticleManager(User user, ArticleDatabase database) {
 // more code...
}
```

This class can be constructed via Mockito. The parameters to the constructor could be supplied via mock objects as demonstrated by the following code snippet.

```
package com.vogella.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ArticleManagerTest {

    @Mock
    ArticleDatabase database;

    @Mock
    User user;

    @InjectMocks
    private ArticleManager manager;

    @Test
    void shouldDoSomething() {
       // TODO perform some tests with this managers
    }
}
```

Mockito can inject mocks either via constructor injection, setter injection, or property injection and in this order. So if `ArticleManager` would have a constructor that would only take `User` and setters for both fields, only the mock for `User` would be injected.

## Capturing the arguments

The `ArgumentCaptor` class allows to access the arguments of method calls during the verification. This allows to capture these arguments of method calls and to use them for tests.

To run this example you need to add [hamcrest-library](https://mvnrepository.com/artifact/org.hamcrest/hamcrest-library) to your project.

```
package com.vogella.junit5;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MockitoArgumentCaptureTest {

    @Captor
    private ArgumentCaptor<List<String>> captor;

    @Test
    public final void shouldContainCertainListItem(@Mock List<String> mockedList) {
        var asList = Arrays.asList("someElement_test", "someElement");
        mockedList.addAll(asList);

        verify(mockedList).addAll(captor.capture());
        List<String> capturedArgument = captor.getValue();
        assertThat(capturedArgument, hasItem("someElement"));
    }
}
```

## Resources
* [Unit tests with Mockito](https://www.vogella.com/tutorials/Mockito/article.html)