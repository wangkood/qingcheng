package cn.wangxing.qing.service.business.impl;
import cn.wangxing.qing.dao.UserMapper;
import cn.wangxing.qing.pojo.user.User;
import cn.wangxing.qing.service.base.impl.BaseServiceImpl;
import cn.wangxing.qing.service.user.UserService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(interfaceClass = UserService.class)
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService  {

    @Autowired
    private UserMapper userMapper;


}
