package cn.bus.biz;

import cn.bus.entity.Admin;
import cn.bus.entity.Menu;
import cn.bus.mapper.IAdminMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @Author:小星
* @Description: 后台用户业务接口实现类
* @Date:上午 9:52 2019/9/19 0019
*/
@Service
public class AdminBizImp implements IAdminBiz {

    @Resource
    private IAdminMapper iAdminMapper;

    @Override
    public Admin login(String anum, String apwd) {
        return iAdminMapper.queryUser(new Admin(anum, apwd));
    }

    @Override
    public Admin loginjax(String anum) {
        return iAdminMapper.loginjax(anum);
    }

    @Override
    public List<Menu> findfmenu(String anum) {
        return iAdminMapper.findfmenu(anum);
    }
    @Override
    public List<Menu> findcmenu(String anum) {
        return iAdminMapper.findcmenu(anum);
    }
}
