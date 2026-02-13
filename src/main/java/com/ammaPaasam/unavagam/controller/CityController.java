package com.ammaPaasam.unavagam.controller;

import com.ammaPaasam.unavagam.dto.CityListResponse;
import com.ammaPaasam.unavagam.dto.CityResponse;
import com.ammaPaasam.unavagam.service.CitiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/cities")
@RequiredArgsConstructor
public class CityController {

    private final CitiesService citiesService;

    @GetMapping
    public ResponseEntity<CityListResponse> getCityListByStateId(@RequestParam UUID StateId){
      return citiesService.getCityByStateId(StateId);
    }
}
