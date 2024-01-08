package com.baeldung.ls.web.controller;

import com.baeldung.ls.mapper.ProjectMapper;
import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.service.IProjectService;
import com.baeldung.ls.web.dto.ProjectDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProjectController.class)
@ComponentScan(basePackages = "com.baeldung")
class MockMvcAltTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private IProjectService service;

    @Test
    void whenProjectExists_thenGetSuccess() throws Exception {
        Project project = new Project();
        project.setId(3L);
        project.setName("Project 3");
        project.setDateCreated(LocalDate.of(2019, 6, 15));
        Optional<Project> optionalProject = Optional.of(project);

        when(service.findById(3L)).thenReturn(optionalProject);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/projects/3")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Project 3"));
    }

    @Test
    void whenValidInputProject_thenReturns201() throws Exception {
        ProjectDto project = new ProjectDto(17L, "Project 17", LocalDate.of(2023, 1, 11));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isCreated());
    }

    @Test
    void whenInvalidInputProject_thenReturns400() throws Exception {
        ProjectDto project = new ProjectDto(17L, null, LocalDate.of(2023, 1, 11));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenValidInput_thenMapsToBusinessModel() throws Exception {
        ProjectDto project = new ProjectDto(17L, "Project 17", LocalDate.of(2023, 1, 11));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isCreated());

        ArgumentCaptor<Project> projectCaptor = ArgumentCaptor.forClass(Project.class);
        verify(service).save(projectCaptor.capture());
        assertThat(projectCaptor.getValue().getName()).isEqualTo("Project 17");
        assertThat(projectCaptor.getValue().getDateCreated()).isEqualTo(LocalDate.of(2023, 1, 11));
    }

    @Test
    void whenValidInput_thenReturnsUserResource() throws Exception {
        ProjectDto project = new ProjectDto(17L, "Project 17", LocalDate.of(2023, 1, 11));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(project)))
                .andReturn();

        String expectedResponseBody = objectMapper.writeValueAsString(project);
        String actualResponseBody = mvcResult.getRequest().getContentAsString();
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }
}
