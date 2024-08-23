// 검색 입력창에서 실시간 검색 기능 추가
document.getElementById('searchInput').addEventListener('input', function() {
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

document.addEventListener("DOMContentLoaded", function() {
    var rows = document.querySelectorAll('.table tbody tr');
    var totalQtySum = 0;
    var lowStockCount = 0; 

    var headerCells = document.querySelectorAll('.table thead th');
    var columnCount = headerCells.length + 1;

    rows.forEach(function(row) {
        var totalQtyCell = row.querySelector('td:nth-child(8)'); // **** nth-child
        if (totalQtyCell) {
            var totalQty = parseInt(totalQtyCell.innerText.trim()); 
            // total_qty 합계 계산
            if (!isNaN(totalQty)) {
                totalQtySum += totalQty;
                if (totalQty <= 50) {
                    lowStockCount++;
                }
            }
        }
    });
    // 계산된 값을 페이지에 표시
    document.getElementById('categoryCount').innerText = columnCount; // 열의 개수를 표시
    document.getElementById('totalQtySum').innerText = totalQtySum; // total_qty의 합계를 표시
    document.getElementById('lowStockCount').innerText = lowStockCount; // 50개 이하 품목의 개수를 표시
});
/*
아직 구현 안됨
// 세부 페이지로 리다이렉션하는 함수
function redirectToDetailsPage(productId) {
    window.location.href = '/products/' + productId; // Spring Boot에서 세부 페이지 URL 설정
}
*/