function setTableColumnWidths(table, widths) {
    if (!table || !widths || !Array.isArray(widths)) {
        throw new Error('Invalid arguments');
    }

    // Ensure that the table has a thead and tbody
    const thead = table.querySelector('thead');
    const tbody = table.querySelector('tbody');

    if (!thead || !tbody) {
        throw new Error('Table must have both thead and tbody');
    }

    // Apply widths to thead th elements
    const ths = thead.querySelectorAll('th');
    for (let i = 0; i < widths.length; i++) {
        ths[i].style.width = widths[i];
    }

    // Set padding for the last th element
    if (ths.length > 0) {
        const scrollbarWidth = thead.clientWidth - tbody.clientWidth;
        ths[ths.length - 1].style.setProperty('padding-right', `${scrollbarWidth}px`, 'important');
        ths[ths.length - 1].style.setProperty('width', '0px', 'important');
    }

    // Apply widths to tbody td elements
    const rows = tbody.querySelectorAll('tr');
    rows.forEach(row => {
        const tds = row.querySelectorAll('td');
        tds.forEach((td, index) => {
            if (widths[index]) {
                td.style.width = widths[index];
            }
        });
    });
}


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

