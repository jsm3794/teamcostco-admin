let editMode = false;
let changes = {};

function toggleEditMode() {
    editMode = !editMode;
    const editableCells = document.querySelectorAll('td.editable span');
    const modiButton = document.querySelector('.modi-btn');

    editableCells.forEach(td => {
        td.onclick = editMode ? () => editCell(td) : null; // 수정 모드일 때만 클릭 이벤트 추가
        td.parentElement.classList.toggle('active', editMode); // active 클래스 추가/제거
        td.style.cursor = editMode ? 'pointer' : 'default'; // 커서 변경
    });

    document.querySelector('.save-btn').style.display = editMode ? 'inline-block' : 'none'; // 저장 버튼 표시/숨김

    // 버튼 가시적 변화
    modiButton.classList.toggle('active', editMode);
    console.log(editMode ? "수정 모드 활성화" : "수정 모드 비활성화");
}

function editCell(td) {
    const currentText = td.innerText;
    const input = document.createElement('input');
    input.type = 'text';
    input.value = currentText;

    input.onblur = () => {
        td.innerText = input.value; // 입력값으로 변경
        td.onclick = () => editCell(td); // 클릭 이벤트 재설정
        changes[td.parentElement.firstChild.innerText] = input.value; // 변경된 값 저장
    };

    td.innerText = ''; // 기존 텍스트 삭제
    td.appendChild(input); // 입력 필드 추가
    input.focus(); // 입력 필드에 포커스
}

function saveChanges(id) {
    // 수정된 값만 포함하는 객체 생성
    const data = {};
    for (const key in changes) {
        if (changes[key]) {
            data[key] = changes[key];
        }
    }

    fetch(`/api/products/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(data => {
        alert("변경 사항이 저장되었습니다: ");
    })
    .catch(error => {
        console.error('Error:', error);
    });

    // 변경 사항 초기화
    changes = {};
    toggleEditMode(); // 수정 모드 비활성화
    
    // 수정 모드 비활성화 후 셀의 원래 상태로 돌아오게 하기
    const editableCells = document.querySelectorAll('td.editable span');
    editableCells.forEach(td => {
        td.onclick = () => editCell(td); // 클릭 이벤트 재설정
    });
}
