package com.ammaPaasam.unavagam.service;

import com.ammaPaasam.unavagam.dto.FoodPostRequest;
import com.ammaPaasam.unavagam.dto.FoodPostResponse;
import com.ammaPaasam.unavagam.dto.PageResponse;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FoodPostService {
    FoodPostResponse createFoodPost(@Valid FoodPostRequest foodPostRequest, MultipartFile image) throws Exception;

    PageResponse<FoodPostResponse> getAllFoodPost(int page, int size);

    FoodPostResponse getFoodPostById(UUID id);

    FoodPostResponse editFoodPost(FoodPostRequest foodPostRequest) throws Exception;

    void deleteFoodPost(UUID id) throws Exception;
}
