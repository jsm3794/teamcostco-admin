package com.ezentwix.teamcostco.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.notice.WritingDTO;
import com.ezentwix.teamcostco.repository.WritingRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WritingService implements PageMetadataProvider{
    
    private final WritingRepository writingRepository;

    public WritingDTO add(WritingDTO writingDTO) {
        return writingRepository.add(writingDTO);
    }

    @Override
    public List<String> getCssFiles() {
        return List.of("/css/contents/writing.css");
    }
    
    @Override
    public String getUri() {
        return "notice/writing";        
    }

    @Override
    public String getPageTitle() {
        return "글쓰기";
    }

    
}
