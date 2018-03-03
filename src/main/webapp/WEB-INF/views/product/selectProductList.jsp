<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/WEB-INF/views/commons/common_head.html" %>
    <link rel="stylesheet" type="text/css" href="/js/jquery/plugins/fancybox/jquery.fancybox.css"/>
    <script type="text/javascript" src="/js/jquery/plugins/fancybox/jquery.fancybox.js"></script>
    <script type="text/javascript" src="/js/jquery/plugins/artDialog/plugins/iframeTools.js"></script>
    <script type="text/javascript">
        $(function () {
            $('.fancybox').fancybox();

            $(".btn_selectProduct").click(function () {
                //获取到当前选择商品的数据
                var jsonObject = $(this).data("product");
                //共享数据给父窗口
                $.dialog.data("jsonObject",jsonObject);
                //关闭当前窗口
                $.dialog.close();
            })
        });
    </script>
    <title>叩丁狼教育PSS（演示版）-商品管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<form id="searchForm" action="/product/selectProducts.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        关键字
                        <input type="text" class="ui_input_txt02" name="keywords" value="${qo.keywords}"/>
                        商品品牌
                        <select class="ui_select01" name="brandId">
                            <option value="-1">全部</option>
                            <c:forEach items="${brands}" var="brand">
                                <option value="${brand.id}" ${qo.brandId==brand.id?"selected='selected'":""}>${brand.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="搜索" class="ui_input_btn01 btn_page"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>商品图片</th>
                        <th>商品名称</th>
                        <th>商品编码</th>
                        <th>商品品牌</th>
                        <th>商品成本价</th>
                        <th>商品销售价</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <c:forEach items="${result.listData}" var="product">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb"/></td>
                            <td>
                                <a class="fancybox" href="${product.imagePath}" data-fancybox-group="gallery" title="${product.name}">
                                    <img src="${product.smallImagePath}" width="80"/>
                                </a>
                            </td>
                            <td>${product.name}</td>
                            <td>${product.sn}</td>
                            <td>${product.brandName}</td>
                            <td>${product.costPrice}</td>
                            <td>${product.salePrice}</td>
                            <td>
                                <input type="button" id="select" data-product='${product.productData}' value="选择该商品" class="left2right btn_selectProduct"/><br/>
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
