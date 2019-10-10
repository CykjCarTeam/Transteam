
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
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
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=eB3UC9RiYf7GGhjGC7XBAuk3WRKZitTG"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/library/MarkerTool/1.2/src/MarkerTool_min.js"></script>
    <script type="text/javascript" src="//api.map.baidu.com/library/LuShu/1.2/src/LuShu_min.js"></script>
</head>

<body >
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="demoTable" >
                    <div class="layui-form-pane">
                        <label class="layui-form-label">起始站点名称</label>
                    </div>
                    <div class="layui-inline ">
                        <input type="text"  id="fstation" name="fstation" placeholder="请输入起始站点名称" autocomplete="off"
                               class="layui-input" list="stationlist">
                        <datalist id="stationlist">
                        </datalist>
                    </div>


                        <div class="layui-form-pane">
                            <label class="layui-form-label">终点站点名称</label>
                        </div>
                    <div class="layui-inline ">
                        <input type="text"  id="lstation" name="lstation" placeholder="请输入终点站点名称" autocomplete="off"
                               class="layui-input" list="stationlist">
                    </div>
                    <div class="layui-inline">
                        <button id="btn_ok" class="layui-btn" data-type="reload">查询</button>
                    </div>

                </div>
                <div  id="bodydiv" class="layui-card-body" align="center" >
                </div>

            </div>
        </div>
    </div>
</div>
</body>
<div id="tc" style="width:800px;height:600px;border:1px solid gray;" class="layui-card-body" align="center" hidden="hidden">

</div>

