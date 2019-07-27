package cn.wangxing.qing.service.business;
import cn.wangxing.qing.pojo.business.Ad;
import cn.wangxing.qing.service.base.BaseService;

import java.util.List;


/**
 * ad业务逻辑层
 */
public interface AdService extends BaseService<Ad> {

    List<Ad> selectAll();
}
