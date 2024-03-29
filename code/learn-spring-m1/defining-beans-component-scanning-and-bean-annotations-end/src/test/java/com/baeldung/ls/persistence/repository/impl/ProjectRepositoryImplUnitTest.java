package com.baeldung.ls.persistence.repository.impl;

import com.baeldung.other.persistence.repository.impl.ProjectRepositoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ProjectRepositoryImplUnitTest {

    @Test
    void findById_givenDummyId_returnsEmpty() {
        assertFalse(new ProjectRepositoryImpl().findById(123L).isPresent());
    }
}