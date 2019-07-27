package cn.wangxing.qing.service.system;


import cn.wangxing.qing.pojo.other.SwapData;
import cn.wangxing.qing.pojo.system.Admin;
import cn.wangxing.qing.service.base.BaseService;

/**
 * admin业务逻辑层
 */
public interface AdminService extends BaseService<Admin> {


    SwapData<Admin> selectByUsername(String username);
}
