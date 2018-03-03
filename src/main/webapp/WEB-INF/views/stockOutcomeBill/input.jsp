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
    <script type="text/javascript" src="/js/jquery/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/jquery/plugins/jquery-form/jquery.form.min.js"></script>


    <script type="text/javascript" src="/js/jquery/plugins/artDialog/plugins/iframeTools.js"></script>
    <script type="text/javascript" src="/js/jquery/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#edit_table_body").on("click", ".searchproduct", function () {
                var currentTr = $(this).closest("tr");
                $.dialog.open("/product/selectProducts.do", {
                    id: "selectProduct",
                    title: '选择商品列表',
                    width: 900,
                    height: 500,
                    left: '50%',
                    top: '50%',
                    background: '#000000',
                    opacity: 0.2,
                    lock: true,
                    resize: false,
                    close: function () {
                        //获取到选择的商品信息
                        var jsonObject = $.dialog.data("jsonObject");
                        if (jsonObject) {
                            currentTr.find("input[tag=name]").val(jsonObject.name);
                            currentTr.find("input[tag=pid]").val(jsonObject.id);
                            currentTr.find("span[tag=brand]").text(jsonObject.brandName);
                            currentTr.find("input[tag=salePrice]").val(jsonObject.salePrice);
                            $.dialog.data("jsonObject", "");
                        }
                    }
                });
            }).on("blur", "input[tag=salePrice],input[tag=number]", function () {
                var currentTr = $(this).closest("tr");
                var salePrice = parseFloat(currentTr.find("input[tag=salePrice]").val()) || 0;
                var number = parseFloat(currentTr.find("input[tag=number]").val()) || 0;
                currentTr.find("span[tag=amount]").text((salePrice * number).toFixed(2));
            }).on("click",".removeItem",function () {
                var currentTr = $(this).closest("tr");
                if($("#edit_table_body tr").size()==1){
                    currentTr.find("input[tag=name]").val("");
                    currentTr.find("input[tag=pid]").val("");
                    currentTr.find("span[tag=brand]").text("");
                    currentTr.find("input[tag=salePrice]").val("");
                    currentTr.find("input[tag=number]").val("");
                    currentTr.find("span[tag=amount]").text("");
                    currentTr.find("input[tag=remark]").val("");
                }else{
                    currentTr.remove();
                }
            });

            //在提交之前修改每个表单元素的name属性的值
            $("#editForm").submit(function () {
                $("#edit_table_body tr").each(function (index, item) {
                    $(item).find("input[tag=pid]").prop("name", "items[" + index + "].product.id")
                    $(item).find("input[tag=salePrice]").prop("name", "items[" + index + "].salePrice")
                    $(item).find("input[tag=number]").prop("name", "items[" + index + "].number")
                    $(item).find("input[tag=remark]").prop("name", "items[" + index + "].remark")
                });
            });

            $(".appendRow").click(function () {
                var newTr = $("#edit_table_body tr:first").clone();
                newTr.find("input[tag=name]").val("");
                newTr.find("input[tag=pid]").val("");
                newTr.find("span[tag=brand]").text("");
                newTr.find("input[tag=salePrice]").val("");
                newTr.find("input[tag=number]").val("");
                newTr.find("span[tag=amount]").text("");
                newTr.find("input[tag=remark]").val("");
                $("#edit_table_body").append(newTr);
            })
        })
    </script>
    <script type="text/javascript" src="/js/commonAll.js"></script>

</head>
<body>
<form name="editForm" data-url="/stockOutcomeBill/list.do" action="/stockOutcomeBill/saveOrUpdate.do" method="post" id="editForm">
    <input type="hidden" name="id" value="${stockOutcomeBill.id}"/>
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">销售出库单编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">出库单编码</td>
                    <td class="ui_text_lt">
                        <input name="sn" class="ui_input_txt02" value="${stockOutcomeBill.sn}"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">仓库</td>
                    <td class="ui_text_lt">
                        <select class="ui_select01" name="depot.id">
                            <c:forEach items="${depots}" var="depot">
                                <option value="${depot.id}" ${stockOutcomeBill.depot.id==depot.id?"selected='selected'":""}>${depot.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">客户</td>
                    <td class="ui_text_lt">
                        <select class="ui_select01" name="client.id">
                            <c:forEach items="${clients}" var="client">
                                <option value="${client.id}" ${stockOutcomeBill.client.id==client.id?"selected='selected'":""}>${client.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">业务时间</td>
                    <td class="ui_text_lt">
                        <fmt:formatDate value="${stockOutcomeBill.vdate}" pattern="yyyy-MM-dd" var="vdate"/>
                        <input name="vdate" class="ui_input_txt02 Wdate" value="${vdate}" onclick="WdatePicker();"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">单据明细</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="button" value="添加明细" class="ui_input_btn01 appendRow"/>
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
                                <th width="60"></th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">
                            <c:if test="${stockOutcomeBill.id==null}">
                                <tr>
                                    <td></td>
                                    <td>
                                        <input disabled="true" readonly="true" class="ui_input_txt02" tag="name"/>
                                        <img src="/images/common/search.png" class="searchproduct"/>
                                        <input type="hidden" tag="pid"/>
                                    </td>
                                    <td><span tag="brand"></span></td>
                                    <td><input tag="salePrice"
                                               class="ui_input_txt00"/></td>
                                    <td><input tag="number"
                                               class="ui_input_txt00"/></td>
                                    <td><span tag="amount"></span></td>
                                    <td><input tag="remark"
                                               class="ui_input_txt02"/></td>
                                    <td>
                                        <a href="javascript:;" class="removeItem">删除明细</a>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${stockOutcomeBill.id!=null}">
                                <c:forEach items="${stockOutcomeBill.items}" var="bill">
                                    <tr>
                                        <td></td>
                                        <td>
                                            <input disabled="true" readonly="true" class="ui_input_txt02" tag="name" value="${bill.product.name}"/>
                                            <img src="/images/common/search.png" class="searchproduct"/>
                                            <input type="hidden" name="items[0].product.id" tag="pid" value="${bill.product.id}"/>
                                        </td>
                                        <td><span tag="brand">${bill.product.brandName}</span></td>
                                        <td><input tag="salePrice" 
                                                   class="ui_input_txt00" value="${bill.salePrice}"/></td>
                                        <td><input tag="number" 
                                                   class="ui_input_txt00" value="${bill.number}"/></td>
                                        <td><span tag="amount">${bill.amount}</span></td>
                                        <td><input tag="remark" 
                                                   class="ui_input_txt02" value="${bill.remark}"/></td>
                                        <td>
                                            <a href="javascript:;" class="removeItem">删除明细</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>

                            </tbody>
                        </table>
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