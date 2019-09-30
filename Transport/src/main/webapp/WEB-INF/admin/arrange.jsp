
<%@ page language="java" contentType="text/html;" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>lib/layui/css/layui.css"  media="all">
    <link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=basePath%>js/jquery1.10.2.js"></script>
    <title></title>
<script src="<%=basePath%>js/jquery.min.js"></script>

<script src="<%=basePath%>lib/layui/layui.js" charset="utf-8"></script>

    <style>
        #div1{
            margin-top: 10px;
            width: 90%;
            border-bottom: 5px solid red;
        }
        #div2{
            margin-top: 10px;
            width: 90%;
            border-bottom: 5px solid green;
        }
    </style>
</head>

<body>
<div style="">
    <div class="clearfix web_widht course_nr" >
        <ul class="course_nr" style="height:50px;margin-left: 2%;" >
            <c:forEach items="${allTime}" var="t">
                <c:if test="${t.spareStart==null}">
                    <li style="height: 40px" style="width:${t.workLength}%">
                        <div class="shiji">在${t.line.line}线路运行
                            <div id="div1"></div>
                            <p>
                            </p><p>${t.times}-${t.end}</p>
                        </div>
                    </li>
                </c:if>
                <c:if test="${t.spareStart!=null}">
                    <li style="height: 40px" style="width:${t.spareLength}%">
                    <div class="shiji">空闲
                        <div id="div2" ></div>
                        <p>
                        </p><p>${t.spareStart}-${t.spareEnd}</p>
                    </div>
                </c:if>
                </li>
            </c:forEach>
        </ul>

    </div>
</div>
<div >
    <p></p>
    <br/>
    <div Style="width:455PX;float:left;margin-left:20px">
        <input type="hidden" id="bbid" value="${bid}">
       始程:
    <table  class="layui-hide" id="LAY_table_line" lay-filter="line">
    </table>
    <script>
        layui.use('table', function(){
            var table = layui.table;
            table.render({
                elem: '#LAY_table_line'
                ,url: '<%=basePath%>arrangeHandle/scheduling.action?llid=${requestScope.lid}&toback=to'
                ,cols: [[
                    // {checkbox: false, fixed: true}
                    {field:'times', title: '时间', width:150, fixed: true,sort: true
                        }
                    ,{field:'bid', title: '发车', width:150, fixed: true
                    ,templet:function(data){
                    return data.bus.bid;}
        }
                    ,{field:'right',toolbar: '#barDemo1', title: '操作',  width:150}
                ]]
                ,id: 'testReload1'
                ,page: false
                // ,height: 310
            });
            //排版状态更改
            table.on('tool(line)', function(obj){
                var data = obj.data;
                if(obj.event === 'open'){
                    layer.confirm('是否排班', function(index){
                        layer.close(index);
                        com("<%=basePath%>arrangeHandle/state.action",data.tid);
                    });
                }else if(obj.event === 'chance'){
                    layer.confirm('是否替换原来的车辆', function(index){
                        layer.close(index);
                        com("<%=basePath%>arrangeHandle/state.action",data.tid);
                    });
                }
            });
            function com(url,tid) {
                $.ajax({
                    url: url,
                    type: "POST",
                    data:{"tid": tid,
                        "bid":$("#bbid").val()
                        },
                    success : function(res){
                        //执行重载
                        table.reload('testReload1', {
                        });
                    }
                });
            }
            $('.demoTable .layui-btn').on('click', function(){
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });
        });
    </script>
    </div>
<%--    //按钮显示--%>
    <script type="text/html" id="barDemo1">
        {{#  if(d.bus.bid ===null&&d.state!==1){d.bus.bid}}
        <a class="layui-btn layui-btn-xs" lay-event="open">排班</a>
        {{# }if(d.bus.bid !==null&&d.state!==1) { d.bus.bid}}
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="chance">排班替换</a>
        {{# }if(d.state===1) { d.state}}
        <a  >车辆行驶中</a>
        {{#  } }}

    </script>
    <div Style="width:455PX;float:right;margin-right:20px">
    返程：
    <table class="layui-hide" id="LAY_table_line2" lay-filter="line2"></table>
    <script>
        layui.use('table', function(){
            var table = layui.table;
            table.render({
                elem: '#LAY_table_line2'
                ,url: '<%=basePath%>arrangeHandle/scheduling.action?llid=${requestScope.lid}&toback=back'
                ,cols: [[
                    {field:'times', title: '时间', width:150, sort: true,fixed: true}
                    ,{field:'bid', title: '发车', width:150, fixed: true
                        ,templet:function(data){
                            return data.bus.bid;}
                    }
                    ,{field:'right',toolbar: '#barDemo1', title: '操作',  width:150}
                ]]
                ,id: 'testReload2'
                ,page: false
                // ,height: 310
            });

            //排版状态更改
            table.on('tool(line2)', function(obj){
                var data = obj.data;
                if(obj.event === 'open'){
                    layer.confirm('是否排班', function(index){
                        layer.close(index);
                        com("<%=basePath%>arrangeHandle/state.action",data.tid);
                    });
                }else if(obj.event === 'chance'){
                    layer.confirm('是否替换原来的车辆', function(index){
                        layer.close(index);
                        com("<%=basePath%>arrangeHandle/state.action",data.tid);
                    });
                }
            });
            function com(url,tid) {
                $.ajax({
                    url: url,
                    type: "POST",
                    data:{"tid": tid,
                        "bid":$("#bbid").val()
                    },
                    success : function(res){
                        //执行重载
                        // table.reload('testReload2', {
                        // });
                    }
                });
            }
            $('.demoTable .layui-btn').on('click', function(){
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });
        });
    </script>
    </div>
</div>

</body>
</html>
