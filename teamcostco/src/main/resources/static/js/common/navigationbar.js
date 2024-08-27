document.addEventListener('DOMContentLoaded', () => {
    const navItems = document.querySelectorAll('.nav-item');
    const headerTitle = document.getElementById('header-title');
    const current_page = document.getElementById("current-page");

    navItems.forEach(item => {
        item.addEventListener('click', function(e) {
            e.preventDefault();

            let target = this.getAttribute('data-target');
            if (target.startsWith('#')) {
                target = target.slice(1);
            }

            let newUrl = `/${target}`;

            // 현재 페이지와 새 페이지가 동일한 경우 리다이렉트 발생
            if (window.location.pathname === newUrl) {
                location.href = newUrl;
                return;
            }

            // 네비게이션 업데이트
            navItems.forEach(navItem => navItem.classList.remove('active'));
            this.classList.add('active');

            // headerTitle이 존재하는 경우에만 textContent를 설정
            if (headerTitle) {
                headerTitle.textContent = target.charAt(0).toUpperCase() + target.slice(1);
            }

            // 현재 페이지 텍스트 업데이트
            if (current_page) {
                current_page.innerText = target;
            }

            // 페이지 로딩
            if (window.location.pathname.includes('/detail') || window.location.pathname !== '/') {
                window.history.pushState({}, '', newUrl);
                loadContent(newUrl);
            } else {
                location.href = newUrl;
            }


        });
    });
});


function loadContent(url) {
    fetch(url)
        .then(response => response.text())
        .then(html => {
            const parser = new DOMParser();
            const doc = parser.parseFromString(html, 'text/html');
            const mainContent = doc.querySelector('main');
            if (mainContent) {
                document.querySelector('main').innerHTML = mainContent.innerHTML;
            }

            // CSS 파일 업데이트
            updateCSSFiles(doc);

            

        })
        .catch(error => console.error('Error:', error));
}

function updateCSSFiles(newDocument) {
    const existingDynamicLinks = document.querySelectorAll('link[data-dynamic="true"]');
    existingDynamicLinks.forEach(link => link.remove());

    const newCSSLinks = newDocument.querySelectorAll('link[rel="stylesheet"]:not([href^="/css/common/"])');
    newCSSLinks.forEach(link => {
        const newLink = document.createElement('link');
        newLink.rel = 'stylesheet';
        newLink.href = link.href;
        newLink.setAttribute('data-dynamic', 'true');
        document.head.appendChild(newLink);
    });
}

function initializeInfoBoxes() {

    const infoBoxes = document.querySelectorAll('.info-box');
    infoBoxes.forEach(box => {
        console.log('Info-box initialized:', box);


    });
}
