// 간소화된 삭제 확인 기능

function deleteBoard(boardId){
    if (confirm("정말로 삭제하시겠습니까?")) {
        window.location.href = "/delete/" + boardId;
    }
}

function fixBoard(boardId){
    location.href = "/fix/" + boardId;
}