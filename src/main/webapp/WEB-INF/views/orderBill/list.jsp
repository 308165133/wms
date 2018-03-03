<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/WEB-INF/views/commons/common_head.html" %>
    <title>叩丁狼教育PSS（演示版）-采购订单管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>

    <script type="text/javascript" src="/js/jquery/plugins/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<form id="searchForm" action="/orderBill/list.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">


                     <%--   开始时间<input name="beginDate" readonly="readonly" onclick="WdatePicker({minDate:'2018-01-01'});" value="${qo.beginDate}"
                                   class="ui_input_txt02 Wdate beginDate"/>~
                        结束时间<input name="endDate" readonly="readonly" onclick="WdatePicker({maxDate:new Date()});" value="${qo.endDate}"
                                   class="ui_input_txt02 Wdate endDate"/>--%>
                        <fmt:formatDate value="${qo.beginDate}" pattern="yyyy-MM-dd" var="beginDate"/>
                        <fmt:formatDate value="${qo.endDate}" pattern="yyyy-MM-dd" var="endDate"/>
                        开始时间<input name="beginDate" readonly="readonly" onclick="WdatePicker();" value="${beginDate}"
                                   class="ui_input_txt02 Wdate beginDate"/>~
                        结束时间<input name="endDate" readonly="readonly" onclick="WdatePicker();" value="${endDate}"
                                   class="ui_input_txt02 Wdate endDate"/>
                        供应商
                        <select name="supplierId" class="ui_select01">
                            <option value="-1">所有供应商</option>
                            <c:forEach items="${suppliers}" var="supplier">
                                <option value="${supplier.id}" ${qo.supplierId==supplier.id?"selected='selected'":""}>${supplier.name}</option>
                            </c:forEach>

                        </select>
                        状态
                        <select id="status" name="status" class="ui_select01">
                            <option value="-1">所有状态</option>
                            <option value="0" ${qo.status==0?"selected='selected'":""}>待审核</option>
                            <option value="1" ${qo.status==1?"selected='selected'":""}>已审核</option>
                        </select>
                        <input type="button" value="查询" class="ui_input_btn01 btn_page"/>
                    </div>

                    <div id="box_bottom">
                        <input type="button" value="新增" class="ui_input_btn01 btn_input" data-url="/orderBill/input.do"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>订单编码</th>
                        <th>业务时间</th>
                        <th>供应商</th>
                        <th>总金额</th>
                        <th>总数量</th>
                        <th>录入人</th>
                        <th>审核人</th>
                        <th>状态</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <c:forEach items="${result.listData}" var="orderBill">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb"/></td>
                            <td>${orderBill.sn}</td>
                            <td><fmt:formatDate value="${orderBill.vdate}" pattern="yyyy-MM-dd"/></td>
                            <td>${orderBill.supplier.name}</td>
                            <td>${orderBill.totalAmount}</td>
                            <td>${orderBill.totalNumber}</td>
                            <td>${orderBill.inputUser.name}</td>
                            <td>${orderBill.auditor.name}</td>
                            <td>
                                <c:if test="${orderBill.status==0}">
                                    <span style="color: red;">待审核</span>
                                </c:if>
                                <c:if test="${orderBill.status==1}">
                                    <span style="color: green;">已审核</span>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${orderBill.status==0}">
                                    <a href="/orderBill/input.do?id=${orderBill.id}">编辑</a>
                                    <a href="javascript:;" class="btn_delete" data-url="/orderBill/delete.do?id=${orderBill.id}">删除</a>
                                    <a href="javascript:;" class="btn_audit" data-url="/orderBill/audit.do?id=${orderBill.id}">审核</a>
                                </c:if>
                                <c:if test="${orderBill.status==1}">
                                    <a href="/orderBill/viewBill.do?id=${orderBill.id}">查看</a>
                                </c:if>


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
