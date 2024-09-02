// 박스 1 차트
const chart1 = document.getElementById('chart1').getContext('2d');

const myChart1 = new Chart(chart1, {
    type: 'line',
    data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
            label: 'sales amount',
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
            x: {
                type: 'time',
                time: {
                    parser: 'yyyy-MM-dd',
                    unit: 'day',
                    displayFormats: {
                        day: 'yyyy-MM-dd',
                        month: 'yyyy-MM'
                    }
                },
                title: {
                    display: true,
                    text: '날짜'
                }
            },
            y: {
                beginAtZero: true
            }
        },
        plugins: {
            tooltip: {
                callbacks: {
                    label: function(context) {
                        let label = context.dataset.label || '';
                        if (label) {
                            label += ': ';
                        }
                        label += context.formattedValue;
                        return label;
                    },
                    title: function(context) {
                        const date = context[0].parsed.x; 
                        return moment(date).format('YYYY-MM-DD'); 
                    }
                }
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

const updateChart2 = () => {
    $.ajax({
        url: "/weeklyTopProducts",
        method: "GET",
        dataType: 'json',
        success: function(TopProducts) {
            
            const chartData = myChart2.data;

            // 라벨 이름 -> 팔린 상품명
            TopProducts.forEach((product, index) => {
                chartData.labels[index] = product.sold_product_name;
                //console.log(product.sold_product_name);
            });

            // 팔린 개수 할당
            // console.dir(chartData.datasets[0].data);

            if (chartData.datasets.length > 0) {
                chartData.datasets[0].data = TopProducts.map(product => product.sold_qty);
            }

            // 차트 업데이트
            myChart2.update();

        },
        error: function(error) {
            console.error('Error fetching weeklyTopProducts data:', error);
        }
    })
};

updateChart2();



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

function aggregateByPeriod(salesData, period) {
    const aggregatedData = {};
    const format = (period === 'month') ? 'YYYY-MM' : 'YYYY';

    // 날짜별로 total_sales를 합산
    salesData.forEach(item => {
        const key = moment(item.sales_date).format(format);
        if (!aggregatedData[key]) {
            aggregatedData[key] = 0;
        }
        aggregatedData[key] += item.total_sales;  // 같은 기간의 판매량을 합산
    });

    // 집계된 데이터를 배열 형태로 반환
    const labels = Object.keys(aggregatedData);
    const totalSales = Object.values(aggregatedData);

    return { labels, totalSales };
}

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

            let labels, totalSales;

            // 일단위 데이터를 월별 또는 년별로 집계
            if (period === 'month' || period === 'year') {
                const aggregated = aggregateByPeriod(salesData, period);
                labels = aggregated.labels;
                totalSales = aggregated.totalSales;
            } else {
                // 일 단위 데이터를 그대로 사용
                labels = salesData.map(item => moment(item.sales_date).format('YYYY-MM-DD'));
                totalSales = salesData.map(item => item.total_sales);
            }

            // 총 상품 데이터 가져오기
            $.ajax({
                url: '/totalproductsbyupdate',
                method: 'GET',
                dataType: 'json',
                success: function(productData) {
                    // 총 상품 수량이 배열로 들어오지 않은 경우 처리
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

$(document).ready(function() {
    $('#monthSalesBtn').on('click', function() {
        updateChart3('month');
    });

    $('#yearSalesBtn').on('click', function() {
        updateChart3('year');
    });

    $('#totalSalesBtn').on('click', function() {
        updateChart3('day');
    });

    // 기본적으로 월단위
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