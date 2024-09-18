document.addEventListener('DOMContentLoaded', () => {
    // 탭 버튼 클릭 이벤트 처리
    document.querySelectorAll('.tab-button').forEach(button => {
        button.addEventListener('click', () => {
            // 현재 활성화된 탭과 콘텐츠를 비활성화
            document.querySelectorAll('.tab-button, .tab-pane').forEach(element => {
                element.classList.remove('active');
            });

            // 클릭된 탭과 해당 콘텐츠를 활성화
            button.classList.add('active');
            document.getElementById(button.getAttribute('data-tab')).classList.add('active');
        });
    });

    // 폼 제출 이벤트 처리
    const form = document.querySelector('form');
    if (form) {
        form.addEventListener('submit', event => {
            event.preventDefault();

            const formData = new FormData(form);

            fetch(form.action, {
                method: 'POST',
                body: formData
            })
                .then(response => response.json())
                .then(data => {
                    alert(data.status === 'success' ? '저장되었습니다.' : '저장 실패: ' + data.message);
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('서버와의 통신 중 오류가 발생했습니다. 다시 시도해 주세요.');
                });
        });
    }
});
