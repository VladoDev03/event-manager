package com.example.event_manager.service;

import com.example.event_manager.dao.EventDao;
import com.example.event_manager.dao.MediaDao;
import com.example.event_manager.dto.CreateMediaDto;
import com.example.event_manager.entity.Event;
import com.example.event_manager.entity.Media;
import com.example.event_manager.exception.EntityNotFoundException;
import com.example.event_manager.exception.NotCreatorException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MediaService {
    private final CloudinaryService cloudinaryService;

    public MediaService(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    public void createMedia(MultipartFile[] files, long eventId, long userId) throws IOException, NotCreatorException {
        Event event = null;

        try {
            event = EventDao.getEventById(eventId);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (userId != event.getCreator().getId()) {
            throw new NotCreatorException(userId, eventId);
        }

        cloudinaryService
                .uploadMedia(files, eventId, userId)
                .stream()
                .forEach(MediaDao::createMedia);
    }

    public void deleteMedia(long mediaId) throws IOException {
        Media media = null;

        try {
            MediaDao.getMediaById(mediaId);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }

        MediaDao.deleteMedia(media);
        cloudinaryService.deleteMedia(media);
    }
}