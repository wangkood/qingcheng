package cn.wangxing.qing.service.goods;

import cn.wangxing.qing.pojo.goods.Sku;
import cn.wangxing.qing.pojo.other.PageResult;

import java.util.List;
import java.util.Map;

/**
 * sku业务逻辑层
 */
public interface SkuService {


    public List<Sku> findAll();


    public PageResult<Sku> findPage(int page, int size);


    public List<Sku> findList(Map<String, Object> searchMap);


    public PageResult<Sku> findPage(Map<String, Object> searchMap, int page, int size);


    public Sku findById(String id);

    public void add(Sku sku);


    public void update(Sku sku);


    public void delete(String id);

}