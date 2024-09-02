const table = document.querySelector('#results');
const thead = table.querySelector('thead');
const tbody = table.querySelector('tbody');
const thElements = thead.querySelectorAll('th');
const tdElements = tbody.querySelectorAll('tr:first-child td');

function applyWidths() {
    if (thElements.length - 1 !== tdElements.length) {
        return;
    }

    thElements.forEach((th, index) => {
        if(index == thElements.length - 1){
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

