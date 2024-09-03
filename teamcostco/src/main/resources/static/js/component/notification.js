document.addEventListener('DOMContentLoaded', function() {
    // 모든 닫기 버튼을 선택
    const closeButtons = document.querySelectorAll('.notification-close');
    
    // 각 닫기 버튼에 클릭 이벤트 리스너 추가
    closeButtons.forEach(button => {
      button.addEventListener('click', function() {
        // 클릭한 버튼의 부모 요소 (알림 카드)를 찾고 제거
        const notification = this.parentElement;
        notification.style.opacity = '0'; // 부드러운 퇴장 애니메이션을 위해 투명도로 설정
        setTimeout(() => notification.remove(), 300); // 애니메이션 시간 후에 요소 제거
      });
    });
  });