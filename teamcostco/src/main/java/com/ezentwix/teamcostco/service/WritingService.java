package com.ezentwix.teamcostco.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.notice.WritingDTO;
import com.ezentwix.teamcostco.repository.WritingRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WritingService implements PageMetadataProvider {
    
    private final WritingRepository writingRepository;
    private final PubSubService pubSubService; // PubSubService 추가

    public WritingDTO add(WritingDTO writingDTO) {
        WritingDTO addedWritingDTO = writingRepository.add(writingDTO);
        try {
            // Pub/Sub에 메시지 발행
            pubSubService.publishMessage("새로운 공지가 등록되었습니다! 제목: " + writingDTO.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
            // 예외 처리 (로그 작성 등)
        }
        return addedWritingDTO;
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
