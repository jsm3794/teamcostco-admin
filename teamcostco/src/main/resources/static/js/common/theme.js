document.addEventListener('DOMContentLoaded', () => {
    const toggleButton = document.getElementById('theme-toggle');

    function toggleTheme() {
        // 현재 테마 가져오기, 기본값은 "light"
        const currentTheme = getCookie("theme") || "light";
        // 새 테마 결정
        const newTheme = currentTheme === "light" ? "dark" : "light";
        // 아이콘 요소 가져오기
        const icon = toggleButton.querySelector('#mode_icon');

        // 쿠키에 새 테마 저장
        setCookie("theme", newTheme, 7);

        if (icon) {
            // 문서에 새 테마 적용
            document.documentElement.setAttribute("data-theme", newTheme);
            // 아이콘 텍스트 변경
            icon.textContent = newTheme === 'dark' ? 'dark_mode' : 'light_mode';
        } else {
            console.error('아이콘 요소를 찾을 수 없습니다.');
        }
    }

    // 클릭 시 테마 전환
    toggleButton.addEventListener('click', toggleTheme);
});

// 쿠키 설정 함수
function setCookie(name, value, days) {
    let expires = "";
    if (days) {
        const date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = `${name}=${value || ""}${expires}; path=/`;
}

// 쿠키 가져오기 함수
function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

// 테마 적용 함수
function applyTheme() {
    const theme = getCookie("theme");
    console.log(theme); // 디버깅용
    if (theme) {
        document.documentElement.setAttribute("data-theme", theme);
    }
}

// 페이지 로드 시 테마 적용
applyTheme();
