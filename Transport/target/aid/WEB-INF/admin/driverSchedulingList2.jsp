<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/9/21
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<%--    <% String path = request.getContextPath() + "/"; %>--%>

    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
    <meta charset="utf-8">
    <link rel="stylesheet" href="<%=basePath%>lib/layui/css/layui.css" media="all">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>司机表</legend>
</fieldset>
<div class="demoTable">
    <div class="layui-inline">
        <label class="layui-form-label">开始日期</label>
        <div class="layui-input-inline">
            <input type="text" name="date" id="begindate" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">结束日期</label>
        <div class="layui-input-inline">
            <input type="text" name="date" id="enddate" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
        </div>
    </div>

    <button class="layui-btn" data-type="reload">搜索</button>

</div>

<table class="layui-table" lay-even="" lay-skin="row">
    <colgroup>
        <col width="150">
        <col width="150">
        <col width="200">
        <col>
    </colgroup>
    <thead>
    <tr>
        <th>人物</th>
        <th>星期一</th>
        <th>星期二</th>
        <th>星期三</th>
        <th>星期四</th>
        <th>星期五</th>
        <th>星期六</th>
        <th>星期七</th>
    </tr>
    </thead>
    <tbody>



    <c:forEach items="${flist}" var="ws">
        <tr>
            <td>${ws.aname}</td>
            <td><button>${ws.states}</button></td>
            <td><button></button></td>
            <td><button></button></td>
            <td><button></button></td>
            <td>}</td>
            <td>1</td>
            <td>1</td>



<%--            <td>--%>

<%--                <c:if test="${ws.shenghe==1}">--%>
<%--                    <a href="<%=path%>/wenda/updatewendang.action?wid=${ws.wid}&&shenghe=${ws.shenghe}" onclick="return confirm('是否通过')">审核未通过</a>--%>
<%--                </c:if>--%>
<%--                <c:if test="${ws.shenghe==2}">--%>
<%--                    <a href="<%=path%>/wenda/updatewendang.action?wid=${ws.wid}&&shenghe=${ws.shenghe}" onclick="return confirm('是否通过？')">审核通过</a>--%>
<%--                </c:if>--%>
<%--            </td>--%>
<%--            <td><a href="<%=path%>/wenda/delewendang.action?wid=${ws.wid}"onclick="return confirm('是否删除？')">删除</a></td>--%>


        </tr>
    </c:forEach>

    </tbody>
</table>




</body>
</html>
