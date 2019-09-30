
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用戶信息</title>
    <link rel="stylesheet" href="<%=basePath%>css/font.css">
    <link rel="stylesheet" href="<%=basePath%>css/xadmin.css">
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <script src="<%=basePath%>lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/xadmin.js" charset="utf-8"></script>
<%--    <script type="text/javascript" src="<%=basePath%>js/xa.js"></script>--%>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-form layui-card-body ">
                    <div class="demoTable" align="center">
                        <div class="layui-inline layui-show-xs-block">
                        <input type="text" class="layui-input"  autocomplete="off" placeholder="用户名称" name="aname" id="aname">
                        </div>
                        <div class="layui-input-inline">
                            <select id="rid" name="rid">
                                <option value="">请选择角色</option>
                                <c:forEach items="${list}" var="i">
                                    <option class="layui-input" value="${i.rid}">${i.role}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button class="layui-btn" data-type="reload">搜索</button>
                    </div>
                </div>
                <div class="layui-card-header">
                    <button class="layui-btn" onclick="xadmin.open('注册用户','<%=basePath%>adminHandle/interface.action?url=enroll',470,570)">
                        <i class="layui-icon"></i>注册</button>
                </div>
                <div class="layui-card-body" align="center" >
                    <table class="layui-table" lay-filter="test" id="demo" align="center">
                        <thead>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#demo'
            ,height: 295
            ,limit: 5
            ,id:'testReload'
            ,url: '<%=basePath%>adminHandle/listajax.action' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {type:'checkbox',title:'id',width:80}
                ,{field: 'anum', title: '用户账号', width:120, sort: true}
                ,{field: 'aname', title: '用户名称', width:100}
                ,{field: 'sex', title: '性别', width:100, sort: true}
                ,{field: 'age', title: '年齡', width:100}
                ,{field: 'state', title: '状态', width:100,templet:function(data){
            return data.parameter.param;}}
                ,{field: 'area', title: '住址', width: 100}
                ,{field: 'phone', title: '电话', width: 150, sort: true}
                ,{field: 'ucz', title: '操作', width: 150, sort: true,toolbar: '#ucz'}
            ]]
        });
        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    fal("<%=basePath%>adminHandle/delect.action",data.anum);
                    layer.close(index);
                    obj.del();
                });
            } else if(obj.event === 'edit'){
                layer.prompt({
                    formType: 2
                    ,value: data.email
                }, function(value, index){
                    obj.update({
                        email: value
                    });
                    layer.close(index);
                });
            }else if(obj.event === 'status'){
                layer.confirm("确认要修改吗?",function(index){
                    if(data.parameter.param=="启用"){
                        upstate(data,3);
                        $(obj).attr('title','停用')
                        $(obj).find('i').html('&#xe62f;');
                        $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                        layer.msg('已禁用!',{icon: 5,time:1000});
                    }else{
                        upstate(data,2);
                        $(obj).attr('title','启用')
                        $(obj).find('i').html('&#xe601;');
                        $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用')
                        layer.msg('已启用!',{icon: 5,time:1000});
                    }

                });
            }else if(obj.event === 'upass'){
                layer.confirm("确认要重置密码吗?",function(index) {
                    uppass(data);
                });
                }

        });
        var $ = layui.$, active = {
            reload: function(){
                var rid = $('#rid');
                var aname=$('#aname');
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        rid:rid.val(),
                        aname:aname.val()
                    }
                }, 'data');
            }
        };
        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        function fal(url,anum) {
            $.ajax({
                async: true,
                type: "post",
                url: url,
                data: {"anum":anum},
                success: function (dat) {
                    if(dat==1){
                        layer.msg("修改成功");
                    }else{
                        layer.msg("修改失败");
                    }
                    table.reload('testReload', {
                        where:{

                        }
                    }, 'data');
                },
                error: function (dat) {
                    layer.msg('错误');
                }
            })
        }
        //状态的ajax
        function upstate(data,pid) {
            $.ajax({
                async: true,
                type: "post",
                url: '<%=basePath%>adminHandle/upstate.action',
                data: {"pid": pid,
                    "anum":data.anum
                },
                success: function (dat) {
                    if (dat == 1) {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                    table.reload('testReload', {
                        where:{
                        }
                    }, 'data');
                },
                error: function (dat) {
                    layer.msg('错误');
                }
            })
        };

        //密码
        function uppass(data) {
            $.ajax({
                async: true,
                type: "post",
                url: '<%=basePath%>adminHandle/uppass.action',
                data: {
                    "anum":data.anum
                },
                success: function (dat) {
                    if (dat == 1) {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                    table.reload('testReload', {
                        where:{
                        }
                    }, 'data');
                },
                error: function (dat) {
                    layer.msg('错误');
                }
            })
        };
    });
</script>

<script type="text/html" id="ucz">
    <div class = "layui-btn-container" >
        <a title="修改密码" lay-event="upass">
            <i class="layui-icon">&#xe631;</i>
        </a>
        <a title="删除" class="layui-btn-xs" lay-event="del">
            <i class="layui-icon">&#xe640;</i></a>
        <a lay-event="status">
            <i class="layui-icon">&#xe601;</i></a>
    </div >
</script>



<script>
    layui.use(['laydate','form'], function(){
        var laydate = layui.laydate;
        var  form = layui.form;

        // 监听全选
        form.on('checkbox(checkall)', function(data){
            if(data.elem.checked){
                $('tbody input').prop('checked',true);
            }else{
                $('tbody input').prop('checked',false);
            }
            form.render('checkbox');
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });
    });






    function delAll (argument) {
        var ids = [];
        // 获取选中的id
        $('tbody input').each(function(index, el) {
            if($(this).prop('checked')){
                ids.push($(this).val())
            }
        });
        layer.confirm('确认要删除吗？'+ids.toString(),function(index){
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
    }
</script>
</div>
</body>
</html>

