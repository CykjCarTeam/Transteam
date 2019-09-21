<%@ page language="java" contentType="text/html;"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%pageContext.setAttribute("path",request.getContextPath()); %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>公交系统</title>
    <link href="${path}/layui/layui.css" rel="stylesheet"  media="all"/>
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
		{{# if(d.state != 9 ){ }}
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">排班</a>
		 {{# }}}
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">修改</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">报废停用</a>
	    {{# if(d.uname != 'a1' ){ }}
                {{# if(d.state === '0' ){ }}
    <button type="button" class="layui-btn" lay-event="close">禁用</button>
                {{# }}}
                {{# if(d.state === '1' ){ }}
    <button type="button" class="layui-btn" lay-event="open">启用</button>
                {{# }}}
	     {{# }}}

<%--            else{d.templet=#switchTpl }}--%>
<%--			{{d.templet}} --%>
<%--			{{# }}}--%>
	</script>
    <script>
        layui.use('table',function () {
            var table=layui.table,
                form = layui.form;
            table.render({
                elem:'#list',
                height:250,
                url:'userList.action',
                page:true,//开启分页
				limit:3,
                id:'reload',
                cols:[[//表头
                    {field:'name',title:'姓名',width:80,sort:true},
                    {field:'addtime',title:'注册时间',width:120,sort:true},
                    {field:'score',title:'积分',width:80,sort:true},
                    {field:'up',title:'上传文档数',width:120,sort:true},
                    {field:'down',title:'下载文档数',width:120,sort:true},
                    {field:'state',title:'状态',width:80,sort:true,templet:function (d) {
                            return d.state=='0'?"可用":"<span class='layui-red'>不可用</span>"
                        }},
                    <%--{field:'event',title:'操作',width:80,sort:true,templet:function (d) {--%>
                    <%--        return d.state=='0'?"<a onclick='return forbid(''+${d.uname}+'')' href='${path}/user/changeState.action?state=${d.state}&uname=${d.uname}&start=${nowPage}'>禁用</a>":--%>
                    <%--            "<a onclick='return allow()' href='${path}/user/changeState.action?state=${d.state}&uname=${d.uname}&start=${nowPage}'>启用</a>"--%>
                    <%--    }},--%>
					// {field:'change',title:'启禁',width:80,templet: '#switchTpl', unresize: true,align:'center'},
					{field:'change',title:'操作',templet:"#status", unresize: true,align:'center'},
                    {field:'test',title:'测试',width:80,toolbar: '#barDemo',align:'center'}
                ]]
            });

            table.on('tool(test)', function(obj){

                var data = obj.data;//获取本行
                if(obj.event === 'del'){
                    layer.confirm('确定删除？', function(index){
						del("${path}/user/delete.action",data.uname);
                        layer.close(index);
                    });
                }
                if(obj.event === 'open'){
                    layer.confirm('确定启用？', function(index){
                        change("${path}/user/state.action",data.uname,data.state);
                        layer.close(index);
                    });
                }
                if(obj.event === 'close'){
                    layer.confirm('确定禁用？', function(index){
                        change("${path}/user/state.action",data.uname,data.state);
                        layer.close(index);
                    });
                }
            });

			function del(url,uname){
				$.ajax({
					type:"post",
					url:url,
					dataType:"text",
					data:{"uname":uname},
					success:function(data){
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

            function change(url,uname,state) {
            	// var state="";
            	// if (check) {
            	// 	state="1";
				// }else {
            	// 	state="0";
				// }
            	console.log(state);
                $.ajax({
                    type:"post",
                    url:url,
                    dataType:"text",
                    data:{"uname":uname,"state":state},
                    success:function(data){
						//执行重载
						table.reload('reload', {

						});
                    },

                })
            }
        })
    </script>

<div id="table">
   <ul>
       <li>姓名</li>
       <li style="width:22%">注册时间</li>
       <li >积分</li>
       <li>上传文档数</li>
	   <li>下载文档数</li>
	   <li style="width:10%">状态</li>
       <li style="">操作</li>
   </ul>

   <c:forEach items="${userList}" var="u" varStatus="s">
       			<ul style="">
        			<li>${u.name}</li>
        			<li style="width:22%">${u.addtime}</li>
        			<li>${u.score}</li>
					<li>${u.up}</li>
					<li>${u.down}</li>
        			<c:if test="${u.state=='0'}">
        				<li style="width:10%">可用</li>
        			</c:if>
        			<c:if test="${u.state=='1'}">
        				<li style="width:10%">不可用</li>
        			</c:if>
        			<li style="">
        			<c:if test="${u.state=='0'}">
				       	<a onclick="return forbid('+${u.uname}+');" href="${path}/user/changeState.action?state=${u.state}&uname=${u.uname}&start=${nowPage}"
				       	 >禁用</a>
			       	</c:if>
			       	<c:if test="${u.state=='1'}">
				       	<a href="${path}/user/changeState.action?state=${u.state}&uname=${u.uname}&start=${nowPage}"
				       	 onclick="return allow();">启用</a>
		       		</c:if>
<%--		       			<a onclick="return reset();" href="${path}/user/reset!reset.action?user.uname=${u.uname}&user.start=${nowPage}"--%>
<%--		       			 >重置密码</a>--%>
				       	<a onclick="return del('+${u.uname}+');" href="${path}/user/delete.action?uname=${u.uname}&start=${nowPage}"
				       	 >删除</a>
				    </li>
       			</ul>
       			<div style="clear:both"></div>
    </c:forEach>

    <!-- 底部分页栏 -->
    <div id="rec">
        <div id="rectext_1">
            共${total}条
        </div>
        <div id="rectext_1">
            当前页数
        </div>
        <div id="rectext_1">
            ${nowPage+1}/${totalPage}
        </div>
        <div id="rectext_1">
           <a href="${path}/user/uLast.action?nowPage=${nowPage}">上一页</a>
        </div>
        <div id="rectext_1">
           <a href="${path}/user/uNext.action?nowPage=${nowPage}">下一页</a>
        </div>
        
    </div>

<%--    <div id="button">--%>
<%--    	<a href="/www/UserAction?method=add&nowPage=${nowPage}">新增</a>--%>
<%--    </div>    	--%>
</div>
</body>
</html>