package com.ammaPaasam.unavagam.commonservice;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public String uploadImage(MultipartFile file) throws IOException {

        Map uploadResult = cloudinary.uploader()
                .upload(file.getBytes(),
                        Map.of("folder", "food-post", "resource_type", "image"));
        return uploadResult.get("secure_url").toString();
    }

    public void deleteImage(String imageUrl) throws IOException {

        String publicId = extractPublicId(imageUrl);
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());

    }

    private String extractPublicId(String imageUrl) {

        String[] parts = imageUrl.split("/upload/");
        String path = parts[1];
        path = path.substring(path.indexOf("/")+1);
        return path.substring(0,path.lastIndexOf("."));

    }
}
