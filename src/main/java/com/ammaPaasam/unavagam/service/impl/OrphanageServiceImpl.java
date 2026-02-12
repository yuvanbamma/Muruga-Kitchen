package com.ammaPaasam.unavagam.service.impl;

import com.ammaPaasam.unavagam.dto.OrphanageRequest;
import com.ammaPaasam.unavagam.dto.OrphanageResponse;
import com.ammaPaasam.unavagam.dto.UserSignUpRequest;
import com.ammaPaasam.unavagam.entity.Orphanage;
import com.ammaPaasam.unavagam.exception.ApiException;
import com.ammaPaasam.unavagam.repository.OrphanageRepository;
import com.ammaPaasam.unavagam.service.OrphanageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.util.BeanUtil;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrphanageServiceImpl implements OrphanageService {

    private final OrphanageRepository orphanageRepository;

    @Override
    public ResponseEntity<OrphanageResponse> createOrphanage(UserSignUpRequest userSignUpRequest) {
          UUID orphanageId = userSignUpRequest.getId();
        Orphanage orphanage;
        if(orphanageId !=null){
             orphanage = orphanageRepository.findById(orphanageId).orElseThrow(()
                     -> new ApiException("No orphanage found for this id.", HttpStatus.NOT_FOUND));
        }else {
           orphanage = new Orphanage();
        }
        this.mapOrphanage(orphanage,userSignUpRequest);
        orphanageRepository.save(orphanage);
        OrphanageResponse orphanageResponse = new OrphanageResponse();
        orphanageResponse.setId(orphanage.getId());
        orphanageResponse.setMessage("Orphanage successfully created");
        return ResponseEntity.ok(orphanageResponse);
    }

    @Override
    public ResponseEntity<OrphanageResponse> list(UUID orphanageId) {

        Orphanage orphanage = orphanageRepository.findById(orphanageId).orElseThrow(() -> new ApiException("No orphanage found for this id.",HttpStatus.NOT_FOUND));
        OrphanageResponse response = new OrphanageResponse();
        BeanUtils.copyProperties(orphanage,response);
        return ResponseEntity.ok(response);
    }

    private void mapOrphanage(Orphanage orphanage,UserSignUpRequest orphanageRequest) {
        orphanage.setBio(orphanageRequest.getBio());
        orphanage.setEmail(orphanageRequest.getEmail());
        orphanage.setLandmark(orphanageRequest.getLandmark());
        orphanage.setLatitude(orphanageRequest.getLatitude());
        orphanage.setLongitude(orphanageRequest.getLongitude());
        orphanage.setVerified(orphanageRequest.isVerified());
        orphanage.setFullAddress(orphanageRequest.getFullAddress());
        orphanage.setOfficialName(orphanageRequest.getOfficialName());
        orphanage.setVisitPolicy(orphanageRequest.getVisitPolicy());
        orphanage.setTotalChilders(orphanageRequest.getTotalChilders());
        orphanage.setWebsiteUrl(orphanageRequest.getWebsiteUrl());
        orphanage.setRegisteredNumber(orphanageRequest.getRegisteredNumber());
        orphanage.setContactPersonContact(orphanageRequest.getContactPersonContact());
        orphanage.setUserIdentity(orphanageRequest.getUserId());

    }
}
