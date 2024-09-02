$(document).ready(function () {
    const currentPath = window.location.pathname;
    const queryParams = new URLSearchParams(window.location.search);
    
    // 현재 페이지와 총 페이지 수를 HTML 속성에서 가져옵니다.
    let currentPage = parseInt($('#page').data('page')); // 현재 페이지 (1부터 시작)
    const totalPages = parseInt($('#page').data('totalpages')); // 타임리프로부터 총 페이지 수 전달

    // 이전 버튼 클릭 핸들러
    $('#prevBtn').click(function (e) {
        e.preventDefault();
        if (currentPage > 1) { // 현재 페이지가 첫 페이지가 아닐 경우에만 이동
            queryParams.set('page', currentPage - 1);
            location.href = `${currentPath}?${queryParams.toString()}`;
        }
    });

    // 다음 버튼 클릭 핸들러
    $('#nextBtn').click(function (e) {
        e.preventDefault();
        if (currentPage < totalPages) { // 현재 페이지가 마지막 페이지가 아닐 경우에만 이동
            queryParams.set('page', currentPage + 1);
            location.href = `${currentPath}?${queryParams.toString()}`;
        }
    });

    // 첫 페이지 버튼 클릭 핸들러
    $("#firstPage").click(function (e) {
        e.preventDefault();
        queryParams.set('page', 1);
        location.href = `${currentPath}?${queryParams.toString()}`;
    });

    // 마지막 페이지 버튼 클릭 핸들러
    $("#lastPage").click(function (e) {
        e.preventDefault();
        queryParams.set('page', totalPages);
        location.href = `${currentPath}?${queryParams.toString()}`;
    });

    // 페이지 번호 클릭 핸들러
    $('.page-item').click(function (e) {
        e.preventDefault();
        const pageNum = $(this).data('pagenum'); // 클릭한 페이지 번호를 가져옵니다.
        queryParams.set('page', pageNum);
        location.href = `${currentPath}?${queryParams.toString()}`;
    });

    // 첫 페이지일 경우 이전 버튼 비활성화
    if (currentPage === 1) {
        $('#prevBtn').attr('disabled', true);
        $('#firstPage').attr('disabled', true);
    }

    // 마지막 페이지일 경우 다음 버튼 비활성화
    if (currentPage === totalPages) {
        $('#nextBtn').attr('disabled', true);
        $("#lastPage").attr('disabled', true);
    }
});
