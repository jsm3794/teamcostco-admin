/* navigationbar script를 기술할 js 파일입니다. */

document.addEventListener('DOMContentLoaded', () => {
    const navItems = document.querySelectorAll('.nav-item');

    navItems.forEach(item => {
        item.addEventListener('click', function() {

            navItems.forEach(navItem => navItem.classList.remove('active'));

            this.classList.add('active');

            console.log('Navigating to:', this.getAttribute('data-target'));
        });
    });
});