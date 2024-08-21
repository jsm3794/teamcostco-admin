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

// 알림 아이콘 노드 가져오기 (처음에 hidden)
const bell = document.getElementById("bell");
bell.style.visibility = 'hidden';  // 초기 상태에서 숨김 처리

// 알림 아이콘 클릭 이벤트
document.getElementById('notification-icon').addEventListener('click', function () {
    // 아이콘을 클릭할 때 visibility를 visible로 변경
    bell.style.visibility = 'visible';  // bell 노드가 보이도록 설정
    this.innerHTML = '';  // 기존 알림 아이콘을 비우고
    this.appendChild(bell);  // bell 아이콘을 notification-icon 안에 삽입
});

// 프로필 이미지 클릭 시 페이지 이동
document.querySelector('.profile-pic').addEventListener('click', function () {
    window.location.href = '/mypage';
});
