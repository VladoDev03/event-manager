package com.example.event_manager.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.event_manager.EventManagerApplication;
import com.example.event_manager.dao.MediaDao;
import com.example.event_manager.dto.CreateMediaDto;
import com.example.event_manager.entity.Media;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;
    private final MediaService mediaService;

    public CloudinaryService(
            @Value("${cloudinary.cloud_name}") String cloudName,
            @Value("${cloudinary.api_key}") String apiKey,
            @Value("${cloudinary.api_secret}") String apiSecret, MediaService mediaService) {
        this.mediaService = mediaService;
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        ));
    }

    public String uploadMedia(MultipartFile file, long eventId) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename != null && originalFilename.contains(".")
                ? originalFilename.substring(originalFilename.lastIndexOf("."))
                : "";
        String uniqueFilename = UUID.randomUUID().toString() + extension;

        String contentType = file.getContentType();
        String resourceType = contentType != null && contentType.startsWith("video/") ? "video" : "image";
        String folderPath = "media/" + resourceType;

        Map<String, Object> uploadParams = ObjectUtils.asMap(
                "use_filename", false,
                "unique_filename", false,
                "overwrite", true,
                "folder", folderPath,
                "public_id", uniqueFilename,
                "resource_type", resourceType
        );

        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadParams);

        String publicId = (String) uploadResult.get("public_id");
        String url = (String) uploadResult.get("url");

        CreateMediaDto media = new CreateMediaDto(url, publicId, eventId);
        mediaService.createMedia(media);

        return url;
    }

    public String uploadMedia(MultipartFile[] files, long eventId) throws IOException {
        StringBuilder urls = new StringBuilder();
        for (MultipartFile file : files) {
            String url = uploadMedia(file, eventId);
            urls.append(url).append("\n");
        }
        return urls.toString();
    }

    public void deleteMedia(long mediaId) throws IOException {
        Media media = MediaDao.getMediaById(mediaId);
        MediaDao.deleteMedia(media);
        deleteImage(media.getPublicId());
    }

    public String getDownloadLink(String publicId) {
        return "https://res.cloudinary.com/" + cloudinary.config.cloudName + "/image/upload/fl_attachment/" + publicId;
    }

    private String deleteImage(String publicId) throws IOException {
        String resourceType = getResourceType(publicId);
        Map<String, String> options = new HashMap<>();
        options.put("resource_type", resourceType);

        Map result = cloudinary.uploader().destroy(publicId, options);
        if ("ok".equals(result.get("result"))) {
            return "Deleted " + resourceType + " with public_id: " + publicId;
        } else {
            return "Failed to delete " + resourceType + " with public_id: " + publicId;
        }
    }

    private String getResourceType(String publicId) throws IOException {
        if (publicId.contains("image/")) {
            return "image";
        } else if (publicId.contains("video/")) {
            return "video";
        } else {
            return "unknown";
        }
    }
}