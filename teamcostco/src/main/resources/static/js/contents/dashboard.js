function aggregateByPeriod(salesData, period) {
    const aggregatedData = {};
    const format = (period === 'month') ? 'YYYY-MM' : 'YYYY';

    // total_sales를 날짜별로 합산
    salesData.forEach(item => {
        const key = moment(item.sales_date).format(format);
        if (!aggregatedData[key]) {
            aggregatedData[key] = 0;
        }
        aggregatedData[key] += item.total_sales;
    });

    // 집계된 데이터 반환
    const labels = Object.keys(aggregatedData);
    const totalSales = Object.values(aggregatedData);
    return { labels, totalSales };
}

// API 요청 및 DOM 업데이트 공통 함수
function fetchDataAndUpdate(url, elements, errorMessage, dataMapping) {
    fetch(url)
        .then(response => response.json())
        .then(data => {
            elements.forEach((elementId, index) => {
                const value = dataMapping ? dataMapping[index](data) : data[index];
                document.getElementById(elementId).textContent = value;
            });
        })
        .catch(error => console.error(errorMessage, error));
}

// 트래킹 요약 가져오기
function fetchTrackSummary() {
    const elements = ["pendingCount", "packedCount", "dispatchedCount", "invoiceCount"];
    const dataMapping = [
        (data) => data.pendingCount,
        (data) => data.packedCount,
        (data) => data.dispatchedCount,
        (data) => data.invoiceCount
    ];
    fetchDataAndUpdate('/api/track', elements, 'Error fetching product summary:', dataMapping);
}

// 판매 요약 가져오기
function fetchSalesSummary() {
    const elements = ["totalSalesValue", "operatingProfitValue"];
    fetchDataAndUpdate('/api/salessummary', elements, 'Error fetching sales summary:');
}

// 제품 요약 가져오기
function fetchProductSummary() {
    fetchDataAndUpdate('/dashboard/productsummary', ["categoryCount"], 'Error fetching product summary:');
}

// 개수 표시 div 클릭 시 상세창
$(".info-box").click(() => {
    console.log("상세보기창 만들수도");
});

// 데이터 최초 로드 및 갱신
fetchProductSummary();
setInterval(fetchProductSummary, 600000);

// 숫자 포맷팅 함수
function formatNumber(number) {
    return number === null || number === undefined ? '0' : number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

// DOMContentLoaded 이벤트
document.addEventListener('DOMContentLoaded', function() {
    // 숫자 포맷팅
    const totalSalesElement = document.getElementById('totalSalesValue');
    const operatingProfitElement = document.getElementById('operatingProfitValue');

    if (totalSalesElement) {
        const totalSales = parseInt(totalSalesElement.textContent, 10);
        totalSalesElement.textContent = formatNumber(totalSales);
    }

    if (operatingProfitElement) {
        const operatingProfit = parseInt(operatingProfitElement.textContent, 10);
        operatingProfitElement.textContent = formatNumber(operatingProfit);
    }

    fetchTrackSummary();
    fetchSalesSummary();
});


const chart1 = document.getElementById('chart1').getContext('2d');
const chart2 = document.getElementById('chart2').getContext('2d');
const chart3 = document.getElementById('chart3').getContext('2d');
const chart4 = document.getElementById('chart4').getContext('2d');

// 랜덤한 RGB 색상을 생성하는 함수
function getRandomColor(alpha) {
    const r = Math.floor(Math.random() * 256);
    const g = Math.floor(Math.random() * 256);
    const b = Math.floor(Math.random() * 256);
    return `rgba(${r}, ${g}, ${b}, ${alpha})`;
}

// 차트에 사용할 랜덤한 색상을 생성하는 함수
function generateRandomColors(numColors) {
    return Array.from({ length: numColors }, () => ({
        background: getRandomColor(0.2),  // 배경색은 투명도 0.2
        border: getRandomColor(1)         // 테두리 색상은 불투명 (alpha 1)
    }));
}

// 차트를 생성하는 함수
function createChart(context, type, labels, colors, options = {}) {
    return new Chart(context, {
        type: type,
        data: {
            labels: labels,
            datasets: [{
                label: 'qty',
                data: [12, 19, 3, 5, 2], // 초기 데이터
                backgroundColor: colors.map(color => color.background),
                borderColor: colors.map(color => color.border),
                borderWidth: 1
            }]
        },
        options: {
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    position: 'bottom'
                }
            },
            ...options // 추가 옵션을 적용
        }
    });
}

// 차트 초기 데이터 및 색상
const labels1 = ['January', 'February', 'March', 'April', 'May'];
const labels2 = ['노트북', '휴대폰', '키보드', '헤드셋', '마우스'];
const labels3 = ['Red', 'Blue', 'Green', 'Yellow', 'Purple'];
const labels4 = ['노트북', '휴대폰', '키보드', '헤드셋', '마우스'];

let colors1 = generateRandomColors(5);
let colors2 = generateRandomColors(5);
let colors3 = generateRandomColors(5);
let colors4 = generateRandomColors(5);

const myChart1 = createChart(chart1, 'line', labels1, colors1);
const myChart2 = createChart(chart2, 'doughnut', labels2, colors2, {
    plugins: {
        legend: {
            position: 'right', // 차트2의 레전드를 오른쪽으로 설정
            align: 'start',    // 세로로 정렬
            labels: {
                boxWidth: 10,
                padding: 10
            }
        }
    }
});
const myChart3 = createChart(chart3, 'bar', labels3, colors3, { indexAxis: 'y' });
const myChart4 = createChart(chart4, 'pie', labels4, colors4);

// 랜덤 데이터를 생성하는 함수
function getRandomData(num) {
    return Array.from({ length: num }, () => Math.floor(Math.random() * 100));
}

// 차트를 업데이트하는 함수
function updateChart(chart, labels) {
    const newColors = generateRandomColors(labels.length); // 새로운 색상 생성
    chart.data.labels = labels; // 레이블 업데이트
    chart.data.datasets[0].data = getRandomData(labels.length); // 새로운 랜덤 데이터 생성
    chart.data.datasets[0].backgroundColor = newColors.map(color => color.background);
    chart.data.datasets[0].borderColor = newColors.map(color => color.border);
    chart.update(); // 차트 업데이트
}

// 버튼 클릭 이벤트 리스너 추가
document.getElementById('weekBtn').addEventListener('click', () => {
    const newLabels = ['Week 1', 'Week 2', 'Week 3', 'Week 4', 'Week 5'];
    updateChart(myChart1, newLabels);
});

document.getElementById('monthBtn').addEventListener('click', () => {
    const newLabels = ['January', 'February', 'March', 'April', 'May'];
    updateChart(myChart1, newLabels);
});

document.getElementById('yearBtn').addEventListener('click', () => {
    const newLabels = ['2019', '2020', '2021', '2022', '2023'];
    updateChart(myChart1, newLabels);
});

// 추가 버튼 클릭 이벤트 설정 (다른 차트들도 동일하게 설정 가능)
document.getElementById('monthSalesBtn').addEventListener('click', () => {
    const newLabels = ['January', 'February', 'March', 'April', 'May'];
    updateChart(myChart3, newLabels);
});

document.getElementById('yearSalesBtn').addEventListener('click', () => {
    const newLabels = ['2020', '2021', '2022', '2023', '2024'];
    updateChart(myChart3, newLabels);
});

document.getElementById('totalSalesBtn').addEventListener('click', () => {
    const newLabels = ['Total A', 'Total B', 'Total C', 'Total D', 'Total E'];
    updateChart(myChart3, newLabels);
});