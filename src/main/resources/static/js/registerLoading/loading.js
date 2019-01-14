
$(document).ready(function () {
  var account1, password;
  var i = 1;
  $('#email-001').on('input propertychange', function () {
    account1 = $('#email-001').val();
  });

  $('#password-001').on('input propertychange', function () {
    password = $('#password-001').val();
  });

  // 从缓存区获取账号和密码
  var hoster1 = window.localStorage.getItem("account");
  var password02 = window.localStorage.getItem("password");
  $('#loading').click(function () {
    // 判断账号密码是否错误
    if (account1 == hoster1 && password == password02) {
      $('#email-001').val("");
      $(location).prop('href', 'success.html');
    } else {
      alert("登陆失败！请检查您的账户和密码");
    }
  });

  var i = 3;
  $("#left").click(function () {
    i--;
    if (i < 1) {
      i = 3;
    }
    showPicture(i)
  });
  $("#right").click(function () {
    i++;
    if (i > 3) {
      i = 1;
    }
    showPicture(i)
  });

  function showPicture(a) {
    switch (a) {
      case 1: $('.main').css({ "background": "url(../../assets/images/registerLoading/image1.png) no-repeat","background-size": "100%" });
        // $('.main-inner').css({ "background": "url(image1.jpg) no-repeat" });
        break;
      case 2: $('.main').css({ "background": "url(../../assets/images/registerLoading/image2.png) no-repeat" ,"background-size": "100%"});
        // $('.main-inner').css({ "background": "url(image2.jpg) no-repeat" });
        break;
      case 3: $('.main').css({ "background": "url(../../assets/images/registerLoading/image3.png) no-repeat" ,"background-size": "100%"});
        // $('.main-inner').css({ "background": "url(image3.jpg) no-repeat" });
        break;
    }
  }
});

