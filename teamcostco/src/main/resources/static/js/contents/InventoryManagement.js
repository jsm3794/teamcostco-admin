document.addEventListener('DOMContentLoaded', () => {
    const processOrder = (qty, type) => {
        const requestId = document.getElementById('request_id').value;
        let url = `/orderrequest/${requestId}/detail`; // URL 확인
        let data = { qty: qty, type: type };

        fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        })
        .then(response => response.json()) // 응답을 JSON으로 처리
        .then(result => {
            if (result.success) {
                alert(`${type} 수량이 성공적으로 처리되었습니다.`);
                location.reload();
            } else {
                alert(`처리 중 오류가 발생했습니다: ${result.message}`);
            }
        })
        .catch(error => console.error('Error:', error));
    };

    document.getElementById('process_order').addEventListener('click', () => {
        const processQty = parseInt(document.getElementById('process_qty').value, 10);
        const processType = document.getElementById('process_type').value;

        if (processType === '입고') {
            processOrder(processQty, 'received');
        } else if (processType === '불량') {
            processOrder(processQty, 'defective');
        }
    });
});
