package cn.wangxing.qing.service.system;
import cn.wangxing.qing.dao.system.RoleMapper;
import cn.wangxing.qing.pojo.system.Role;
import cn.wangxing.qing.service.base.impl.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(interfaceClass = RoleService.class)
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
}
