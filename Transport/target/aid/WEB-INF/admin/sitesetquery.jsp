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

</head>
<body>
<div align="center">
<div al style="width:600px;height:500px;border:1px solid gray" id="container"></div>
</div>
</body>
</html>
<script type="text/javascript">
    var map = new BMap.Map("container");
    var  coor_x=${requestScope.coor_x};
    var  coor_y=${requestScope.coor_y};
    var point= new BMap.Point(coor_x, coor_y);
    var myIcon=new BMap.Icon("<%=basePath%>images/zd.png",new BMap.Size(50,50));
    var marker = new BMap.Marker(point,{icon:myIcon});// 创建标注
    map.centerAndZoom(point,20);
    map.addOverlay(marker);             // 将标注添加到地图
    var inforwin=new BMap.InfoWindow(
        "<h2 style='margin:0 0 5px 0;padding:0.2em 0'>站点名称:${requestScope.station}</h2><br/>"+
            "<h4>站点所属城市：${requestScope.city}<h4>"+
        "<h4>站点坐标：(${requestScope.coor_x},${requestScope.coor_y})<h4>"
    );
    marker.setTitle("站点名称:${requestScope.station}");
    marker.openInfoWindow(inforwin);
    map.enableScrollWheelZoom();

</script>
