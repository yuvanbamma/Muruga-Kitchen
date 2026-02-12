package com.ammaPaasam.unavagam.service.impl;

import com.ammaPaasam.unavagam.commonservice.CloudinaryService;
import com.ammaPaasam.unavagam.dto.FoodPostRequest;
import com.ammaPaasam.unavagam.dto.FoodPostResponse;
import com.ammaPaasam.unavagam.dto.PageResponse;
import com.ammaPaasam.unavagam.entity.FoodPost;
import com.ammaPaasam.unavagam.exception.ApiException;
import com.ammaPaasam.unavagam.repository.FoodPostRepository;
import com.ammaPaasam.unavagam.service.FoodPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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

    private final CloudinaryService cloudinaryService;

    @Override
    public FoodPostResponse createFoodPost(FoodPostRequest foodPostRequest, MultipartFile image) throws Exception {
        String imageUrl = saveImageToFolder(image);
        FoodPost foodPost = new FoodPost();
        foodPost.setName(foodPostRequest.getName());
        foodPost.setDescription(foodPostRequest.getDescription());
        foodPost.setQuantityRequired(foodPostRequest.getQuantityRequired());
        foodPost.setExpireTime(foodPostRequest.getExpireTime());
        foodPost.setCollectedQuantity(foodPostRequest.getCollectedQuantity());
        foodPost.setOrphaneId(foodPostRequest.getOrphaneId());
        foodPost.setUserId(foodPostRequest.getUserId());
        foodPost.setImageUrl(imageUrl);
        FoodPost savedEntity = foodPostRepository.save(foodPost);
        return mapToResponse(savedEntity);
    }

    @Override
    public PageResponse<FoodPostResponse> getAllFoodPost(int page, int size, UUID orphanageId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("updatedAt").descending());
        Page<FoodPost> paginatedResponseFromDb;
        if (orphanageId != null) {
            paginatedResponseFromDb = foodPostRepository.findByIsDeletedFalseAndOrphaneId(orphanageId, pageable);
        } else {
            paginatedResponseFromDb = foodPostRepository.findByIsDeletedFalse(pageable);

        }
        List<FoodPostResponse> content = new ArrayList<>();
        for (FoodPost food : paginatedResponseFromDb) {
            FoodPostResponse foodPost = new FoodPostResponse();
            foodPost.setName(food.getName());
            foodPost.setCollectedQuantity(food.getCollectedQuantity());
            foodPost.setQuantityRequired(food.getQuantityRequired());
            foodPost.setExpireTime(food.getExpireTime());
            foodPost.setRequirement(food.getRequirement());
            foodPost.setImageUrl(food.getImageUrl());
            foodPost.setOrphaneId(food.getOrphaneId());
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

    @Override
    public FoodPostResponse editFoodPost(FoodPostRequest foodPostRequest) throws Exception {

        if (foodPostRequest.getId() == null) {
            throw new ApiException("Id cannot be empty while updating user.", HttpStatus.BAD_GATEWAY);
        }
        Optional<FoodPost> foodPost = foodPostRepository.findById(foodPostRequest.getId());
        if (foodPost.isPresent()) {
            FoodPost foodPost1 = foodPost.get();
            if (foodPostRequest.getName() != null) {
                foodPost1.setName(foodPostRequest.getName());
            }
            if (foodPostRequest.getDescription() != null) {
                foodPost1.setDescription(foodPostRequest.getDescription());
            }
            if (foodPostRequest.getCollectedQuantity() != null) {
                foodPost1.setCollectedQuantity(foodPostRequest.getCollectedQuantity());
            }
            if (foodPostRequest.getExpireTime() != null) {
                foodPost1.setExpireTime(foodPostRequest.getExpireTime());
            }
            if (foodPostRequest.getRequirement() != null) {
                foodPost1.setRequirement(foodPostRequest.getRequirement());
            }

            FoodPost result = foodPostRepository.save(foodPost1);
            return mapFullResponse(result);

        } else {
            throw new ApiException("No food post found for this id.", HttpStatus.BAD_GATEWAY);
        }
    }

    @Override
    public void deleteFoodPost(UUID id) throws Exception {

        Optional<FoodPost> foodPost = foodPostRepository.findById(id);
        if (foodPost.isPresent()) {
            FoodPost foodPost1 = foodPost.get();
            String imageUrlKey = foodPost1.getImageUrl();
            foodPost1.setDeleted(true);
            foodPost1.setActive(false);
            foodPost1.setImageUrl(null);
            foodPostRepository.save(foodPost1);
            if (imageUrlKey != null) {
                cloudinaryService.deleteImage(imageUrlKey);
            }
        } else {
            throw new ApiException("No food post found for this id.", HttpStatus.BAD_GATEWAY);
        }
    }

    private FoodPostResponse mapToResponse(FoodPost foodPost) {
        FoodPostResponse foodPostResponse = new FoodPostResponse();
        foodPostResponse.setName(foodPost.getName());
        foodPostResponse.setQuantityRequired(foodPost.getQuantityRequired());
        foodPostResponse.setRequirement(foodPost.getRequirement());
        foodPostResponse.setOrphaneId(foodPost.getOrphaneId());
        foodPostResponse.setCollectedQuantity(foodPost.getCollectedQuantity());
        foodPostResponse.setExpireTime(foodPost.getExpireTime());
        foodPostResponse.setDescription(foodPost.getDescription());
        return foodPostResponse;
    }

    private FoodPostResponse mapFullResponse(FoodPost foodPost) {
        FoodPostResponse foodPostResponse = new FoodPostResponse();
        foodPostResponse.setName(foodPost.getName());
        foodPostResponse.setQuantityRequired(foodPost.getQuantityRequired());
        foodPostResponse.setRequirement(foodPost.getRequirement());
        foodPostResponse.setOrphaneId(foodPost.getOrphaneId());
        foodPostResponse.setCollectedQuantity(foodPost.getCollectedQuantity());
        foodPostResponse.setExpireTime(foodPost.getExpireTime());
        foodPostResponse.setDescription(foodPost.getDescription());
        foodPostResponse.setImageUrl(foodPost.getImageUrl());
        foodPostResponse.setUpdatedAt(foodPost.getUpdatedAt());
        foodPostResponse.setCreatedAt(foodPost.getCreatedAt());
        foodPostResponse.setDeleted(foodPost.isDeleted());
        foodPostResponse.setActive(foodPost.isActive());
        return foodPostResponse;
    }

    private String saveImageToFolder(MultipartFile image) throws Exception {

        if (image != null && !image.isEmpty()) {

            // try {
            // Files.createDirectories(Paths.get(upload_dir));
            // String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            // Path filePath = Paths.get(upload_dir, fileName);
            // Files.copy(image.getInputStream(), filePath,
            // StandardCopyOption.REPLACE_EXISTING);
            // return "/uploads/" + fileName;
            // } catch (Exception e) {
            //
            // throw new Exception("Failed to store image files");
            // }

            try {
                return cloudinaryService.uploadImage(image);
            } catch (Exception e) {
                throw new ApiException("Failed during image upload to cloudinary.", HttpStatus.BAD_GATEWAY);
            }
        }
        return null;

    }
}
