
<%@ page language="java" contentType="text/html;" pageEncoding="utf-8" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css"  media="all">
    <meta name="renderer" content="webkit">
    <title>车辆排班</title>
<script src="<%=basePath%>assets/js/jquery.min.js"></script>
<script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>

</head>

<body>

<table class="layui-hide" id="LAY_table_user" lay-filter="user"></table>
<script>
    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#LAY_table_user'
            ,url: '<%=basePath%>adminHandle/findline.action'
            ,cols: [[
                {checkbox: true, fixed: true}
                ,{field:'lid', title: '序号', width:100, sort: true, fixed: true}
                ,{field:'line', title: '线路', width:150, sort: true, fixed: true}
                ,{field:'right',toolbar: '#barDemo', title: '操作',  width:150}
            ]]
            ,id: 'testReload'
            ,page: true
            // ,height: 310
        });
        //弹窗
        table.on('tool(user)', function(obj){
            var data = obj.data;
            if(obj.event === 'paiban'){
                //弹出修改窗口
                layer.open({
                type: 2,
                title: "排班",
                area: ['970px', '500px'],
                content:"<%=basePath%>adminHandle/arrange.action"+"?lid="+encodeURIComponent(data.lid)
            });
        }
        });
        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>

<script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="paiban" >排班</a>
</script>

</body>
</html>
