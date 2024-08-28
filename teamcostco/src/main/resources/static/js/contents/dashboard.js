// 박스 1 차트
const chart1 = document.getElementById('chart1').getContext('2d');

const myChart1 = new Chart(chart1, {
    type: 'line',
    data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
            label: '# of Votes',
            data: [12, 19, 3, 5, 2, 3],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        maintainAspectRatio: false,
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});

function updateChart(period) {
    fetch(`/sales-data?period=${period}`)
        .then(response => response.json())
        .then(data => {
            if (data.length === 0) {
                // 데이터가 없을 경우 처리
                mychart1.data.labels = [];
                mychart1.data.datasets[0].data = [];
                mychart1.update();
                console.log("No data available for the selected period");
                return;
            }

            mychart1.data.labels = data.map(item => new Date(item.salesDate));
            mychart1.data.datasets[0].data = data.map(item => item.totalPrice);
            mychart1.options.scales.x = {
                type: 'time',
                time: {
                    parser: 'yyyy-MM-dd',
                    unit: period === 'year' ? 'month' : 'day',
                    displayFormats: {
                        day: 'yyyy-MM-dd',
                        month: 'yyyy-MM'
                    }
                },
                title: {
                    display: true,
                    text: '날짜'
                }
            };
            mychart1.update();
        })
        .catch(error => console.error('Error fetching sales data:', error));
}

// 차트 초기화 후 기본 데이터 로드
document.addEventListener('DOMContentLoaded', () => {
    updateChart('week');
});

// 버튼에 이벤트 리스너 추가
document.getElementById('weekBtn').addEventListener('click', () => updateChart('week'));
document.getElementById('monthBtn').addEventListener('click', () => updateChart('month'));
document.getElementById('yearBtn').addEventListener('click', () => updateChart('year'));

// 박스 2 차트
const chart2 = document.getElementById('chart2').getContext('2d');
const myChart2 = new Chart(chart2, {
    type: 'doughnut',
    data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple'],
        datasets: [{
            label: '# of Votes',
            data: [12, 19, 3, 5, 2],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});



// 박스 3 차트
const chart3 = document.getElementById('chart3').getContext('2d');
const myChart3 = new Chart(chart3, {
    type: 'bar',
    data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple'],
        datasets: [{
            label: '# of Votes',
            data: [12, 19, 7, 5, 9],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(255, 99, 132, 0.2)',
                'rgba(255, 99, 132, 0.2)',
                'rgba(255, 99, 132, 0.2)',
                'rgba(255, 99, 132, 0.2)',
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(255, 99, 132, 1)',
                'rgba(255, 99, 132, 1)',
                'rgba(255, 99, 132, 1)',
                'rgba(255, 99, 132, 1)',
            ],
            borderWidth: 1
        },
        {
            label: '# of Votes',
            data: [3, 21, 2, -3, 1],
            backgroundColor: [
                'rgba(255, 206, 86, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(255, 206, 86, 0.2)',
            ],
            borderColor: [
                'rgba(255, 206, 86, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(255, 206, 86, 1)',
            ],
            borderWidth: 1
        },
        {
            label: '# of Votes',
            data: [19, 14, 3, 1, 2],
            backgroundColor: [
                'rgba(54, 162, 235, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(54, 162, 235, 0.2)',
            ],
            borderColor: [
                'rgba(54, 162, 235, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(54, 162, 235, 1)',
            ],
            borderWidth: 1
        },
        {
            label: '# of Votes',
            data: [12, 19, 3, 5, -1],
            backgroundColor: [
                'rgba(75, 192, 192, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(75, 192, 192, 0.2)'
            ],
            borderColor: [
                'rgba(75, 192, 192, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(75, 192, 192, 1)',
            ],
            borderWidth: 1
        }
        ]
    },
    options: {
        maintainAspectRatio: false,
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});



// 박스 4 차트
const chart4 = document.getElementById('chart4').getContext('2d');
const myChart4 = new Chart(chart4, {
    type: 'bar',
    data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple'],
        datasets: [{
            label: '# of Votes',
            data: [12, 19, 3, 5, 2],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        indexAxis:'y',
        scales: {
            x: {
                beginAtZero: true
            }
        }
    }
});


// 3박스 관련 함수
function initializeInventory() {
    fetchProductSummary();
    // 기타 인벤토리 페이지 초기화 로직
}

function fetchProductSummary() {
    fetch('/productsummary')
        .then(response => response.json())
        .then(data => {
            document.getElementById("categoryCount").textContent = data.totalCategories;
            document.getElementById("totalQtySum").textContent = data.totalProductsQty;
            document.getElementById("lowStockCount").textContent = data.lowProducts;
        })
        .catch(error => console.error('Error fetching product summary:', error));
}

// 10분마다 데이터 갱신
setInterval(fetchProductSummary, 600000);

// 개수 표시 div 클릭시 상세창
$(".info-box").click((e) => {
    console.log("상세보기창 만들수도");
});
// 개수 최초 데이터 로드
fetchProductSummary();
// 10분마다 개수 데이터 갱신 (밀리초 단위)
setInterval(fetchProductSummary, 600000);