<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/WEB-INF/views/commons/common_head.html" %>
    <script type="text/javascript" src="/js/jquery/plugins/My97DatePicker/WdatePicker.js"></script>
    <title>叩丁狼教育PSS（演示版）-订货报表管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<form id="searchForm" action="/charts/orderCharts.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        <fmt:formatDate value="${qo.beginDate}" pattern="yyyy-MM-dd" var="beginDate"/>
                        <fmt:formatDate value="${qo.endDate}" pattern="yyyy-MM-dd" var="endDate"/>
                        业务时间
                        <input name="beginDate" onclick="WdatePicker();" readonly="readonly" value="${beginDate}"
                               class="ui_input_txt02 Wdate beginDate"/>
                        ~
                        <input name="endDate" onclick="WdatePicker();" readonly="readonly" value="${endDate}" class="ui_input_txt02 Wdate endDate"/>
                        供应商
                        <select name="supplierId" class="ui_select01">
                            <option value="-1">所有供应商</option>

                            <c:forEach items="${suppliers}" var="supplier">
                                <option value="${supplier.id}" ${qo.supplierId==supplier.id?"selected='selected'":""}>${supplier.name}</option>
                            </c:forEach>

                        </select>
                        货品品牌
                        <select id="brandId" name="brandId" class="ui_select01">
                            <option value="-1">所有品牌</option>

                            <c:forEach items="${brands}" var="brand">
                                <option value="${brand.id}" ${qo.brandId==brand.id?"selected='selected'":""}>${brand.name}</option>
                            </c:forEach>

                        </select>
                        分组
                        <select id="groupByType" name="groupByType" class="ui_select01">
                            <c:forEach items="${groupByTypes}" var="groupByType">
                                <option value="${groupByType.key}"  ${groupByType.key==qo.groupByType?"selected='selected'":""}>${groupByType.value}</option>
                            </c:forEach>

                        </select>
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                    </div>


                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>分组类型</th>
                        <th>采购总数量</th>
                        <th>采购总金额</th>
                    </tr>
                    <tbody>
                    <c:forEach items="${orderCharts}" var="orderChart">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb"/></td>
                            <td>${orderChart.groupType}</td>
                            <td>${orderChart.totalNumber}</td>
                            <td>${orderChart.totalAmount}</td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</form>
</body>
</html>
