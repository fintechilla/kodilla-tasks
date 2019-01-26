package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class TaskMapperTest {
    TaskMapper taskMapper = new TaskMapper();
    @Test
    public void mapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "task_dto", "taskDto");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(Optional.of(1L), Optional.of(task.getId()));
        assertEquals("task_dto", task.getTitle());

    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task task = new Task(2L, "test_task", "task");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(Optional.of(2L), Optional.of(taskDto.getId()));
        assertEquals("test_task", taskDto.getTitle());

    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        Task task = new Task(2L, "test_task", "task");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
         //When
        List<TaskDto> taskDtoListActual = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(Optional.of(2L), Optional.of(taskDtoListActual.get(0).getId()));
        assertEquals("test_task", taskDtoListActual.get(0).getTitle());
    }
}