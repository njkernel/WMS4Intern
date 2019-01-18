//确认补货成功
function checkConfirmed() {
    var RepertoryNum = $('#firstname').val();
    var id = $('.goodsid').val();
    var url = '/goodsRepertory/replenishRepertory';
    $.ajax({
        url: url,
        type: 'get',
        data: {
            "id": id,
            "num": RepertoryNum
        },
        success: function (data) {
            console.log(data);
            if (data == "success") {
                alert("补货成功！");
                window.location.href = "/goodsRepertory/showRealRepertory?currPage=1";
            } else if (data == "error") {
                alert("补货失败，补货数量不能为空！");
                window.location.href = "/goodsRepertory/showRealRepertory?currPage=1";
            }

        }
    })
}

//分页跳转
function go(currPage) {
    $('#currPage').val(currPage)
    document.frm.submit();
}

$('.table tbody tr td input').click(function () {
    $(this).addClass('checkbox-style');
})
// 弹窗验证
// 传入表单对象


//将id的值传给.goodsid
$(function () {
    $('.btn-xs').on('click', function () {
        $('.goodsid').val($(this).attr('id'))
    })
})
