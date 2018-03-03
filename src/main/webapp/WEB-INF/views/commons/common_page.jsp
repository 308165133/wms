<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="ui_tb_h30">
    <div class="ui_flt" style="height: 30px; line-height: 30px;">
        共有
        <span class="ui_txt_bold04">${result.totalCount}</span>
        条记录，当前第
        <span class="ui_txt_bold04">${result.currentPage}/${result.totalPage}</span>
        页
    </div>
    <div class="ui_frt">
        <input type="button" value="首页" class="ui_input_btn01 btn_page" data-page="1"/>
        <input type="button" value="上一页" class="ui_input_btn01 btn_page" data-page="${result.prevPage}"/>
        <input type="button" value="下一页" class="ui_input_btn01 btn_page" data-page="${result.nextPage}"/>
        <input type="button" value="尾页" class="ui_input_btn01 btn_page" data-page="${result.totalPage}"/>

        <select  name="pageSize" class="ui_select02" data-pagesize="${result.pageSize}">
            <option>5</option>
            <option>10</option>
            <option>15</option>
        </select>

        转到第<input type="number" max="${result.totalPage}" min="1" style="width: 40px;" name="currentPage" value="${result.currentPage}" class="ui_input_txt01"/>页
        <input type="submit" class="ui_input_btn01" value="跳转"/>
    </div>
</div>