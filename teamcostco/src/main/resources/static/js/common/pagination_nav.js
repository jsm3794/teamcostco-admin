// 현재 페이지 경로를 가져옵니다.
const currentPath = window.location.pathname;
console.log(`현재 경로: ${currentPath}`);

$(document).ready(function() {
    // 현재 페이지와 총 페이지 수를 HTML 속성에서 가져옵니다.
    let currentPage = parseInt($('#page').data('page')); // 현재 페이지 (0부터 시작)
    const totalPages = parseInt($('#page').data('totalpages')); // 타임리프로부터 총 페이지 수 전달
    console.log(`현재페이지: ${currentPage}, 총 페이지: ${totalPages}`);

    // 이전 버튼 클릭 핸들러
    $('#prevBtn').click(function() {
        if (currentPage > 0) { // 현재 페이지가 첫 페이지가 아닐 경우에만 이동
            location.href = currentPath + '?page=' + (currentPage - 1);
        }
    });

    // 다음 버튼 클릭 핸들러
    $('#nextBtn').click(function() {
        if (currentPage < totalPages) { // 현재 페이지가 마지막 페이지가 아닐 경우에만 이동
            location.href = currentPath + '?page=' + (currentPage + 1);
        }
    });

    // 첫 페이지 버튼 클릭 핸들러
    $("#firstPage").click(function() {
        location.href = currentPath + '?page=1';
    });

    // 마지막 페이지 버튼 클릭 핸들러
    $("#lastPage").click(function() {
        location.href = currentPath + '?page=' + totalPages;
    });

    // 페이지 번호 클릭 핸들러
    $('.page-item').click(function() {
        const pageNum = $(this).data('pagenum'); // 클릭한 페이지 번호를 가져옵니다.
        location.href = currentPath + '?page=' + pageNum;
    });

    // 첫 페이지일 경우 이전 버튼 비활성화
    if (currentPage === 1) {
        $('#prevBtn').attr('disabled', true);
    }

    // 마지막 페이지일 경우 다음 버튼 비활성화
    if (currentPage === totalPages) {
        $('#nextBtn').attr('disabled', true);
    }
});
