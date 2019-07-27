package cn.wangxing.qing.service.system;
import cn.wangxing.qing.pojo.other.SwapData;
import cn.wangxing.qing.pojo.system.Menu;
import cn.wangxing.qing.service.base.BaseService;


/**
 * menu业务逻辑层
 */
public interface MenuService extends BaseService<Menu> {

    SwapData<Menu> selectAll();
}
