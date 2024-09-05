
// 모든 행 선택 함수
function selectAllRows() {
    var selectAllCheckbox = document.getElementById('selectAll');
    var rowCheckboxes = document.querySelectorAll('.row-checkbox');
    rowCheckboxes.forEach(checkbox => {
        checkbox.checked = selectAllCheckbox.checked;
    });
}


// 세부 페이지로 리다이렉션하는 함수
function redirectToDetailsPage(productId) {
    window.location.href = '/detail/' + productId; // Spring Boot에서 세부 페이지 URL 설정
}

initFilter('inventory');

// 3박스 관련 함수
function initializeInventory() {
    fetchProductSummary();
    // 기타 인벤토리 페이지 초기화 로직
}

function fetchProductSummary() {
    fetch('/productsummary')
        .then(response => response.json())
        .then(data => {
            document.getElementById("totalQtySum").textContent = data.totalProductsQty;
            document.getElementById("defectedCount").textContent = data.defectedProducts;
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