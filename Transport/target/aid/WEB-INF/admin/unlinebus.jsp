<%@ page language="java" contentType="text/html;" pageEncoding="utf-8" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>lib/layui/css/layui.css" media="all">
    <meta name="renderer" content="webkit">
    <title>实时停站车辆查询</title>
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <script src="<%=basePath%>lib/layui/layui.js" charset="utf-8"></script>

</head>

<body onload="province()">

<h2>实时停站车辆查询</h2>
<p></p>
<br/>
<div class="demoTable">
    <div class="layui-form-item">
        <div class="layui-inline">
            省份：
            <select id="province" name="province" onchange="city()">
                <option value=''>请选择省</option>
            </select>
        </div>
        <div class="layui-inline">
            城市：
            <select id="city" name="city">
                <option value=''>请选择市</option>
            </select>
        </div>
        <div class="layui-inline">
            站点： <input clsass="layui-input" name="station" id="station" autocomplete="off" placeholder="请输入总站名">
        </div>
        车牌：
        <div class="layui-inline">
            <input clsass="layui-input" name="bid" id="bid" autocomplete="off" placeholder="请输入车牌">
        </div>
        <button class="layui-btn" data-type="reload">搜索</button>
    </div>
</div>
<table class="layui-hide" id="LAY_table_user" lay-filter="user"></table>
</th>

<script src="<%=path%>layui/layui.js" charset="utf-8"></script>
<script>
    layui.use('table', function () {
        var table = layui.table;
        table.render({
            elem: '#LAY_table_user'
            , url: '<%=basePath%>arrangeHandle/remove.action'
            , cols: [[
                {field: 'bid', title: '车牌', width: 100, sort: false,
                    templet: function (data) {return data.bus.bid;}
                    }
                , {field: 'station', title: '停靠站点', width: 160, sort: false,
                    templet: function (data) {return data.bus.station.station;}
                    }
                , {field: 'intotime', title: '已停靠', width: 160, sort: false,
                        templet: function (data) {return data.bus.intotime;}

                }
                , {field: 'times', title: '临近排班发车时间', width: 200, sort: false}
                , {field: 'aaa', toolbar: '#barDemo', title: '操作', width: 200}
            ]]
            , id: 'testReload'
            , page: true
            // ,height: 310
        });
        //救援调度
        table.on('tool(user)', function (obj) {
            var data = obj.data;
            if (obj.event === 'sos') {
                layer.confirm('是否对该车辆执行指派任务', function (index) {
                    obj.update({
                    });
                    {document.getElementById("sos").innerHTML="已成功派往";
                        document.getElementById("sos").style.background = "red"}
                    layer.close(index);
                });
            }
        });
        //条件查询
        var $ = layui.$, active = {
            reload: function () {
                var bid = $('#bid');
                var station = $('#station');
                var province = $('#province');
                var city = $('#city');
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        bid:bid.val(),
                        station:station.val(),
                        city:city.val(),
                        province:province.val()
                    }
                }, 'data');
            }
        };
        $('.demoTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });

    //下拉框
    function province() {
        $.ajax({
            url: '<%=basePath%>arrangeHandle/province.action',
            type: "POST",
            data: {},
            success: function (Data) {
                var list = Data;
                if (list != null) {
                    $("#province").empty();
                    $("#province").append("<option value=''>请选择省</option>");
                    $.each(list, function (i, n) {

                        $("#province").append("<option value='" + list[i].province + "'>" + list[i].province + "</option>");
                    });
                }
                ;
            }
        });
    }

    function city() {
        var province = $('#province').val();
        $.ajax({
            url: '<%=basePath%>arrangeHandle/city.action',
            type: "POST",
            data: {"province": province},
            success: function (Data) {
                var list = Data;
                if (list != null) {
                    $("#city").empty();
                    $("#city").append("<option value=''>请选择市</option>");
                    $.each(list, function (i, n) {
                        $("#city").append("<option value='" + list[i].city + "'>" + list[i].city + "</option>");
                    });
                }
                ;
            }
        });
    }
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="sos" id="sos" >救援调度</a>
</script>

</body>
</html>
