<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html class="x-admin-sm">

<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <meta charset="UTF-8">
    <title></title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="<%=basePath%>css/font.css">
    <link rel="stylesheet" href="<%=basePath%>css/xadmin.css">
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/xadmin.js"></script>
    <script src="<%=basePath%>js/echarts.min.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=eB3UC9RiYf7GGhjGC7XBAuk3WRKZitTG"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/library/MarkerTool/1.2/src/MarkerTool_min.js"></script>
</head>

<body onload="cityselect()">
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card" title="<%=basePath%>" id="selectctiy">

                    <div class="layui-form-pane">
                        <label class="layui-form-label">选择城市：</label>
                    </div>
                    <div class="layui-form layui-input-inline ">
                        <select id="citys" name="city" lay-filter="city"  >
                            <option ></option>

                        </select>
                    </div>
                <div  class="layui-card-body" align="center" >

                </div>
                <div id="show" style="width: 700px;height:400px;float: left;"></div>
                <div id="show2" style="width: 700px;height:400px; float: left;"></div>
            </div>
        </div>
    </div>
</div>
</body>

<script src="<%=basePath%>lib/layui/layui.js"></script>
<script src="<%=basePath%>js/cashier_statistics.js"></script>
</html>