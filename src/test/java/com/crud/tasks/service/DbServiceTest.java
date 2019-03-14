package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
//@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {
    @Autowired
    DbService service;
    @Autowired
    TaskRepository repository;

    @Test
    public void getAllTasksDb(){
        //Given
//        Task task = new Task(1L, "task_db", "content_db");
//        List<Task> taskList = new ArrayList<>();
//        taskList.add(task);
//        when(repository.findAll()).thenReturn(taskList);
//        //When
//        List<Task> list = service.getAllTasksDb();
//        //Then
//        assertEquals(1L, Optional.of(list.get(0).getId()));
    }

    @Test
    public void getTaskByIdDb() {
        //Given
//        Task task = new Task(1L, "task_db", "content_db");
//        Long id = 1L;
//        //When
//        when(repository.findById(id)).thenReturn(Optional.ofNullable(task));
//        //Then
//        assertEquals(1L, Optional.ofNullable(task.getId()));

    }

    @Test
    public void saveTask() {
        //Given
//        Task task = new Task(1L, "task_db", "content_db");
//        //When
//        when(repository.save(task)).thenReturn(task);
//        //Then
//        verify(repository.save(task));
    }

    @Test
    public void getTask() {
//        Task task = new Task(1L, "task_db", "content_db");
//        Long id = new Long(1L);
//        //When
//        when(repository.findById(id)).thenReturn(Optional.ofNullable(task));
//        //Then
//        assertEquals(1L, Optional.ofNullable(task.getId()));
    }

    @Test
    public void deleteTask() {
        //Given
//        Long id = new Long(1L);
//        //When
//        doNothing().
//                doThrow(new RuntimeException())
//                .when(repository).deleteById(id);
//        //Then
//        verify(repository.deleteById(id));
    }
}