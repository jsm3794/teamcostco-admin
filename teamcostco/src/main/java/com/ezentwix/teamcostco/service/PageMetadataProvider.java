package com.ezentwix.teamcostco.service;

import java.util.List;

import org.springframework.ui.Model;

import jakarta.annotation.Nullable;

public interface PageMetadataProvider {
    String getUri();

    String getPageTitle();

    @Nullable
    default List<String> getCssFiles() {
        return null;
    }

    @Nullable
    default List<String> getJsFiles() {
        return null;
    }

    default void configureModel(Model model) {
        model.addAttribute("uri", getUri());
        model.addAttribute("pageTitle", getPageTitle());
        model.addAttribute("rootName", getRootName());

        // CSS 파일 목록 설정
        model.addAttribute("cssFiles", getCssFiles());

        // JS 파일 목록 설정
        model.addAttribute("jsFiles", getJsFiles());
    }

    default String getRootName() {
        String uri = getUri();
        if (uri == null || uri.trim().isEmpty()) {
            return ""; // URI가 null 또는 빈 문자열일 경우 빈 문자열 반환
        }

        String[] parts = uri.split("/");
        return parts.length > 0 ? parts[0] : ""; // 첫 번째 부분이 존재하면 반환, 그렇지 않으면 빈 문자열 반환
    }
}
