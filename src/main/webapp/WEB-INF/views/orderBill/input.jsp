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
            $(".searchproduct").click(function () {
                //获取到点击的放大镜所在的行
                var currentTr = $(this).closest("tr");
                art.dialog.open("/product/selectProducts.do", {
                    id: 'ajxxList',
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
                        var jsonObject = $.dialog.data("jsonObject");
                        if (jsonObject) {
                            //为当前行中的对应的元素设值
                            currentTr.find("input[tag='pid']").val(jsonObject.id);
                            currentTr.find("input[tag='name']").val(jsonObject.name);
                            currentTr.find("input[tag='costPrice']").val(jsonObject.costPrice);
                            currentTr.find("span[tag='brand']").text(jsonObject.brandName);
                            //清空json数据
                            $.dialog.data("jsonObject","");
                        }
                    }
                });
            });

            //为数量和成本价的输入框绑定失去焦点事件
            $("input[tag=costPrice],input[tag=number]").blur(function () {
                //通过当前触发事件的元素,找到所在的行
                var currentTr = $(this).closest("tr");
                var costPrice = parseFloat(currentTr.find("input[tag=costPrice]").val()) || 0;
                var number = parseFloat(currentTr.find("input[tag=number]").val()) || 0;
                currentTr.find("span[tag=amount]").text((costPrice * number).toFixed(2));
            });

            //添加明细
            $(".appendRow").click(function () {
                //克隆第一行的数据,然后添加到当前表格中
                var newTr = $("#edit_table_body tr:first").clone(true);
                //情况newTr中表单元素的值
                newTr.find("input[tag='pid']").val("");
                newTr.find("input[tag='name']").val("");
                newTr.find("input[tag='costPrice']").val("");
                newTr.find("span[tag='brand']").text("");
                newTr.find("input[tag='number']").val("");
                newTr.find("span[tag='amount']").text("");
                newTr.find("input[tag='remark']").val("");
                $("#edit_table_body").append(newTr);
            });

            //在表单提交之前修改表单元素的name属性的值
            $("#editForm").submit(function () {
                $("#edit_table_body tr").each(function (index, item) {
                    $(item).find("input[tag=pid]").prop("name", "items[" + index + "].product.id");
                    $(item).find("input[tag=costPrice]").prop("name", "items[" + index + "].costPrice");
                    $(item).find("input[tag=number]").prop("name", "items[" + index + "].number");
                    $(item).find("input[tag=remark]").prop("name", "items[" + index + "].remark");
                });
            });

            //删除明细
            $(".removeItem").click(function () {
                var currentTr = $(this).closest("tr");
                //通过当前点击的超链接找到所在的行
                if ($("#edit_table_body tr").size() == 1) {
                    //如果当前行是最后一行,不删除,清空数据
                    currentTr.find("input[tag='pid']").val("");
                    currentTr.find("input[tag='name']").val("");
                    currentTr.find("input[tag='costPrice']").val("");
                    currentTr.find("span[tag='brand']").text("");
                    currentTr.find("input[tag='number']").val("");
                    currentTr.find("span[tag='amount']").text("");
                    currentTr.find("input[tag='remark']").val("");
                } else {
                    //如果不是最后一行,删除找到的行
                    currentTr.remove();
                }


            })
        })
    </script>
    <script type="text/javascript" src="/js/commonAll.js"></script>

</head>
<body>
<form name="editForm" data-url="/orderBill/list.do" action="/orderBill/saveOrUpdate.do" method="post" id="editForm">
    <input type="hidden" name="id" value="${orderBill.id}"/>
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">采购订单编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">订单编码</td>
                    <td class="ui_text_lt">
                        <input name="sn" class="ui_input_txt02" value="${orderBill.sn}"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">供应商</td>
                    <td class="ui_text_lt">
                        <select class="ui_select01" name="supplier.id">
                            <c:forEach items="${suppliers}" var="supplier">
                                <option value="${supplier.id}" ${orderBill.supplier.id==supplier.id?"selected='selected'":""}>${supplier.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">业务时间</td>
                    <td class="ui_text_lt">
                        <fmt:formatDate value="${orderBill.vdate}" pattern="yyyy-MM-dd" var="vdate"/>
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
                            <c:if test="${orderBill.id==null}">
                                <tr>
                                    <td></td>
                                    <td>
                                        <input disabled="true" readonly="true" class="ui_input_txt02" tag="name"/>
                                        <img src="/images/common/search.png" class="searchproduct"/>
                                        <input type="hidden" name="items[0].product.id" tag="pid"/>
                                    </td>
                                    <td><span tag="brand"></span></td>
                                    <td><input tag="costPrice" name="items[0].costPrice"
                                               class="ui_input_txt00"/></td>
                                    <td><input tag="number" name="items[0].number"
                                               class="ui_input_txt00"/></td>
                                    <td><span tag="amount"></span></td>
                                    <td><input tag="remark" name="items[0].remark"
                                               class="ui_input_txt02"/></td>
                                    <td>
                                        <a href="javascript:;" class="removeItem">删除明细</a>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${orderBill.id!=null}">
                                <c:forEach items="${orderBill.items}" var="bill">
                                    <tr>
                                        <td></td>
                                        <td>
                                            <input disabled="true" readonly="true" class="ui_input_txt02" tag="name" value="${bill.product.name}"/>
                                            <img src="/images/common/search.png" class="searchproduct"/>
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