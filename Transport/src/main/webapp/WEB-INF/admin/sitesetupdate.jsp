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
<div al style="width:800px;height:600px;border:1px solid gray" id="container"></div>
</div>
<div align="center">
    <form id="myform" class="layui-form" onsubmit="return false;">
        <table align="center">
            <tr>
                <td>站点所属城市：<input name="cid" class="layui-input" type="text" disabled="disabled" id="addname" value="${requestScope.city}"/></td>
            </tr>
            <tr>
                <td>站点名称：<input name="station" class="layui-input" type="text" id="sitename" value="${requestScope.station}"/></td>
            </tr>
            <tr>
                <td>坐标X：<input class="layui-input" type="text" id="addrx"  disabled="disabled" name="coor_x" value="${requestScope.coor_x}" /></td>
            </tr>
            <tr>
                <td>坐标Y：<input class="layui-input" type="text" id="addry"  disabled="disabled" name="coor_y" value="${requestScope.coor_y}"/></td>
            </tr>
            <tr>
                <td><button class="layui-btn" onclick="updatstation()">提交</button></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
<script type="text/javascript">
    var map = new BMap.Map("container");
    var  coor_x=$("#addrx").val();
    var  coor_y=$("#addry").val();
    var point= new BMap.Point(coor_x, coor_y);
    var myIcon=new BMap.Icon("<%=basePath%>images/zd.png",new BMap.Size(50,50));
    var marker = new BMap.Marker(point,{icon:myIcon});// 创建标注
    var adx;
    var ady;
    map.centerAndZoom(point,16);
    map.addOverlay(marker);             // 将标注添加到地图中
    map.enableScrollWheelZoom();
    marker.enableDragging();//设置可拖拽
    marker.addEventListener("dragend", function (e) {

        $("#addrx").val(e.point.lng); //经度
        $("#addry").val(e.point.lat); //纬度

    });
    //验证
    function test() {
        var sitename=document.getElementById("sitename").value;
        var han = /^[\u4e00-\u9fa5]+$/;
        if(sitename == null || "" == sitename)
        {

            layer.msg("站点名称不能为空",{icon:3,time:1500,title:"提示"});
            return false;
        }else
        {
            if(!han.test(sitename))
            {
                layer.msg("请输入汉字",{icon:3,time:1500,title:"提示"});
                return false;
            }
            return  true;
        }

    }
    //修改站点
    function updatstation() {
        if(test()){
        var station=document.getElementById("sitename").value;
            adx=document.getElementById("addrx").value;
            ady=document.getElementById("addry").value;
        $.ajax({
            url: "<%=basePath%>siteHandle/stationupdate.action?sid=${requestScope.sid}&station="+station+"&coor_x="+adx+"&coor_y="+ady,
            type: "post",
            dataType:"text",

            success: function (redata) {

                    if (redata=="0")
                    {
                        layer.msg("修改成功",{icon:1,time:1500,title:"提示"});
                    }else
                    {
                        layer.msg("修改失败",{icon:5,time:1500,title:"提示"});
                    }

            },
            error:function () {
                console.log("出现异常");
            }

        });
    }
    }
</script>
