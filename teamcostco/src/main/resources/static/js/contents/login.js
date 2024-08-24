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
});
