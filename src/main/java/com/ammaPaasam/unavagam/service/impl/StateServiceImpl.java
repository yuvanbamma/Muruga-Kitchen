package com.ammaPaasam.unavagam.service.impl;

import com.ammaPaasam.unavagam.dto.StateListResponse;
import com.ammaPaasam.unavagam.dto.StateResponse;
import com.ammaPaasam.unavagam.entity.States;
import com.ammaPaasam.unavagam.repository.StateRepository;
import com.ammaPaasam.unavagam.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;


    @Override
    public ResponseEntity<StateListResponse> getStates() {
        List<States> states = stateRepository.findAll();
        StateListResponse stateListResponse = new StateListResponse();
        List<StateResponse> stateResponses = new ArrayList<>();
        for(States statesIte: states){
            StateResponse stateResponse = new StateResponse();
            stateResponse.setId(statesIte.getId());
            stateResponse.setName(statesIte.getStateName());
            stateResponses.add(stateResponse);
        }
        stateListResponse.setStateResponseList(stateResponses);
        return ResponseEntity.ok(stateListResponse);
    }
}
