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
    <link rel="stylesheet" href="<%=basePath %>lib/layui/css/layui.css" media = "all">
    <script src="<%=basePath %>js/jquery.min.js"></script>
</head>
<body>

	<div class="layui-form-item" style = "margin-top:10px" id = "path" title = "<%=basePath%>">
		<label class="layui-form-label">线路名称</label>
		<div class="layui-input-inline">
			<input type="text" name="line" placeholder="线路名称" autocomplete="off" class="layui-input" id = "line_info">
		</div>
		<button class="layui-btn" data-type="add">提交线路</button>
	</div>

    <div id="lineData" class = "layui-form-item" style = "margin-left:20px;"></div>
	<script src="<%=basePath %>lib/layui/layui.js"></script>
	<script src="<%=basePath %>lib/layui/lay/modules/transfer.js"></script>
	<script src="<%=basePath %>js/addline.js"></script>
</body>
</html>