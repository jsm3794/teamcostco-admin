package com.ezentwix.teamcostco.service.dashboard;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.service.ConfigurableService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashBoardService implements ConfigurableService{

    @Override
    public String getUri() {
        return "dashboard/dashboard";
    }

    @Override
    public String getPageTitle() {
        return "대시보드";
    }

    // 아래 두 메서드(getCssFiles, getJsFiles)는
    // Override 메서드를 구현하지않으면 null을 반환하도록 설계되어있음

    @Override
    public List<String> getCssFiles() {
        return List.of("/css/dashboard/styles.css");
    }
    
    @Override
    public List<String> getJsFiles() {
        return null;
    }
}