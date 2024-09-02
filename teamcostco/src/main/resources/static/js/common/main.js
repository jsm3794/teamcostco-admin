/* 공통적인 script를 기술할 js 파일입니다. */

  // 웹소켓 연결 설정
  const socket = new WebSocket("ws://localhost:9999/notifications");

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
      showNotificationPopup(event.data);
  };

  function showNotificationPopup(message) {
    const popup = document.createElement("div");
    popup.classList.add("notification-popup");
    popup.textContent = message;
    $('.container')[0].appendChild(popup);
    setTimeout(() => {
        popup.remove();
    }, 3000); // 3초 후 팝업 사라짐
}