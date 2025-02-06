package com.example.event_manager.service;

import com.example.event_manager.dao.EventDao;
import com.example.event_manager.dao.UserDao;
import com.example.event_manager.dto.CreateEventDto;
import com.example.event_manager.dto.DisplayEventDto;
import com.example.event_manager.entity.Event;
import com.example.event_manager.entity.User;
import com.example.event_manager.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WishlistService {
    public void addEventToWishlist(long eventId, long userId) throws EntityNotFoundException {
        Event event = EventDao.getEventById(eventId);
        User user = UserDao.getUserById(userId);

        user.getWishlist().add(event);

        UserDao.updateUser(user);
    }

    public boolean removeEventFromWishlist(long eventId, long userId) {
        try {
            User user = UserDao.getUserById(userId);

            boolean removed = user.getWishlist().removeIf(e -> e.getId() == eventId);

            if (removed) {
                UserDao.updateUser(user);
            }

            return removed;
        } catch (Exception e) {
            return false;
        }
    }

    public List<DisplayEventDto> getWishlist(long userId) {
        return EventDao.getUserWishlist(userId);
    }

    public boolean isEventInWishlist(long eventId, long userId) throws EntityNotFoundException {
        User user = UserDao.getUserById(userId);
        return user.getWishlist()
                .stream()
                .anyMatch(event -> event.getId() == eventId);
    }

}
