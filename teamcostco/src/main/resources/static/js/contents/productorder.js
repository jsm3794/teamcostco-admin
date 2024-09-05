document.addEventListener('DOMContentLoaded', function () {
    var orderbtn = document.getElementById('order_btn');
    orderbtn.addEventListener("click", (e) => {
        e.preventDefault();
        let successCount = 0;
        let failureCount = 0;
        var selectedProducts = [];
        var checkboxes = document.querySelectorAll('input[name="orderCheck"]:checked');

        if (checkboxes.length === 0) {
            alert('체크한 물품이 없습니다.');
            return;
        }

        var errors = [];
        var emp_id = document.getElementById("emp_id").value;

        // Define a maximum length for the title
        var MAX_TITLE_LENGTH = 10;

        checkboxes.forEach(function (checkbox) {
            var row = checkbox.closest('tr');
            var imageSrc = row.querySelector('td:nth-child(2) > img').getAttribute("src");
            var purchasePrice = row.querySelector('td:nth-child(5)').innerText;
            var quantityInput = row.querySelector('input[name="orderQuantity"]');
            var quantity = quantityInput.value;
            var productCode = row.querySelector('td:nth-child(3)').innerText;
            var title = row.querySelector('td:nth-child(4)').innerText;
            var mallName = row.querySelector('td:nth-child(6)').innerText;
            var mallLink = row.querySelector('td:nth-child(7) > a').getAttribute("href");

            // Sanitize the title by removing newline characters
            title = title.replace(/[\r\n]+/g, ' ').trim();

            if (quantity <= 0 || quantity === '') {
                errors.push(`수량을 설정하지 않은 제품: ${title.substring(0, MAX_TITLE_LENGTH) + '...'} (${productCode})`);
                return;
            }

            selectedProducts.push({
                image_url: imageSrc,
                product_id: checkbox.value,
                product_name: title,
                product_code: productCode,
                purchase_price: purchasePrice,
                request_qty: quantity,
                mall_name: mallName,
                mall_link: mallLink,
                emp_id: emp_id
            });
        });

        if (errors.length > 0) {
            alert(errors.join('\n'));
            return;
        }

        // Create an array of fetch promises
        var fetchPromises = selectedProducts.map(product => {
            return fetch('/productorder', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: toUrlEncoded(product)
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text();
                })
                .then(() => {
                    successCount++;
                })
                .catch(() => {
                    failureCount++;
                });
        });
        // Wait for all fetch requests to complete
        Promise.all(fetchPromises)
            .then(() => {
                alert(`발주 완료: ${successCount}건\n발주 실패: ${failureCount}건`);
            })
            .catch((error) => {
                console.error('Error:', error);
                alert('발주 처리 중 오류가 발생했습니다.');
            });
    });
});

function toUrlEncoded(obj) {
    var str = [];
    for (var p in obj) {
        if (obj.hasOwnProperty(p)) {
            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        }
    }
    return str.join("&");
}
