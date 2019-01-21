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
        if (!Regxx.test(password001)) {
            $('#pass1').hide();
            $('#pass1-01').show();
        } else {
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
        } else {
            $('#pass2-01').hide();
            $('#pass2').show();
            check3 = 1;
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
        } else {
            $('#code-1-01').hide();
            $('#code-1').show();
            check5 = 1;
        }
    });
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
            $(location).prop('href', 'loading.html');
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

$('.table tbody tr').mouseover(function () {
    var number = $(this).index();
    $('.table tbody tr td').eq(4 + (number) * 6).find('select:first').attr("id", "changeId");
})
$('.table tbody tr').mouseout(function () {
    var number = $(this).index();
    $('.table tbody tr td').eq(4 + (number) * 6).find('select:first').attr("id", "sel");
});

function saveJudge(form) {
    var d = {};
    var t = form.serializeArray();
    console.log(JSON.stringify(t));
    $.each(t, function () {
        d[this.name] = this.value;
    });
    for (i = 0; i < t.length; i++) {
        if (t[i].value == '') {
            alert("请输入相关信息");
            return false;
        }
    }
    alert("保存成功！");
}

$("#save").click(function () {
    saveJudge($(".form-horizontal.add"));
});
$("#save1").click(function () {
    saveJudge($(".form-horizontal.compile"));
});

function insert() {
    $.ajax({
        url: "/user/register",
        type: "post",
        dataType: "json",
        data: $('#form1').serialize(),
        success: function (result) {
            console.log(result);
            if (result.resultCode == 200) {
                alert("SUCCESS");
            }
            window.location.href = "/user/queryAll";
        },
        error: function (result) {
            window.location.href = "/user/queryAll";
        }
    })
}

function getData(id) {
    $.ajax({
        url: "/user/getUserByID?id=" + id,
        type: "get",
        dataType: "json",
        success: function (user) {
            $('#firstname-1').val(user.username);
            $('#userpassword').val(user.password);
            $('#lastname-1').val(user.telephone);
            $('#lastname-3').val(user.role);
            $('#firstname-id').val(id);
        }
    })
}

function modify() {
    var username = $('#firstname-1').val();
    var password = $('#userpassword').val();
    var telephone = $('#lastname-1').val();
    var role = $('#lastname-3').val();
    var id = $('#firstname-id').val();
    user = {
        "id": id,
        "username": username,
        "password": password,
        "telephone": telephone,
        "role": role
    }
    $.ajax({
        url: "/user/updateByPrimaryKey",
        type: "post",
        dataType: "json",
        data: user,
        success: function (result) {
            console.log(result);
            if (result.resultCode == 200) {
                alert("SUCCESS");
            }
            window.location.href = "/user/queryAll";
        },
        error: function (result) {
            window.location.href = "/user/queryAll";
        }
    })
}

function deleteUser() {
    var username = $('#firstname-1').val();
    var password = $('#userpassword').val();
    var telephone = $('#lastname-1').val();
    var role = $('#lastname-3').val();
    var id = $('#firstname-id').val();
    user = {
        "id": id,
        "username": username,
        "password": password,
        "telephone": telephone,
        "role": role
    }
    $.ajax({
        url: "/user/delete",
        type: "post",
        dataType: "json",
        data: user,
        success: function (result) {
            console.log(result);
            if (result.resultCode == 200) {
                alert("SUCCESS");
            }
            window.location.href = "/user/queryAll";
        },
        error: function (result) {
            window.location.href = "/user/queryAll";
        }
    })
}

function checkPhone1() {
    var reg = /^1[3578][01379]\d{8}|1[34578][01256]\d{8}|(134[012345678]\d{7}|1[34578][012356789]\d{8})$/g;
    $.ajax({
        url: "/user/delete",
        type: "post",
        dataType: "json",
        data: user,
        success: function (result) {
            console.log(result);
            if (result.resultCode == 200) {
                alert("SUCCESS");
            }
            window.location.href = "/user/queryAll";
        },
        error: function (result) {
            window.location.href = "/user/queryAll";
        }
    })
    if (!reg.test($("#telephone").val())) {
        document.getElementById("user_phone").style.display = "block";
        $("#user_phone").html("<font style='color:red' size='1px'>请输入有效的手机号码</font>");
        $("#save").attr("disabled", true);
        return false;
    } else {
        document.getElementById("user_phone").style.display = "none";
        $("#save").attr("disabled", false);
        return true;
    }
}

function checkPhone2() {
    var reg = /^1[3578][01379]\d{8}|1[34578][01256]\d{8}|(134[012345678]\d{7}|1[34578][012356789]\d{8})$/g;
    if (!reg.test($("#lastname-1").val())) {
        document.getElementById("user_phone1").style.display = "block";
        $("#user_phone1").html("<font style='color:red' size='1px'>请输入有效的手机号码</font>");
        $("#save1").attr("disabled", true);
        return false;
    } else {
        document.getElementById("user_phone1").style.display = "none";
        $("#save1").attr("disabled", false);
        return true;
    }
}

function checkName1() {
    var reg = new RegExp("^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9_]){2,20}$");
    if (!reg.test($("#username").val())) {
        document.getElementById("user_name1").style.display = "block";
        $("#user_name1").html("<font style='color:red' size='1px'>用户名格式错误</font>");
        $("#save").attr("disabled", true);
        return false;
    } else {
        document.getElementById("user_name1").style.display = "none";
        $("#save").attr("disabled", false);
        return true;
    }
}

function checkName2() {
    var reg = new RegExp("^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9_]){2,20}$");
    if (!reg.test($("#firstname-1").val())) {
        document.getElementById("user_name2").style.display = "block";
        $("#user_name2").html("<font style='color:red' size='1px'>用户名格式错误</font>");
        $("#save1").attr("disabled", true);
        return false;
    } else {
        document.getElementById("user_name1").style.display = "none";
        $("#save1").attr("disabled", false);
        return true;
    }
}

function checkPass1() {
    var reg = /^\w{6,18}$/;
    if (!reg.test($("#password").val())) {
        document.getElementById("password1").style.display = "block";
        $("#password1").html("<font style='color:red' size='1px'>输入6-18位数字字母组合</font>");
        $("#save").attr("disabled", true);
        return false;
    } else {
        document.getElementById("password1").style.display = "none";
        $("#save").attr("disabled", false);
        return true;
    }
}

function checkPass2() {
    var reg = /^\w{6,18}$/;
    if (!reg.test($("#userpassword").val())) {
        document.getElementById("password2").style.display = "block";
        $("#password2").html("<font style='color:red' size='1px'>输入6-18位数字字母组合</font>");
        $("#save1").attr("disabled", true);
        return false;
    } else {
        document.getElementById("password2").style.display = "none";
        $("#save1").attr("disabled", false);
        return true;
    }
}


//表单重新编号
$(function () {
    var len = $('table tr').length;
    for (var i = 1; i < len; i++) {
        $('table tr:eq(' + i + ') td:first').text(i);
    }
});
