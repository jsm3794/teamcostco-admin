document.addEventListener('DOMContentLoaded', () => {
    const dropdown = document.querySelector('.shop-dropdown');
    const dropdownBtn = document.querySelector('.dropdown-btn');
    const dropdownContent = document.querySelector('.dropdown-content');
    let timeout;

    // 드롭다운 버튼 및 내용 표시/숨김 제어
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

// 알림 아이콘 클릭 이벤트
document.getElementById('notification-icon').addEventListener('click', function () {
    // 알림 아이콘을 클릭하면 알림 내용 제거
    const notificationContainer = this.querySelector('.notifications');
    while (notificationContainer.firstChild) {
        notificationContainer.removeChild(notificationContainer.firstChild);
    }
    const notificationIcon = document.querySelector('.material-symbols-outlined');
    notificationIcon.textContent = 'notifications';
});
// 프로필 이미지 클릭 시 페이지 이동
document.querySelector('.profile-pic').addEventListener('click', function () {
    window.location.href = '/mypage';
});
