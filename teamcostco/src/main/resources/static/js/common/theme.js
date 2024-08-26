const toggleButton = document.getElementById('theme-toggle');
function setCookie(name, value, days) {
    let expires = "";
    if (days) {
        const date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "") + expires + "; path=/";
}
function setDarkMode(isDarkMode) {
    setCookie("theme", isDarkMode ? "dark" : "light", 7); // 7일 동안 유지
}
function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}
// 페이지 로드 시 테마 설정
function applyTheme() {
    const theme = getCookie("theme");
    if (theme) {
        document.documentElement.setAttribute("data-theme", theme);
    }
}
function toggleTheme() {
    let currentTheme = getCookie("theme") || "light";
    let newTheme = currentTheme === "light" ? "dark" : "light";
    const icon = toggleButton.querySelector('#mode_icon');
    setCookie("theme", newTheme, 7);
    if (icon) {
        if (newTheme === 'dark') {
            document.documentElement.setAttribute("data-theme", newTheme);
            icon.textContent = 'dark_mode'; // 아이콘 변경
        } else {
            document.documentElement.setAttribute("data-theme", newTheme);
            icon.textContent = 'light_mode'; // 아이콘 변경
        }
    } else {
        console.error('아이콘 요소를 찾을 수 없습니다.');
    }
}
document.getElementById('theme-toggle').addEventListener('click', toggleTheme);

document.addEventListener("DOMContentLoaded", applyTheme);