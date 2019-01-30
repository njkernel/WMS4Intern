//显示需要编辑的快递公司的信息
function getData(id) {
    $.ajax({
        url: "/expressCompany/findById?id=" + id,
        type: "get",
        dataType: "json",
        success: function (expressCompany) {
            $('#firstname2').val(expressCompany.expressCompanyName);
            $('#secondname2').val(expressCompany.contactWay);
            $('#key').val(expressCompany.id);

        }
    })
}

//检查修改公司名称格式
function checkName() {
    var reg1 = new RegExp("[\u4e00-\u9fa5]{4,8}$");
    if (!reg1.test($("#firstname2").val())) {
        document.getElementById("name2").style.display = "block";
        $("#name2").html("<font style='color:red' size='1px'>公司名称格式错误,请输入4-8个汉字字符</font>");
        return false;
    } else {
        document.getElementById("name2").style.display = "none";
        return true;
    }
}

//检查新增公司名称格式
function checkName1() {
    var reg1 = new RegExp("[\u4e00-\u9fa5]{4,8}$");
    if (!reg1.test($("#firstname").val())) {
        document.getElementById("name").style.display = "block";
        $("#name").html("<font style='color:red' size='1px'>公司名称格式错误,请输入4-8个汉字字符</font>");
        return false;
    } else {
        document.getElementById("name").style.display = "none";
        return true;
    }
}

//检查新增公司联系方式格式
function checkPhone1() {
    var reg2 = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(16[0-9]{1})|(18[0-9]{1})|(19[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
    if(!reg2.test($("#secondname").val())){
        document.getElementById("contactWay1").style.display = "block";
        $("#contactWay1").html("<font style='color:red' size='1px'>请输入有效的手机号码！</font>");
        return false;
    }else {
        document.getElementById("contactWay1").style.display = "none";
        return true;
    }
}

//检查修改公司联系方式格式
function checkPhone() {
    var reg2 = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(16[0-9]{1})|(18[0-9]{1})|(19[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
    if(!reg2.test($("#secondname2").val())){
        document.getElementById("contactWay2").style.display = "block";
        $("#contactWay2").html("<font style='color:red' size='1px'>请输入有效的手机号码！</font>");
        return false;
    }else {
        document.getElementById("contactWay2").style.display = "none";
        return true;
    }
}

//修改公司信息按钮验证
function checkSave() {
    var check = checkName() &&  checkPhone();
    if (check) {
        $("#save").attr("disabled", false);
    } else {
        $("#save").attr("disabled", true);
    }
}

function checkSave1() {
    var check = checkName1() &&  checkPhone1();
    if (check) {
        $("#save1").attr("disabled", false);
    } else {
        $("#save1").attr("disabled", true);
    }
}

//点击取消后清空表单
function clean() {
    document.getElementById("name").style.display = "none";
    document.getElementById("name2").style.display = "none";
    document.getElementById("contactWay2").style.display = "none";
    document.getElementById("contactWay1").style.display = "none";
}


function add() {
    var expressCompanyName = $('#firstname').val();
    var contactWay = $('#secondname').val();
    var url = '/expressCompany/Add';
    $.ajax({
        url: url,
        type: 'get',
        dataType: 'json',
        data:{
            "expressCompanyName": expressCompanyName,
            "contactWay": contactWay
        },
        success:function (data) {
            if(data==1){
                alert("该公司已经存在！");
            }else if (data==2) {
                alert("添加公司成功！");
                window.location.reload()
            }
        }
    })
}


function update() {
    var id = $('#key').val();
    var expressCompanyName = $('#firstname2').val();
    var contactWay = $('#secondname2').val();
    var url = '/expressCompany/Update';
    $.ajax({
        url: url,
        type: 'get',
        dataType: 'json',
        data:{
            "id" : id,
            "expressCompanyName": expressCompanyName,
            "contactWay": contactWay
        },
        success:function (data) {
            if(data==1){
                alert("修改公司信息成功！");
                window.location.reload()
            }else if(data==2){
                alert("改公司已经存在！");
            }
        }
    })
}

function go(currPage) {
    $('#currPage').val(currPage)
    document.frm.submit();
}