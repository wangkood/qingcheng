package cn.wangxing.qing.service.goods.impl;

import cn.wangxing.qing.dao.goods.SpecMapper;
import cn.wangxing.qing.dao.goods.TemplateMapper;
import cn.wangxing.qing.pojo.goods.Spec;
import cn.wangxing.qing.pojo.other.SwapData;
import cn.wangxing.qing.service.base.impl.BaseServiceImpl;
import cn.wangxing.qing.service.goods.SpecService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service(interfaceClass = SpecService.class)
public class SpecServiceImpl extends BaseServiceImpl<Spec> implements SpecService {

    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private TemplateMapper templateMapper;

    // 插入
    @Override
    public SwapData<Spec> insert(Spec spec) {
        try{
            SwapData<Spec> swapData = super.insert(spec);
            if ( swapData ==null || !"0".equals(swapData.getErrorCode()) ){
                return  swapData;
            }
            // 更新 参数_数量
            templateMapper.refreshSpecNum(spec.getTemplate_id());

            return swapData;
        }catch (Exception e){
            rollback();
            return new SwapData<Spec>("500",e.getMessage());
        }
    }

    // 删除
    @Override
    public SwapData<Spec> delete(Long id) {

        try {
            // 获取将要删除对象
            Spec spec = specMapper.selectByPrimaryKey(id);

            // 执行删除
            SwapData<Spec> swapData = super.delete(id);
            if( !"0".equals(swapData.getErrorCode()) ){
                rollback();
                return swapData;
            }

            // 更新 参数_数量
            templateMapper.refreshSpecNum(spec.getTemplate_id());
            return swapData;
        }catch (Exception e){
            rollback();
            return new SwapData<Spec>("500",e.getMessage());
        }

    }

    /**
     * 更具 模板对象查找 规格
     * @param templateId
     * @return
     */
    @Override
    public SwapData<List<Spec>> listByTemplateId(long templateId) {
        try{
            Example example = new Example(Spec.class);
            example.createCriteria().andEqualTo("templateId",templateId);

            List<Spec> specList = specMapper.selectByExample(example);
            return new SwapData<List<Spec>>(SwapData.SUCCESS_CODE,"",specList);
        }catch (Exception e){
            return new SwapData<List<Spec>>(SwapData.EXCEPTION_CODE,e.getMessage());
        }
    }
}
