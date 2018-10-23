package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }
    public TaskDto getTask(Long id) {
        return new TaskDto(1L, "testTitle", "testContent");
    }
    public void deleteTask(Long id) {
    }
    public TaskDto updateDto(TaskDto taskDto) {
        return new TaskDto(1L, "editedtestTitle", "editedTestContent");
    }
    public void createDto(TaskDto taskDto){

    }
}
