<%--
  Created by IntelliJ IDEA.
  User: 92090
  Date: 2019/9/23
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>新增站点</title>
    <link rel="stylesheet" href="<%=basePath%>css/font.css">
    <link rel="stylesheet" href="<%=basePath%>css/xadmin.css">
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <script src="<%=basePath%>lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/xadmin.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=eB3UC9RiYf7GGhjGC7XBAuk3WRKZitTG"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/library/MarkerTool/1.2/src/MarkerTool_min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
</head>
<body>
<div align="center">
<div al style="width:800px;height:600px;border:1px solid gray" id="container"></div>
<input type="button" class="layui-btn" value="新增站点" onClick="selectStyle()" />
<input type="button" class="layui-btn" value="关闭新增站点" onClick="mkrTool.close()" />
</div>
<div align="center">
    <form id="myform" class="layui-form">
        <table align="center">
            <tr>
                <td>站点所属城市：<input name="city" class="layui-input" type="text" disabled="disabled" id="addname" value="${requestScope.city}"/></td>
            </tr>
            <tr>
                <td>站点名称：<input name="station" class="layui-input" type="text" id="sitename"/></td>
            </tr>
            <tr>
                <td>坐标X：<input class="layui-input" type="text" id="addrx"  disabled="disabled"/></td>
            </tr>
            <tr>
                <td>坐标Y：<input class="layui-input" type="text" id="addry"  disabled="disabled"/></td>
            </tr>
            <tr>
                <td><button class="layui-btn">提交</button></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
<script type="text/javascript">
    var map = new BMap.Map("container");
    var point= new BMap.Point(116.404, 39.915);
    var city=$("#addname").val();
    map.centerAndZoom(city,16);
    map.enableScrollWheelZoom();
    var mkrTool = new BMapLib.MarkerTool(map, {autoClose: false, followText: "新增站点"});
    var remkr;
    mkrTool.addEventListener("markend",function(evt){
        if(remkr!=null){map.removeOverlay(remkr);}
        var mkr=evt.marker;
        remkr=mkr;
        var adx=mkr.getPosition().lng;
        var ady=mkr.getPosition().lat;
        document.getElementById("addrx").value=adx;
        document.getElementById("addry").value=ady;
    });
    //选择样式
    function selectStyle(){
        mkrTool.open(); //打开工具
        var icon =new BMap.Icon("<%=basePath%>images/zd.png",new BMap.Size(50,50)); //设置工具样式
        mkrTool.setIcon(icon);
    }
</script>
