    var path=$("#utable").attr("title");
    var map = new BMap.Map("container");
    var point= new BMap.Point(116.404, 39.915);
    //地图初始化
    function addmap(city)
    {
    map.centerAndZoom(city,10);
    map.enableScrollWheelZoom();//启用滚轮放大缩小
    var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
    var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
    var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL});
    map.addControl(top_left_control);
    map.addControl(top_left_navigation);
    map.addControl(top_right_navigation);
    }
    //添加点坐标
    function addMarker(point){
        var marker = new BMap.Marker(point);
        map.addOverlay(marker);
    }
    addmap(point);
    var val;//城市下拉框文本
    var cid;//城市下拉框id
    function cityselect() {
        layui.use('form', function () {
               var form = layui.form,
                $ = layui.$;
            $.ajax({
                url: path + "siteHandle/stationcity.action",
                type: "post",
                dataType:"json",
                success: function (redata) {
                    $.each(redata,function (i,u) {

                        $("#citys").append
                        (
                            "<option value="+u.cid+">"+u.city+"</option>"

                        )
                        form.render();
                    })

                },

            });
        });

    }

layui.use(['table','form'], function () {
    var table = layui.table,
        form=layui.form,
        $=layui.$;

    form.on('select(city)',function (cityva) {
        val=cityva.elem[cityva.elem.selectedIndex].text;
        cid=cityva.value;
    });


    table.render({
        elem: '#utable'
        , height: 600
        , method:"post"
        , url: path+'siteHandle/stationlist.action'//数据接口
        , page: true //开启分页
        , limit: 10
        , id: 'testReload'
        , parseData: function (res) {

            console.log("返回值" + res.city);
            console.log("状态码" + res.code);
            // console.log("消息" + res.msg);
            console.log("条数" + res.count);
            console.log("数据" + res.data);
            map.clearOverlays();
            $.each(res.data,function (i,u) {
                var points=new BMap.Point(u.coor_x, u.coor_y);
                addMarker(points);
            })
            return {
                "code": eval(res.code), //解析接口状态
                // "msg": res.msg, //解析提示文本
                "count": res.count, //解析数据长度
                "data": res.data//解析数据列表
            };
        }
        , cols: [[ //表头
            {field: 'sid', title: '站点编号', minWidth: 100}
            , {field: 'station', title: '站点名称', minWidth: 150}
            , {field: 'coor_x', title: '站点X坐标', minWidth: 80}
            , {field: 'coor_y', title: '站点Y坐标', minWidth: 80}
            , {field: 'line', title: '站点经过线路', minWidth: 300}
            , {field: 'right',fixed:'right', title: '操作', minWidth: 300, toolbar: '#barDemo'}
        ]]
    });
    table.on('tool(test)', function(obj) {
        var data = obj.data;

        if (obj.event === 'Del') {
            layer.confirm('确定删除？站点:'+data.station, function (index) {

                sdel(path+"siteHandle/stationdel.action?sid="+data.sid+"&cid="+data.cid);
                layer.close(index);
            });
        }else if(obj.event==="edit")
        {
            layer.open({
                type: 2
                ,title: "站点修改" //不显示标题栏
                ,closeBtn: false
                ,area: ['800px', '800px']
                ,shade: 0.8
                ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                ,btn: [ '返回']
                ,btnAlign: 'c'
                ,moveType: 1 //拖拽模式，0或者1
                ,content: path+'siteHandle/site_uppage.action?city='+data.city+'&sid='+data.sid
                    +'&coor_x='+data.coor_x+'&coor_y='+data.coor_y+'&station='+data.station
                ,success: function(layero){
                    var btn = layero.find('.layui-layer-btn');
                    btn.find('.layui-layer-btn0').attr({

                        target: '_blank'
                    });
                    table.reload('testReload',{},'data');
                }
            });
        }
    });
    /* function fal(url,data) {

     }*/
    //触发查询按钮
    var $ = layui.$, active = {
        reload: function () {
            var station= $('#stiename');
            var city = $('#citys');
            addmap(val);
            //执行重载
            table.reload('testReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    station: station.val(),
                    city: city.val(),


                }
            }, 'data');
        },
        add:function () {
            if(cid==null||cid=="")
            {
                layer.msg("请先选择城市",{icon:5,time:1500,title:"提示"})
            }else
            {
            layer.open({
                type: 2
                ,title: "站点新增" //不显示标题栏
                ,closeBtn: false
                ,area: ['800px', '800px']
                ,shade: 0.8
                ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                ,btn: ['返回']
                ,btnAlign: 'c'
                ,moveType: 1 //拖拽模式，0或者1
                ,content: path+'siteHandle/site_addpage.action?city='+val+'&cid='+cid
                ,success: function(layero){
                    var btn = layero.find('.layui-layer-btn');
                    btn.find('.layui-layer-btn0').attr({
                        target: '_blank'
                    });
                }
            });
            }
        }
    };

    $('.demoTable .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    })
    //删除
    function sdel(url) {
        $.ajax({
            url: url,
            type: "post",
            dataType:"text",
            success: function (redata) {
                if (redata=="0")
                {
                    layer.msg("删除成功",{icon:1,time:1500,title:"提示"});
                    table.reload('testReload',{},'data');
                }else
                {
                    layer.msg("删除失败",{icon:5,time:1500,title:"提示"});
                }
            },

        });
    }
});
