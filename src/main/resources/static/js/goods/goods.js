//将id的值传给.goodsid
$(function () {
    $('.btn-xs').on('click', function () {
        $('.goodsid').val($(this).attr('id'))
    })
})

//保存修改后的信息
function saveData() {
    var id = $('.goodsid').val();
    var goodsName = $('#firstname').val();
    var goodsPrice = $('#secondname').val();
    var url = '/goods/update';
    $.ajax({
        url: url,
        type: 'get',
        data: {
            "id": id,
            "goodsName": goodsName,
            "goodsPrice": goodsPrice
        },
        success: function (data) {
            console.log(data);
            if (data == "success") {
                alert("修改成功！");
                window.location.href = "/goods/findAll?currPage=1";
            } else if (data == "error") {
                alert("修改失败,请输入正确的商品价格！");
                window.location.href = "/goods/findAll?currPage=1";
            } else if (data == "fail") {
                alert("修改失败,请输入正确的商品名称！")
                window.location.href = "/goods/findAll?currPage=1";
            }

        }
    })
}

//显示需要编辑的商品的信息
function getData(id) {
    $.ajax({
        url: "/goods/getGoodsById?id=" + id,
        type: "get",
        dataType: "json",
        success: function (goods) {
            $('#firstname').val(goods.goodsName);
            $('#secondname').val(goods.goodsPrice);
        }
    })
}


$("#secondname").on('keyup', function (event) {
    var $amountInput = $(this);
    //响应鼠标事件，允许左右方向键移动
    event = window.event || event;
    if (event.keyCode == 37 | event.keyCode == 39) {
        return;
    }
    //先把非数字的都替换掉，除了数字和.
    $amountInput.val($amountInput.val().replace(/[^\d.]/g, "").//只允许一个小数点
    replace(/^\./g, "").replace(/\.{2,}/g, ".").//只能输入小数点后两位
    replace(".", "$#$").replace(/\./g, "").replace("$#$", ".").replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'));
    //$("#save1").attr("disabled",false);
});
$("#secondname").on('blur', function () {
    var $amountInput = $(this);
    //最后一位是小数点的话，移除
    $amountInput.val(($amountInput.val().replace(/\.$/g, "")));
});