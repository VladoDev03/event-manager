package com.example.event_manager.dao;

import com.example.event_manager.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MediaDaoTest {
    @Test
    public void givenMediaId_whenSearch_thenThrowsException() {
        assertThrows(EntityNotFoundException.class, () -> {
            MediaDao.getMediaById(1);
        });
    }
}