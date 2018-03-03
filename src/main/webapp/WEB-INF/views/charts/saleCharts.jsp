<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/WEB-INF/views/commons/common_head.html" %>
    <script type="text/javascript" src="/js/jquery/plugins/My97DatePicker/WdatePicker.js"></script>
    <title>叩丁狼教育PSS（演示版）-销售报表管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script type="text/javascript" src="/js/jquery/plugins/artDialog/plugins/iframeTools.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".btn_bar").click(function () {
                //打开图形报表的窗口
                $.dialog.open("/charts/saleChartsByBar.do?"+$("#searchForm").serialize(), {
                    id: 'ajxxList',
                    title: '销售报表-柱状',
                    width: 700,
                    height: 500,
                    left: '50%',
                    top: '50%',
                    background: '#000000',
                    opacity: 0.2,
                    lock: true,
                    resize: false
                });
            });
            $(".btn_pie").click(function () {
                //打开图形报表的窗口
                $.dialog.open("/charts/saleChartsByPie.do?"+$("#searchForm").serialize(), {
                    id: 'ajxxList',
                    title: '销售报表-饼状',
                    width: 700,
                    height: 500,
                    left: '50%',
                    top: '50%',
                    background: '#000000',
                    opacity: 0.2,
                    lock: true,
                    resize: false
                });
            })

        })

    </script>
</head>
<body>
<form id="searchForm" action="/charts/saleCharts.do" method="post">
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
                        客户
                        <select name="clientId" class="ui_select01">
                            <option value="-1">所有客户</option>

                            <c:forEach items="${clients}" var="client">
                                <option value="${client.id}" ${qo.clientId==client.id?"selected='selected'":""}>${client.name}</option>
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
                    <div class="box_bottom" style="padding-right: 10px;text-align: right;">
                        <input type="button" value="柱状报表" class="left2right btn_bar"/>
                        <input type="button" value="饼图报表" class="left2right btn_pie"/>
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
                        <th>销售总数量</th>
                        <th>销售总金额</th>
                        <th>毛利润</th>
                    </tr>
                    <tbody>
                    <c:forEach items="${saleCharts}" var="saleChart">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb"/></td>
                            <td>${saleChart.groupType}</td>
                            <td>${saleChart.totalNumber}</td>
                            <td>${saleChart.totalAmount}</td>
                            <td>${saleChart.profit}</td>
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
