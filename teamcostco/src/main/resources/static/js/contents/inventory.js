// 검색 입력창에서 실시간 검색 기능 추가
document.getElementById('searchInput').addEventListener('input', function () {
    var searchTerm = this.value.toLowerCase();
    var rows = document.querySelectorAll('.table tbody tr');
    rows.forEach(row => {
        var text = row.innerText.toLowerCase();
        row.style.display = text.includes(searchTerm) ? '' : 'none';
    });
});
// 날짜 필터 적용 함수
function applyDateFilter() {
    // 내용 작성하기
}
// 필터 적용 함수
function applyFilter() {
    // 내용 작성하기
}
// 드롭다운 토글 함수
function toggleDropdown(id) {
    var dropdowns = document.querySelectorAll('.dropdown-content');
    dropdowns.forEach(dropdown => {
        if (dropdown.id !== id) {
            dropdown.style.display = 'none'; // 현재 열려있는 다른 드롭다운을 닫음
        }
    });
    var dropdown = document.getElementById(id);
    dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block'; // 클릭한 드롭다운을 토글
}
// 클릭 외부에서 드롭다운 숨기기
window.onclick = function (event) {
    // 드롭다운 버튼이나 드롭다운 내용이 아닌 곳을 클릭했을 때만 드롭다운을 숨김
    if (!event.target.matches('.dropdown-btn') && !event.target.closest('.dropdown-content')) {
        var dropdowns = document.querySelectorAll('.dropdown-content');
        dropdowns.forEach(dropdown => {
            dropdown.style.display = 'none';
        });
    }
};
// 모든 행 선택 함수
function selectAllRows() {
    var selectAllCheckbox = document.getElementById('selectAll');
    var rowCheckboxes = document.querySelectorAll('.row-checkbox');
    rowCheckboxes.forEach(checkbox => {
        checkbox.checked = selectAllCheckbox.checked;
    });
}
// document.addEventListener("DOMContentLoaded", function() {
//     var rows = document.querySelectorAll('.table tbody tr');
//     var totalQtySum = 0;
//     var lowStockCount = 0;
//     var headerCells = document.querySelectorAll('.table thead th');
//     var columnCount = headerCells.length + 1;
//     rows.forEach(function(row) {
//         var totalQtyCell = row.querySelector('td:nth-child(8)'); // **** nth-child
//         if (totalQtyCell) {
//             var totalQty = parseInt(totalQtyCell.innerText.trim());
//             // total_qty 합계 계산
//             if (!isNaN(totalQty)) {
//                 totalQtySum += totalQty;
//                 if (totalQty <= 50) {
//                     lowStockCount++;
//                 }
//             }
//         }
//     });
//     // 계산된 값을 페이지에 표시
//     document.getElementById('categoryCount').innerText = columnCount; // 열의 개수를 표시
//     document.getElementById('totalQtySum').innerText = totalQtySum; // total_qty의 합계를 표시
//     document.getElementById('lowStockCount').innerText = lowStockCount; // 50개 이하 품목의 개수를 표시
// });
const categoryCount = $("#categoryCount");
const totalQtySum = $("#totalQtySum");
const lowStockCount = $("#lowStockCount");
const fetchProductSummary = () => {
    $.ajax({
        url: '/productsummary',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            categoryCount.text(data.totalCategories);
            totalQtySum.text(data.totalProductsQty);
            lowStockCount.text(data.lowProducts);
        },
        error: function (error) {
            console.error('Error fetching product summary:', error);
        }
    });
};
// 개수 표시 div 클릭시 상세창
$(".info-box").click((e) => {
    console.log("상세보기창 만들수도");
});
// 개수 최초 데이터 로드
fetchProductSummary();
// 10분마다 개수 데이터 갱신 (밀리초 단위)
setInterval(fetchProductSummary, 600000);
// pagination
$(document).ready(function() {
    // 현재 페이지와 총 페이지 수를 HTML 속성에서 가져옵니다.
    let currentPage = parseInt($('#page').data('page')); // 현재 페이지 (0부터 시작)
    const totalPages = parseInt($('#page').data('totalpages'));// 타임리프로부터 총 페이지 수 전달
    console.log(`현재페이지: ${currentPage}, 총 페이지: ${totalPages}`);
    // 이전 버튼 클릭 핸들러
    $('#prevBtn').click(function() {
        if (currentPage > 0) { // 현재 페이지가 첫 페이지가 아닐 경우에만 이동
            location.href = '/inventory?page=' + (currentPage - 1);
        }
    });
    // 다음 버튼 클릭 핸들러
    $('#nextBtn').click(function() {
        if (currentPage < totalPages - 1) { // 현재 페이지가 마지막 페이지가 아닐 경우에만 이동
            location.href = '/inventory?page=' + (currentPage + 1);
        }
    });
    // 첫 페이지일 경우 이전 버튼 비활성화
    if (currentPage === 0) {
        $('#prevBtn').attr('disabled', true);
    }
    // 마지막 페이지일 경우 다음 버튼 비활성화
    if (currentPage === totalPages - 1) {
        $('#nextBtn').attr('disabled', true);
    }
});

// 세부 페이지로 리다이렉션하는 함수
function redirectToDetailsPage(productId) {
    window.location.href = '/inventory/' + productId; // Spring Boot에서 세부 페이지 URL 설정
}
