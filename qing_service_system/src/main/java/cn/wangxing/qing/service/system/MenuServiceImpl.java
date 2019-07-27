package cn.wangxing.qing.service.system;
import cn.wangxing.qing.dao.system.MenuMapper;
import cn.wangxing.qing.pojo.other.PageInfo;
import cn.wangxing.qing.pojo.other.SwapData;
import cn.wangxing.qing.pojo.system.Menu;
import cn.wangxing.qing.service.base.impl.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service(interfaceClass = MenuService.class)
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public SwapData<Menu> selectAll() {
        List<Menu> menus = menuMapper.selectAll();

        List<Menu> newList = gList(menus, "0");

        PageInfo pageInfo = new PageInfo<Menu>();
        pageInfo.setRows(newList);

        return new SwapData<>(SwapData.SUCCESS_CODE,"",null,pageInfo);
    }


    // 有待优化。。。。。。。。。。。
    public List<Menu> gList(List<Menu> menuList,String parent_id){

        List<Menu> newList = new ArrayList<>();
        for (Menu menu : menuList) {
            if (menu.getParentId().equals(parent_id)){

                menu.setChild(gList(menuList,menu.getId()));
                newList.add(menu);
            }
        }
        return newList;

    }
}
