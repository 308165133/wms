<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/WEB-INF/views/commons/common_head.html"%>
    <title>叩丁狼教育PSS（演示版）-权限管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $(".btn_loadPermission").click(function () {
                $.dialog({
                    title:"温馨提示",
                    content:"加载权限比较耗时,您确定要加载吗?",
                    icon:"face-smile",
                    ok:function () {
                        var dialog = $.dialog({
                            title:"温馨提示",
                            icon:"face-smile",
                        });
                        $.get("/permission/ .do",function (data) {
                            if(data.success){
                                dialog.content("加载成功");
                                dialog.button({
                                    name:"朕知道了",
                                    callback:function () {
                                        window.location.reload();
                                    }
                                })
                            }
                        })
                    },
                    cancel:true,
                    lock:true
                });
            });
        })
    </script>
</head>
<body>
<form id="searchForm" action="/permission/list.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_bottom">
                        <input type="button" value="加载权限" class="ui_input_btn01 btn_loadPermission"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>编号</th>
                        <th>权限表达式</th>
                        <th>权限名称</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <c:forEach items="${result.listData}" var="permission">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb"/></td>
                            <td>${permission.id}</td>
                            <td>${permission.expression}</td>
                            <td>${permission.name}</td>
                            <td>
                                <a href="javascript:;" class="btn_delete" data-url="/permission/delete.do?id=${permission.id}">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <jsp:include page="/WEB-INF/views/commons/common_page.jsp"/>
        </div>
    </div>
</form>
</body>
</html>
