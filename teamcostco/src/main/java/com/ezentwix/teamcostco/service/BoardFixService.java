package com.ezentwix.teamcostco.service;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.notice.BoardFixDTO;
import com.ezentwix.teamcostco.repository.BoardFixRepository;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardFixService implements PageMetadataProvider {

    private final BoardFixRepository boardFixRepository;

    public BoardFixDTO get(Integer notice_id) {
        return boardFixRepository.get(notice_id);
    }

    public void fix(BoardFixDTO boardFixDTO) {
        boardFixRepository.fix(boardFixDTO);
    }

    public void delete(Integer notice_id) {
        boardFixRepository.delete(notice_id);
    }

    @Override
    public List<String> getCssFiles() {
        return List.of("/css/contents/boardfix.css");
    }

    @Override
    public String getUri() {
        return "notice/fix";
    }

    @Override
    public String getPageTitle() {
        return "게시글 수정";
    }
}
