<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/9/21
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%--    <% String path = request.getContextPath() + "/"; %>--%>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
    <meta charset="utf-8">
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>工资表</legend>
</fieldset>

<div class="demoTable">
    姓名：
    <div class="layui-inline">
        <input class="layui-input" name="key" id="demoReload" autocomplete="off">
    </div>

    <button class="layui-btn" data-type="reload">搜索</button>
    <%--  <button class="layui-btn" data-type="delete">测试</button>--%>
</div>

<table id="demo" lay-filter="test"></table>

<%--必须用script标签包裹，如果用普通的div会多一对--%>
<script type="text/html" id="barDemo">


    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="update">修改</a>

</script>

<script src="<%=basePath%>layui/layui.js"></script>
<script>

    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#demo'
            ,height: 312
            ,url:  '<%=basePath%>driwagelist/find.action' //数据接口
            ,page: true //开启分页
            ,limit:5
            ,id: 'testReload'

            ,parseData:function(res){
                console.log("返回值" + res);
                console.log("状态码" + res.code);
                console.log("消息" + res.msg);
                console.log("条数" + res.count);
                console.log("数据" + res.data);
                return {
                    "code": eval(res.code), //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.count, //解析数据长度
                    "data": res.data //解析数据列表
                };
            }
            ,cols: [[ //表头

                {field: 'anum', title: '账号'}
                ,{field: 'aname', title: '姓名'}
                ,{field: 'phone', title: '电话'}
                ,{field: 'account', title: '工资'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
            ]]
        });


        //弹窗

        table.on('tool(test)', function(obj){
            var data = obj.data;
            $("#aname").val(data.aname)
            $("#phone1").val(data.phone)
            $("#gongzi").val(data.account)
            if(obj.event === 'edit'){

                //弹出工作量窗口

            }else if (obj.event === 'update') {

                //弹出修改窗口
                layer.open({
                    type: 1,
                    title: "工资修改",
                    area: ['420px', '530px'],
                    content: $("#updategonzi")//引用的弹出层的页面层的方式加载修改界面表单
                });
            }
        });







        //根据data-type值调用对应事件
        var $ = layui.$,

            active = {//这东西相当于一个事件数组
                reload: function(){
                    var demoReload = $('#demoReload');


                    table.reload('testReload', {
                        //执行重载
                        page: {
                            curr: 1 //重新从第 1 页开始
                        }
                        ,where: {
                            "dname": demoReload.val(),

                        }
                    }, 'data');
                }



            };

        //监听查询按钮事件
        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type'); //获取data-type里的值
            //相当于遍历匹配数组中的元素(匹配到就回调)
            active[type] ? active[type].call(this) : '';
        });


    });

</script>

<%--------------------------------修改界面----------------%>

<div id="updategonzi" style="display: none">

    <form class="layui-form" action="<%=basePath%>driwagelist/change.action" lay-filter="example"   >
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="aname" id="aname"
                           readonly="true">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-inline">
                <%--                <input type="text" name="phone1"  readonly="true" autocomplete="off" class="layui-input" value="data.phone"--%>
                <%--                       id="phone1">--%>
                <input type="text" name="phone1" id="phone1"  readonly="true"  class="layui-input"
                >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">工资</label>
            <div class="layui-input-inline">
                <input type="text" name="gongzi" id="gongzi" required
                       lay-verify="required"  autocomplete="off"
                       class="layui-input">
            </div>
            <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
        </div>


        <div class="layui-form-item">
            <div class="layui-input-block">

                <button class="layui-btn" lay-submit="" lay-filter="*">修改</button>

            </div>
        </div>
    </form>





</div>






</body>
</html>
