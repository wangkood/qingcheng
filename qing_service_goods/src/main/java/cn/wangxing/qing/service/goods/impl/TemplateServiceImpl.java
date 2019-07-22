package cn.wangxing.qing.service.goods.impl;

import cn.wangxing.qing.dao.goods.ParaMapper;
import cn.wangxing.qing.dao.goods.SpecMapper;
import cn.wangxing.qing.dao.goods.TemplateMapper;
import cn.wangxing.qing.pojo.goods.Para;
import cn.wangxing.qing.pojo.goods.Spec;
import cn.wangxing.qing.pojo.goods.Template;
import cn.wangxing.qing.pojo.other.PageInfo;
import cn.wangxing.qing.pojo.other.SwapData;
import cn.wangxing.qing.service.base.impl.BaseServiceImpl;
import cn.wangxing.qing.service.goods.TemplateService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;


@Service(interfaceClass = TemplateService.class)
public class TemplateServiceImpl extends BaseServiceImpl<Template> implements TemplateService {

    @Autowired
    private TemplateMapper templateMapper;

    @Autowired
    private ParaMapper paraMapper;

    @Autowired
    private SpecMapper specMapper;


    // 更具分页对象获取分页
    @Override
    public SwapData<Template> select(PageInfo<Template> pageInfo) {
        try {
            SwapData<Template> swapData = super.select(pageInfo);
            if(!"0".equals(swapData.getErrorCode())){
                return swapData;
            }

            // 添加 参数 和 规格
            for (Template row : swapData.getPageInfo().getRows()) {

                // 规格
                Example example = new Example(Spec.class);
                example.createCriteria().andEqualTo("template_id",row.getId());
                row.setSpecList( specMapper.selectByExample(example) );

                // 参数
                Example example2 = new Example(Para.class);
                example2.createCriteria().andEqualTo("template_id",row.getId());
                row.setParaList( paraMapper.selectByExample(example2) );

            }
            return swapData;

        }catch (Exception e){
            return new SwapData<>("500",e.getMessage(),null,null);
        }


    }
}
