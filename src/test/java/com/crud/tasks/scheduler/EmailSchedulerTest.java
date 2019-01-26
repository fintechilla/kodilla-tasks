package com.crud.tasks.scheduler;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.trello.config.AdminConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTest {
    @InjectMocks
    EmailScheduler emailScheduler;
    @Mock
    TaskRepository taskRepository;
    @Mock
    AdminConfig adminConfig;
    @Mock
    SimpleEmailService simpleEmailService;
    @Test
    public void sendInformationEmail() {
        emailScheduler.sendInformationEmail();
        //Mail mail = new Mail("mail@mail.com", "Tasks: Once a day email","Currently in database you got: 1 task");
        //When
//        when(taskRepository.count()).thenReturn(1L);
//        when(adminConfig.getAdminMail()).thenReturn("mail@mail.com");
        //Then
        verify(simpleEmailService, times(1)).send(any());//(mail);//(any());
    }
}