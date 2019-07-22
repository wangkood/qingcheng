package cn.wangxing.qing.service.goods.impl;

import cn.wangxing.qing.dao.goods.AlbumMapper;
import cn.wangxing.qing.dao.goods.BrandMapper;
import cn.wangxing.qing.pojo.goods.Brand;
import cn.wangxing.qing.service.base.impl.BaseServiceImpl;
import cn.wangxing.qing.service.goods.BrandService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Service(interfaceClass = BrandService.class)
public class BrandServiceImpl extends BaseServiceImpl<Brand> implements  BrandService{

   @Autowired
   BrandMapper brandMapper;

   @Autowired
   AlbumMapper albumMapper;


}
