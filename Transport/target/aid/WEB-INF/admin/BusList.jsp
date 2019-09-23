<%@ page language="java" contentType="text/html;"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%pageContext.setAttribute("path",request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()); %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>公交系统</title>
    <link href="${path}/layui/css/layui.css" rel="stylesheet"  media="all"/>
	<script type="text/javascript" src="${path}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/layui/layui.js"></script>
	<script type="text/javascript" src="${path}/layui/lay/modules/jquery.js"></script>
	<script type="text/javascript" src="${path}/layui/lay/modules/layer.js"></script>
	<script type="text/javascript" src="${path}/layui/lay/modules/form.js"></script>
	<script type="text/javascript" src="${path}/layui/layui.all.js"></script>

<script type="text/javascript">


</script>
</head>

<body>
<%--新增的弹框--%>
<form class="layui-form layui-form-panel" id="form2" name="form2" style="display: none;" action="${path}/bus/change.action" method="post" lay-filter="first1">
<%--	<input type="hidden" name="oldbid"/>--%>

	<label class="layui-form-label">${city.city}市</label>
	<div class="layui-input-inline">
		<input type="text" name="bid"  lay-verify="" readonly="readonly" class="layui-input">
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">车牌</label>
		<div class="layui-input-inline">
			<input type="text" name="bid" id="bid-add" lay-verify="required" class="layui-input">
		</div>
	</div>

	<div class="layui-form-item">
		<label class="layui-form-label">车名</label>
		<div class="layui-input-inline">
			<input type="text" name="bus" id="bus-add" lay-verify="required" class="layui-input">
		</div>
	</div>

	<div class="layui-form-item">
		<label class="layui-form-label">维护人</label>
		<div class="layui-input-inline">
			<select id = "protector-add" name="protector" lay-filter = "protector">
				<c:forEach items="${protectors}" var="p">
					<option value="${p.pid}">${p.param}</option>
				</c:forEach>
			</select>
		</div>
	</div>

	<div class="layui-form-item">
		<label class="layui-form-label">上牌年限</label>
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="year-add" name="year" lay-verify="required" placeholder="yyyy">
		</div>
	</div>

	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="*">确定</button>
		</div>
	</div>
</form>

<%--修改的弹框--%>
<form class="layui-form layui-form-panel" id="form1" name="form1" style="display: none;" action="${path}/bus/change.action" method="post" lay-filter="first1">

<%--	<div class="layui-form-item">--%>
<%--		<label class="layui-form-label">id</label>--%>
<%--		<div class="layui-input-inline">--%>
<%--			<input type="text" name="bus" id="bus" lay-verify="required|title" required readonly="readonly" autocomplete="off" class="layui-input">--%>
<%--		</div>--%>
<%--	</div>--%>
	<input type="hidden" id="oldbid" name="oldbid"/>
	<div class="layui-form-item">
		<label class="layui-form-label">车牌</label>
		<div class="layui-input-inline">
			<input type="text" name="bid" id="bid" lay-verify="required" class="layui-input">
		</div>
	</div>

	<div class="layui-form-item">
		<label class="layui-form-label">车名</label>
		<div class="layui-input-inline">
			<input type="text" name="bus" id="bus" lay-verify="required" class="layui-input">
		</div>
	</div>

	<div class="layui-form-item">
		<label class="layui-form-label">维护人</label>
		<div class="layui-input-inline">
			<select id = "protector" name="protector" lay-filter = "protector">
				<c:forEach items="${protectors}" var="p">
					<option value="${p.pid}">${p.param}</option>
				</c:forEach>
			</select>
		</div>
	</div>

	<div class="layui-form-item">
		<label class="layui-form-label">上牌年限</label>
		<div class="layui-input-inline">
			<input type="text" class="layui-input" id="year" name="year" lay-verify="required" placeholder="yyyy">
		</div>
	</div>

	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="*">修改</button>
		</div>
	</div>
