const alertbtn = document.getElementById("alert");

alertbtn.addEventListener("click", () => {
    alert("수정되었습니다");
});

const getTheme = () => {
    return getCookie("theme") === "light" ? "oxide" : "oxide-dark";
}