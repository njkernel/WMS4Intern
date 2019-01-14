jQuery(document).ready(function () {
    // 弹窗验证逻辑
    $(".list_dt").on("click", function () {
        // $('.list_li').removeClass("selected");
        $('.list_dd').stop();
        $(this).siblings("dt").removeAttr("id");
        if ($(this).attr("id") == "open") {
            $(this).removeAttr("id").siblings("dd").slideUp(300);
        } else {
            $(this).attr("id", "open").next().slideDown(300).siblings("dd").slideUp(300);
        }
    });
    var i=0;     //收缩
    $('.shrink').click(function(){
        // $('.left').toggle({},$('.test').toggle()); 
        $('.left').toggle('normal'); 
        if (i==0){ 
            $(".right").animate({marginLeft:"0px"});
            $(this).find('i').addClass("fa-arrow-right");
            i=1;
        }
        else{
            $(this).find('i').removeClass("fa-arrow-right");   
            $(".right").animate({marginLeft:"256px"});
            i=0;
        }
    });
    $(function () {
        //计算内容区域高度
        var calcHeight = function () {
            // // 浏览器的高度
            var browserHeight = $(window).innerHeight();
            // var topHeight = $('#mainFrameHeadBar').outerHeight(true);
            // var tabMarginTop = parseInt($('#mainFrameTabs').css('margin-top'));//获取间距
            // var heardHeight = parseInt($('div.tab-content').css('margin-top'));
            // var tabHeadHeight = $('ul.nav-tabs',$('#mainFrameTabs')).outerHeight(true) + tabMarginTop;
            // var contentMarginTop = parseInt($('div.tab-content',$('#mainFrameTabs')).css('margin-top'));//获取内容区间距
            // var contentHeight = browserHeight - topHeight - tabHeadHeight - contentMarginTop;
            var contentHeight = browserHeight - 64;
            $('div.tab-content', $('#mainFrameTabs')).height(contentHeight);
        };
        //菜单点击
        $('a', $('.list_dd_ul')).on('click', function (e) {
            e.stopPropagation();
            $('.list_dd_ul a').css({'background':'','color':''});
            $(this).css({'background':'#4285f4','color':'#fff'});
            var li = $(this).closest('li'),
                menuId = $(li).attr('mid'),
                url = $(li).attr('funurl'),
                title = $(this).text();
            $('#mainFrameTabs').bTabsAdd(menuId, title, url);
            //计算Tab可用区域高度
            calcHeight();
        });
        //初始化
        // 默认第一个被点击
        $("#default").trigger("click");
        $('#mainFrameTabs').bTabs();
    });
    
});