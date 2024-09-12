// 박스 1 차트
const chart1 = document.getElementById('chart1').getContext('2d');


const myChart1 = new Chart(chart1, {
    type: 'line',
    data: {
        datasets: [{
            label: 'sales amount',
            borderWidth: 2
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
            },
            y: {
                beginAtZero: true
            }
        },
        plugins: {
            tooltip: {
                callbacks: {
                    label: function (context) {
                        let label = context.dataset.label || '';
                        if (label) {
                            label += ': ';
                        }
                        label += context.formattedValue;
                        return label;
                    },
                    title: function (context) {
                        const date = context[0].parsed.x;
                        return moment(date).format('YYYY-MM-DD');

                    }
                }
            },
            legend: {
                position: 'top',         // 라벨을 오른쪽에 배치
                labels: {
                    boxWidth: 60,         // 라벨 박스의 너비
                    boxHeight: 12,        // 라벨 박스의 높이
                    padding: 10          // 라벨 간격
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
                    // text: '날짜'
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
        datasets: [{
            label: 'qty',
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
        maintainAspectRatio: false,
        plugins: {
            legend: {
                position: 'right',         // 라벨을 오른쪽에 배치
                labels: {
                    boxWidth: 12,         // 라벨 박스의 너비
                    boxHeight: 12,        // 라벨 박스의 높이
                    padding: 10          // 라벨 간격
                }
            }
        },
        scales: {
            y: {
                beginAtZero: true
            }
        },

        layout: {
            padding: {
                left: 10,
                right: 20,
                top: 10,
                bottom: 30
            }
        }
    }
});

const updateChart2 = async () => {
    try {

        const response = await fetch("/weeklyTopProducts");

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const topProducts = await response.json();
        const chartData = myChart2.data;

        topProducts.forEach((product, index) => {
            chartData.labels[index] = product.sold_product_name;
        });

        if (chartData.datasets.length > 0) {
            chartData.datasets[0].data = topProducts.map(product => product.sold_qty);
        }

        myChart2.update();
        

    } catch (error) {
        console.error('Error fetching weeklyTopProducts data:', error);
    }
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
        relatives: true,
        maintainAspectRatio: false,
        plugins: {
            legend: {
                position: 'right',         // 라벨을 오른쪽에 배치
                labels: {
                    boxWidth: 12,         // 라벨 박스의 너비
                    boxHeight: 50,        // 라벨 박스의 높이
                    padding: 30          // 라벨 간격
                }
            }
        },
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
        },
        layout: {
            padding: {
                left: 10,
                right: 10,
                top: 10,
                bottom: 30
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

async function updateChart3 (period){
    try {

        let salesResponse = await fetch('/totalsales');
        let salesData = await salesResponse.json();

        if (salesData.length === 0) {
            myChart3.data.labels = [];
            myChart3.data.datasets[0].data = [];
            myChart3.update();
            console.log('No data available for the selected period');
            
            return;

        }

        let labels, totalSales;

        if (period === 'month' || period === 'year') {
            const aggregated = aggregatedByPeriod(salesData, period);
            lables = aggregated.labels;
            totalSales = aggregated.totalSales;
        } else {

            labels = salesData.map(item => moment(item.sales_data).foramt('YYYY-MM-DD'));
            totalSales = salesData.map(item => item.total_sales);
        }

        let productResponse = await fetch('/totalproductsbyupdate');
        let productData = await productResponse.json();

        const totalProducts = Array.isArray(productData) ? productData : Array(labels.length).fill(productData);

        myChart3.data.labels = labels;
        myChart3.data.datasets[0].data = totalSales;
        myChart3.data.datasets[1].data = totalProducts;

        myChart3.options.scales.x.time.unit = period === 'month' ? 'month' : period === 'year' ? 'year' : 'day';
        
        myChart3.update();
    } catch (error) {
        console.error('Error: ', error);
    }

}

$(document).ready(function () {
    $('#monthSalesBtn').on('click', function () {
        updateChart3('month');
    });

    $('#yearSalesBtn').on('click', function () {
        updateChart3('year');
    });

    $('#totalSalesBtn').on('click', function () {
        updateChart3('day');
    });

    // 기본적으로 월단위
    updateChart3('month');
});

// 박스 4 차트
const chart4 = document.getElementById('chart4').getContext('2d');
const myChart4 = new Chart(chart4, {
    type: 'pie',
    data: {
        labels: ['Olive Green', 'Burnt Sienna', 'Steel Blue', 'Rose Gold', 'Slate Blue'],
        datasets: [{
            label: 'qty',
            data: [12, 19, 3, 5, 2],
            backgroundColor: [
                'rgba(128, 128, 0, 0.2)',   // Olive Green
                'rgba(233, 116, 81, 0.2)',   // Burnt Sienna
                'rgba(70, 130, 180, 0.2)',   // Steel Blue
                'rgba(255, 192, 203, 0.2)',  // Rose Gold
                'rgba(106, 90, 205, 0.2)'    // Slate Blue
            ],
            borderColor: [
                'rgba(128, 128, 0, 1)',     // Olive Green
                'rgba(233, 116, 81, 1)',    // Burnt Sienna
                'rgba(70, 130, 180, 1)',    // Steel Blue
                'rgba(255, 192, 203, 1)',   // Rose Gold
                'rgba(106, 90, 205, 1)'     // Slate Blue
            ],
            borderWidth: 1
        }]
    },
    options: {
        maintainAspectRatio: false,
        plugins: {
            legend: {
                position: 'bottom',        
                    boxWidth: 12,         // 라벨 박스의 너비
                    boxHeight: 12,        // 라벨 박스의 높이
                    padding: 10          // 라벨 간격
                }
            }
        },
        scales: {
            x: {
                beginAtZero: true
            }
        },
        layout: {
            padding: {
                left: 10,
                right: 10,
                top: 20,
                bottom: 20
            }
        }
    });

const updateChart4 = async () => {
    try {

        const response = await fetch("/requestqty");

        if (!response.ok) {
            throw new Error('Nerwork response was not ok');
        }

        const requestQty = await response.json();

        myChart4.data.labels = requestQty.map(request => request.product_name);

        console.dir(myChart4.data.labels);

        if (myChart4.data.datasets.length > 0) {
            myChart4.data.datasets[0].data = requestQty.map(request => request.request_qty);
        }

        myChart4.update();
    } catch (error) {
        console.error('Error fetching requestqty data: ', error);
    }
};

updateChart4();


function fetchTrackSummary() {
    fetch('/api/track')
        .then(response => response.json())
        .then(data => {
            document.getElementById("pendingCount").textContent = data.pendingCount;
            document.getElementById("packedCount").textContent = data.packedCount;
            document.getElementById("dispatchedCount").textContent = data.dispatchedCount;
            document.getElementById("invoiceCount").textContent = data.invoiceCount;
        })
        .catch(error => console.error('Error fetching product summary:', error));
}

function fetchSalesSummary() {
    fetch('/api/salessummary')
        .then(response => response.json())
        .then(data => {
            document.getElementById("totalSalesValue").textContent = data[0];
            document.getElementById("operatingProfitValue").textContent = data[1];
        })
        .catch(error => comsole.error('Error fetching sales summary', error));
}


fetchTrackSummary();
fetchSalesSummary();

// 3박스 관련 함수
function initializeInventory() {
    fetchProductSummary();
    // 기타 인벤토리 페이지 초기화 로직
}

function fetchProductSummary() {
    fetch('/dashboard/productsummary')
        .then(response => response.json())
        .then(data => {
            document.getElementById("categoryCount").textContent = data.totalCategories;
        })
        .catch(error => console.error('Error fetching product summary:', error));
}

// 개수 표시 div 클릭시 상세창
$(".info-box").click((e) => {
    console.log("상세보기창 만들수도");
});
// 개수 최초 데이터 로드
fetchProductSummary();
// 10분마다 개수 데이터 갱신 (밀리초 단위)
setInterval(fetchProductSummary, 600000);

document.addEventListener('DOMContentLoaded', function() {
    // 숫자 포맷 함수
    function formatNumber(number) {
        if (number === null || number === undefined) {
            return '0';
        }
        return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    }

    // HTML 요소에서 숫자를 가져와서 포맷팅
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

});

