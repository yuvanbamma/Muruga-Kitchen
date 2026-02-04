package com.ammaPaasam.unavagam.controller;

import com.ammaPaasam.unavagam.dto.FoodPostRequest;
import com.ammaPaasam.unavagam.dto.FoodPostResponse;
import com.ammaPaasam.unavagam.dto.PageResponse;
import com.ammaPaasam.unavagam.service.FoodPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("api/food-posts")
@RequiredArgsConstructor
@Validated
public class FoodPostController {

    private final FoodPostService foodPostService;

    @PostMapping
    public FoodPostResponse createFoodPost(@RequestPart("data") @Valid FoodPostRequest foodPostRequest,
                                           @RequestPart(value = "file", required = false) MultipartFile image) throws Exception {
        return foodPostService.createFoodPost(foodPostRequest,image);
    }

    @GetMapping
    public PageResponse<FoodPostResponse> getAllFoodResponse(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size){
        return foodPostService.getAllFoodPost(page,size);

    }

    @GetMapping("/{id}")
    public FoodPostResponse getFoodpostById(@PathVariable UUID id){
        return foodPostService.getFoodPostById(id);
    }

    @PutMapping("/{id}")
    public FoodPostResponse editFoodPost(@RequestBody FoodPostRequest foodPostRequest) throws Exception {
        return foodPostService.editFoodPost(foodPostRequest);
    }

    @DeleteMapping
    public void deleteFoodPost(UUID id) throws Exception {
         foodPostService.deleteFoodPost(id);
    }
}
