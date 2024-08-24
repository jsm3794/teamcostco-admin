package com.ezentwix.teamcostco.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;

@Service
public class EmployeeService implements PageMetadataProvider{

    @Override
    public String getUri() {
        return "employee/employee";
    }

    @Override
    public String getPageTitle() {
        return "직원관리";
    }

    
    @Override
    public List<String> getCssFiles() {
        return null;
    }
    
    @Override
    public List<String> getJsFiles() {
        return null;
    }

}