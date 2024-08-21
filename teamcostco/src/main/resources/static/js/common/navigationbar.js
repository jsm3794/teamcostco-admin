/* navigationbar script를 기술할 js 파일입니다. */

document.addEventListener('DOMContentLoaded', () => {
    const navItems = document.querySelectorAll('.nav-item');
    const headerTitle = document.getElementById('header-title');
    const current_page = document.getElementById("current-page");
    navItems.forEach(item => {
        item.addEventListener('click', function() {
            navItems.forEach(navItem => navItem.classList.remove('active'));
            this.classList.add('active');
            let target = this.getAttribute('data-target');
            if (target.startsWith('#')) {
                target = target.slice(1);
            }
            target = target.charAt(0).toUpperCase() + target.slice(1);
            console.log('Navigating to:', target);
            console.log('Navigating to:', this.getAttribute('data-target'));
            headerTitle.textContent = `${target}`;
            current_page.innerText = this.getAttribute('data-target');
        });
    });
});