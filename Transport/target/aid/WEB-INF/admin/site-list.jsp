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
    <script src="<%=basePath%>lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/xadmin.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=eB3UC9RiYf7GGhjGC7XBAuk3WRKZitTG"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/library/MarkerTool/1.2/src/MarkerTool_min.js"></script>
</head>

<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="demoTable">

                    <div class="layui-form layui-input-inline">
                        <select id="citys" lay-filter="city" >
                            <option>厦门</option>
                            <option>泉州</option>
                            <option>福州</option>
                        </select>
                    </div>
                    <div class="layui-inline ">
                        <input type="text" id="stiename" name="stiename" placeholder="请输入站点名称" autocomplete="off"
                               class="layui-input"></div>
                    <div class="layui-inline">
                        <button class="layui-btn" data-type="reload">查询</button>
                        <button class="layui-btn" data-type="add">新增站点</button>
                    </div>

                </div>
                <div class="layui-card-body" align="center">
                    <table class="layui-table" lay-filter="test" id="utable" align="center">
                    </table>
                    <%--                地图--%>
                    <div  style="width:800px;height:600px;border:1px solid gray" id="container">
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
<script id="barDemo" type="text/html">
    <a class="layui-btn layui-btn-xs " lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="Del">删除</a>
</script>
<script src="<%=basePath%>lib/layui/layui.js"></script>
<script>

    function addmap(city) {
        var map = new BMap.Map("container");
        var point= new BMap.Point(116.404, 39.915);
        // alert(city);
        map.centerAndZoom(city,16);
    }
    window.onload=function (ev) {

    }
    layui.use(['table','form'], function () {
        var table = layui.table,
        form=layui.form;
        form.on('select(city)',function (cityva) {
            var val=cityva.value;
            addmap(val);
        });
        table.render({
            elem: '#utable'
            , height: 600
            , url: '<%=basePath%>admin/find.action' //数据接口
            , page: true //开启分页
            , limit: 10
            , id: 'testReload'
            , parseData: function (res) {

                console.log("返回值" + res);
                console.log("状态码" + res.code);
                // console.log("消息" + res.msg);
                console.log("条数" + res.count);
                console.log("数据" + res.data);

                return {
                    "code": eval(res.code), //解析接口状态
                    // "msg": res.msg, //解析提示文本
                    "count": res.count, //解析数据长度
                    "data": res.data//解析数据列表
                };
            }
            , cols: [[ //表头
                {field: 'sid', title: '序号', minWidth: 100}
                , {field: 'station', title: '站点名称', minWidth: 150}
                , {field: 'coor_x', title: '站点X坐标', minWidth: 80}
                , {field: 'coor_y', title: '站点Y坐标', minWidth: 80}
                , {field: 'stationline', title: '站点经过线路', minWidth: 300}
                , {field: 'right',fixed:'right', title: '操作', minWidth: 300, toolbar: '#barDemo'}
            ]]
        });
        table.on('tool(test)', function(obj) {
            var data = obj.data;

            if (obj.event === 'Del') {
                layer.confirm('确定删除？ID:'+data.userid, function (index) {
                    fal("<%=path%>useman/userMana!useDel.action",data.userid);
                    layer.close(index);
                });
            }else if(obj.event==="edit")
            {
                layer.open({
                    type: 1
                    ,title: "用户修改" //不显示标题栏
                    ,closeBtn: false
                    ,area: '600px;'
                    ,shade: 0.8
                    ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                    ,btn: ['修改', '返回']
                    ,btnAlign: 'c'
                    ,moveType: 1 //拖拽模式，0或者1
                    ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;"><div class="layui-form-item">\n' +
                        '                        <label for="L_email" class="layui-form-label">\n' +
                        '                            <span class="x-red">*</span>邮箱</label>\n' +
                        '                        <div class="layui-input-inline">\n' +
                        '                            <input type="text" id="L_email" name="email" required="" lay-verify="email" autocomplete="off" class="layui-input" value="'+data.userid+'"></div>\n' +
                        '                        <div class="layui-form-mid layui-word-aux">\n' +
                        '                            <span class="x-red">*</span>将会成为您唯一的登入名</div></div>\n' +
                        '                    <div class="layui-form-item">\n' +
                        '                        <label for="L_username" class="layui-form-label">\n' +
                        '                            <span class="x-red">*</span>昵称</label>\n' +
                        '                        <div class="layui-input-inline">\n' +
                        '                            <input type="text" id="L_username" name="username" required="" lay-verify="nikename" autocomplete="off" class="layui-input"></div>\n' +
                        '                    </div>\n' +
                        '                    <div class="layui-form-item">\n' +
                        '                        <label for="L_pass" class="layui-form-label">\n' +
                        '                            <span class="x-red">*</span>密码</label>\n' +
                        '                        <div class="layui-input-inline">\n' +
                        '                            <input type="password" id="L_pass" name="pass" required="" lay-verify="pass" autocomplete="off" class="layui-input"></div>\n' +
                        '                        <div class="layui-form-mid layui-word-aux">6到16个字符</div></div>\n' +
                        '                    <div class="layui-form-item">\n' +
                        '                        <label for="L_repass" class="layui-form-label">\n' +
                        '                            <span class="x-red">*</span>确认密码</label>\n' +
                        '                        <div class="layui-input-inline">\n' +
                        '                            <input type="password" id="L_repass" name="repass" required="" lay-verify="repass" autocomplete="off" class="layui-input"></div>\n' +
                        '                    </div>' +
                        '</div>'
                    ,success: function(layero){
                        var btn = layero.find('.layui-layer-btn');
                        btn.find('.layui-layer-btn0').attr({
                            href: 'http://www.layui.com/'
                            ,target: '_blank'
                        });
                    }
                });
            }
        });
       /* function fal(url,data) {

        }*/
        //触发查询按钮
        var $ = layui.$, active = {
            reload: function () {
                var userid = $('#userid');
                var ftime = $('#ftime');
                var ltime = $('#ltime');
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        userid: userid.val(),
                        ftime: ftime.val(),
                        ltime: ltime.val(),

                    }
                }, 'data');
            },
            add:function () {
                layer.open({
                    type: 2
                    ,title: "用户修改" //不显示标题栏
                    ,closeBtn: false
                    ,area: ['800px', '800px']
                    ,shade: 0.8
                    ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                    ,btn: ['返回']
                    ,btnAlign: 'c'
                    ,moveType: 1 //拖拽模式，0或者1
                    ,content: '<%=basePath%>SiteHandle/site_addpage.action'
                    ,success: function(layero){
                        var btn = layero.find('.layui-layer-btn');
                        btn.find('.layui-layer-btn0').attr({
                            target: '_blank'
                        });
                    }
                });
            }
        };

        $('.demoTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        })
    });

</script>

</html>