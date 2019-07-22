package cn.wangxing.qing.service.base.impl;

import cn.wangxing.qing.pojo.other.PageInfo;
import cn.wangxing.qing.pojo.other.SwapData;
import cn.wangxing.qing.service.base.BaseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.*;
import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T> {


    // 运行时期泛型类型
    private final Class<T> ENTITY_CLASS;

    // 运行时期主mapper
    private Mapper<T> mainMapper;




    public BaseServiceImpl() {

        // 获取运行时期泛型 class对象
        Type genType = this.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Class entityClass = (Class) params[0];
        this.ENTITY_CLASS = entityClass;

    }

    // 获取 主mapper
    public Mapper<T> getMapper(){
        if (mainMapper != null){
            return mainMapper;
        }

        // 实体简单名
        String entityShortName = ENTITY_CLASS.getSimpleName();

        // 转小写
        String lowerName = (new StringBuilder()).append(Character.toLowerCase(entityShortName.charAt(0))).append(entityShortName.substring(1)).toString();

        try {

            Field field = this.getClass().getDeclaredField(lowerName + "Mapper");
            field.setAccessible(true);
            this.mainMapper = (Mapper<T>) field.get(this);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return this.mainMapper;
        }

    }



    // 回滚事务
    public void rollback() {
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动开启事务回滚
    }


    ///////////////////////////////////////////////////
    //
    //              一下为业务方法
    //


    // 插入 one
    @Override public SwapData<T> insert(T t) {

        try {


            int insert = getMapper().insert(t);

            if (insert != 1) {
                rollback();
                return new SwapData<T>("500", "插入失败");
            } else {
                return new SwapData<T>("0", "success");
            }

        } catch (Exception e) {
            rollback();
            return new SwapData<T>("500", e.getMessage(), null);
        }

    }

    // 插入 many
    @Override public SwapData<T> insert(List<T> ts) {
        try {

            for (T t : ts) {
                if (getMapper().insertSelective(t) != 1) {
                    rollback();
                    return new SwapData<T>("500", "有数据未插入");
                }
            }
            return new SwapData<T>("0", "success");
        } catch (Exception e) {
            rollback();
            return new SwapData<T>("500", e.getMessage(), null);
        }
    }

    // 删除 one
    @Override public SwapData<T> delete(Long id) {
        try {

            if (getMapper().deleteByPrimaryKey(id) != 1) {
                rollback();
                return new SwapData<T>("500", "删除失败");
            } else
                return new SwapData<T>("0", "success");

        } catch (Exception e) {
            rollback();
            return new SwapData<T>("500", e.getMessage(), null);
        }
    }

    // 删除 many
    @Override public SwapData<T> delete(List<Long> idList) {
        try {
            for (Long id : idList) {
                if (getMapper().deleteByPrimaryKey(id) != 1) {
                    rollback();
                    return new SwapData<T>("500", "删除失败");
                }
            }
            return new SwapData<T>("0", "success");
        } catch (Exception e) {
            rollback();
            return new SwapData<T>("500", e.getMessage(), null);
        }

    }

    // 更新 one

    @Override public SwapData<T> update(T t) {


        try {
            int update = getMapper().updateByPrimaryKeySelective(t);
            System.out.println(update);
            if (update != 1) {
                rollback();
                return new SwapData<T>("500", "更新失败");
            } else
                return new SwapData<T>("0", "success");
        } catch (Exception e) {
            rollback();
            return new SwapData<T>("500", e.getMessage(), null);
        }
    }

    // 更新 many
    @Override public SwapData<T> update(List<T> ts) {

        try {
            for (T t : ts) {
                if (getMapper().updateByPrimaryKeySelective(t) != 1) {
                    rollback();
                    return new SwapData<T>("500", "更新失败");
                }
            }
            return new SwapData<T>("0", "success");
        } catch (Exception e) {
            rollback();
            return new SwapData<T>("500", e.getMessage(), null);
        }
    }


    // 获取 one
    @Override public SwapData<T> selectById(Long id) {
        try {
            T t = getMapper().selectByPrimaryKey(id);

            if (t == null) {
                rollback();
                return new SwapData<T>("400", "没有查到对象", null);
            } else
                return new SwapData<T>("0", "success", t);
        } catch (Exception e) {
            rollback();
            return SwapData.SERVICERROR;
        }
    }

    /**
     * 分页获取对象
     * @param pageInfo
     * @return
     */
    @Override
    public SwapData<T> select(PageInfo<T> pageInfo) {

        try {
            // 数据校验
            if (
                    pageInfo.getPageCurrent() <= 0 ||
                            pageInfo.getPageSize() <= 0 ||
                            pageInfo.getPageSize() > 50 ||
                            (pageInfo.getSearchStr() != null && pageInfo.getSearchStr().contains("%"))
            ){
                return new SwapData<>("error","当前页,页面大小 不能为空或小于1, 页面大小不能大于50,搜索字段不能包含有 %");
            }


            // 开始创建 查询例子对象
            Example example = new Example(ENTITY_CLASS);
            Example.Criteria criteria = example.createCriteria();


            // 添加 搜索条件 !!!!!!! 如果对应不上会报异常
            if (
                    pageInfo.getSearchField() != null &&
                            pageInfo.getSearchStr() != null &&
                            !pageInfo.getSearchField().equals("") &&
                            !pageInfo.getSearchStr().equals("")
            ){
                criteria.andLike(pageInfo.getSearchField(),"%"+pageInfo.getSearchStr()+"%");
            }

            // 添加 分页信息
            Page<T> info = PageHelper.startPage((int) pageInfo.getPageCurrent(), (int) pageInfo.getPageSize());


            // 添加排序!!!!!数据不对抛异常
            if (pageInfo.getOrderBy() != null && !pageInfo.getOrderBy().equals("")){
                if (pageInfo.isOrderDesc())
                    info.setOrderBy(pageInfo.getOrderBy()+" desc");
                else
                    info.setOrderBy(pageInfo.getOrderBy());
            }

            // 执行查询
            com.github.pagehelper.Page<T> page = (com.github.pagehelper.Page<T>) getMapper().selectByExample(example);

            pageInfo.setItemCount(page.getTotal());
            pageInfo.setRows(page.getResult());


            return new SwapData<T>("0", "success", null, pageInfo);
        }catch (Exception e){
            rollback();
            return new SwapData<T>("500", "服务器未知异常", null, null);
        }

    }
}
