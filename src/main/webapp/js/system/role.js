//选项移动
$(function () {
    $("#selectAll").click(function () {
        $(".allPermissions option").appendTo($(".selectedPermissions"));
    });
    $("#select").click(function () {
        $(".allPermissions option:selected").appendTo($(".selectedPermissions"));
    });
    $("#deselectAll").click(function () {
        $(".selectedPermissions option").appendTo($(".allPermissions"));
    });
    $("#deselect").click(function () {
        $(".selectedPermissions option:selected").appendTo($(".allPermissions"));
    });

    //页面加载完成之后,获取到已经分配的权限的编号,使用数组来存放
    var ids = [];
    $.each($(".selectedPermissions option"),function (index,item) {
        ids.push(item.value);
    });

    //遍历左边框中所有的选项,取出权限的id和ids中的id比较
    $.each($(".allPermissions option"),function (index,item) {
        //使用inArray函数实现数据的比较
        if($.inArray(item.value,ids)>=0){
            $(item).remove();
        }
    });
    //系统菜单
    $("#mselectAll").click(function () {
        $(".allSystemMenus option").appendTo($(".selectedSystemMenus"));
    });
    $("#mselect").click(function () {
        $(".allSystemMenus option:selected").appendTo($(".selectedSystemMenus"));
    });
    $("#mdeselectAll").click(function () {
        $(".selectedSystemMenus option").appendTo($(".allSystemMenus"));
    });
    $("#mdeselect").click(function () {
        $(".selectedSystemMenus option:selected").appendTo($(".allSystemMenus"));
    });

    //页面加载完成之后,获取到已经分配的权限的编号,使用数组来存放
    var mids = [];
    $.each($(".selectedSystemMenus option"),function (index,item) {
        mids.push(item.value);
    });

    //遍历左边框中所有的选项,取出权限的id和ids中的id比较
    $.each($(".allSystemMenus option"),function (index,item) {
        //使用inArray函数实现数据的比较
        if($.inArray(item.value,mids)>=0){
            $(item).remove();
        }
    });

    //为表单绑定一个提交事件,在提交的时候,将右边框中的所有的选项选中
    $("#editForm").submit(function () {
        $(".selectedPermissions option").prop("selected",true);
        $(".selectedSystemMenus option").prop("selected",true);
    });

})