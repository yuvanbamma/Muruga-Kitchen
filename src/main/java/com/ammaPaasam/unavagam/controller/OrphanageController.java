package com.ammaPaasam.unavagam.controller;

import com.ammaPaasam.unavagam.dto.OrphanageRequest;
import com.ammaPaasam.unavagam.dto.OrphanageResponse;
import com.ammaPaasam.unavagam.service.OrphanageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orphanage")
@RequiredArgsConstructor
@Validated
public class OrphanageController {

    private final OrphanageService orphanageService;

    @PostMapping
    public ResponseEntity<OrphanageResponse> createOrphanage( @Valid @RequestBody OrphanageRequest orphanageRequest){
        return orphanageService.createOrphanage(orphanageRequest);
    }
}
