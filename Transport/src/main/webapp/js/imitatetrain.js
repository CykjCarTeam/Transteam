window.onload = function(){

    layui.use(['table','form','layer'], function(){
        var table = layui.table,
            form  = layui.form,
            layer = layui.layer;
            $ = layui.$;
        //获取下拉框的值
        form.on('select(city)', function(data){
            var val = data.value;
            var centerCity = data.elem[data.elem.selectedIndex].text;//获取下拉框文本内容

            if(val != "-1"){
                $("#load").show();//显示数据表格
                loadData(table,val,centerCity);
            }else{
                $("#load").hide();
            }
        });
    });

}

/**
 * 加载路线表格
 * @param table
 * @param city_id  定位的城市id
 * @param city    定位的城市
 */
function loadData(table,city_id, city) {

    var path = $("#line").attr("title");//获取根路径
    //数据接口
    table.render({
        elem: '#line'
        ,height: 312
        ,url: path + 'lineHandle/init.action?cid='+city_id //数据接口
        ,page: true //开启分页
        ,id: 'lineTable'
        ,method:"post"
        ,parseData:function(res){
            return {
                "code": eval(res.code), //解析接口状态
                "msg": res.msg, //解析提示文本
                "count": res.count, //解析数据长度
                "data": res.data //解析数据列表
            };
        }
        ,cols: [[ //表头
            {field: 'line', title: '线路名称'}
            ,{field: 'station_count', title: '经站点数',templet:function(data){
                return data.allStations.length;
            }}
            ,{field: 'bus_count', title: '线路在用车辆',templet:function(data){
                return data.allBus.length;
                }}
            ,{fixed: 'right', title:'操作', toolbar: '#toolBar', width:250}
        ]]
    });

    //监听行工具事件（rout对应表格的lay-filter值）
    table.on('tool(rout)', function(obj){//obj指整个表格对象
        var data = obj.data;//具体某一行
            //查看路线
         if(obj.event === 'imitate'){

            loadMap(path, data.allStations,city);//加载路线地图
            layer.open(
                {
                    type:1, //页面
                    title:data.line,
                    area:['800px','600px'],
                    content:$("#lineMap"),
                    cancel: function(index){
                        layer.close(index);
                    }
                }
            );
        }
    });

}

/**
 * 加载地图
 * @param path 根路径
 * @param stationData  站点数据
 * @param centerCity  中心城市
 */
function loadMap(path, stationData, centerCity){

    // 百度地图API功能
    var map = new BMap.Map("lineMap");
    map.centerAndZoom(centerCity, 18);
    map.enableScrollWheelZoom(true);

    var station = [];//站点
    var station_info = [];//站点信息
    var pass = [];//经站点
    var pauseStation = [];//路书上的停站点
    var lush = null;//路书

    $.each(stationData, function(item,val){

        var point = new BMap.Point(val.coor_x,val.coor_y);//生成地图上的点
        station.push(point);
        station_info.push(val.station);
        if(item != 0 && item != stationData.length - 1){
            pass.push(point);
            pauseStation.push({lng:val.coor_x, lat:val.coor_y, html:val.station, pauseTime:3});//路书格式
        }
    });

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
                        autoView: true,//是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
                        icon: new BMap.Icon(path+'images/bus.png', new BMap.Size(52, 26), {anchor: new BMap.Size(26, 13)}),
                        speed: 45,
                        enableRotation: true,//是否设置marker随着道路的走向进行旋转
                        landmarkPois: pauseStation   //沿途站点
                    });
                }
            }
        });

    driving.search(station[0],station[station.length-1],{waypoints:pass});//waypoints表示途经点
    //在地图上添加站点标记
    for(var i = 0; i < station.length; i++){
        var myIcon = new BMap.Icon(path+"images/car.png", new BMap.Size(50,50));
        var marker2 = new BMap.Marker(station[i],{icon:myIcon});  // 创建标注
        var label = new BMap.Label(station_info[i],{position:station[i]});
        map.addOverlay(marker2);              // 将标注添加到地图中
        map.addOverlay(label);
    }

   // 小车开始运动
    setTimeout(function(){
        lush.start();
      //  lush.showInfoWindow();为啥会未定义
    },1500);

}

