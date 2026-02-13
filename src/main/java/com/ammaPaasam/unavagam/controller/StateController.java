package com.ammaPaasam.unavagam.controller;

import com.ammaPaasam.unavagam.dto.StateListResponse;
import com.ammaPaasam.unavagam.dto.StateResponse;
import com.ammaPaasam.unavagam.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/states")
@RequiredArgsConstructor
public class StateController {

    private final StateService stateService;

    @GetMapping
    public ResponseEntity<StateListResponse> getStates(){
        return stateService.getStates();
    }



}
