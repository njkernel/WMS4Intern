function changeStatus() {
    var flag;
    var receiveButtonIdValue = document.getElementById("receiveButtonId").value;
    var checkBox = document.getElementsByName('checkboxName');
    var checkedArray = new Array();
    var checkedStatusArray = new Array();
    var checkedOutRepoNoArray = new Array();
    for (var i = 0; i < checkBox.length; i++) {
        if (checkBox[i].checked) {
            checkedArray.push(checkBox[i].value);
            var tr = checkBox[i].parentNode.parentNode;
            checkedStatusArray.push(tr.cells[2].innerHTML);
            checkedOutRepoNoArray.push(tr.cells[3].innerHTML);
        }
    }

    if (receiveButtonIdValue == "waittingPackaged") {
        for (var j = 0; j < checkedStatusArray.length; j++) {
            if (checkedStatusArray[j] != "待检货") {
                alert("请选择符合状态的出库单！");
                flag = false;
                break;
            }
            flag = true;
        }
        if (flag == true) {
            $.getJSON("../outRepoOrderController/updateOutRepoOrderStatus", {
                "status": receiveButtonIdValue,
                "outRepoOrderIdArray": JSON.stringify(checkedArray)
            }, function (data) {
                if (data == "202") {
                    alert("wms端出现异常无法获取oms服务，检货失败！");
                } else if (data == "200") {
                    alert("检货成功！")
                    location.href = "../outRepoOrderController/outRepoOrderList";
                } else {
                    alert("oms端出现异常！")
                }
            })
        }
    } else if (receiveButtonIdValue == "waittingShipped") {
        for (var j = 0; j < checkedStatusArray.length; j++) {
            if (checkedStatusArray[j] != "待包装") {
                alert("请选择符合状态的出库单！");
                flag = false;
                break;
            }
            flag = true;
        }
        if (flag == true) {
            $.getJSON("../outRepoOrderController/updateOutRepoOrderStatus", {
                "status": receiveButtonIdValue,
                "outRepoOrderIdArray": JSON.stringify(checkedArray)
            }, function (data) {
                if (data == "202") {
                    alert("wms端出现异常无法获取oms服务，包装失败！");
                } else if (data == "200") {
                    alert("包装成功！")
                    location.href = "../outRepoOrderController/outRepoOrderList";
                } else {
                    alert("oms端出现异常！");
                }
            })
        }
    } else if (receiveButtonIdValue == "haveCanceled") {
        for (var j = 0; j < checkedStatusArray.length; j++) {
            if (checkedStatusArray[j] == "已发货" || checkedStatusArray[j] == "已取消") {
                alert("请选择符合状态的出库单！");
                flag = false;
                break;
            }
            flag = true;
        }
        if (flag == true) {
            $.getJSON("../outRepoOrderController/cancelOutRepoOrder", {"outRepoOrderNoArray": JSON.stringify(checkedOutRepoNoArray)}, function (data) {
                if (data == "202") {
                    alert("wms端出现异常无法获取oms服务，取消失败！");
                } else if (data == "200") {
                    alert("取消成功！")
                    location.href = "../outRepoOrderController/outRepoOrderList";
                } else {
                    alert("oms端出现异常！");
                }
            })
        }
    }

}


$(document).ready(function () {
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
        console.log(id);
        document.getElementById('iframeId').src = "../outRepoOrderController/preUpdateOutRepoOrder?outRepoOrderId=" + id + "&xmx=" + Math.random();
    });
});

function go(currPage) {
    $('#currPage').val(currPage);
    document.frm.submit();
}


function closeIFrame() {
    $('#popup').css("display", "none");
    $('#mask_shadow').css("display", "none");
    var child = document.getElementById("iframeId").contentWindow.document.getElementsByName("id");
    var checkedArray = new Array();
    checkedArray.push(child[0].defaultValue);
    $.getJSON("../outRepoOrderController/updateOutRepoOrderStatus", {
        "status": "haveShipped",
        "outRepoOrderIdArray": JSON.stringify(checkedArray)
    }, function (data) {
        if (data == "202") {
            alert("wms端出现异常无法获取oms服务，发货失败！");
        }else if(data == "200"){
            alert("发货成功！");
            window.location.replace("../outRepoOrderController/toLoad?currPage=" + page.currPage);
            //location.href = "../outRepoOrderController/outRepoOrderList";
        }else {
            alert("oms端出现异常！");
        }
    })

}



