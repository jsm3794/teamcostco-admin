package com.ezentwix.teamcostco.pagination;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class PagingModule<T> {

    
    protected abstract PagingRepositoryInterface<T> getPagingRepository();

    private Page<T> getPagedItems(int page, int size) {
        // 페이지 번호는 0부터 시작하므로, 실제 페이지 번호를 조정
        int start = (page - 1) * size + 1;
        int end = page * size;

        // 공통된 페이징 쿼리 실행 
        List<T> items = getPagingRepository().findTableItems(start, end);

        // 총 개수 조회
        int totalItems = getPagingRepository().countTableItems();

        Pageable pageable = PageRequest.of(page - 1, size); // 페이지 번호를 0 기반으로 조정
        // PageImpl 객체를 사용하여 결과를 래핑
        return new PageImpl<>(items, pageable, totalItems);
    }


    // page : 페이지 시작번호입니다
    // size: 한 페이지에 보이는 데이터 개수입니다
    public void configurePagingModel(Model model,
        int page, 
        int size) {

        // 페이지 처리된 아이템 목록을 가져옴
        Page<T> productPage = getPagedItems(page, size);

        // Model에 페이지 처리된 아이템 목록을 추가
        // 뷰 템플릿에서 반복문을 통해 리스트를 출력할 때 사용됩니다.
        model.addAttribute("items", productPage.getContent());

        // Model에 현재 페이지 번호를 추가 (0부터 시작합니다)
        // 현재 페이지를 표시하거나 페이지 네비게이션에서 활성화된 페이지를 보여줄 때 사용됩니다.
        model.addAttribute("currentPage", productPage.getNumber() + 1);

        // Model에 총 페이지 수를 추가
        // 페이지 네비게이션을 만들 때 총 페이지 수가 필요할 수 있습니다.
        model.addAttribute("totalPages", productPage.getTotalPages());

        // Model에 전체 아이템 수를 추가
        // 전체 아이템 수를 표시하거나 페이지 네비게이션 계산 시 사용될 수 있습니다.
        model.addAttribute("totalItems", productPage.getTotalElements());

        // 테스트용
        log.info("items: {}", productPage.getContent());
        log.info("보이는 데이터 번호 시작{} ~ 끝{}", (page - 1) * size + 1, size * page);
        log.info("총 페이지 수 : {}", productPage.getTotalPages());
        log.info("현재 페이지 : {}", productPage.getNumber() + 1);
        log.info("총 아이템 수 : {}", productPage.getTotalElements());
        }

    
}
