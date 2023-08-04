$(document).ready(function(){

    let passMatchError = $('#passMatchError');
    let registrationButton = $("#registrationButton");
    let loginError = $('#loginError');
    let loginOk = $('#loginOk');
    let validLogin = false;
    let validPassword = false;

    function passMatch() {
        let pass1 = $('#check_password').val();
        let pass2 = $('#check_password2').val();
        if (pass1 != "") {
            if (pass1 == pass2) {
                passMatchError.css('display', 'none');
                passMatchTriger (true);
            }else{
                passMatchError.text('Пароли не совпадают');
                passMatchError.css('display', 'block');
                registrationButton.attr('disabled', 'disabled');
                passMatchTriger (false);
            }
        }else{
            passMatchError.text('Введите пароль');
            passMatchError.css('display', 'block');
            registrationButton.attr('disabled', 'disabled');
            passMatchTriger (false);
        }
    }

    function tryLogin() {
        var login = $("#check_login").val();
        if (login != ""){
            if (login.length >=4){
               loginError.css('display', 'none');
               loginOk.css('display', 'none');
               $.ajax({
                    type: 'POST',
                    url:"/login/test",
                    data: {login:login},
                    success: function (data) {
                        if(data != "") {
                            loginError.text('Данный логин занят');
                            loginError.css('display', 'block');
                            registrationButton.attr('disabled', 'disabled');
                            validLoginTriger (false);
                        }else{
                            loginOk.text('Вы можете использовать этот логин');
                            loginOk.css('display', 'block');
                            validLoginTriger (true);
                        }
                    ;},
                    error: function() {
                        alert("error");
                    }
                });
            }else{
                loginOk.css('display', 'none');
                loginError.text('Минимальная длина логина 4 символа');
                loginError.css('display', 'block');
                registrationButton.attr('disabled', 'disabled');
                validLoginTriger (false);
            }
        }else{
            loginOk.css('display', 'none');
            loginError.text('Введите логин');
            loginError.css('display', 'block');
            registrationButton.attr('disabled', 'disabled');
            validLoginTriger (false);
        }
    }

    function validLoginTriger (bool) {
        validLogin = bool;
    }

    function passMatchTriger (bool) {
        validPassword = bool;
    }

    function activateButton () {
        if (validLogin && validPassword){
            registrationButton.removeAttr('disabled', 'disabled');
        }
    }

    function validForm () {
        tryLogin();
        passMatch();
    }

    $('#check_password, #check_password2').on('focus', e => $(e.target).on('keypress', function () {
        setTimeout(validForm, 10);
        setTimeout(activateButton,3000);
    })).on('blur', e => $(e.target).off('keypress'));

    $("#check_login").blur(function () {
        setTimeout(validForm, 10);
        setTimeout(activateButton,1000);
    });
});









