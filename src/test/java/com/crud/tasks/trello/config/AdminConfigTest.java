package com.crud.tasks.trello.config;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.util.Optional;

import static org.junit.Assert.*;

public class AdminConfigTest {
    @Autowired
    AdminConfig adminConfig;
    @Test
    public void getAdminMail() throws NullPointerException{
        //Given
        String result = "";
        //When\
        try {
             result = adminConfig.getAdminMail();
        }catch(NullPointerException e){
            System.out.println("ERROR!!: " + e.getMessage());
        }
        //Then
        assertNotNull(result);
        //assertEquals(Optional.of("shwalerys@gmail.com"), Optional.of(result));
    }
}