window.onload = function(){

    layui.use(['table','form','layer'], function(){
        var table = layui.table,
            form  = layui.form,
            layer = layui.layer;
            $ = layui.$;
        //获取下拉框的值
        form.on('select(city)', function(data){
            var val = data.value;
            if(val != "-1"){
                $("#load").show();//显示数据表格
                loadData(table,val);
            }
        });
    });

}

function loadData(table,city_id) {

    var path = $("#line").attr("title");//获取根路径
    //数据接口
    table.render({
        elem: '#line'
        ,height: 312
        ,url: path + 'lineHandle/init.action?cid='+city_id //数据接口
        ,page: true //开启分页
        ,id: 'lineTable'
        ,method:"post"
        ,parseData:function(res){
            return {
                "code": eval(res.code), //解析接口状态
                "msg": res.msg, //解析提示文本
                "count": res.count, //解析数据长度
                "data": res.data //解析数据列表
            };
        }
        ,cols: [[ //表头
            {field: 'line', title: '线路名称'}
            ,{field: 'station_count', title: '经站点数'}
            ,{field: 'run_count', title: '每日运行班次数'}
            ,{field: 'bus_count', title: '线路在用车辆'}
            ,{fixed: 'right', title:'操作', toolbar: '#toolBar', width:250}
        ]]
    });

    //监听行工具事件（rout对应表格的lay-filter值）
    table.on('tool(rout)', function(obj){//obj指整个表格对象
        var data = obj.data;//具体某一行
        if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                obj.del();//假删除
                //ajax后台删除
                layer.close(index);
            });
        }
    });

    //根据data-type值调用对应事件
    var active = {//这东西相当于一个事件数组
        query: function(){
            var station_info = $('#station_info');
            //执行重载
            table.reload('lineTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    station: station_info.val()
                }
            }, 'data');
        },
        add: function(){
            //开启弹窗
            layer.open(
                {
                    type:2, //网页
                    title:"配置路线",
                    area:['500px','530px'],
                    content:path + 'lineHandle/addline.action',
                    cancel: function(index){
                        layer.close(index);
                        table.reload('lineTable',{},'data');
                    }
                }
            );
        }
    };
    //监听查询按钮事件
    $('.condition .layui-btn').on('click', function(){
        var type = $(this).data('type'); //获取data-type里的值
        //相当于遍历匹配数组中的元素(匹配到就回调)
        active[type] ? active[type].call(this) : '';
    });
}