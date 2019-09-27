    var path=$("#selectctiy").attr("title");
    var myChart = echarts.init(document.getElementById('show'));
    var myChart2 = echarts.init(document.getElementById('show2'));
    // addmap(point);
    var val;//城市下拉框文本
    var cid;//城市下拉框id
    function cityselect() {
        layui.use('form', function () {
               var form = layui.form,
                $ = layui.$;
            $.ajax({
                url: path + "siteHandle/stationcity.action",
                type: "post",
                dataType:"json",
                success: function (redata) {
                    $.each(redata,function (i,u) {

                        $("#citys").append
                        (
                            "<option value="+u.cid+">"+u.city+"</option>"

                        )
                        form.render();
                    })

                },

            });
        });

    }

layui.use(['table','form'], function () {
    var table = layui.table,
        form=layui.form,
        $=layui.$;

    form.on('select(city)',function (cityva) {
        val=cityva.elem[cityva.elem.selectedIndex].text;
        cid=cityva.value;
        if(cid==null||cid=="")
        {
            layer.msg("请先选择城市",{icon:5,time:1500,title:"提示",weight:600});
        }
        else{
        option = {
            xAxis: {
                type: 'category',
                data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: [120, 200, 150, 80, 70, 110, 130],
                type: 'bar'
            }]
        };

        var data=[{value:335,name:"11路"},{value:331,name:"12路"}];
        option1 = {
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                data:['11路','12路']
            },
            series: [
                {
                    name:'访问来源',
                    type:'pie',
                    radius: ['50%', '70%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data:data
                }
            ]
        };
        myChart.setOption(option);
        myChart2.setOption(option1);
        }
    });
});
