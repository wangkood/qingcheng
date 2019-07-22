package cn.wangxing.qing.controller.common;

import cn.wangxing.qing.pojo.other.PageInfo;
import cn.wangxing.qing.pojo.other.SwapData;
import cn.wangxing.qing.service.base.BaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class BaseController<T> {


    // 运行时期泛型类型
    private final Class<T> ENTITY_CLASS;

    // 运行时期主mapper
    private BaseService<T> mainService;


    public BaseController() {
        synchronized (BaseController.class){
            // 获取运行时期泛型 class对象
            Type genType = this.getClass().getGenericSuperclass();
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            Class entityClass = (Class) params[0];

            this.ENTITY_CLASS = entityClass;
        }
    }

    // 获取运行时 service
    private BaseService<T> getService(){
        if (mainService != null){
            return mainService;
        }

        // 实体简单名
        String entityShortName = ENTITY_CLASS.getSimpleName();

        // 转小写
        String lowerName = (new StringBuilder()).append(Character.toLowerCase(entityShortName.charAt(0))).append(entityShortName.substring(1)).toString();

        try {

            Field field = this.getClass().getDeclaredField(lowerName + "Service");
            field.setAccessible(true);
            this.mainService = (BaseService<T>) field.get(this);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return this.mainService;
        }
    }



    //
    //                     一下为基础 url设计
    //
    ///////////////////////////////////////////////////////

    // 插入一个用户 xxx
    @PostMapping("/insert_one")
    @ResponseBody
    public SwapData<T> insertOne(@RequestBody T T){
        return getService().insert(T);
    }

    // 插入一个用户 xxx
    @PostMapping("/update_one")
    @ResponseBody
    public SwapData<T> updateOne(@RequestBody T T){
        return getService().update(T);
    }

    // 删除一个
    @PostMapping("/delete_one")
    @ResponseBody
    public SwapData<T> deleteOne(@RequestBody Integer id){
        return getService().delete((long)id);
    }

    // 分页搜索
    @PostMapping("/select_one")
    @ResponseBody
    public SwapData<T> selectOne(@RequestBody Long id){
        return getService().selectById(id);
    }

    // 分页搜索
    @PostMapping("/select_page")
    @ResponseBody
    public SwapData<T> selectByPage(@RequestBody PageInfo<T> pageInfo){
        return getService().select(pageInfo);
    }




}
