const table = document.querySelector('#results') || null;
const thead = table?.querySelector('thead') || null;
const tbody = table?.querySelector('tbody') || null;
const thElements = thead?.querySelectorAll('th') || null;
const tdElements = tbody?.querySelectorAll('tr:first-child td') || null;

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

document.addEventListener("DOMContentLoaded", (e) => {
    if (tinymce) {
        tinymce.init({
            selector: 'textarea',
            plugins: [
                // Core editing features
                'anchor', 'autolink', 'charmap', 'codesample', 'emoticons', 'image', 'link', 'lists', 'media', 'searchreplace', 'table', 'visualblocks', 'wordcount',
                // Your account includes a free trial of TinyMCE premium features
                // Try the most popular premium features until Sep 13, 2024:
                'checklist', 'mediaembed', 'casechange', 'export', 'formatpainter', 'pageembed', 'a11ychecker', 'tinymcespellchecker', 'permanentpen', 'powerpaste', 'advtable', 'advcode', 'editimage', 'advtemplate', 'ai', 'mentions', 'tinycomments', 'tableofcontents', 'footnotes', 'mergetags', 'autocorrect', 'typography', 'inlinecss', 'markdown',
            ],
            toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | addcomment showcomments | spellcheckdialog a11ycheck typography | align lineheight | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
            tinycomments_mode: 'embedded',
            tinycomments_author: 'Author name',
            skin: getTheme(),
            mergetags_list: [
                { value: 'First.Name', title: 'First Name' },
                { value: 'Email', title: 'Email' },
            ],
            ai_request: (request, respondWith) => respondWith.string(() => Promise.reject('See docs to implement AI Assistant')),
        });
    }
});