</form>
<%--条件查询--%>
<div>
	<label class="layui-form-label" style="width: 120px">${city.city}市公交车列表</label>
</div>
<div class="layui-form">
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">是否固定线路</label>
			<div class="layui-input-inline">
				<select name="online-1" id="online-1">
					<option value="是">是</option>
					<option value="否">否</option>
				</select>
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">车辆状态</label>
			<div class="layui-input-inline">
				<select name="state" id="state-find">//循环添加
				<c:forEach items="${stateList}" var="s"><%--来自参数表--%>
					<option value="${s.pid}">${s.param}</option>
				</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">线路</label>
			<div class="layui-input-inline">
				<select name="line" id="line-find">//循环添加下拉框所有线路
					<c:forEach items="${lineList}" var="s"><%--来自参数表--%>
						<option value="${s.lid}">${s.line}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">维护人</label>
			<div class="layui-input-inline">
				<select name="protector" id="protector-find">//循环添加下拉框所有维护人
					<c:forEach items="${protectors}" var="p"><%--来自参数表--%>
						<option value="${p.pid}">${p.param}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">车牌</label>
			<div class="layui-input-inline">
				<select name="bid" id="bid-find">//循环添加下拉框所有车牌
					<c:forEach items="${allBus}" var="b"><%--来自参数表--%>
						<option value="${b.bid}">${b.bus}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<button class="layui-btn" data-type="find" style="margin-left: 6%">搜索</button>
		<button class="layui-btn" data-type="add" style="margin-left: 3%">新增</button>
	</div>
</div>
<%--显示数据的表格--%>
    <table id="list" class="layui-table" lay-filter="test"></table>
	<input type="hidden" id="cid" value="${bus.cid}">
<%--显示数据的表格--%>

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

	</script>
    <script>
		var cid=$("#cid").val();
		layui.use(['table','layer','laydate','form'],function () {
			var table=layui.table,
				layer=layui.layer,
			laydate=layui.laydate,

			form=layui.form;

			form.render();

			laydate.render({
				elem: '#year',
				type: "year"
			});
            table.render({
                elem:'#list',
                height:350,
                url:'${path}/bus/findBus.action?cid='+cid,
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
					$("#oldbid").val(data.bid);//修改之前的车牌
					$("#bid").val(data.bid);
					$("#bus").val(data.bus);
					var str = "<option value="+data.protector+">"+data.protector+"</option>";
					$("#protector").append(str);
					form.render();//渲染下拉框
					//弹出修改窗口
					layer.open({
						type: 1,
						title: "修改",
						area: ['420px', '530px'],
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
					dataType:"json",
					data:data,
					success:function(res){
						//执行重载
						table.reload('reload', {
						});
					},

				})
			}
			var $ = layui.$, active = {
				find: function(){
					//执行重载
					table.reload('reload', {
						page: {
							curr: 1 //重新从第 1 页开始
						}
						,where: {
							key: {
								online: $("#online-find").val(),
								state:$("#state-find").val(),
								line:$("#line-find").val(),
								protector:$("#protector-find").val(),
								bid:$("#bid-find").val()
							}
						}
					}, 'data');
				}
			};
			//监听条件查询
			$('.layui-form .layui-btn').on('click', function(){
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});

        })
    </script>
<%--$.ajax({--%>
<%--	type:"post",--%>
<%--	url:"${path}/bus/findDriver.action",//查询所有司机--%>
<%--	dataType:"json",--%>
<%--	data:data,--%>
<%--	success:function(res){--%>
<%--		var json=data;--%>
<%--		$("#protector").empty();//清空下拉框--%>
<%--		$.each(json,function(i,value){--%>
<%--			$("#protector").append("<option value='"+value.aid+"'>"+value.aname+"</option>")//添加新的数据--%>
<%--		})--%>
<%--	},--%>

<%--})--%>

</div>
</body>
</html>