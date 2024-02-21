package com.baeldung.ls.spel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SpELTest {

    @Autowired
    private SpELBeanA spELBeanA;

    @Autowired
    private SpELBeanB spELBeanB;

    @Test
    public void whenSpELBeanA_thenAllResolvedCorrectly() {
        assertNotNull(spELBeanA);
        assertEquals(5, spELBeanA.getAdd());
        assertEquals("Learn Spring", spELBeanA.getAddString());
        assertEquals(true, spELBeanA.isEqual());
        assertEquals("a", spELBeanA.getTernary());
        assertEquals("10", spELBeanA.getOtherBeanProperty());
    }

    @Test
    public void whenSpELBeanB_thenAllResolvedCorrectly() {
        assertNotNull(spELBeanB);
        assertEquals(10, spELBeanB.getProp1());
    }

}
