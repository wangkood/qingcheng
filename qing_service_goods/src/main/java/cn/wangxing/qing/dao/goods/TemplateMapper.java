package cn.wangxing.qing.dao.goods;

import cn.wangxing.qing.pojo.goods.Template;
import tk.mybatis.mapper.common.Mapper;

public interface TemplateMapper extends Mapper<Template> {


    void refreshSpecNum(long id);

    void refreshPareNum(long id);

}
