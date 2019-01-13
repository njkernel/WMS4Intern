
$(document).ready(function () {
    var account, password001, password002, phonenumber, code001, check1, check2, check3, check4, check5, check6, check7;
    //   监听邮箱账号变化
    $('#email1').on('input propertychange', function () {
        account = $('#email1').val();
        if ($('#email1').val() === "") {
            $('#address-01').hide();
            $('#paragraph1-show').show();
        }
    });
    var Regx = /^[a-zA-Z]\w{5,17}$/;
    //  离开焦点后发生变化
    $('#email1').blur(function () {
        //   首位必须是字母
        if (!Regx.test(account)) {
            $('#paragraph1-show').hide();
            $('#address-01').show();

        } else {
            $('#paragraph1-show').show();
            $('#address-01').hide();
            check1 = 1;
        }
    });
    //   监听密码变化
    $('#password1').on('input propertychange', function () {
        password001 = $('#password1').val();
    });
    //  离开焦点后发生变化
         var Regxx = /^\w{6,18}$/;
    $('#password1').blur(function () {
        //    密码长度的限制     
        if (!Regxx.test( password001)) {
            $('#pass1').hide();
            $('#pass1-01').show();
        }
        else {
            $('#pass1-01').hide();
            $('#pass1').show();
            check2 = 1;
        }
    });
    //   监听再次输入密码变化
    $('#password2').on('input propertychange', function () {
        password002 = $('#password2').val();
    });
    //  离开焦点后发生变化
    // 确认密码是否和密码相同
    $('#password2').blur(function () {
        if (password002 != password001) {
            $('#pass2').hide();
            $('#pass2-01').show();
        }
        else {
            $('#pass2-01').hide();
            $('#pass2').show();
            check3 = 1;
        }
    });
    //   监听电话号码变化
    $('#phone1').on('input propertychange', function () {
        phonenumber = $('#phone1').val();
    });
    //  离开焦点后发生变化  
    //手机号码正则表达式
    var reg = /^1[3578][01379]\d{8}|1[34578][01256]\d{8}|(134[012345678]\d{7}|1[34578][012356789]\d{8})$/g;
    $('#phone1').blur(function () {
        //   手机号码的要求
        if (!reg.test(phonenumber)) {
            $('#phone-1').hide();
            $('#phone-1-01').show();
        }
        else {
            $('#phone-1-01').hide();
            $('#phone-1').show();
            check4 = 1;
        }
    });
    //   监听验证码变化
    $('#code1').on('input propertychange', function () {
        code001 = $('#code1').val();
    });
    //  离开焦点后发生变化
    $('#code1').blur(function () {
        // 获取缓存区的验证码,并比较
        if (code001 != window.localStorage.getItem("code01")) {
            $('#code-1').hide();
            $('#code-1-01').show();
        }
        else {
            $('#code-1-01').hide();
            $('#code-1').show();
            check5 = 1;
        }
    });
    //获取动态的手机验证码
    // var code1;
    // $('.phone-code').click(function () {
    //     code1 = "";
    //     var codeLength = 5;
    //     var checkCode = document.getElementById("checkCode");
    //     var random = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
    //         'W', 'X', 'Y', 'Z');
    //     for (var i = 0; i < codeLength; i++) {
    //         var charIndex = Math.floor(Math.random() * 36);
    //         code1 += random[charIndex];
    //     }
    //     alert(code1)
    // });
    //   监听手机验证码变化
    // $('#messager1').on('input propertychange', function () {
    //     code002 = $('#messager1').val();
    // });
    //  离开焦点后发生变化
    // 检验手机的验证码
    // $('#messager1').blur(function () {
    //     if (code002 !== code1) {
    //         $('#messager-01').hide();
    //         $('#messager-01-01').show();
    //     }
    //     else {
    //         $('#messager-01-01').hide();
    //         $('#messager-01').show();
    //         check6 = 1;
    //     }
    // });
    //    同意按钮的检验
    $('#checkOne').click(function () {
        check7 = 1;
    });
    var hoster, password01;
    $('.last').click(function () {
        //  判断是否全部正确
        if (check1 == 1 && check2 == 1 && check3 == 1 && check4 == 1 && check5 == 1 && check7 == 1) {
            alert("注册成功！");
            //    获取本地的登陆页面
            $(location).prop('href', 'registerOrder.html');
            //    将值存到缓存区
            window.localStorage.setItem("account", account);
            window.localStorage.setItem("password", password001);
            //    清空所有信息
            $('#email1').val("");
            $('#password1').val("");
            $('#password2').val("");
            $('#phone1').val("");
            $('#code1').val("");
            $('#checkOne').prop('checked', false);
        } else {
            alert("有错误！！！");
        }
    });
});

