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
        ,limit:3
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
        //删除某一行
        if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                //ajax后台删除
                $.ajax({
                    type:"post",
                    url:path + "lineHandle/del.action",
                    dataType:"text",
                    data:{"lid":data.lid},
                    success:function(res){
                        if(res == "1"){
                            table.reload('lineTable',{},'data');
                            layer.msg("删除成功！")
                        }else{
                            layer.msg("删除失败！");
                        }
                    }
                });
                layer.close(index);
            });
            //查看路线
        }else if(obj.event === 'see'){

            loadMap(path, data.allStations,city);//加载路线地图
            layer.open(
                {
                    type:1, //页面
                    title:data.line,
                    area:['500px','500px'],
                    content:$("#lineMap"),
                    cancel: function(index){
                        layer.close(index);
                    }
                }
            );
            //路线编辑
        }else{
            layer.prompt({
                title:"线路编辑"
                ,formType: 2
                ,value: data.line //修改的位置
            }, function(value, index){

                $.ajax({
                    type:"post",
                    url:path + "lineHandle/upd.action",
                    dataType:"text",
                    data:{"line":value, "lid": data.lid},
                    success:function(res){

                        if(res == "1") {
                            layer.msg("线路更新成功！");
                            obj.update({
                                line: value
                            });
                            layer.close(index);
                            return;
                        }

                        layer.msg("该线路已存在！");

                    }
                });

            });

        }
    });

    //根据data-type值调用对应事件
    var active = {//这东西相当于一个事件数组
        query: function(){
            var line_info = $('#line_info');
            //执行重载
            table.reload('lineTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    line: line_info.val()
                }
            }, 'data');
        },
        add: function(){
            //开启弹窗
            layer.open(
                {
                    type:2, //网页
                    title:"配置路线",
                    area:['500px','530px'],
                    content:path + 'lineHandle/addline.action',
                    cancel: function(index){
                        layer.close(index);
                        table.reload('lineTable',{},'data');
                    }
                }
            );
        }
    };

    //监听查询按钮事件
    $('.condition .layui-btn').on('click', function(){
        var type = $(this).data('type'); //获取data-type里的值
        //相当于遍历匹配数组中的元素(匹配到就回调)
        active[type] ? active[type].call(this) : '';
    });
}

/**
 * 加载地图
 * @param path 根路径
 * @param stationData  站点数据
 * @param centerCity  中心城市
 */
function loadMap(path, stationData, centerCity){

    console.log("站点名" + stationData[0].station);
    console.log("站点经度" + stationData[0].coor_x);
    console.log("站点纬度" + stationData[0].coor_y);
    console.log("中心城市" + centerCity);
    // 百度地图API功能
    var map = new BMap.Map("lineMap");
    map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
    map.enableScrollWheelZoom(true);

    var data = [
        [116.301934,39.977552, "厦大西村"],
        [116.365942,39.996165, "高林街道"],
        [116.462801,39.97694, "T4候机楼"],
        [116.508328,39.919141, "软件园东二门"]
    ];

    var station = [];//站点
    var station_info = [];//站点信息
    var pass = [];//经站点
    //真数据
    $.each(stationData, function(item,val){
        console.log(val.station);
        console.log(val.coor_x);
        // var point = new BMap.Point(val.coor_x,val.coor_y);//生成地图上的点
        // station.push(point);
        // station_info.push(val.station);
        // if(item != 0 && item != data.length - 1){
        //     pass.push(point);
        // }
    });
    //假数据
    $.each(data, function(item,val){
        var point = new BMap.Point(val[0], val[1]);//生成地图上的点
        station.push(point);
        station_info.push(val[2]);
        if(item != 0 && item != data.length - 1){
            pass.push(point);
        }
    });
    var driving = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});

    driving.search(station[0],station[station.length-1],{waypoints:pass});//waypoints表示途经点

    for(var i = 0; i < station.length; i++){
        var myIcon = new BMap.Icon(path+"images/car.png", new BMap.Size(50,50));
        var marker2 = new BMap.Marker(station[i],{icon:myIcon});  // 创建标注
        var label = new BMap.Label(station_info[i],{position:station[i]});
        map.addOverlay(marker2);              // 将标注添加到地图中
        map.addOverlay(label);
    }

}