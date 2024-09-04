const getTheme = () => {
    return getCookie("theme") === "light" ? "oxide" : "oxide-dark";
}

document.addEventListener("DOMContentLoaded", (e) => {
    const writeForm = document.getElementById("writeForm");
    const submitBtn = document.getElementById("submitBtn");
    submitBtn.addEventListener("click", (e) =>{
        e.preventDefault();
        tinymce.triggerSave();
        writeForm.submit();
    });
})
