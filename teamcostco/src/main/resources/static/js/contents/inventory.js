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

window.initializePage = function() {
    if (window.location.pathname === '/inventory') {
        initializeInventory();
    }
    // 다른 페이지에 대한 초기화 로직도 여기에 추가할 수 있습니다.
};

function initializeInventory() {
    fetchProductSummary();
    // 기타 인벤토리 페이지 초기화 로직
}

function fetchProductSummary() {
    fetch('/productsummary')
        .then(response => response.json())
        .then(data => {
            document.getElementById("categoryCount").textContent = data.totalCategories;
            document.getElementById("totalQtySum").textContent = data.totalProductsQty;
            document.getElementById("lowStockCount").textContent = data.lowProducts;
        })
        .catch(error => console.error('Error fetching product summary:', error));
}

// 10분마다 데이터 갱신
setInterval(fetchProductSummary, 600000);

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
        if (currentPage < totalPages) { // 현재 페이지가 마지막 페이지가 아닐 경우에만 이동
            location.href = '/inventory?page=' + (currentPage + 1);
        }
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

// 세부 페이지로 리다이렉션하는 함수
function redirectToDetailsPage(productId) {
    window.location.href = '/detail/' + productId; // Spring Boot에서 세부 페이지 URL 설정
}
