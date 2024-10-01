document.addEventListener('DOMContentLoaded', function () {
  // 모든 닫기 버튼을 선택
  const closeButtons = document.querySelectorAll('.notification-close');

  // 각 닫기 버튼에 클릭 이벤트 리스너 추가
  closeButtons.forEach(button => {
    button.addEventListener('click', function () {
      // 클릭한 버튼의 부모 요소 (알림 카드)를 찾고 제거
      const notification = this.parentElement;
      notification.style.opacity = '0'; // 부드러운 퇴장 애니메이션을 위해 투명도로 설정
      notification.remove();
    });
  });
});

const protocol = window.location.protocol === 'https:' ? 'wss' : 'ws';
const host = window.location.host; // 현재 페이지의 호스트(도메인 + 포트)
const path = '/notifications'; // WebSocket 경로

const socket = new WebSocket(`${protocol}://${host}${path}`);

// 웹소켓 연결 상태 확인
socket.onopen = function () {
  console.log("WebSocket connection established.");
};

socket.onerror = function (error) {
  console.error("WebSocket Error: ", error);
};

socket.onclose = function (event) {
  console.log("WebSocket connection closed:", event);
};

// 웹소켓 메시지 수신 처리
socket.onmessage = function (event) {
  console.log("Received WebSocket message:", event.data); // 수신한 메시지 로그
  addNotificationItem(event.data);
};

function addNotificationItem(title) {
  // Create the notification container if it doesn't exist
  var $notificationContainer = $('.notification-container');

  // Create the notification element
  var $notification = $('<div>', {
    class: 'notification'
  });

  // Create and add title
  var $title = $('<div>', {
    class: 'notification-title',
    html: title
  });
  $notification.append($title);

  // Create and add content
  var $contentDiv = $('<div>', {
    class: 'notification-content',
    text: '' // Empty content for now
  });
  $notification.append($contentDiv);

  // Create and add close button
  var $closeButton = $('<span>', {
    class: 'notification-close',
    text: 'X'
  }).on('click', function () {
    $(this).closest('.notification').remove(); // Remove the notification on click
  });
  $notification.append($closeButton);
  
  // Append the notification to the container
  $notificationContainer.append($notification);
}
