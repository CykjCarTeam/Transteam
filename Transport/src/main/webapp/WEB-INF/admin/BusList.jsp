<%@ page language="java" contentType="text/html;"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%pageContext.setAttribute("path",request.getContextPath()); %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>公交系统</title>
    <link href="${path}/layui/css/layui.css" rel="stylesheet"  media="all"/>
	<script type="text/javascript" src="${path}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${path}/layui/layui.js"></script>
<script type="text/javascript">

	function del(uid){
		if (uid=="+a1+") {
			alert("管理员不能被删除");
			return false;
		}
		if (confirm("确定删除？")) {
			return true;
		}
		return false;
	}
	function forbid(uid){
		if (uid=="+a1+") {
			alert("管理员不能被禁用");
			return false;
		}
		if (confirm("确定禁用？")) {
			return true;
		}
		return false;
	}
	function allow(){
		if (confirm("确定启用？")) {
			return true;
		}
		return false;
	}
	function reset(){
		if (confirm("确定重置？")) {
			return true;
		}
		return false;
	}
</script>
</head>

<body>
<%--修改的弹框--%>
<form class="layui-form layui-form-pane1" id="form1" name="form1" style="display: none;" action="${path}/bus/change.action" method="post" lay-filter="first1">
	<div class="layui-form-item" style="display: none">
		<label class="layui-form-label">id</label>
		<div class="layui-input-inline">
			<input type="text" name="id" id="id" lay-verify="required|title" required  autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">车牌</label>
		<div class="layui-input-inline">
			<input type="text" name="bus" id="bus" lay-verify="required|title" required readonly="readonly" autocomplete="off" class="layui-input">
		</div>
	</div>

	<div class="layui-form-item">
		<label class="layui-form-label">维护人</label>
		<div class="layui-input-inline">
			<input type="text" name="protector" id="protector" lay-verify="required|pass" placeholder="" autocomplete="off" class="layui-input">
		</div>
	</div>

	<div class="layui-form-item">
		<label class="layui-form-label">年限</label>
		<div class="layui-input-inline">
			<input type="text" name="year" id="year" lay-verify="required|pass" placeholder="" autocomplete="off" class="layui-input">
		</div>
	</div>

	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="*">修改</button>
		</div>
	</div>
</form>

<div id="bar">
	用户列表信息
</div>
<form action="${path}/user" method="post">
	<div id="button">
		<input type="hidden" name="method" value="find">
		姓名：<input type="text" name="name" style="width:70px;"/>
		状态：<select name="state">
		<option value="" selected="selected">全部</option>
		<option value="ok">启用</option>
		<option value="no">禁用</option>
	</select>
		职称：<input type="text" name="rank" style="width:70px;"/>
		<input type="submit" name="rank" value="查询"/>
	</div>
</form>
    <table id="list" class="layui-table" lay-filter="test"></table>
        <script type="text/html" id="switchTpl">
            <!-- 这里的 checked 的状态只是演示 -->
            <input type="checkbox" name="sex" value="{{d.uname}}" lay-skin="switch" lay-text="禁用|启用" lay-filter="sexDemo" {{ d.state ==='0' ? 'checked' : '' }}>
        </script>

        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>
	<script type="text/html" id="status">
		{{# if(d.status != '报废停用' ){ }}
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="use">排班</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="stop">报废停用</a>
		 {{# }}}
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="change">修改</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>

<%--	    {{# if(d.uname != 'a1' ){ }}--%>
<%--                {{# if(d.state === '0' ){ }}--%>
<%--    <button type="button" class="layui-btn" lay-event="close">禁用</button>--%>
<%--                {{# }}}--%>
<%--                {{# if(d.state === '1' ){ }}--%>
<%--    <button type="button" class="layui-btn" lay-event="open">启用</button>--%>
<%--                {{# }}}--%>
<%--	     {{# }}}--%>

<%--            else{d.templet=#switchTpl }}--%>
<%--			{{d.templet}} --%>
<%--			{{# }}}--%>
	</script>
    <script>
		layui.use('table',function () {
			var table=layui.table,
				// layer=layui.layer,
                form = layui.form;
            table.render({
                elem:'#list',
                height:250,
                url:'${path}/bus/findBus.action?cid=${bus.cid}',
                page:true,//开启分页
				limit:3,
                id:'reload',
                cols:[[//表头
                    {field:'bid',title:'序号',width:80,align:'center',sort:true},
                    {field:'bus',title:'车牌',width:80,align:'center',sort:true},
                    {field:'protector',title:'维护人',align:'center',width:80},
                    {field:'status',title:'状态',align:'center',width:120},
                    {field:'down',title:'全天工作时间',align:'center',sort:true},
					{field:'year',title:'可用年限',align:'center',width:120,sort:true},
                    <%--{field:'event',title:'操作',width:80,sort:true,templet:function (d) {--%>
                    <%--        return d.state=='0'?"<a onclick='return forbid(''+${d.uname}+'')' href='${path}/user/changeState.action?state=${d.state}&uname=${d.uname}&start=${nowPage}'>禁用</a>":--%>
                    <%--            "<a onclick='return allow()' href='${path}/user/changeState.action?state=${d.state}&uname=${d.uname}&start=${nowPage}'>启用</a>"--%>
                    <%--    }},--%>
					// {field:'change',title:'启禁',width:80,templet: '#switchTpl', unresize: true,align:'center'},
					{field:'change',title:'操作',templet:"#status", unresize: true,align:'center'},
                ]]

            });

            table.on('tool(test)', function(obj){
                var data = obj.data;//获取本行
                if(obj.event === 'use'){
                    //排班

                }
                if(obj.event === 'change'){
                	//修改
					$("#bus").val(data.bus);
					$("#protector").val(data.protector);
					$("#year").val(data.year);
					//弹出修改窗口
					layer.open({
						type: 1,
						title: "修改",
						area: ['420px', '330px'],
						content: $("#form1")//引用的弹出层的页面层的方式加载修改界面表单
					});
                }

                if(obj.event === 'del'){
					//删除
					layer.confirm('确定删除？', function(index){
						var msg=[{"bid":data.bid}]
						operation("${path}/bus/delete.action",msg);
						layer.close(index);
					});
                }
				if(obj.event === 'stop'){
					//报废
					layer.confirm('确定停用？', function(index){
						var msg=[{"bid":data.bid}]
						operation("${path}/bus/stop.action",msg);
						layer.close(index);
					});
				}
            });
            //操作使用同一个方法
			function operation(url,data){
				$.ajax({
					type:"post",
					url:url,
					dataType:"text",
					data:data,
					success:function(res){
						//执行重载
						table.reload('docReload', {

						});
					},

				})
			}

            //状态
            form.on('switch(sexDemo)', function(obj){
            	var uname=this.value;//this是这个按钮，包含name、value、title等
				change("${path}/user/state.action",uname,obj.elem.checked);
            });

        })
    </script>


</div>
</body>
</html>