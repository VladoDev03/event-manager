package com.example.event_manager.dao;

import com.example.event_manager.entity.User;
import com.example.event_manager.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserDaoTest {
    @Test
    void givenValidUser_whenSave_thenGetOk() {
        User user = new User(
                "tanya_meh",
                "0000",
                "Tanya",
                "Mehandzhvieva",
                LocalDateTime.now());

        UserDao.createUser(user);
        User savedUser = null;
        try {
            savedUser = UserDao.getUserById(1);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(savedUser);
        assertEquals("tanya_meh", savedUser.getUsername());
        assertEquals("0000", savedUser.getPassword());
        assertEquals("Tanya", savedUser.getFirstName());
        assertEquals("Mehandzhvieva", savedUser.getLastName());

    }
}
