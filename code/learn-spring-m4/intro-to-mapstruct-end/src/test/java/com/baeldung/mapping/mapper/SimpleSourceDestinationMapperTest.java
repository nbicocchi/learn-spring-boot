package com.baeldung.mapping.mapper;

import com.baeldung.mapping.entity.SimpleDestination;
import com.baeldung.mapping.entity.SimpleSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SimpleSourceDestinationMapperTest {
    @Autowired
    SimpleSourceDestinationMapper mapper;

    @Test
    void sourceToDestination() {
        SimpleSource source = new SimpleSource("a", "b");
        SimpleDestination destination = mapper.sourceToDestination(source);

        assertEquals(source.getName(), destination.getName());
        assertEquals(source.getDescription(), destination.getDescription());
    }

    @Test
    void destinationToSource() {
        SimpleDestination destination = new SimpleDestination("a", "b");
        SimpleSource source = mapper.destinationToSource(destination);

        assertEquals(source.getName(), destination.getName());
        assertEquals(source.getDescription(), destination.getDescription());
    }
}