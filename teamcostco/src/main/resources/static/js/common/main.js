const table = document.querySelector('#results') || null;
const thead = table?.querySelector('thead') || null;
const tbody = table?.querySelector('tbody') || null;
const thElements = thead?.querySelectorAll('th') || null;
const tdElements = tbody?.querySelectorAll('tr:first-child td') || null;


// 웹소켓 연결 설정
const socket = new WebSocket("ws://192.168.0.2:9999/notifications");

// 웹소켓 연결 상태 확인
socket.onopen = function () {
    console.log("WebSocket connection established.");
};

socket.onerror = function (error) {
    console.error("WebSocket Error: ", error);
};

socket.onclose = function (event) {
    console.log("WebSocket connection closed:", event);
};

// 웹소켓 메시지 수신 처리
socket.onmessage = function (event) {
    console.log("Received WebSocket message:", event.data); // 수신한 메시지 로그
    showNotificationPopup(event.data);
};

function showNotificationPopup(message) {
    const popup = document.createElement("div");
    popup.classList.add("notification-popup");
    popup.textContent = message;
    $('.container')[0].appendChild(popup);
    setTimeout(() => {
        popup.remove();
    }, 3000); // 3초 후 팝업 사라짐
}

function applyWidths() {
    if (thElements.length - 1 !== tdElements.length) {
        return;
    }

    thElements.forEach((th, index) => {
        if (index == thElements.length - 1) {
            return;
        }
        const width = th.style.width || th.getAttribute('width');
        if (width) {
            Array.from(tbody.rows).forEach(row => {
                row.cells[index].style.width = width;
            });
        }
    });

    tbody.classList.add('show');
}

if (table) {
    // MutationObserver 설정
    const observer = new MutationObserver((mutations) => {
        mutations.forEach((mutation) => {
            if (mutation.type === 'childList' || mutation.type === 'attributes') {
                applyWidths();
            }
        });
    });

    // tbody의 변화 관찰 시작
    observer.observe(tbody, {
        childList: true, // 자식 요소 추가/제거 감지
        subtree: true, // 모든 하위 요소 변경 감지
        attributes: true, // 속성 변경 감지
        attributeFilter: ['style', 'width'] // style과 width 속성 변경만 감지
    });

    // 테이블 헤더의 변화도 관찰
    observer.observe(thead, {
        subtree: true,
        attributes: true,
        attributeFilter: ['style', 'width']
    });


    applyWidths();
}