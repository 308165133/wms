<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/WEB-INF/views/commons/common_head.html" %>
    <title>叩丁狼教育PSS（演示版）-即时库存管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<form id="searchForm" action="/productStock/list.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        货品名称或编码<input name="keywords" value="${qo.keywords}" class="ui_input_txt02"/>
                        所在仓库
                        <select id="depotId" name="depotId" class="ui_select01">
                            <option value="-1">所有仓库</option>
                            <c:forEach items="${depots}" var="depot">
                                <option value="${depot.id}" ${depot.id==qo.depotId?"selected='selected'":""}>${depot.name}</option>
                            </c:forEach>
                        </select>
                        货品品牌
                        <select id="brandId" name="brandId" class="ui_select01">
                            <option value="-1">所有品牌</option>

                            <c:forEach items="${brands}" var="brand">
                                <option value="${brand.id}" ${brand.id==qo.brandId?"selected='selected'":""}>${brand.name}</option>
                            </c:forEach>

                        </select>
                        库存阈值<input name="limitNumber" value="${qo.limitNumber}" class="ui_input_txt02"/>

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
                        <th>仓库</th>
                        <th>商品名称</th>
                        <th>商品编码</th>
                        <th>品牌</th>
                        <th>库存数量</th>
                        <th>成本</th>
                        <th>库存汇总</th>
                    </tr>
                    <tbody>
                    <c:forEach items="${result.listData}" var="productStock">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb"/></td>
                            <td>${productStock.depot.name}</td>
                            <td>${productStock.product.name}</td>
                            <td>${productStock.product.sn}</td>
                            <td>${productStock.product.brandName}</td>
                            <td>${productStock.storeNumber}</td>
                            <td>${productStock.price}</td>
                            <td>${productStock.amount}</td>
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
