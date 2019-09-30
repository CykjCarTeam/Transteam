
function check() {
    layui.use('layer', function(){ //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer;
        var anum=$("#anum").val();
        if(null==anum||anum==""){
            layer.msg("账户不为空");
            return false;
        }else{
            $.ajax({
                url: "/aid_war_exploded/adminHandle/logajax.action",
                type: "POST",
                data:{"anum":anum},
                dataType: "text",
                success : function(res){
                    if(res=="1"){
                        layer.msg("账户已存在");
                    }else{
                        layer.msg("账户未存在");
                    }
                }
            });
        }});

}


function check1() {
    layui.use('layer', function() { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer;
        layer = layui.layer;
        var apwd = $("#apwd").val();
        if (null == apwd || apwd == "" || apwd.length < 6) {
            layer.msg("请输入六位数的密码");
        } else {
            layer.msg("密码位数符合");
        }
    });
}


function check2() {
    layui.use('layer', function() { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer;
        layer = layui.layer;
        var age = $("#age").val();
        if (null == age || age == "" || age <=0||age >=100) {
            layer.msg("请输入准确的年龄");
        } else {
            layer.msg("年龄符合");
        }
    });
}


function check3(){
    var phone=$("#phone").val();
var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
alert(phone);
if (!myreg.test(phone)) {
    layer.msg("请输入正确号码");
} else {
    layer.msg("号码正确");
}
}
function img(){
    var fileact=$("#fileact").val();
}






