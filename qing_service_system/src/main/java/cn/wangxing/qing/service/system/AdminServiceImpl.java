package cn.wangxing.qing.service.system;
import cn.wangxing.qing.dao.system.AdminMapper;
import cn.wangxing.qing.dao.system.RoleMapper;
import cn.wangxing.qing.pojo.other.SwapData;
import cn.wangxing.qing.pojo.system.Admin;
import cn.wangxing.qing.pojo.system.Role;
import cn.wangxing.qing.service.base.impl.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

@Service(interfaceClass = AdminService.class)
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    private RoleMapper roleMapper;

    @Override
    public SwapData<Admin> selectByUsername(String username) {

        // 查出账户，角色
        Admin admin = adminMapper.selectByUsername(username);
        if (admin == null ){
            return new SwapData<Admin>("400","没找到");
        }

        // 查出 全限

        return new SwapData<Admin>(SwapData.SUCCESS_CODE,"success",admin);
    }
}
