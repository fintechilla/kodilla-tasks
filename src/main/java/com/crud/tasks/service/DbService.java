package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class DbService {
    @Autowired
    private TaskRepository repository;
//    @Autowired
//    TaskMapper mapper;

    public List<Task> getAllTasksDb() {
        return repository.findAll();
    }
    public Task getTaskByIdDb(final Long id) {
        return repository.findById(id).orElse(null);
    }
    public Task saveTask(final Task task) {
        return repository.save(task);
    }
    public Optional<Task> getTask(final Long id){
        return repository.findById(id);
    }
    public void deleteTask(final Long id){repository.deleteById(id);}
    //public void deleteTaskByObject(final Task task){repository.deleteTaskByTitleAndContent(task);}
}
