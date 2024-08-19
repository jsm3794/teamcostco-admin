package com.ezentwix.teamcostco.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.dto.TestDTO;
import com.ezentwix.teamcostco.repository.TestRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TestService {
    private final TestRepository testRepository;

    public List<TestDTO> getAll() {
        return testRepository.getAll();
    }
}
