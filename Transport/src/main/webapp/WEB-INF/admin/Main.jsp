<%@ page language="java" contentType="text/html;"
    pageEncoding="utf-8" import="java.util.*,cn.bus.entity.*"%>
<%pageContext.setAttribute("path",request.getContextPath()); %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文件共享</title>
<link href="${path}/a_css/Main.css" rel="stylesheet"/>
	<link href="${path}/a_css/layui.css" rel="stylesheet"  media="all"/>
<script type="text/javascript" src="${path}/a_js/jquery.min.js"></script>
<%--	<script type="text/javascript" src="${path}/a_js/layui.all.js"></script>--%>
	<script type="text/javascript" src="${path}/layui/layui.js"></script>
<style type="text/css">

</style>
<script type="text/javascript">


</script>
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
		<div class="layui-logo">后台管理</div>
		<!-- 头部区域（可配合layui已有的水平导航） -->
		<ul class="layui-nav layui-layout-left">
			<li class="layui-nav-item"><a href="">控制台</a></li>
			<li class="layui-nav-item"><a href="">商品管理</a></li>
			<li class="layui-nav-item"><a href="">用户</a></li>
			<li class="layui-nav-item">
				<a href="javascript:;">其它系统</a>
				<dl class="layui-nav-child">
					<dd><a href="">邮件管理</a></dd>
					<dd><a href="">消息管理</a></dd>
					<dd><a href="">授权管理</a></dd>
				</dl>
			</li>
		</ul>
		<ul class="layui-nav layui-layout-right">
			<li class="layui-nav-item">
				<a href="javascript:;">
					<img src="http://t.cn/RCzsdCq" class="layui-nav-img">
					贤心
				</a>
				<dl class="layui-nav-child">
					<dd><a href="">基本资料</a></dd>
					<dd><a href="">安全设置</a></dd>
				</dl>
			</li>
			<li class="layui-nav-item"><a href="">退了</a></li>
		</ul>
	</div>

	<div class="layui-side layui-bg-black">
		<div class="layui-side-scroll">
			<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
			<ul class="layui-nav layui-nav-tree"  lay-filter="test">
				<c:forEach items="${menuList}" var="m" varStatus="s">
<%--					<li class="layui-nav-item layui-nav-itemed">--%>
					<li class="layui-nav-item">
						<a class="" >${m.key}</a>
						<dl class="layui-nav-child">
							<c:forEach items="${m.value}" var="v" varStatus="a">
								<c:if test="${v.mname!=null}">
									<dd><a href="${path}/${v.url}" target="content">${v.mname}</a></dd>
								</c:if>
							</c:forEach>
						</dl>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>

	<div class="layui-body">
		<!-- 内容主体区域 -->
		<div style = "padding:15px;">
			<iframe name = "content" width = "100%" height = "500px" border = "0" frameBorder = "0">
			</iframe>
		</div>
	</div>

	<div class="layui-footer">
		<!-- 底部固定区域 -->
		© layui.com - 底部固定区域
	</div>
</div>
<script src="${path}/layui/layui.all.js"></script>
<script>
	//JavaScript代码区域
	layui.use('element', function(){
		var element = layui.element;

	});
</script>
</body>
</html>