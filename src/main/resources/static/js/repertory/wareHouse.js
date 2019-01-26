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
            //console.log(data);
            if (data == "success") {
                alert("补货成功！");
                window.location.href = "/goodsRepertory/showRealRepertory?currPage=1";
            } else if (data == "error") {
                alert("补货失败，补货数量应为正整数！");
                window.location.href = "/goodsRepertory/showRealRepertory?currPage=1";
            }else if (data=="fail"){
                alert("补货成功，同步失败，请手动同步！");
                window.location.href = "/goodsRepertory/showRealRepertory?currPage=1";
            }

        }
    })
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

//确认同步成功
function confirmSyn() {
    var id = $('.goodsid').val();
    var url = '/goodsRepertory/synchronizeRepertory';
    $.ajax({
        url: url,
        type: 'get',
        data: {
            "id": id
        },
        success: function (data) {
            //console.log(data);
            if (data == "success") {
                alert("同步成功！");
                window.location.href = "/goodsRepertory/showRealRepertory?currPage=1";
            } else if (data == "error") {
                alert("已为最新数据，无需同步！");
                window.location.href = "/goodsRepertory/showRealRepertory?currPage=1";
            }else if (data =="fail"){
                alert("同步失败！");
                window.location.href = "/goodsRepertory/showRealRepertory?currPage=1";
            }

        }
    })

}