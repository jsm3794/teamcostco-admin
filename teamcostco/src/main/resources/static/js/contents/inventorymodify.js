document.addEventListener('DOMContentLoaded', () => {
    const popupAlert = document.getElementById('popup-alert');
    const popupMessage = document.getElementById('popup-message');
    const popupCloseBtn = document.getElementById('popup-close-btn');

    // 팝업 닫기 버튼 이벤트
    popupCloseBtn.addEventListener('click', () => {
        popupAlert.style.display = 'none';
    });

    // 이동 버튼 클릭 이벤트 처리
    document.getElementById('move-btn').addEventListener('click', () => {
        const storageQtyInput = document.getElementById('storage_qty');
        const moveQtyInput = document.getElementById('move-qty');
        const displayQtyInput = document.getElementById('display_qty');

        const storageQty = parseInt(storageQtyInput.value, 10);
        const moveQty = parseInt(moveQtyInput.value, 10);
        const displayQty = parseInt(displayQtyInput.value, 10);

        if (isNaN(storageQty) || isNaN(moveQty)) {
            alert('정확한 숫자를 입력해주세요.');
            return;
        }

        if (moveQty < 0) {
            alert('이동할 수량은 0 이상이어야 합니다.');
            return;
        }

        if (moveQty > storageQty) {
            alert('옮길 재고 수량이 부족합니다. 남은 재고 수량을 확인하세요.');
            return;
        }

        // UI에서 값만 임시로 업데이트
        storageQtyInput.value = storageQty - moveQty;
        displayQtyInput.value = displayQty + moveQty;
        moveQtyInput.value = '';
    });

    // 새로운 진열수량에서 재고수량으로 옮기는 버튼 클릭 이벤트 처리
    document.getElementById('reverse-move-btn').addEventListener('click', () => {
        const storageQtyInput = document.getElementById('storage_qty');
        const reverseMoveQtyInput = document.getElementById('reverse-move-qty');
        const displayQtyInput = document.getElementById('display_qty');

        const storageQty = parseInt(storageQtyInput.value, 10);
        const reverseMoveQty = parseInt(reverseMoveQtyInput.value, 10);
        const displayQty = parseInt(displayQtyInput.value, 10);

        if (isNaN(displayQty) || isNaN(reverseMoveQty)) {
            alert('정확한 숫자를 입력해주세요.');
            return;
        }

        if (reverseMoveQty < 0) {
            alert('이동할 수량은 0 이상이어야 합니다.');
            return;
        }

        if (reverseMoveQty > displayQty) {
            alert('옮길 진열 수량이 부족합니다. 남은 진열 수량을 확인하세요.');
            return;
        }

        // UI에서 값만 임시로 업데이트
        storageQtyInput.value = storageQty + reverseMoveQty;
        displayQtyInput.value = displayQty - reverseMoveQty;
        reverseMoveQtyInput.value = '';
    });

    // 폼 제출 시 Ajax로 처리
    const form = document.getElementById('inventory-form');
    form.addEventListener('submit', (event) => {
        event.preventDefault(); // 폼 제출 시 페이지 이동 방지

        const formData = new FormData(form);
        const url = form.getAttribute('action');

        fetch(url, {
            method: 'POST',
            body: formData,
        })
        .then(response => response.json())
        .then(result => {
            if (result.status === 'success') {
                popupMessage.textContent = '저장 성공: ' + result.message;
            } else {
                popupMessage.textContent = '저장 실패: ' + result.message;
            }
            popupAlert.style.display = 'block';
        })
        .catch(error => {
            popupMessage.textContent = '저장 중 오류가 발생했습니다.';
            popupAlert.style.display = 'block';
        });
    });
});