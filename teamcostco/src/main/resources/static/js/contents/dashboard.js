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
                myChart1.data.labels = [];
                myChart1.data.datasets[0].data = [];
                myChart1.update();
                console.log("No data available for the selected period");
                return;
            }

            myChart1.data.labels = data.map(item => new Date(item.salesDate));
            myChart1.data.datasets[0].data = data.map(item => item.totalPrice);
            myChart1.options.scales.x = {
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
            myChart1.update();
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
        labels: [],  // 차트의 X축 레이블
        datasets: [
            {
                label: 'Total Sales',
                data: [],  // Total Sales 데이터
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
                label: 'Total Products',
                data: [],  
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
            }
        ]
    },
    options: {
        maintainAspectRatio: false,
        scales: {
            x: {
                type: 'time',
                time: {
                    unit: 'day',  // 기본 단위는 일
                    tooltipFormat: 'yyyy-MM-dd',
                    displayFormats: {
                        day: 'yyyy-MM-dd',
                        month: 'yyyy-MM',
                        year: 'yyyy'
                    }
                }
            },
            y: {
                beginAtZero: true,
            }
        }
    }
});

// 차트 업데이트 함수
function updateChart3(period) {
    $.ajax({
        url: '/totalsales',
        method: 'GET',
        dataType: 'json',
        success: function(salesData) {
            if (salesData.length === 0) {
                myChart3.data.labels = [];
                myChart3.data.datasets[0].data = [];
                myChart3.update();
                console.log("No data available for the selected period");
                return;
            }

            let labels = [];
            let totalSales = [];
            
            salesData.forEach(item => {
                let date = new Date(item.sales_date);
                labels.push(date);
                totalSales.push(item.total_sales);
            });

            // 기간에 따른 집계 처리
            if (period === 'month') {
                labels = aggregateByPeriod(labels, 'month');
            } else if (period === 'year') {
                labels = aggregateByPeriod(labels, 'year');
            }

            // Total Products 데이터 가져오기
            $.ajax({
                url: '/totalproductsbyupdate',
                method: 'GET',
                dataType: 'json',
                success: function(productData) {
                    const totalProducts = Array.isArray(productData) ? productData : Array(labels.length).fill(productData);

                    // 차트 데이터 업데이트
                    myChart3.data.labels = labels;
                    myChart3.data.datasets[0].data = totalSales;
                    myChart3.data.datasets[1].data = totalProducts;

                    // x축 단위 업데이트
                    myChart3.options.scales.x.time.unit = period === 'month' ? 'month' : period === 'year' ? 'year' : 'day';
                    
                    // 차트 업데이트
                    myChart3.update();
                },
                error: function(error) {
                    console.error('Error fetching total products data:', error);
                }
            });
        },
        error: function(error) {
            console.error('Error fetching sales data:', error);
        }
    });
}

// 기간별 데이터 집계 함수
function aggregateByPeriod(dates, period) {
    const aggregatedData = {};
    const format = period === 'month' ? 'YYYY-MM' : 'YYYY';
    
    dates.forEach(date => {
        const key = moment(date).format(format);
        if (!aggregatedData[key]) {
            aggregatedData[key] = 0;
        }
        aggregatedData[key] += 1; // Adjust based on your aggregation logic
    });

    return Object.keys(aggregatedData).map(key => moment(key, format).toDate());
}

// 버튼 클릭 이벤트 처리
$('#monthSalesBtn').click(() => updateChart3('month'));

$('#yearSalesBtn').click(() => updateChart3('year'));

$('#totalSalesBtn').click(() => updateChart3('day'));

// 초기 로드 시 월 단위 데이터로 차트 설정
$(document).ready(function() {
    updateChart3('month');
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