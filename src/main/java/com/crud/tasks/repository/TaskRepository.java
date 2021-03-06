package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    List<Task> findAll();
    @Override
    Task save(Task task);
    @Override
    Optional<Task> findById(final Long id);
    @Override
    void deleteById(final Long id);
    @Override
    long count();
}
