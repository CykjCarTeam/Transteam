<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <% String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  %>
  <meta charset="utf-8">
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="<%=basePath%>lib/layui/css/layui.css" media="all">
  <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ykrYFllyk7nAN0oTIWHPwdxTkRkzfPuE&callback"></script>
  <script type="text/javascript" src="//api.map.baidu.com/library/LuShu/1.2/src/LuShu_min.js"></script>
  <style type="text/css">
    body, html,#lineMap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
  </style>
</head>
<body>

<%--城市列表--%>
<div class="layui-form layui-form-pane" style = "margin-left:20px;">
  <label class="layui-form-label">选择城市：</label>
  <div class="layui-input-inline">
    <select lay-filter="city">
      <option value = "-1">请选择</option>
      <c:forEach items="${requestScope.citys}" var = "citys">
         <option value = "${citys.cid}">${citys.city}</option>
      </c:forEach>
    </select>
  </div>
</div>
<hr class="layui-bg-green">

<div class = "layui-form" id = "load" style="display: none">
  <%--表格载体--%>
  <table id="line" lay-filter="rout" title="<%=basePath%>"></table>
</div>
<%--地图线路载体--%>
<div id="lineMap" style="display: none"></div>
<%-- 必须用script标签包裹，如果用普通的div会多一对
   表格操作的工具栏
--%>
<script type="text/html" id="toolBar">
  <a class="layui-btn layui-btn-xs" lay-event="imitate">模拟行车</a>
</script>

<script src="<%=basePath%>lib/layui/layui.js"></script>
<script src = "<%=basePath%>js/imitatetrain.js"></script>
</body>
</html>