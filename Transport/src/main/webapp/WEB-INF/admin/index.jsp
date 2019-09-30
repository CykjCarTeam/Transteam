<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html class="x-admin-sm">
<head>
  <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  %>
  <base href="<%=basePath%>">
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="公交,出行,定位">
  <meta http-equiv="description" content="智慧公交">
  <meta charset="UTF-8">
  <title>智慧公交后台</title>
  <meta name="renderer" content="webkit|ie-comp|ie-stand">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="stylesheet" href="<%=basePath%>css/font.css">
  <link rel="stylesheet" href="<%=basePath%>css/xadmin.css">
  <link rel="stylesheet" href="<%=basePath%>lib/layui/css/layui.css">
  <!-- <link rel="stylesheet" href="./css/theme5.css"> -->
  <script src="<%=basePath%>lib/layui/layui.js" charset="utf-8"></script>
  <script type="text/javascript" src="<%=basePath%>js/xadmin.js" charset="utf-8"></script>
</head>
<body class="index">
<!-- 顶部开始 -->
<div class="container">
  <div class="logo">
    <a href="<%=basePath%>index.jsp">智慧公交后台</a></div>
  <div class="left_open">
    <a><i title="展开左侧栏" class="iconfont">&#xe699;</i></a>
  </div>
  <ul class="layui-nav right" lay-filter="">
    <li class="layui-nav-item">
      <a href="javascript:;">
        ${sessionScope.anum}
      </a>
      <dl class="layui-nav-child">
        <!-- 二级菜单 -->
        <dd>
          <a onclick="xadmin.open('个人信息','http://www.baidu.com')">个人信息</a></dd>
        <dd>
          <a href="<%=basePath%>">退出</a></dd>
      </dl>
    </li>
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
          <i class="iconfont left-nav-li" lay-tips="hygl">&#xe6b8;</i>
          <cite>${i.key.menu}</cite>
          <i class="iconfont nav_right">&#xe697;</i>
        </a>
        <ul class="sub-menu">
          <%--          子级菜单--%>
  <c:forEach items="${i.value}" var="a">
          <li>
            <a href = '<%=basePath%>${a.url}' target = "content">
              <cite>${a.menu}</cite>
            </a>
          </li>
  </c:forEach>
        </ul>
      </li>
</c:forEach>
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
        <iframe src = "" frameborder="0" scrolling="yes"  class="x-iframe" name = "content"></iframe>
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
<%--      <c:forEach items="${map}" var="i">--%>
<%--        <li class="list">--%>
<%--          <a href="javascript:;">--%>
<%--            <i class="iconfont">&#xe70b;</i>--%>
<%--              ${i.key.menu}--%>
<%--            <i class="iconfont nav_right">&#xe697;</i>--%>
<%--          </a>--%>
<%--          <ul class="sub-menu">--%>
<%--            <c:forEach items="${i.value}" var="a">--%>
<%--              <li>--%>
<%--                <a href="<%=basePath%>adminHandle/interface.action?url=${a.url}">--%>
<%--                  <i class="iconfont">&#xe6a7;</i>--%>
<%--                    ${a.menu}--%>
<%--                </a>--%>
<%--              </li>--%>
<%--            </c:forEach>--%>
<%--          </ul>--%>
<%--        </li>--%>
<%--      </c:forEach>--%>
