const alertbtn = document.getElementById("alert");

alertbtn.addEventListener("click", () => {
    alert("수정되었습니다");
});

const getTheme = () => {
    return getCookie("theme") === "light" ? "oxide" : "oxide-dark";
}

// 간소화된 삭제 확인 기능
const deleteBtn = document.getElementById("delete_btn");
if (deleteBtn) {
    deleteBtn.onclick = function(event) {
        event.preventDefault();
        if (confirm("정말로 삭제하시겠습니까?")) {
            window.location.href = this.getAttribute("data-delete-url");
        }
    };
}

tinymce.init({
    // ... (기존 tinymce 설정 유지)
});