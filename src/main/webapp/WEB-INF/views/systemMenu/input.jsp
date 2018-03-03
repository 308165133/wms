<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <%@include file="/WEB-INF/views/commons/common_head.html"%>
</head>
<body>
<form name="editForm"  data-url="/systemMenu/list.do?parentId=${parentId}" action="/systemMenu/saveOrUpdate.do" method="post" id="editForm">
    <input type="hidden" name="id" value="${systemMenu.id}"/>
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">系统菜单编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">父级菜单</td>
                    <td class="ui_text_lt">
                        <input class="ui_input_txt02" value="${parentName}" disabled="disabled" style="background-color: pink;"/>
                        <input name="parent.id" type="hidden" value="${parentId}"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">菜单名称</td>
                    <td class="ui_text_lt">
                        <input name="name" class="ui_input_txt02" value="${systemMenu.name}"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">菜单编码</td>
                    <td class="ui_text_lt">
                        <input  name="sn"  class="ui_input_txt02" value="${systemMenu.sn}"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">URL</td>
                    <td class="ui_text_lt">
                        <input  name="url"  class="ui_input_txt02" value="${systemMenu.url}"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</form>
</body>
</html>