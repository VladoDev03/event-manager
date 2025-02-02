package com.example.event_manager.dao;

import com.example.event_manager.entity.Review;
import com.example.event_manager.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReviewDaoTest {
    private Review review;

    @Test
    public void givenReviewId_whenSearch_thenThrowsException() {
        assertThrows(EntityNotFoundException.class, () -> {
            ReviewDao.getReviewById(1);
        });
    }
}