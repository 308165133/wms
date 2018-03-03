<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript">
        $(function () {
            $(":input").prop("readonly",true);
        })
    </script>
</head>
<body>
<form name="editForm" data-url="/stockIncomeBill/list.do" action="/stockIncomeBill/saveOrUpdate.do" method="post" id="editForm">
    <input type="hidden" name="id" value="${stockIncomeBill.id}"/>
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">采购入库单查看</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">采购入库单编码</td>
                    <td class="ui_text_lt">
                        <input  class="ui_input_txt02" value="${stockIncomeBill.sn}"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">仓库</td>
                    <td class="ui_text_lt">
                        <input  class="ui_input_txt02" value="${stockIncomeBill.depot.name}"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">业务时间</td>
                    <td class="ui_text_lt">
                        <fmt:formatDate value="${stockIncomeBill.vdate}" pattern="yyyy-MM-dd" var="vdate"/>
                        <input name="vdate" class="ui_input_txt02 Wdate" value="${vdate}" />
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">单据明细</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <table class="edit_table" cellspacing="0" cellpadding="0" border="0" style="width: auto">
                            <thead>
                            <tr>
                                <th width="10"></th>
                                <th width="200">货品</th>
                                <th width="120">品牌</th>
                                <th width="80">价格</th>
                                <th width="80">数量</th>
                                <th width="80">金额小计</th>
                                <th width="150">备注</th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">

                                <c:forEach items="${stockIncomeBill.items}" var="bill">
                                    <tr>
                                        <td></td>
                                        <td>
                                            <input disabled="true" readonly="true" class="ui_input_txt02" tag="name" value="${bill.product.name}"/>
                                            <input type="hidden" name="items[0].product.id" tag="pid" value="${bill.product.id}"/>
                                        </td>
                                        <td><span tag="brand">${bill.product.brandName}</span></td>
                                        <td><input tag="costPrice" name="items[0].costPrice"
                                                   class="ui_input_txt00" value="${bill.costPrice}"/></td>
                                        <td><input tag="number" name="items[0].number"
                                                   class="ui_input_txt00" value="${bill.number}"/></td>
                                        <td><span tag="amount">${bill.amount}</span></td>
                                        <td><input tag="remark" name="items[0].remark"
                                                   class="ui_input_txt02" value="${bill.remark}"/></td>

                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="button" value="返回列表" onclick="window.history.back();" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</form>
</body>
</html>