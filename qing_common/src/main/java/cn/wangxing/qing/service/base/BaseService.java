package cn.wangxing.qing.service.base;

import cn.wangxing.qing.pojo.other.PageInfo;
import cn.wangxing.qing.pojo.other.SwapData;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 这是一个基础service类
 *  只要 显示声明此接口，然后继承 cn.wangxing.qing.service.base.impl.BaseServiceImpl 实现类
 *  即可拥有共有的增删改查方法
 * @param <T>   实体类，一般对应数据库表
 */
@Transactional
public interface BaseService<T> {

    /************************************************************
     *                          增加操作
     ************************************************************/
    SwapData<T> insert(T t);
    SwapData<T> insert(List<T> tList);


    /************************************************************
     *                          删除操作
     ************************************************************/
    SwapData<T> delete(Long id);
    SwapData<T> delete(List<Long> idList);


    /************************************************************
     *                          修改操作
     ************************************************************/
    SwapData<T> update(T t);
    SwapData<T> update(List<T> tList);


    /************************************************************
     *                          查询操作
     ************************************************************/
    SwapData<T> selectById(Long id);
    SwapData<T> select(PageInfo<T> pageInfo);


}
