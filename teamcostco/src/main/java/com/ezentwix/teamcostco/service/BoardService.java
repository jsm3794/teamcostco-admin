package com.ezentwix.teamcostco.service;

import org.springframework.stereotype.Repository;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.notice.BoardDTO;
import com.ezentwix.teamcostco.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardService implements PageMetadataProvider{
    
    private final BoardRepository boardRepository;

    public BoardDTO get(Integer notice_id) {
        return boardRepository.get(notice_id);
    }

    @Override
    public List<String> getCssFiles() {
        return List.of("/css/contents/board.css");
    }

    @Override
    public String getUri() {
        return "notice/board";
    }

    @Override
    public String getPageTitle() {
        return "게시글";        
    }
}
