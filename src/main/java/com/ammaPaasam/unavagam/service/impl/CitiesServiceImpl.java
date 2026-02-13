package com.ammaPaasam.unavagam.service.impl;

import com.ammaPaasam.unavagam.dto.CityListResponse;
import com.ammaPaasam.unavagam.dto.CityResponse;
import com.ammaPaasam.unavagam.entity.Cities;
import com.ammaPaasam.unavagam.repository.CityRepository;
import com.ammaPaasam.unavagam.service.CitiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CitiesServiceImpl implements CitiesService {

    private final CityRepository cityRepository;
    @Override
    public ResponseEntity<CityListResponse> getCityByStateId(UUID stateId) {

        List<Cities> citiesList = cityRepository.findAllByStateId(stateId);
        CityListResponse cityListResponse = new CityListResponse();
        List<CityResponse> cityResponseList = new ArrayList<>();
        for(Cities city: citiesList){
            CityResponse cityResponse = new CityResponse();
            cityResponse.setCityName(city.getCityName());
            cityResponse.setId(city.getId());
            cityResponseList.add(cityResponse);
        }
        cityListResponse.setCityResponseList(cityResponseList);
        return ResponseEntity.ok(cityListResponse);
    }
}
