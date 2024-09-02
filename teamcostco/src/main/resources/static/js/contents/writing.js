document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");

  

    // 폼 제출 처리
    form.addEventListener("submit", function (event) {
        event.preventDefault(); // 폼의 기본 제출 동작을 막습니다.

        const formData = new FormData(form);

        // 데이터 객체를 만듭니다.
        const data = {};
        formData.forEach((value, key) => {
            data[key] = value;
        });

        const jsonData = JSON.stringify(data);

        // AJAX 요청을 보냅니다.
        fetch("/writing", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: jsonData
        })
            .then(response => {
                if (response.ok) {
                    console.log("Form submission successful.");
                    // 폼 제출 후 페이지를 리다이렉트합니다.
                    window.location.href = "/notice";
                } else {
                    return response.text().then(text => {
                        // 오류 처리
                        console.error("Form submission error:", text);
                        showNotificationPopup("오류: " + text);
                    });
                }
            })
            .catch(error => {
                console.error("Fetch error:", error);
                showNotificationPopup("알 수 없는 오류가 발생했습니다.");
            });
    });


});


