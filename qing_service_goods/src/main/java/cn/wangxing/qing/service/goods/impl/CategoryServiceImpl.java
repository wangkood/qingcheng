package cn.wangxing.qing.service.goods.impl;

import cn.wangxing.qing.dao.goods.CategoryMapper;
import cn.wangxing.qing.pojo.goods.Category;
import cn.wangxing.qing.pojo.other.PageResult;
import cn.wangxing.qing.service.goods.CategoryService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.omg.PortableInterceptor.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CategoryService.class)
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> list(long parentId) {

        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("isShow", 1);

        List<Category> categoryList = categoryMapper.selectByExample(example);

        return toNewList(categoryList,(int) parentId);
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
