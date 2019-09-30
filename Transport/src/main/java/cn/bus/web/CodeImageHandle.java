package cn.bus.web;

import cn.bus.biz.IAdminBiz;
import cn.bus.tool.CodeUtil;
import com.google.zxing.BarcodeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
* @Author:小星
* @Description: 后台用户访问句柄
* @Date:上午 9:53 2019/9/19 0019
*/
@Controller
@RequestMapping("/image/")
public class CodeImageHandle {

    @Resource
    private IAdminBiz iAdminBiz;

    @RequestMapping("image")
    @ResponseBody
    public void image(HttpServletRequest request,HttpServletResponse response)throws IOException
    {
        CodeUtil test = new CodeUtil();
        String filePostfix="png";//二维码图片格式
        String path=request.getSession().getServletContext().getRealPath("/");
        path=path.substring(0,path.indexOf("\\",13))+"/Transport/src/main/webapp/image/";
        File file = new File(path+"test_QR_CODE."+filePostfix);//二维码图片存放路径
        //生成二维码图片
        test.encode("http://192.168.8.121:8080/aid_war_exploded/bus/allProvince.action", file,filePostfix, BarcodeFormat.QR_CODE, 100, 100);
        //读取二维码图片内容
        Map map=test.decode(file);
        BufferedImage bufferedImage=(BufferedImage)map.get("image");

        String resultStr=map.get("resultStr").toString();

        ImageIO.write(bufferedImage,"JPEG",response.getOutputStream());
    }

    @RequestMapping("checkPay")//扫码确认充值
    @ResponseBody
    public void check(Integer aid,Integer money){
//        int aid=1;//账户id
//        int money=1;
        Map map=new HashMap();
        map.put("aid",aid);
        map.put("money",money);

//        "select account from admin where aid=#{aid}"
        int oldMoney=10;//账户原有金额
        int add=money+oldMoney;
        map.put("add",add);
//        "updata admin set account =#{add} account where aid=#{aid}"
    }
}
