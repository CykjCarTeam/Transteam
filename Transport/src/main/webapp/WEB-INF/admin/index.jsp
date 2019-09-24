<%--
  Created by IntelliJ IDEA.
  User: 92090
  Date: 2019/8/21
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="x-admin-sm">
<head>
  <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
  <meta charset="UTF-8">
  <title>智慧公交后台界面</title>
  <meta name="renderer" content="webkit|ie-comp|ie-stand">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="stylesheet" href="<%=basePath%>css/font.css">
  <link rel="stylesheet" href="<%=basePath%>css/xadmin.css">
  <!-- <link rel="stylesheet" href="./css/theme5.css"> -->
  <script src="<%=basePath%>lib/layui/layui.js" charset="utf-8"></script>
  <script type="text/javascript" src="<%=basePath%>js/xadmin.js"></script>
  <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
  <!--[if lt IE 9]>
  <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
  <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
  <script>
    // 是否开启刷新记忆tab功能
    // var is_remember = false;
  </script>
</head>
<body class="index">
<!-- 顶部开始 -->
<div class="container">
  <div class="logo">
    <a href="<%=basePath%>index.jsp">X-admin v2.2</a></div>
  <div class="left_open">
    <a><i title="展开左侧栏" class="iconfont">&#xe699;</i></a>
  </div>
  <ul class="layui-nav right" lay-filter="">
    <li class="layui-nav-item">
      <a href="javascript:;">${sessionScope.anum}</a>
      <dl class="layui-nav-child">
        <dd>
          <a href="<%=basePath%>">退出</a></dd>
      </dl>
    </li>
    <li class="layui-nav-item to-index">
      <a href="/">前台首页</a></li>
  </ul>
</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
  <div id="side-nav">
    <ul id="nav">
<c:forEach items="${map}" var="i">
      <li>
        <a href="javascript:;">
          <i class="iconfont left-nav-li" lay-tips="${i.key.menu}">&#xe6b4;</i>
          <cite>${i.key.menu}</cite>
          <i class="iconfont nav_right">&#xe697;</i></a>
        <ul class="sub-menu">
          <c:forEach items="${i.value}" var="a">
          <li>
            <a onclick="xadmin.add_tab('${a.menu}','welcome1.html')">
              <i class="iconfont">&#xe6a7;</i>
              <cite>${a.menu}</cite></a>
          </li>
          </c:forEach>
        </ul>
      </li>
</c:forEach>
        </ul>
      </li>
    </ul>
  </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
  <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
    <ul class="layui-tab-title">
      <li class="home">
        <i class="layui-icon">&#xe68e;</i>我的桌面</li></ul>
    <div class="layui-unselect layui-form-select layui-form-selected" id="tab_right">
      <dl>
        <dd data-type="this">关闭当前</dd>
        <dd data-type="other">关闭其它</dd>
        <dd data-type="all">关闭全部</dd></dl>
    </div>
    <div class="layui-tab-content">
      <div class="layui-tab-item layui-show">
        <iframe src='./welcome.html' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
      </div>
    </div>
    <div id="tab_show"></div>
  </div>
</div>
<div class="page-content-bg"></div>
<style id="theme_style"></style>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
</body>

</html>
