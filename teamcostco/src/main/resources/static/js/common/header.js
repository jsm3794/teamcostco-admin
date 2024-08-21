/* header script를 기술할 js 파일입니다. */

document.addEventListener('DOMContentLoaded', () => {
    const dropdown = document.querySelector('.shop-dropdown');
    const dropdownBtn = document.querySelector('.dropdown-btn');
    const dropdownContent = document.querySelector('.dropdown-content');
    let timeout;
    dropdownBtn.addEventListener('mouseenter', () => {
        clearTimeout(timeout);
        dropdownContent.style.display = 'block';
    });
    dropdownBtn.addEventListener('mouseleave', () => {
        timeout = setTimeout(() => {
            dropdownContent.style.display = 'none';
        }, 200);
    });
    dropdownContent.addEventListener('mouseenter', () => {
        clearTimeout(timeout);
    });
    dropdownContent.addEventListener('mouseleave', () => {
        timeout = setTimeout(() => {
            dropdownContent.style.display = 'none';
        }, 200);
    });
});
const bell = document.getElementById("bell").outerHTML;
document.getElementById('notification-icon').addEventListener('click', function () {
    this.innerHTML = bell;
});
document.querySelector('.profile-pic').addEventListener('click', function () {
    window.location.href = '/mypage';
});