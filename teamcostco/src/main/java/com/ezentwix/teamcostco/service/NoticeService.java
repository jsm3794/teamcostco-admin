package com.ezentwix.teamcostco.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.notice.NoticeDTO;
import com.ezentwix.teamcostco.pagination.PaginationRepository;
import com.ezentwix.teamcostco.pagination.PaginationResult;
import com.ezentwix.teamcostco.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeService implements PageMetadataProvider {
    private final PaginationRepository paginationRepository;

    private final NoticeRepository noticeRepository;

    public PaginationResult<NoticeDTO> getPage(String query, int page, int size, Map<String, Object> params) {
        return paginationRepository.getPage("Notice.getAll", PageRequest.of(page, size), params, NoticeDTO.class);
    }

    public List<NoticeDTO> getAll() {
        return noticeRepository.getAll();
    }

    @Override
    public List<String> getCssFiles() {
        return List.of("/css/contents/notice.css");
    }

    @Override
    public List<String> getJsFiles() {
        return List.of("/js/contents/notice.js");
    }

    @Override
    public String getUri() {
        return "notice/notice";
    }

    @Override
    public String getPageTitle() {
        return "공지사항";
    }
}
