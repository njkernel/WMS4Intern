function changeStatus() {
    var flag;
    var receiveButtonIdValue = document.getElementById("receiveButtonId").value;
    var checkBox = document.getElementsByName('checkboxName');
    var checkedArray = new Array();
    var checkedStatusArray = new Array();
    var checkedOutRepoNoArray = new Array();
    var checkedExpressId;
    var checkedExpressCompany;
    for (var i = 0; i < checkBox.length; i++) {
        if (checkBox[i].checked) {
            checkedArray.push(checkBox[i].value);
            var tr = checkBox[i].parentNode.parentNode;
            checkedStatusArray.push(tr.cells[2].innerHTML);
            checkedOutRepoNoArray.push(tr.cells[3].innerHTML);
        }
    }
    if(checkedArray.length==1){
        checkedExpressCompany=tr.cells[6].innerHTML;
        checkedExpressId=tr.cells[7].innerHTML;
    }

    if (receiveButtonIdValue == "waittingPackaged") {
        for (var j = 0; j < checkedStatusArray.length; j++) {
            if (checkedStatusArray[j] != "待检货") {
                alert("请选择符合状态的出库单！");
                flag=false;
                break;
            }
            flag=true;
        }
        if(flag==true){
            $.getJSON("../outRepoOrderController/updateOutRepoOrderStatus", {
                "status": receiveButtonIdValue,
                "outRepoOrderIdArray": JSON.stringify(checkedArray)
            }, function (data) {
                if (data == "20190107") {
                    alert("操作失败！");
                } else {
                    location.href = "../outRepoOrderController/outRepoOrderList";
                }
            })
        }
    } else if (receiveButtonIdValue == "waittingShipped") {
        for (var j = 0; j < checkedStatusArray.length; j++) {
            if (checkedStatusArray[j] != "待包装") {
                alert("请选择符合状态的出库单！");
                flag=false;
                break;
            }
            flag=true;
        }
        if(flag==true){
            $.getJSON("../outRepoOrderController/updateOutRepoOrderStatus", {
                "status": receiveButtonIdValue,
                "outRepoOrderIdArray": JSON.stringify(checkedArray)
            }, function (data) {
                if (data == "20190107") {
                    alert("操作失败！");
                } else {
                    location.href = "../outRepoOrderController/outRepoOrderList";
                }
            })
        }
    } else if (receiveButtonIdValue == "haveShipped") {
        console.log(checkedExpressId+"000000"+checkedExpressCompany);
        if(checkedArray.length!=1){
            alert("请选择一条出库单进行操作！");
        }else if(checkedStatusArray[0] != "待发货"){
            alert("请选择符合状态的出库单！");
        }else if(checkedExpressId =="" || checkedExpressCompany == ""){
            alert("请将快递信息填写完整！")
        }else{
            $.getJSON("../outRepoOrderController/updateOutRepoOrderStatus", {
                "status": receiveButtonIdValue,
                "outRepoOrderIdArray": JSON.stringify(checkedArray)
            }, function (data) {
                if (data == "20190107") {
                    alert("操作失败！");
                } else {
                    location.href = "../outRepoOrderController/outRepoOrderList";
                }
            })
        }
    } else if (receiveButtonIdValue == "haveCanceled") {
        for (var j = 0; j < checkedStatusArray.length; j++) {
            if (checkedStatusArray[j] == "已发货"||checkedStatusArray[j] == "已取消") {
                alert("请选择符合状态的出库单！");
                flag=false;
                break;
            }
            flag=true;
        }
        if(flag==true){
            $.getJSON("../outRepoOrderController/cancelOutRepoOrder", {"outRepoOrderNoArray": JSON.stringify(checkedOutRepoNoArray)}, function (data) {
                if (data == "20190107") {
                    alert("操作失败！");
                } else {
                    location.href = "../outRepoOrderController/outRepoOrderList";
                }
            })
        }
    }

}


$(document).ready(function(){
    var saveStatusFromBack = document.getElementById("saveStatusFromBack").value;
    $("#selectStatus").val(saveStatusFromBack);
});


$(function () {
    $('.action').on("click", function () {
        $('#demoModal .modal-header .modal-title').empty().text($(this).text() + "操作");
        $('#demoModal .modal-body').empty().text("确认进行" + $(this).text() + "操作？");
        $('#demoModal .modal-footer #receiveButtonId').attr("value", $(this).attr('id'));
        $('#demoModal').modal();
    });
});
/*把取消按钮和别的操作按钮分开*/
$(function () {
    $('.cancel').on("click", function () {
        $('#demoModal .modal-header .modal-title').empty().text($(this).text() + "操作");
        $('#demoModal .modal-body').empty().text("确认进行" + $(this).text() + "操作？");
        $('#demoModal .modal-footer #receiveButtonId').attr("value", $(this).attr('id'));
        $('#demoModal').modal();
    });
});


//查询出库单
$(function () {
    $('.btn-default').on("click", function () {
        var id = $(this).attr('id');
        document.getElementById('iframeId').src = "../outRepoOrderController/toOutRepoDetail?outRepoOrderId=" + id + "&xmx=" + Math.random();
    });
});

//修改出库单
$(function () {
    $('.btn-danger').on("click", function () {
        var id = $(this).attr('id');
        document.getElementById('iframeId').src = "../outRepoOrderController/preUpdateOutRepoOrder?outRepoOrderId=" + id + "&xmx=" + Math.random();
    });
});

function go(currPage) {
    $('#currPage').val(currPage);
    document.frm.submit();
}


