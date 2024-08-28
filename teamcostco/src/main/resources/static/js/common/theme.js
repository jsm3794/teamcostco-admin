document.addEventListener('DOMContentLoaded', () => {
    const toggleButton = document.getElementById('theme-toggle');
    let currentTheme = getCookie("theme") || "light";

    function toggleTheme() {
        currentTheme = getCookie("theme") || "light";

        // 새 테마 결정
        const newTheme = currentTheme === "light" ? "dark" : "light";
        // 아이콘 요소 가져오기
        const icon = toggleButton.querySelector('#mode_icon');

        // 쿠키에 새 테마 저장
        setCookie("theme", newTheme, 7);

        if (icon) {
            // 문서에 새 테마 적용
            document.documentElement.setAttribute("data-theme", newTheme);
            applyThemeToCharts(myChart1, newTheme);
            applyThemeToCharts(myChart2, newTheme);
            applyThemeToCharts(myChart3, newTheme);
            applyThemeToCharts(myChart4, newTheme);
            applyTheme();
            // 아이콘 텍스트 변경
            icon.textContent = newTheme === 'dark' ? 'dark_mode' : 'light_mode';

        } else {
            console.error('아이콘 요소를 찾을 수 없습니다.');
        }
    }

    // 클릭 시 테마 전환
    toggleButton.addEventListener('click', toggleTheme);

    
    applyThemeToCharts(myChart1, currentTheme);
    applyThemeToCharts(myChart2, currentTheme);
    applyThemeToCharts(myChart3, currentTheme);
    applyThemeToCharts(myChart4, currentTheme);
});

function applyThemeToCharts(chart, newTheme) {
    // 새로운 테마에 맞는 색상 설정
    const textColor = newTheme === 'dark' ? '#eee' : '#555';
    const gridColor = newTheme === 'dark' ? 'rgba(255, 255, 255, 0.3)' : 'rgba(0, 0, 0, 0.1)';
    const tooltipColor = newTheme === 'dark' ? '#eee' : '#555';

    if (chart) {
        // 범례 텍스트 색상 설정
        if (chart.options.plugins && chart.options.plugins.legend && chart.options.plugins.legend.labels) {
            chart.options.plugins.legend.labels.color = textColor;
        }

        // 툴팁 텍스트 색상 설정
        if (chart.options.plugins && chart.options.plugins.tooltip) {
            chart.options.plugins.tooltip.bodyColor = tooltipColor;
        }

        // x축과 y축 그리드 색상 설정
        if (chart.options.scales) {
            // x축 그리드 색상
            if (chart.options.scales.x && chart.options.scales.x.grid) {
                chart.options.scales.x.grid.color = gridColor;
            }
            // y축 그리드 색상
            if (chart.options.scales.y && chart.options.scales.y.grid) {
                chart.options.scales.y.grid.color = gridColor;
            }
            
            // x축 라벨 색상 설정
            if (chart.options.scales.x && chart.options.scales.x.ticks) {
                chart.options.scales.x.ticks.color = textColor;
            }

            // y축 라벨 색상 설정
            if (chart.options.scales.y && chart.options.scales.y.ticks) {
                chart.options.scales.y.ticks.color = textColor;
            }
        }

        // 차트 업데이트
        chart.update();
    } else {
        console.error('차트 인스턴스를 찾을 수 없습니다.');
    }
}



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
    if (theme) {
        document.documentElement.setAttribute("data-theme", theme);
    }
}

// 페이지 로드 시 테마 적용
applyTheme();