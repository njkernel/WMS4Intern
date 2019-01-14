function add() {
    console.log("添加")
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
            if(data == "1"){
                alert("该公司已经存在！");
            }


        }
    })
}


function update() {
    console.log("添加")
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
            alert("修改公司信息成功！");
        }
    })
}