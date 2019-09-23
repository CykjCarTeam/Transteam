<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

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
</head>
<body>

<%--城市列表--%>
<div class="layui-form layui-form-pane" style = "margin-left:20px;">
  <label class="layui-form-label">选择城市：</label>
  <div class="layui-input-inline">
    <select lay-filter="city">
      <option value = "-1">请选择</option>
      <option value = "1">厦门</option>
      <option value = "2" >泉州</option>
      <option value = "3" >莆田</option>
    </select>
  </div>
</div>
<hr class="layui-bg-green">

<div class = "layui-form" id = "load" style="display: none">
  <%--查询操作--%>
  <div class="layui-form condition">
    <div class="layui-form-item">
      <label class="layui-form-label">线路名称</label>
      <div class="layui-input-inline">
        <input type="text" name="line" placeholder="线路名称" autocomplete="off" class="layui-input" id = "line_info">
      </div>
      <button class="layui-btn" data-type="query">搜索</button>
      <button class="layui-btn" data-type="add">添加</button>
    </div>
  </div>
  <%--表格载体--%>
  <table id="line" lay-filter="rout" title="<%=basePath%>"></table>
</div>
<%-- 必须用script标签包裹，如果用普通的div会多一对
   表格操作的工具栏
--%>
<script type="text/html" id="toolBar">
  <a class="layui-btn layui-btn-xs" lay-event="see">查看路线</a>
  <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script src="<%=basePath%>lib/layui/layui.js"></script>
<script src = "<%=basePath%>js/cityline.js"></script>
</body>
</html>