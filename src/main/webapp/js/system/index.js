//加载当前日期
function loadDate() {
    var time = new Date();
    var myYear = time.getFullYear();
    var myMonth = time.getMonth() + 1;
    var myDay = time.getDate();
    if (myMonth < 10) {
        myMonth = "0" + myMonth;
    }
    document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "." + myDay;
}

/**
 * 隐藏或者显示侧边栏
 *
 **/
function switchSysBar(flag) {
    var side = $('#side');
    var left_menu_cnt = $('#left_menu_cnt');
    if (flag == true) {	// flag==true
        left_menu_cnt.show(500, 'linear');
        side.css({width: '280px'});
        $('#top_nav').css({width: '77%', left: '304px'});
        $('#main').css({left: '280px'});
    } else {
        if (left_menu_cnt.is(":visible")) {
            left_menu_cnt.hide(10, 'linear');
            side.css({width: '60px'});
            $('#top_nav').css({width: '100%', left: '60px', 'padding-left': '28px'});
            $('#main').css({left: '60px'});
            $("#show_hide_btn").find('img').attr('src', 'images/common/nav_show.png');
        } else {
            left_menu_cnt.show(500, 'linear');
            side.css({width: '280px'});
            $('#top_nav').css({width: '77%', left: '304px', 'padding-left': '0px'});
            $('#main').css({left: '280px'});
            $("#show_hide_btn").find('img').attr('src', 'images/common/nav_hide.png');
        }
    }
}

$(function () {
    loadDate();

    // 显示隐藏侧边栏
    $("#show_hide_btn").click(function () {
        switchSysBar();
    });
});


var setting = {
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onClick: function (event, treeId, treeNode) {
            //修改iframe中src属性的值
            $("#rightMain").prop("src", treeNode.controller);
        }
    },
    async: {
        //开启异步加载
        enable: true,
        //指定访问资源的路径
        url:"/systemMenu/loadMenByParentSn.do",
        //提交给服务端的参数:将当前节点中的哪一个属性进行提交
        //可以 使用原始名称=修改之后的名称  来修改参数的名字
        autoParam:["sn=parentSn"]
    },
};


var zNodes = {
    business: [
        {id: 1, pId: 0, name: "业务模块",sn:"business", open: true, isParent: true}
    ], system: [
        {id: 2, pId: 0, name: "系统模块",sn:"system", open: true, isParent: true}
    ], chart: [
        {id: 3, pId: 0, name: "报表模块",sn:"charts", open: true, isParent: true}
    ]
};

$(document).ready(function () {
    $.fn.zTree.init($(".ztree"), setting, zNodes["business"]);
});


//主页面的效果实现
$(function () {
    $("#TabPage2 li").click(function () {
        //将所有的li元素的选中状态取消
        $("#TabPage2 li").each(function (index, item) {
            //删除每个li的演示
            $(item).removeClass("selected");
            //修改每个li中的img的src值

            $(item).find("img").prop("src", "images/common/" + (index + 1) + ".jpg");
        });
        $(this).addClass("selected");

        //获取当前li元素所在ui中的索引
        var index = $(this).index();
        //修改当前li元素中的img元素的src属性的值
        $(this).find("img").prop("src", "images/common/" + (index + 1) + "_hover.jpg");

        //修改模块的图片
        $("#nav_module").find("img").prop("src", "images/common/module_" + (index + 1) + ".png");

        $("#here_area").html("当前位置：系统&nbsp;>&nbsp;" + $(this).prop("title"));


        //切换菜单
        //获取模块的名称
        var modelName = $(this).data("rootmenu");
        $.fn.zTree.init($(".ztree"), setting, zNodes[modelName]);
    })
});























