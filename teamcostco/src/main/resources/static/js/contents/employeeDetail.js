document.addEventListener('DOMContentLoaded', (event) => {
    document.getElementById('hire_date').addEventListener('focus', function() {
        this.showPicker();
    });

    document.getElementById('join_date').addEventListener('focus', function() {
        this.showPicker(); 
    });
});

document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('form');
    if (form) {
        form.addEventListener('submit', function (event) {
            event.preventDefault(); // 기본 폼 제출 방지

            const formData = new FormData(form);

            fetch(form.action, {
                method: 'POST',
                body: formData
            })
                .then(response => response.json())
                .then(data => {
                    if (data.status === 'success') {
                        alert(data.message); // 성공 메시지
                    } else {
                        alert(data.message); // 실패 메시지
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('서버와의 통신 중 오류가 발생했습니다. 다시 시도해 주세요.');
                });
        });
    }
});