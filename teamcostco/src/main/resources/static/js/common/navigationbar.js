document.addEventListener('DOMContentLoaded', () => {
    const navItems = document.querySelectorAll('.nav-item');
    const headerTitle = document.getElementById('header-title');
    const current_page = document.getElementById("current-page");

    function handleNavigation(e) {
        e.preventDefault();

        let target = this.getAttribute('data-target');
        if (target.startsWith('#')) {
            target = target.slice(1);
        }

        let newUrl = `/${target}`;

        // 항상 페이지를 새로고침하여 리다이렉트
        window.location.href = newUrl;
    }

    navItems.forEach(item => {
        item.addEventListener('click', handleNavigation);
    });

    // 초기 페이지 로드 시 현재 페이지에 맞는 네비게이션 항목 활성화
    updateActiveNavItem();
});

function updateActiveNavItem() {
    const currentPath = window.location.pathname.split('/')[1] || 'dashboard';
    const navItems = document.querySelectorAll('.nav-item');
    const headerTitle = document.getElementById('header-title');
    const current_page = document.getElementById("current-page");

    navItems.forEach(item => {
        const itemTarget = item.getAttribute('data-target').replace('#', '');
        if (itemTarget === currentPath) {
            item.classList.add('active');
            if (headerTitle) {
                headerTitle.textContent = itemTarget.charAt(0).toUpperCase() + itemTarget.slice(1);
            }
            if (current_page) {
                current_page.innerText = itemTarget;
            }
        } else {
            item.classList.remove('active');
        }
    });
}

window.addEventListener('load', () => {
    updateActiveNavItem();
    if (typeof window.initializePage === 'function') {
        window.initializePage();
    }
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
