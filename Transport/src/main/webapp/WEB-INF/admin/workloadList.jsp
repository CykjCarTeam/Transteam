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
    <legend>工作量</legend>
</fieldset>
<div class="demoTable">
    <div class="layui-inline">
        <label class="layui-form-label">开始日期</label>
        <div class="layui-input-inline">
            <input type="date" name="date" id="begindate" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-inline">
        <label class="layui-form-label">结束日期</label>
        <div class="layui-input-inline">
            <input type="date" name="date1" id="enddate1" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
        </div>
    </div>
    <button class="layui-btn" data-type="reload">搜索</button>

    <button class="layui-btn" data-type="agree">出站确认</button>

</div>

<table id="demo" lay-filter="test"></table>

<script src="<%=basePath%>layui/layui.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#demo'
            ,height: 312
            ,url: '<%=basePath%>worklist/wrokload.action'
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
                {field: 'id', title: '序号'}
                ,{field: 'states', title: '车牌'}
                ,{field: 'line', title: '线路'}
                ,{field: 'dates', title: '时间'}

            ]]
        });



        //根据data-type值调用对应事件
        var $ = layui.$,

            active = {//这东西相当于一个事件数组
                reload: function(){
                    var begindate = $('#begindate');
                    var enddate1 = $('#enddate1');

                    table.reload('testReload', {
                        //执行重载
                        page: {
                            curr: 1 //重新从第 1 页开始
                        }
                        ,where: {
                            "begindate": begindate.val(),
                            "enddate":enddate1.val(),
                        }
                    }, 'data');
                }
                ,agree: function(){
                    layer.open({
                        type: 1,
                        title: "出站确认",
                        area: ['420px', '530px'],
                        content: $("#chuzhan")//引用的弹出层的页面层的方式加载修改界面表单
                    });
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

<div id="chuzhan" style="display: none">

    <form class="layui-form" action="<%=basePath%>drilist/change.action" lay-filter="example"   >

        <div class="layui-form-item">
            <label class="layui-form-label">时间</label>
            <div class="layui-input-inline">

                <input type="text" name="aid1" id="aid1"  readonly="true"  class="layui-input"
                >
            </div>
        </div>
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

                <input type="text" name="phone1" id="phone1"  readonly="true"  class="layui-input"
                >
            </div>
        </div>
        <%--        <div class="layui-form-item">--%>
        <%--            <label class="layui-form-label">线路</label>--%>
        <%--            <div class="layui-input-inline">--%>
        <%--                <input type="text" name="xianlu" id="xianlu" required--%>
        <%--                       lay-verify="required"  autocomplete="off"--%>
        <%--                       class="layui-input">--%>
        <%--            </div>--%>
        <%--            <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->--%>
        <%--        </div>--%>
        <div class="layui-form-item">
            <label class="layui-form-label">线路</label>
            <div class="layui-input-inline">
                <select name="majorid" lay-verify="required" id="majorid1"
                        lay-filter="majorid1">
                    <%--                    <option value="1">aa</option>--%>
                    <%--                    <option value="2">bb</option>--%>
                </select>
            </div>
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
