package cn.bus.biz;

import cn.bus.entity.BusUser;
import cn.bus.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
* @Author:小星
* @Description: 后台用户业务接口实现类
* @Date:上午 9:52 2019/9/19 0019
*/
@Service
public class UserBizImp implements UserBiz {

    @Resource
    private UserMapper userMapper;

    @Override
    public BusUser find(ModelAndView model, Integer uid) {
        BusUser user1 =userMapper.findUser(uid);
        model.addObject("user",user1);
        return null;
    }

    @Override
    public void addHead(Map map) {
        userMapper.addHead(map);
    }

    @Override
    public void makeTrade(Map map) {
        userMapper.makeTrade(map);
    }

    @Override
    public void addMoney(Map map) {
        userMapper.addMoney(map);
    }
}
