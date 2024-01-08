package com.baeldung.ls.web.controller;

import com.baeldung.ls.web.dto.ProjectDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void givenProjectExists_whenGet_thenSuccess() {
        ResponseEntity<ProjectDto> response = restTemplate.getForEntity("http://localhost:" + port + "/projects/1", ProjectDto.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody().id(), equalTo(1L));
        assertThat(response.getBody().name(), equalTo("Project 1"));
    }

    @Test
    public void givenNewProject_whenCreated_thenSuccess() {
        ProjectDto newProject = new ProjectDto(17L, "Project 17", LocalDate.now());
        ResponseEntity<ProjectDto> response = restTemplate.postForEntity("http://localhost:" + port + "/projects", newProject, ProjectDto.class);
        assertSame(response.getStatusCode(), HttpStatus.CREATED);
    }
}
