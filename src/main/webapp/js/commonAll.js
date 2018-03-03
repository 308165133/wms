//禁用数组的序列化功能,不在数组类型的参数后面加[]
jQuery.ajaxSettings.traditional = true;
/** table鼠标悬停换色* */
$(function () {
    // 如果鼠标移到行上时，执行函数
    $(".table tr").mouseover(function () {
        $(this).css({background: "#CDDAEB"});
        $(this).children('td').each(function (index, ele) {
            $(ele).css({color: "#1D1E21"});
        });
    }).mouseout(function () {
        $(this).css({background: "#FFF"});
        $(this).children('td').each(function (index, ele) {
            $(ele).css({color: "#909090"});
        });
    });
});


$(function () {
    $(".btn_input").click(function () {
        window.location.href = $(this).data("url");
    });

    //为所有的翻页按钮绑定点击事件
    $(".btn_page").click(function () {
        //this,表单当前点击的元素的DOM对象
        var currentPage = $(this).data("page") || 1;
        //为当前页输入框设值
        $(":input[name='currentPage']").val(currentPage);
        //提交表单
        $("#searchForm").submit();
    });

    //页面大小的设值
    $(":input[name='pageSize']").change(function () {
        $(":input[name='currentPage']").val(1);
        //提交表单
        $("#searchForm").submit();
    });

    //pageSize的回显
    $(":input[name='pageSize'] option").each(function (index, item) {
        var pageSize = $(":input[name='pageSize']").data("pagesize");
        if (pageSize == item.innerHTML) {
            $(item).prop("selected", true);
        }
    })
});

//删除提示
$(function () {
    $(".btn_delete").click(function () {
        var url = $(this).data("url");
        //使用artDialog给用户一个提示信息
        $.dialog({
            title: "温馨提示",
            content: "您确定要删除该数据吗?",
            icon: "face-smile",
            lock: true,
            ok: function () {
                //点击确认发送请求执行删除操作
                $.get(url, function (data) {
                    if (data.success) {
                        $.dialog({
                            title: "温馨提示",
                            content: "删除成功",
                            icon: "face-smile",
                            lock: true,
                            ok: function () {
                                window.location.reload();
                            }
                        });
                    } else {
                        $.dialog({
                            title: "温馨提示",
                            content: data.message,
                            icon: "face-smile",
                            lock: true
                        });
                    }
                });
            },
            cancel: true
        })
    });
});
//批量删除
$(function () {
    //每次将所有的复选框的选中状态取消
    $(".acb,#all").prop("checked", false);
    $(".btn_batchDelete").click(function () {
        //如果用户没有选择要删除的数据,给用户一个提示
        if ($(".acb:checked").size() == 0) {
            $.dialog({
                title: "温馨提示",
                content: "请先选择要删除的数据!",
                icon: "face-smile",
                lock: true,
                ok: true
            });
            return;
        }
        //获取到访问的路径
        var url = $(this).data("url");
        //获取到删除数据的id
        var ids = [];
        $(".acb:checked").each(function (index, item) {
            ids.push($(item).data("id"));
        })
        //提示用户是否删除
        $.dialog({
            title: "温馨提示",
            content: "您确定要删除选中的数据吗?",
            icon: "face-smile",
            lock: true,
            ok: function () {
                //点击确认发送请求执行删除操作
                $.get(url, {ids: ids}, function (data) {
                    if (data.success) {
                        $.dialog({
                            title: "温馨提示",
                            content: "删除成功",
                            icon: "face-smile",
                            lock: true,
                            ok: function () {
                                window.location.reload();
                            }
                        });
                    }
                });
            },
            cancel: true
        })
    });

    //复选框的全选和全不选
    $("#all").change(function () {
        $(".acb").prop("checked", this.checked);
    });


    //将请求方式修改为ajax的请求
    $("#editForm").ajaxForm(function (data) {
        if (data.success) {
            $.dialog({
                title: "温馨提示",
                content: "保存成功",
                icon: "face-smile",
                lock: true,
                ok: function () {
                    //成功之后跳转到list页面
                    window.location.href = $("#editForm").data("url");
                }
            });
        }
    });

});

//审核提示
$(function () {
    $(".btn_audit").click(function () {
        var url = $(this).data("url");
        //使用artDialog给用户一个提示信息
        $.dialog({
            title: "温馨提示",
            content: "您确定要审核该数据吗?",
            icon: "face-smile",
            lock: true,
            ok: function () {
                //点击确认发送请求执行删除操作
                $.get(url, function (data) {
                    if (data.success) {
                        $.dialog({
                            title: "温馨提示",
                            content: "审核成功",
                            icon: "face-smile",
                            lock: true,
                            ok: function () {
                                window.location.reload();
                            }
                        });
                    } else {
                        $.dialog({
                            title: "温馨提示",
                            content: data.message,
                            icon: "face-smile",
                            lock: true
                        });
                    }
                });
            },
            cancel: true
        })
    });
});
