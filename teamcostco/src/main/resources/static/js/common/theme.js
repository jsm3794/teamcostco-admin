let currentTheme = getCookie("theme") || "light";

document.addEventListener('DOMContentLoaded', () => {
    const toggleButton = document.getElementById('theme-toggle');
    const icon = document.querySelector('#mode_icon');
    icon.textContent = getCookie('theme') === 'dark' ? 'light_mode' : 'dark_mode';
    
    const charts = [
        typeof myChart1 !== 'undefined' ? myChart1 : null,
        typeof myChart2 !== 'undefined' ? myChart2 : null,
        typeof myChart3 !== 'undefined' ? myChart3 : null,
        typeof myChart4 !== 'undefined' ? myChart4 : null
    ];

    function toggleTheme() {
        const newTheme = getCookie("theme") === "light" ? "dark" : "light";
        console.log(newTheme);
        setCookie("theme", newTheme, 7);
        if (icon) {
            document.documentElement.setAttribute("data-theme", newTheme);
            icon.textContent = getCookie('theme') === 'dark' ? 'light_mode' : 'dark_mode';
            setChartTheme(newTheme);
            $('#theme-logo')[0].setAttribute('src', '/images/logo.png?t=' + new Date().getTime());
        } else {
            console.error('아이콘 요소를 찾을 수 없습니다.');
        }
    }

    function setChartTheme(theme) {
        charts.forEach(chart => {
            if (chart) {
                applyThemeToCharts(chart, theme);
            }
        });
    }


    toggleButton.addEventListener('click', toggleTheme);
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

applyTheme();