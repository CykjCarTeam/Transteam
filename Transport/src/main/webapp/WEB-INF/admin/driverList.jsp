<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<%--  <% String path = request.getContextPath() + "/"; %>--%>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
  <meta charset="utf-8">
  <link rel="stylesheet" href="<%=basePath%>lib/layui/css/layui.css" media="all">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>发车表</legend>
</fieldset>



<div class="demoTable">
  姓名：
  <div class="layui-inline">
    <input class="layui-input" name="key" id="demoReload" autocomplete="off">
  </div>
  手机号：
  <div class="layui-inline">
    <input class="layui-input" name="key" id="phone" autocomplete="off">
  </div>
  站点：
  <div class="layui-inline">
    <input class="layui-input" name="key" id="site" autocomplete="off">
  </div>
  <button class="layui-btn" data-type="reload">搜索</button>

<%--  <button class="layui-btn" data-type="delete">测试</button>--%>
</div>

<table id="demo" lay-filter="test"></table>

<%--必须用script标签包裹，如果用普通的div会多一对--%>
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="edit">工作量查看</a>

  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="update">修改</a>

</script>

<script src="<%=basePath%>lib/layui/layui.js"></script>
<script>

  layui.use(['table','form'], function(){
    var table = layui.table,
    form = layui.form;

    table.render({
      elem: '#demo'
      ,height: 312
      ,url:  '<%=basePath%>drilist/find.action' //数据接口
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
        // {title : '全选', type : 'checkbox'},
        {field: 'aid', title: '序号'}
        ,{field: 'aname'+"", title: '姓名'}
        ,{field: 'phone', title: '电话'}
         ,{field: 'line', title: '上班线路'}
        ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
      ]]
    });

    //监听行工具事件
    <%--table.on('tool(test)', function(obj){--%>
    <%--  var data = obj.data;--%>
    <%--  if(obj.event === 'del'){--%>
    <%--    layer.confirm('真的删除行么', function(index){--%>
    <%--        --%>
    <%--        fal("<%=path%>custom/updateuser.action",data.username);--%>
    <%--        layer.close(index);--%>
    <%--        obj.del();--%>
    <%--    });--%>
    <%--  } else if(obj.event === 'edit'){--%>
    <%--    layer.prompt({--%>
    <%--      formType: 2--%>
    <%--      ,value: data.username //修改的位置--%>
    <%--    }, function(value, index){--%>


    <%--    --%>
    <%--    });--%>
    <%--  }--%>
    <%--});--%>
      //弹窗

      table.on('tool(test)', function(obj){
          var data = obj.data;

          if(obj.event === 'edit'){

              //弹出工作量窗口
              layer.open({
                  type: 1,
                  title: "工作量",
                  area: ['420px', '530px'],
                  content: $("#scheduling")//引用的弹出层的页面层的方式加载修改界面表单
              });
          }else if (obj.event === 'update') {

              //1走ajax获取所有线路
              //1走ajax获取所有线路
              $.ajax({
                  url: '<%=basePath%>Linelist/find.action',
                  type:"POST",
                 data:{"aid":"kkk"},
                  // dataType:"json",
                  success:function (flist){
                    // alert(flist[2].line);
                    //   alert(flist[2].lid);
                    var items=flist;
                      $("#majorid1").empty();
                      $.each(items,function (i,n) {

                          $("#majorid1").append("<option value = '"+items[i].lid+"'>"+items[i].line+"<option>")
                          $("#aid1").val(data.aid);
                          $("#aname").val(data.aname);
                          $("#phone1").val(data.phone);
                          form.render();
                      });
                      // for(var i = 0; i < flist.length; i++){
                      //     sel.append("<option value = "+'flist[i].lid'+">"+flist[i].line+"<option>")
                      // }
                  }
              });

              // var line = ["1","一号线", "2","2号线"];

              //2.去获取下拉框对象，给select追加option
              // var sel = $("#majorid1");
              //遍历线路

              // for(var i = 0; i < line.length; i++){
              //     sel.append("<option value = "+'line[i]'+">"+line[i]+"<option>")
              // }

              //3.打开弹窗
              //弹出修改窗口
              layer.open({
                  type: 1,
                  title: "工修改",
                  area: ['420px', '530px'],
                  content: $("#updatexianlu")//引用的弹出层的页面层的方式加载修改界面表单
              });
          }
      });







    //根据data-type值调用对应事件
    var $ = layui.$,

    active = {//这东西相当于一个事件数组
      reload: function(){
          alert("ooo")
        var demoReload = $('#demoReload');
        var phone = $('#phone');
        var site = $('#site');

        table.reload('testReload', {
          //执行重载
          page: {
            curr: 1 //重新从第 1 页开始
          }
          ,where: {
             "dname": demoReload.val(),
             "dphone": phone.val(),
             "dsite": site.val()
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

<div id="updatexianlu" style="display: none">

    <form class="layui-form" action="<%=basePath%>drilist/change.action" lay-filter="example"   >

        <div class="layui-form-item">
            <label class="layui-form-label">序号</label>
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
                <select name="majorid1" lay-verify="required" id="majorid1"
                        lay-filter="majorid1">
<%--                    <option value="6">aa</option>--%>
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

<%--&lt;%&ndash;//工作查看---------------------------&ndash;%&gt;--%>


<div id="scheduling" style="display: none">
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
        <button class="layui-btn" data-type="reload11">搜索</button>

    </div>
<%--    -----%>
    <table class="layui-hide" id="LAY_table_line" lay-filter="line"></table>

    </div>
    <script>
        layui.use('table', function(){
            var table = layui.table;
            table.render({
                elem: '#LAY_table_line'
                ,url: '<%=basePath%>worklist/wrokload.action'
                ,cols: [[ //表头

                    {field: 'id', title: '序号'}
                    ,{field: 'states', title: '车牌'}
                    ,{field: 'line', title: '线路'}
                    ,{field: 'dates', title: '时间'}

                ]]
                ,id: 'testReload'
                ,page: true
                ,limit:5
                // ,height: 310
            });

            //根据data-type值调用对应事件
            var $ = layui.$,

                active = {//这东西相当于一个事件数组
                    reload11: function(){
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



                };


            layui.$('.demoTable .layui-btn').on('click', function(){
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });
        });
    </script>


</div>

</body>
</html>