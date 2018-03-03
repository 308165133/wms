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
<form name="editForm" data-url="/supplier/list.do" action="/supplier/saveOrUpdate.do" method="post" id="editForm">
    <input type="hidden" name="id" value="${supplier.id}"/>
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">供应商编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">名称</td>
                    <td class="ui_text_lt">
                        <input name="name" class="ui_input_txt02" value="${supplier.name}"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">电话</td>
                    <td class="ui_text_lt">
                        <input  name="phone"  class="ui_input_txt02" value="${supplier.phone}"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">地址</td>
                    <td class="ui_text_lt">
                        <input  name="address"  class="ui_input_txt02" value="${supplier.address}"/>
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