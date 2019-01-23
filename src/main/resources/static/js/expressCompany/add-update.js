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
            }else if(data==3){
                alert("手机号格式错误！")
            }else if(data==4){
                alert("公司名称格式错误！")
            }else if(data==5){
                alert("填写信息不完整！")
            }
        }
    })
}


function update() {
    var newName = $('#firstname2').val();
    var expressCompanyName = $('#firstname1').val();
    var contactWay = $('#secondname1').val();
    var url = '/expressCompany/Update';
    $.ajax({
        url: url,
        type: 'get',
        dataType: 'json',
        data:{
            "newName" : newName,
            "expressCompanyName": expressCompanyName,
            "contactWay": contactWay
        },
        success:function (data) {
            if(data==2){
                alert("修改公司信息成功！");
                window.location.reload()
            }else if(data==1){
                alert("该公司已经存在！");
            }else if(data==3){
                alert("手机号格式错误！")
            }else if(data==4){
                alert("公司名称格式错误！")
            }else if(data==5){
                alert("填写信息不完整！")
            }else if(data==6){
                alert("原公司不存在，不能进行修改!")
            }
        }
    })
}

function go(currPage) {
    $('#currPage').val(currPage)
    document.frm.submit();
}