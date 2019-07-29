package cn.wangxing.qing.service.goods.impl;

import cn.wangxing.qing.dao.goods.CategoryMapper;
import cn.wangxing.qing.pojo.goods.Category;
import cn.wangxing.qing.pojo.other.PageResult;
import cn.wangxing.qing.pojo.other.SwapData;
import cn.wangxing.qing.service.goods.CategoryService;
import cn.wangxing.qing.util.RedisUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.omg.PortableInterceptor.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CategoryService.class)
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取指定父id 的所有子分类，tree结构
     * @param parentId
     * @return
     */
    @Override
    public SwapData<List<Category>> treelist(long parentId) {
        try {
            List<Category> categoryTreeList = (List<Category>)redisTemplate.boundValueOps(RedisUtils.Keys.CATEGORY_KEY).get();
            if(categoryTreeList == null){
                // 从数据库中查找
                Example example = new Example(Category.class);
                example.createCriteria().andEqualTo("isShow", 1);
                List<Category> categoryList = categoryMapper.selectByExample(example);
                // 数据处理成 tree 结构
                categoryTreeList = toNewList(categoryList, (int) parentId);
                // 加入redis缓存
                redisTemplate.boundValueOps(RedisUtils.Keys.CATEGORY_KEY).set(categoryTreeList);
            }

            return new SwapData<List<Category>>(SwapData.SUCCESS_CODE,"",categoryTreeList);
        }catch (Exception e){
            return new SwapData<>(SwapData.EXCEPTION_CODE, e.getMessage());
        }
    }
    // 递归将 平铺式集合 改成 结构式集合
    public List<Category> toNewList(List<Category> oldList, Integer parentId){
        List<Category> newList = new ArrayList();
        for (Category category : oldList) {
            if (category.getParentId().equals(parentId)){

                category.setChildList(toNewList(oldList,category.getId()));
                newList.add(category);
            }
        }
        return newList;
    }
}
