document.getElementById('process_order').addEventListener('click', function() {
    const requestId = document.getElementById('request_id').value;
    const selectedStatus = document.getElementById('process').value;

    fetch(`/api/orderrequest/status/${requestId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            status: selectedStatus
        }),
    })
    .then(response => response)
    .then(data => {
        if (data.ok) {
            alert('상태가 성공적으로 변경되었습니다.');
            location.reload();
        } else {
            alert('상태 변경에 실패했습니다.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('오류가 발생했습니다.');
    });
});
