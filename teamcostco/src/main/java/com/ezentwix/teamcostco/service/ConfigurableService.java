package com.ezentwix.teamcostco.service;

import java.util.List;

import org.springframework.ui.Model;

public interface ConfigurableService {
    String getUri();
    String getPageTitle();
    
    default List<String> getCssFiles() {
        return null;
    }

    default List<String> getJsFiles() {
        return null;
    }

    default void configureModel(Model model) {
        model.addAttribute("uri", getUri());
        model.addAttribute("pageTitle", getPageTitle());

        // CSS 파일 목록 설정
        model.addAttribute("cssFiles", getCssFiles());

        // JS 파일 목록 설정
        model.addAttribute("jsFiles", getJsFiles());
    }
}
