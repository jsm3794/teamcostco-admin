/* navigationbar script를 기술할 js 파일입니다. */

document.addEventListener('DOMContentLoaded', () => {
    const navItems = document.querySelectorAll('.nav-item');
    const headerTitle = document.getElementById('header-title');
    const current_page = document.getElementById("current-page");
    navItems.forEach(item => {
        item.addEventListener('click', function(e) {
            
            e.preventDefault;

            // navItems.forEach(navItem => navItem.classList.remove('active'));
            // this.classList.add('active');
            let target = this.getAttribute('data-target');
            if (target.startsWith('#')) {
                target = target.slice(1);
            }

            let newUrl = `/${target}`;

            // target = target.charAt(0).toUpperCase() + target.slice(1);
            // console.log('Navigating to:', target);
            // console.log('Navigating to:', this.getAttribute('data-target'));
            // headerTitle.textContent = `${target}`;
            // current_page.innerText = this.getAttribute('data-target');

            if (window.location.pathname.includes('/detail') || window.location.pathname !== '/') {
                window.history.pushState({}, '', newUrl);
                loadContent(newUrl);
            } else {
                location.href = newUrl;
            }

            navItems.forEach(navItem => navItem.classList.remove('active'));
            this.classList.add('active');

            headerTitle.textContent = target.charAt(0).toUpperCase() + target.slice(1);
            current_page.innerText = target;

            
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
        })
        .catch(error => console.error('Error:', error));
}