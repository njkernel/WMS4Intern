$('.table tbody tr td input').click(function () {
    $(this).addClass('checkbox-style');
})


$('.table tbody tr td input').click(function () {
    $(this).addClass('checkbox-style');
});


$(function () {
    $('#popup').popup();
    $("body").css("overflow-y", "hidden");
});


$(function () {
    $('.ck1').on("click", function () {
        console.log($(this).attr('name'));
        document.getElementById('iframeId').src = "/exception/toDetail?id=" + $(this).attr('name');
    });
});

function go(currPage) {
    $('#currPage').val(currPage)
    document.frm.submit();
}
