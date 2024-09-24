$(document).ready(function() {
    $('#loginFrm').on('submit', function(event) {
        event.preventDefault();

        var id = $('#id').val().trim();
        var pw = $('#pw').val().trim();

        if (id === '') {
            alert('아이디를 입력해 주세요.');
            $('#id').focus(); 
            return;
        }

        if (pw === '') {
            alert('비밀번호를 입력해 주세요.');
            $('#pw').focus();
            return;
        }

        var formData = {
            id: id,
            pw: pw
        };

        $.ajax({
            url: $(this).attr('action'),
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            data: $.param(formData),
            dataType: 'json',
            success: function(response) {
                if (response.fail) {
                    alert('[로그인 실패] ' + response.fail);
                } else if (response.ok) {
                    window.location.href = '/';
                }
            },
            error: function(xhr, status, error) {
                var errorResponse = JSON.parse(xhr.responseText);
                if (errorResponse.fail) {
                    alert('[로그인 실패] ' + errorResponse.fail);
                } else {
                    alert('[로그인 실패] ' + xhr.responseText);
                }
            }
        });
    });

    // 페이지 로드 시 URL에서 토큰을 가져와 인증 요청
    const urlParams = new URLSearchParams(window.location.search);
    const token = urlParams.get('token');

    if (token) {
        $.ajax({
            url: '/employee/newtoken',
            type: 'GET',
            data: { token: token },
            dataType: 'json',
            success: function(response) {
                if (response.success) {
                    alert(response.message); // 인증 성공 메시지
                    window.location.href = '/login'; // 로그인 페이지로 리다이렉트
                } else {
                    alert(response.message); // 인증 실패 메시지
                }
            },
            error: function(xhr, status, error) {
                var errorResponse = JSON.parse(xhr.responseText);
                alert('[오류] ' + errorResponse.message || xhr.responseText);
            }
        });
    }
});
