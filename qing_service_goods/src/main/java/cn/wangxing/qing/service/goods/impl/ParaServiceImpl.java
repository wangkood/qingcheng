package cn.wangxing.qing.service.goods.impl;

import cn.wangxing.qing.dao.goods.ParaMapper;
import cn.wangxing.qing.dao.goods.TemplateMapper;
import cn.wangxing.qing.pojo.goods.Para;
import cn.wangxing.qing.pojo.other.SwapData;
import cn.wangxing.qing.service.base.impl.BaseServiceImpl;
import cn.wangxing.qing.service.goods.ParaService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(interfaceClass = ParaService.class)
public class ParaServiceImpl extends BaseServiceImpl<Para> implements ParaService {

    @Autowired
    private ParaMapper paraMapper;

    @Autowired
    private TemplateMapper templateMapper;



    @Override
    public SwapData<Para> insert(Para para) {
        try{
            // 插入
            SwapData<Para> swapData = super.insert(para);
            if ( swapData ==null || !"0".equals(swapData.getErrorCode()) ){
                return  swapData;
            }

            // 更新 参数_数量
            templateMapper.refreshPareNum(para.getTemplate_id());

            return swapData;
        }catch (Exception e){
            rollback();
            return new SwapData<Para>("500",e.getMessage());
        }
    }

    @Override
    public SwapData<Para> delete(Long id) {
        try {
            // 获取要删除的对象
            Para para = paraMapper.selectByPrimaryKey(id);

            // 执行删除
            SwapData<Para> swapData = super.delete(id);
            if( !"0".equals(swapData.getErrorCode()) ){
                rollback();
                return swapData;
            }

            // 刷新数量
            templateMapper.refreshPareNum(para.getTemplate_id());



            return new SwapData<>("0","success");
        }catch (Exception e){
            rollback();
            return new SwapData<>("500", e.getMessage());
        }

    }
}
