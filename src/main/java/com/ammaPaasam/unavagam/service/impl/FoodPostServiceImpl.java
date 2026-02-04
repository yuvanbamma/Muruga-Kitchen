package com.ammaPaasam.unavagam.service.impl;

import com.ammaPaasam.unavagam.dto.FoodPostRequest;
import com.ammaPaasam.unavagam.dto.FoodPostResponse;
import com.ammaPaasam.unavagam.dto.PageResponse;
import com.ammaPaasam.unavagam.entity.FoodPost;
import com.ammaPaasam.unavagam.repository.FoodPostRepository;
import com.ammaPaasam.unavagam.service.FoodPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FoodPostServiceImpl implements FoodPostService {

    private final FoodPostRepository foodPostRepository;

    private static final String upload_dir = "uploads";

    @Override
    public FoodPostResponse createFoodPost(FoodPostRequest foodPostRequest, MultipartFile image) throws Exception {
        String imageUrl = saveImageToFolder(image);
        FoodPost foodPost = new FoodPost();
        foodPost.setName(foodPostRequest.getName());
        foodPost.setDescription(foodPostRequest.getDescription());
        foodPost.setQuantity(foodPostRequest.getQuantity());
        foodPost.setImageUrl(imageUrl);
        FoodPost savedEntity = foodPostRepository.save(foodPost);
        return mapToResponse(savedEntity);
    }

    @Override
    public PageResponse<FoodPostResponse> getAllFoodPost(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("updatedAt").descending());
        Page<FoodPost> paginatedResponseFromDb = foodPostRepository.findByIsDeletedFalse(pageable);
        List<FoodPostResponse> content = new ArrayList<>();
        for (FoodPost food : paginatedResponseFromDb) {
            FoodPostResponse foodPost = new FoodPostResponse();
            foodPost.setName(food.getName());
            foodPost.setQuantity(food.getQuantity());
            foodPost.setDescription(food.getDescription());
            foodPost.setImageUrl(food.getImageUrl());
            foodPost.setId(food.getId());
            content.add(foodPost);
        }

        PageResponse<FoodPostResponse> pageResponse = new PageResponse<>();
        pageResponse.setContent(content);
        pageResponse.setTotalPages(paginatedResponseFromDb.getTotalPages());
        pageResponse.setPage(paginatedResponseFromDb.getNumber());
        pageResponse.setSize(paginatedResponseFromDb.getSize());
        pageResponse.setTotalElements(paginatedResponseFromDb.getTotalElements());
        return pageResponse;
    }

    @Override
    public FoodPostResponse getFoodPostById(UUID id) {

        FoodPost foodPost = foodPostRepository
                .findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Food post not found"));

        return mapFullResponse(foodPost);
    }


    private FoodPostResponse mapToResponse(FoodPost foodPost) {
        FoodPostResponse foodPostResponse = new FoodPostResponse();
        foodPostResponse.setName(foodPost.getName());
        foodPostResponse.setQuantity(foodPost.getQuantity());
        foodPostResponse.setDescription(foodPost.getDescription());
        return foodPostResponse;
    }

    private FoodPostResponse mapFullResponse(FoodPost foodPost) {
        FoodPostResponse foodPostResponse = new FoodPostResponse();
        foodPostResponse.setName(foodPost.getName());
        foodPostResponse.setQuantity(foodPost.getQuantity());
        foodPostResponse.setDescription(foodPost.getDescription());
        foodPostResponse.setImageUrl(foodPost.getImageUrl());
        foodPostResponse.setUpdatedAt(foodPost.getUpdatedAt());
        foodPostResponse.setCreatedAt(foodPost.getCreatedAt());
        foodPostResponse.setDeleted(foodPost.isDeleted());
        foodPostResponse.setActive(foodPost.isActive());
        return foodPostResponse;
    }

    private String saveImageToFolder(MultipartFile image) throws Exception {

        if (image == null || image.isEmpty()) {
            throw new Exception("Image cannot be empty");
        }
        try {
            Files.createDirectories(Paths.get(upload_dir));
            String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Path filePath = Paths.get(upload_dir, fileName);
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return "/uploads/" + fileName;
        } catch (Exception e) {
            throw new Exception("Failed to store image files");
        }
    }
}
