package cn.wangxing.qing.service.business.impl;
import cn.wangxing.qing.dao.business.ActivityMapper;
import cn.wangxing.qing.pojo.business.Activity;
import cn.wangxing.qing.service.base.impl.BaseServiceImpl;
import cn.wangxing.qing.service.business.ActivityService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(interfaceClass = ActivityService.class)
public class ActivityServiceImpl extends BaseServiceImpl<Activity> implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;


}
