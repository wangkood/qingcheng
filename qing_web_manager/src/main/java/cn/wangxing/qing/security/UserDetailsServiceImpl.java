package cn.wangxing.qing.security;

import cn.wangxing.qing.pojo.other.SwapData;
import cn.wangxing.qing.pojo.system.Admin;
import cn.wangxing.qing.pojo.system.Role;
import cn.wangxing.qing.service.system.AdminService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;


// 进行用户验证，更具用户名从数据库查找对应数据
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference
    AdminService adminService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 查找出 账户
        SwapData<Admin> adminSwapData = adminService.selectByUsername(username);
        if ( !SwapData.SUCCESS_CODE.equals(adminSwapData.getErrorCode()) ){
            return null;
        }

        // 设置角色
        ArrayList<GrantedAuthority> list = new ArrayList<>();
        for (Role role : adminSwapData.getObj().getRoleList()) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        }

        return new User(username,adminSwapData.getObj().getPassword(),list);
    }
}
