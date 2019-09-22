package cn.bus.biz;

import cn.bus.entity.Bus;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
* @Author:小星
* @Description:后台用户业务接口
* @Date:上午 9:53 2019/9/19 0019
*/
public interface BusBiz {
    void findCity(ModelAndView model);
    public void find(ModelAndView model, Bus bus);
}
