package cn.wangxing.qing.dao.system;

import cn.wangxing.qing.pojo.system.Admin;
import tk.mybatis.mapper.common.Mapper;

public interface AdminMapper extends Mapper<Admin> {

    Admin selectByUsername(String username);

}
