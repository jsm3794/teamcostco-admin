package com.ezentwix.teamcostco.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.notice.NoticeDTO;
import com.ezentwix.teamcostco.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class NoticeService implements PageMetadataProvider{
   
    private final NoticeRepository noticeRepository;

    public List<NoticeDTO> getAll() {
        return noticeRepository.getAll();
    }

    @Override
    public List<String> getCssFiles() {
        return List.of("/css/contents/notice.css");
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
