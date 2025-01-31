package com.example.event_manager.service;

import com.example.event_manager.dao.MediaDao;
import com.example.event_manager.dto.CreateMediaDto;
import com.example.event_manager.entity.Media;
import org.springframework.stereotype.Service;

@Service
public class MediaService {
    public void createMedia(CreateMediaDto media) {
        MediaDao.createMedia(media);
    }
}