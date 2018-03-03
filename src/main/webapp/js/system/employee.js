//选项移动
$(function () {
    $("#selectAll").click(function () {
        $(".allRoles option").appendTo($(".selectedRoles"));
    });
    $("#select").click(function () {
        $(".allRoles option:selected").appendTo($(".selectedRoles"));
    });
    $("#deselectAll").click(function () {
        $(".selectedRoles option").appendTo($(".allRoles"));
    });
    $("#deselect").click(function () {
        $(".selectedRoles option:selected").appendTo($(".allRoles"));
    });

    //页面加载完成之后,获取到已经分配的权限的编号,使用数组来存放
    var ids = [];
    $.each($(".selectedRoles option"), function (index, item) {
        ids.push(item.value);
    });

    //遍历左边框中所有的选项,取出权限的id和ids中的id比较
    $.each($(".allRoles option"), function (index, item) {
        //使用inArray函数实现数据的比较
        if ($.inArray(item.value, ids) >= 0) {
            $(item).remove();
        }
    });

    //为表单绑定一个提交事件,在提交的时候,将右边框中的所有的选项选中
    $("#editForm").submit(function () {
        $(".selectedRoles option").prop("selected", true);
    });
});

/*数据校验*/
$(function () {
    $("#editForm").validate({
        /*校验的规则*/
        rules: {
            name: {
                required: true,
                minlength: 5
            },
            password: {
                required: true,
                minlength: 5
            },
            repassword: {
                equalTo: "#password"
            }
        },
        /*错误提示信息*/
        messages: {
            name: {
                required: "用户名不能为空",
                minlength: "至少5位"
            },
            password: {
                required: "密码不能为空",
                minlength: "至少5位"
            },
            repassword: {
                equalTo: "两次密码不一致"
            }
        }
    });
});
