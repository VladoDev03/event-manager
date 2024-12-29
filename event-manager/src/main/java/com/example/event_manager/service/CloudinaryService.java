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
    private final MediaDao mediaDao;

    public CloudinaryService(
            @Value("${cloudinary.cloud_name}") String cloudName,
            @Value("${cloudinary.api_key}") String apiKey,
            @Value("${cloudinary.api_secret}") String apiSecret,
            MediaDao mediaDao) {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        ));
        this.mediaDao = mediaDao;
    }

    public String uploadMedia(MultipartFile file) throws IOException {
        // Generate a unique filename using UUID
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename != null && originalFilename.contains(".")
                ? originalFilename.substring(originalFilename.lastIndexOf("."))
                : "";
        String uniqueFilename = UUID.randomUUID().toString() + extension;

        // Determine the resource type based on the file's MIME type
        String contentType = file.getContentType();
        String resourceType = contentType != null && contentType.startsWith("video/") ? "video" : "image";
        String folderPath = "media/" + resourceType;

        // Set upload parameters
        Map<String, Object> uploadParams = ObjectUtils.asMap(
                "use_filename", false,  // Allow unique naming
                "unique_filename", false,  // We are handling unique filenames manually
                "overwrite", true,  // Overwrite if a file with the same name exists
                "folder", folderPath,  // Folder based on media type
                "public_id", uniqueFilename,  // Specify the unique filename
                "resource_type", resourceType  // Specify the resource type
        );

        // Upload the file
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadParams);

        String publicId = (String) uploadResult.get("public_id");
        String url = (String) uploadResult.get("url");

        CreateMediaDto media = new CreateMediaDto(url, publicId);
        MediaDao.createMedia(media);

        // Return the uploaded file's URL
        return url;
    }


    public String uploadMedia(MultipartFile[] files) throws IOException {
        StringBuilder urls = new StringBuilder();
        for (MultipartFile file : files) {
            String url = uploadMedia(file);
            urls.append(url).append("\n");
        }
        return urls.toString();
    }

    // Function to create an image (upload it to Cloudinary)
    public String createImage(String resourceName) throws IOException {
        InputStream inputStream = EventManagerApplication.class.getClassLoader().getResourceAsStream(resourceName);

        if (inputStream == null) {
            throw new RuntimeException("File not found in resources: " + resourceName);
        }

        // Create a temporary file and write the resource content to it
        Path tempFile = Files.createTempFile("upload-", ".png");
        Files.copy(inputStream, tempFile, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

        // Parameters for the upload
        Map uploadParams = ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", false,
                "overwrite", true,
                "folder", "test"
        );

        // Upload the temporary file to Cloudinary
        Map uploadResult = cloudinary.uploader().upload(tempFile.toFile(), uploadParams);

        // Extract the public_id from the result
        String publicId = (String) uploadResult.get("public_id");

        // Clean up the temporary file
        Files.delete(tempFile);

        return publicId;
    }

    public void deleteMedia(long mediaId) throws IOException {
        Media media = MediaDao.getMediaById(mediaId);
        MediaDao.deleteMedia(media);
        deleteImage(media.getPublicId());
    }

    // Function to get the download link for the uploaded image
    public String getDownloadLink(String publicId) {
        return "https://res.cloudinary.com/" + cloudinary.config.cloudName + "/image/upload/fl_attachment/" + publicId;
    }

    public String getCloudName() {
        return cloudinary.config.cloudName;
    }

    // Function to delete an image from Cloudinary
    private String deleteImage(String publicId) throws IOException {
        String resourceType = getResourceType(publicId);
        Map<String, String> options = new HashMap<>();
        options.put("resource_type", resourceType);

        System.out.println("==========");
        System.out.println(resourceType);
        System.out.println("==========");

        Map result = cloudinary.uploader().destroy(publicId, options);
        if ("ok".equals(result.get("result"))) {
            return "Deleted " + resourceType + " with public_id: " + publicId;
        } else {
            return "Failed to delete " + resourceType + " with public_id: " + publicId;
        }

//        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
//        return "Deleted image with public_id: " + publicId;
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
