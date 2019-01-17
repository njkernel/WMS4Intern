//分页
function go(currPage) {
    $('#currPage').val(currPage)
    document.frm.submit();
}
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