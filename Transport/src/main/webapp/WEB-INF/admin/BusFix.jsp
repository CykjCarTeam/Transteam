<%@ page language="java" contentType="text/html;"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%pageContext.setAttribute("path",request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()); %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>车辆维修信息</title>
    <link href="${path}/layui/css/layui.css" rel="stylesheet"  media="all"/>
	<script type="text/javascript" src="${path}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/layui/layui.js"></script>
	<script type="text/javascript" src="${path}/layui/lay/modules/jquery.js"></script>
	<script type="text/javascript" src="${path}/layui/lay/modules/layer.js"></script>
	<script type="text/javascript" src="${path}/layui/lay/modules/form.js"></script>
	<script type="text/javascript" src="${path}/layui/layui.all.js"></script>

<script type="text/javascript">
    $(function(){
        $("#bid-add").blur(function () {//新增车牌唯一
            var bid=$("#bid-add").val();
            $.ajax({
                url:"${path}/bus/checkBid.action",
                type:"post",
                data:{"bid":bid},
                dataType:"text",
                success:(function (res) {
                    if(res=="1"){
                        alert("该车牌已存在");
                    }
                })
            })
        })
    });

</script>
</head>

<body>
<input type="hidden" id="cid" value="${city.cid}">
<%--//后台回传，再发送请求需要--%>

<div class="layui-inline">
	<label class="layui-form-label" style="width: 150px">${city.city}市车辆维修查看</label>
</div>

<%--在修车辆列表--%>
<div>
	<label class="layui-form-label" style="width: 120px">在修车辆查看</label>
</div>
<table id="onFix" class="layui-table" lay-filter="test"></table>

<div>
	<label class="layui-form-label" style="width: 120px">维修记录查看</label>
</div>
<div class="layui-form">
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">车牌</label>
			<div class="layui-input-inline">
				<select name="bid" id="bid-find">//循环添加下拉框所有车牌
                    <option value="">所有</option>
					<c:forEach items="${allBus}" var="b"><%--来自参数表--%>
						<option value="${b.bid}">${b.bid}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<button class="layui-btn" data-type="find" style="margin-left: 6%">搜索</button>
	</div>
</div>
<%--显示数据的表格--%>
    <table id="fixRecord" class="layui-table" lay-filter="test"></table>
<%--显示数据的表格--%>

<%--获取实体内 bus 的protector属性--%>
	<script type="text/html" id="getPro">
            {{d.bus.protector}}
	</script>
    <script>
		var cid=$("#cid").val();
		layui.use(['table','layer','laydate','form'],function () {
			var table=layui.table,
				layer=layui.layer,
			laydate=layui.laydate,
			form=layui.form;
			form.render();

            //在修车辆
			table.render({
				elem:'#onFix',
				height:200,
				url:'${path}/busFix/busOnFix.action?cid='+cid,
				page:true,//开启分页
				limit:3,
				id:'table1',
				cols:[[//表头
					{field:'bid',title:'车牌',align:'center',sort:true,width:80},
					{field:'maintain',title:'事件',align:'center',width:120},
					{field:'protector',title:'维护人',align:'center',width:120,templet:"#getPro"}
				]]

			});
			//查询在修车辆
            table.render({
                elem:'#fixRecord',
                height:250,
                url:'${path}/busFix/fixRecord.action?cid='+cid,
                page:true,//开启分页
				limit:3,
                id:'table2',
                cols:[[//表头
                    {field:'bid',title:'车牌',align:'center',sort:true,width:80},
                    {field:'reason',title:'事件',align:'center',width:120},
                    {field:'maintain',title:'时间',align:'center',width:160}
                ]]
            });

            table.on('tool(test)', function(obj){
                var data = obj.data;//获取本行

            });

			var $ = layui.$, active = {
				find: function(){//点击查询
					//执行重载
					table.reload('table2', {
						page: {
							curr: 1 //重新从第 1 页开始
						}
						,where: {
						    cid:$("#cid").val(),
                            bid:$("#bid-find").val()
						}
					});
				}

			};
			//监听条件查询
			$('.layui-form .layui-btn ').on('click', function(){
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