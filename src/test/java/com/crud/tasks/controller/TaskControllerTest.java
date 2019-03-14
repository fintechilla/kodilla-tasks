package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    TaskMapper taskMapper;
    @MockBean
    DbService dbService;
    @InjectMocks
    TaskController taskController;
    @Test
    public void getTasks() throws Exception {
        //Given
        Task task = new Task(1L, "test_task", "test_description");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        TaskDto taskDto = new TaskDto(1L, "test_task_dto", "test_description_dto");
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(taskDto);
        Mockito.when(dbService.getAllTasksDb()).thenReturn(taskList);
        Mockito.when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);
        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("test_task_dto")))
                .andExpect(jsonPath("$[0].content", is("test_description_dto")));
    }

    @Test
    public void getTask() throws Exception {
        Task task = new Task(1L, "test_task1", "test_description");
        TaskDto taskDto = new TaskDto(1L, "test_task_dto1", "test_description_dto1");

        Long testId = 1L;
        Mockito.when(dbService.getTask(testId)).thenReturn(java.util.Optional.ofNullable(task));
        Mockito.when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        mockMvc.perform(get("/v1/task/getTask").param("id","1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test_task_dto1")))
                .andExpect(jsonPath("$.content", is("test_description_dto1")));
    }

    @Test
    public void deleteTask() throws Exception {
        //Given
//        Long id = 1L;
//        doNothing().
//                doThrow(new RuntimeException())
//                .when(dbService).deleteTask(id);
//        //When & Then
////        mockMvc.perform(delete("/v1/task/deleteTask").param("taskId", "1").contentType(MediaType.APPLICATION_JSON))
////                .andExpect(status().is(200));
//        verify(dbService, times(1)).deleteTask(id);
    }

    @Test
    public void updateTask() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(1L, "test_task_dto1", "test_description_dto1");
        Task task = new Task(1L, "test_task1", "test_description1");

        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(dbService.saveTask(task)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
            .andExpect(status().is(200))
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.title", is("test_task_dto1")))
            .andExpect(jsonPath("$.content", is("test_description_dto1")))
        ;
    }

    @Test
    public void createTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test_task_dto1", "test_description_dto1");
        Task task = new Task(1L, "test_task1", "test_description1");
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().is(200));
    }
}