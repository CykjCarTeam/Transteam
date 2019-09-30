<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!doctype html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>智能公交车后台系统</title>
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>lib/layui/layui.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/normalize.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/default.css">
	<script src="<%=basePath%>js/prefixfree.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/login.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/styles.css">
</head>
<body>
<div id="logo">
	<h1 class="hogo"><i>BUS BACKSTAGE</i></h1>
</div>
<section class="stark-login">
	<form class="registerform" action="<%=basePath%>adminHandle/login.action"method="post">
		<div id="fade-box">
			<div>
				<input type="text" name="anum" id="anum" placeholder="用户名" onblur="check()" required >
			</div>

			<input type="password" name="apwd" id="apwd" placeholder="密码" onblur="check1()" required >
			<div class="form-group">
				<input id="authCode" class="i-text" name="authCode" placeholder="请输入验证码" required="required" type="text" onblur="return yanzhenma()"/>
				<img type="image" src="<%=basePath%>yzm/authCode.action" id="codeImage" title="图片看不清"style="cursor:pointer;"/></label>
				<label>
					<a onclick="chageCode()" id="change" href="<%=basePath%>yzm/authCode.action">换一张</a>
				</label>
			</div>
			<button>登录</button>
		</div>
	</form>
	<div class="hexagons">
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<br>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<br>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>

		<br>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<br>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
	</div>
</section>

<div id="circle1">
	<div id="inner-cirlce1">
		<h2> </h2>
	</div>
</div>
<ul>
	<li></li>
	<li></li>
	<li></li>
	<li></li>
	<li></li>
</ul>
</body>
</html>
