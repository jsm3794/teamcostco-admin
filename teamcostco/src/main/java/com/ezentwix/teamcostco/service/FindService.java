package com.ezentwix.teamcostco.service;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.dto.find.FindDTO;
import com.ezentwix.teamcostco.repository.FindRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindService {

    private final FindRepository findRepository;
    
    public boolean find_idCount(FindDTO findDTO) {
        
        return findRepository.find_idCount(findDTO);
    }

    public boolean find_pwCount(FindDTO findDTO) {

        return findRepository.find_pwCount(findDTO);
    }

    public String find_id(FindDTO findDTO) {
        
        return findRepository.find_id(findDTO);
    }

    public String find_pw(FindDTO findDTO) {
        
        return findRepository.find_pw(findDTO);
    }
}
