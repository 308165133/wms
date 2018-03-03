<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/WEB-INF/views/commons/common_head.html" %>
    <title>叩丁狼教育PSS（演示版）-角色管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script type="text/javascript">

    </script>
</head>
<body>
<form id="searchForm" action="/role/list.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">

                    <div id="box_bottom">
                        <input type="button" value="新增" class="ui_input_btn01 btn_input" data-url="/role/input.do"/>
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
                        <th>名称</th>
                        <th>编码</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <c:forEach items="${result.listData}" var="role">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb"/></td>
                            <td>${role.id}</td>
                            <td>${role.name}</td>
                            <td>${role.sn}</td>
                            <td>
                                <a href="/role/input.do?id=${role.id}">编辑</a>
                                <a href="javascript:;" class="btn_delete" data-url="/role/delete.do?id=${role.id}">删除</a>
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