<script src="<%=basePath%>lib/layui/layui.js"></script>
<script>

    $.ajax({
        url:"<%=basePath%>uquerylineHandle/uquerystation.action" ,
        type: "post",
        dataType:"json",
        success: function (slist) {
            $.each(slist,function (i,x) {
                $("#stationlist").append(
                    '<option>'
                    +x.station
                    +'</option>'
                )
            })
        }
    });
    layui.use(['table','form'], function () {
        var table = layui.table,
            form=layui.form,
            $=layui.$;
    $("#btn_ok").click(function () {
        var fstation=$("#fstation").val();
        var lstation=$("#lstation").val();
        if((fstation==null||fstation=="")&&(lstation==null||lstation==""))
        {
            layer.msg("起始站点或终点站点不能为空",{icon:5,time:1500,title:"提示"})
        }else
        {
            $.ajax({
                url:"<%=basePath%>uquerylineHandle/uquerystation2.action?fstation="+fstation+"&lstation="+lstation ,
                type: "post",
                dataType:"json",
                success: function (redata) {
                    // alert(redata.ul);
                    var map = new BMap.Map("tc");
                    var point= new BMap.Point(116.404, 39.915);
                    var myIcon=new BMap.Icon("<%=basePath%>images/zd.png",new BMap.Size(50,50));
                    //地图初始化
                    map.centerAndZoom(point,16);
                    map.enableScrollWheelZoom();//启用滚轮放大缩小

                    var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
                    var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
                    var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL});
                    map.addControl(top_left_control);
                    map.addControl(top_left_navigation);
                    map.addControl(top_right_navigation);
                    var url="";
                    $("#bodydiv").empty();
                    $.each(redata,function (i,u) {
                        if(u.mes==0)
                        {
                            $("#bodydiv").append(
                                '<div id="'+i+'"  style="background-color: #00A0E8">'+
                                '线路名称：'+u.line+
                                '</br>'+
                                '起点：'+u.ul.fstation+
                                '</br>'+
                                '终点：'+u.ul.lstation+'</div>'+ '</br>'
                            )
                            url="uquerylineHandle/linemap.action?lid="+u.lid;
                        }else
                        {
                            $("#bodydiv").append(
                                '<div id="'+i+'" style="background-color: #00A0E8">'+
                                '线路名称：'+u.fline+
                                '</br>'+
                                '起点：'+u.ul2.fstation+
                                '</br>'+
                                '终点：'+u.ul2.lstation+
                                '</br>'+
                                '换乘线路：'+u.lline+
                                '</br>'+
                                '换乘站点：'+u.hstation+
                                '</div>'+ '</br>'
                            )
                            url="uquerylineHandle/linemap.action?flid="+u.flid+"&llid="+u.llid;
                        }

                        $("#"+i).click(function () {

                            $.ajax({
                                url:url ,
                                type: "post",
                                dataType:"json",
                                success: function (redat) {
                                    if(redat.code==0)
                                    {
                                        map.clearOverlays();
                                        load(redat.line1,redat.line2,map);

                                    }else
                                    {
                                        load(redat.line,null,map);
                                    }

                                    layer.open({
                                        type: 1
                                        , title: "站点线路图" //不显示标题栏
                                        , closeBtn: false
                                        , area: ['800px', '600px']
                                        , shade: 0.8
                                        , id: 'LAY_layuipro' //设定一个id，防止重复弹出
                                        , btn: ['返回']
                                        , btnAlign: 'c'
                                        ,anim: 4
                                        , moveType: 1 //拖拽模式，0或者1
                                        , content: $("#tc")
                                        , success: function (layero) {
                                            var btn = layero.find('.layui-layer-btn');
                                            btn.find('.layui-layer-btn0').attr({
                                                target: '_blank'
                                            });
                                        }
                                    });
                                }


                            });
                        })
                    })

                },

            });
        }

    })


    });
    function load(list,list2,map) {
        var fstation = [];//首线路站点
        var fpass = [];//首线路经站点
        var fpauseStation = [];//路书上的停站点
        var lush = null;//路书1
        var lstation = [];//首线路站点
        var lpass = [];//首线路经站点
        var lpauseStation = [];//路书上的停站点
        var lush2 = null;//路书2
        $.each(list,function (item,u) {
            var point = new BMap.Point(u.coor_x,u.coor_y);
            fstation.push(point);
            if(item != 0 && item != list.length-1){
                fpass.push(point);
                fpauseStation.push({lng:u.coor_x, lat:u.coor_y,html:"", pauseTime:3});//路书格式
            }
        })

        var driving = new BMap.DrivingRoute(map,
            {
                onSearchComplete: function(res) {
                    if (driving.getStatus() == BMAP_STATUS_SUCCESS) {
                        var plan = res.getPlan(0);
                        var arrPoints = [];
                        for (var j = 0; j < plan.getNumRoutes(); j++) {
                            var route = plan.getRoute(j);
                            arrPoints = arrPoints.concat(route.getPath());
                        }
                        map.addOverlay(new BMap.Polyline(arrPoints, {strokeColor: '#33ffb1'}));
                        map.setViewport(arrPoints);
                        lush = new BMapLib.LuShu(map, arrPoints, {
                            defaultContent:"",
                            autoView: false,//是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
                            icon: new BMap.Icon('<%=basePath%>images/bus.png', new BMap.Size(52, 26), {anchor: new BMap.Size(26, 13)}),
                            speed: 90,
                            enableRotation: true,//是否设置marker随着道路的走向进行旋转
                            landmarkPois: fpauseStation   //沿途站点
                        });
                    }
                }
            });
        driving.search(fstation[0],fstation[fstation.length-1],{waypoints:fpass});//waypoints表示途经点
        //在地图上添加站点标记
        for(var i = 0; i < fstation.length; i++){
            var myIcon = new BMap.Icon("<%=basePath%>images/car.png", new BMap.Size(50,50));
            var marker2 = new BMap.Marker(fstation[i],{icon:myIcon});  // 创建标注
            map.addOverlay(marker2);              // 将标注添加到地图中
        }
        // 小车开始运动

        setTimeout(function(){

            lush.start();
        },1500);
        setTimeout(function(){

            layer.msg("快要到站了！",{icon:6,time:1500,title:"提示"});
        },15000);
        if(list2!=null)
        {
            $.each(list2,function (item,u) {
                var point = new BMap.Point(u.coor_x,u.coor_y);
                lstation.push(point);
                if(item != 0 && item != list.length-1){
                    lpass.push(point);
                    lpauseStation.push({lng:u.coor_x, lat:u.coor_y,html:"", pauseTime:3});//路书格式
                }
            })

            var driving = new BMap.DrivingRoute(map,
                {
                    onSearchComplete: function(res) {
                        if (driving.getStatus() == BMAP_STATUS_SUCCESS) {
                            var plan = res.getPlan(0);
                            var arrPoints = [];
                            for (var j = 0; j < plan.getNumRoutes(); j++) {
                                var route = plan.getRoute(j);
                                arrPoints = arrPoints.concat(route.getPath());
                            }
                            map.addOverlay(new BMap.Polyline(arrPoints, {strokeColor: '#33ffb1'}));
                            map.setViewport(arrPoints);
                            lush2 = new BMapLib.LuShu(map, arrPoints, {
                                defaultContent:"",
                                autoView: false,//是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
                                icon: new BMap.Icon('<%=basePath%>images/bus.png', new BMap.Size(52, 26), {anchor: new BMap.Size(26, 13)}),
                                speed: 45,
                                enableRotation: true,//是否设置marker随着道路的走向进行旋转
                                landmarkPois: lpauseStation   //沿途站点
                            });
                        }
                    }
                });
            driving.search(lstation[0],lstation[lstation.length-1],{waypoints:lpass});//waypoints表示途经点
            //在地图上添加站点标记
            for(var i = 0; i < lstation.length; i++){
                var myIcon = new BMap.Icon("<%=basePath%>images/car.png", new BMap.Size(50,50));
                var marker2 = new BMap.Marker(lstation[i],{icon:myIcon});  // 创建标注
                map.addOverlay(marker2);              // 将标注添加到地图中
            }
            // 小车开始运动
            setTimeout(function(){
                lush2.start();
            },1500);
        }

    }

</script>
</html>