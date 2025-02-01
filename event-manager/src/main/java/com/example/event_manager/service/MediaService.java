package com.example.event_manager.service;

import com.example.event_manager.dao.EventDao;
import com.example.event_manager.dao.MediaDao;
import com.example.event_manager.dto.CreateMediaDto;
import com.example.event_manager.entity.Media;
import com.example.event_manager.exception.EntityNotFoundException;
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

    public void createMedia(MultipartFile[] files, long eventId, long userId) throws IOException {
        try {
            EventDao.getEventById(eventId);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }

        cloudinaryService
                .uploadMedia(files, eventId, userId)
                .stream()
                .forEach(MediaDao::createMedia);
    }

    public void deleteMedia(long mediaId) throws IOException {
        Media media = MediaDao.getMediaById(mediaId);
        MediaDao.deleteMedia(media);
        cloudinaryService.deleteMedia(media);
    }
}