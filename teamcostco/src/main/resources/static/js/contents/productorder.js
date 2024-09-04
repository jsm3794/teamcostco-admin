document.addEventListener('DOMContentLoaded', function() {
    var orderForm = document.getElementById('orderForm');
    if (orderForm) {
        orderForm.addEventListener('submit', function(e) {
            e.preventDefault();
            
            var selectedProducts = [];
            var checkboxes = document.querySelectorAll('input[name="orderCheck"]:checked');
            
            if (checkboxes.length === 0) {
                alert('체크한 물품이 없습니다.');
                return;
            }

            var isQuantityValid = true;
            
            checkboxes.forEach(function(checkbox) {
                var row = checkbox.closest('tr');
                var quantityInput = row.querySelector('input[name="orderQuantity"]');
                var quantity = quantityInput.value;
                var title = row.querySelector('td:nth-child(3)').innerText;
                
                if (quantity <= 0 || quantity === '') {
                    alert('수량을 설정하지 않았습니다.');
                    isQuantityValid = false;
                    return;
                }
                
                selectedProducts.push({
                    productId: checkbox.value,
                    title: title,
                    quantity: quantity
                });
            });
            
            if (!isQuantityValid) {
                return;
            }
            
            console.log('Selected products:', selectedProducts);
            
            // 서버로 데이터 전송
            fetch('/order', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(selectedProducts)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.text();
            })
            .then(data => {
                console.log('Success:', data);
                alert('발주가 완료되었습니다.');
                // 필요하다면 추가적인 UI 업데이트 로직 추가
            })
            .catch((error) => {
                console.error('Error:', error);
                alert('발주 처리 중 오류가 발생했습니다.');
            });
        });
    }
});
