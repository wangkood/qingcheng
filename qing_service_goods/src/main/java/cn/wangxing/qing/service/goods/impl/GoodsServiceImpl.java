package cn.wangxing.qing.service.goods.impl;

import cn.wangxing.qing.dao.goods.SkuMapper;
import cn.wangxing.qing.dao.goods.SpuMapper;
import cn.wangxing.qing.exception.ServiceException;
import cn.wangxing.qing.pojo.goods.Goods;
import cn.wangxing.qing.pojo.goods.Sku;
import cn.wangxing.qing.pojo.goods.Spu;
import cn.wangxing.qing.pojo.other.PageInfo;
import cn.wangxing.qing.pojo.other.SwapData;
import cn.wangxing.qing.service.base.impl.BaseServiceImpl;
import cn.wangxing.qing.service.goods.GoodsService;
import cn.wangxing.qing.service.goods.SkuService;
import cn.wangxing.qing.service.goods.SpuService;
import cn.wangxing.qing.util.SnowflakeIdWorker;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;


@Service(interfaceClass = GoodsService.class)
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    SpuService spuService;

    @Autowired
    SpuMapper spuMapper;

    @Autowired
    SkuService skuService;

    @Autowired
    SkuMapper skuMapper;

    @Autowired
    SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public SwapData<Goods> insert(Goods goods) {
        try {
            if (goods.getSpu() == null){
                throw new ServiceException("关键数据不能为空");
            }

            // 保存 spu
            goods.getSpu().setId(snowflakeIdWorker.nextId());
            if( spuMapper.insert(goods.getSpu()) != 1){
                throw new ServiceException("插入 spu 表 时失败");
            }

            // 保存 sku
            if (goods.getSkuList()!=null){
                for (Sku sku : goods.getSkuList()) {
                    sku.setId(snowflakeIdWorker.nextId());
                    if( skuMapper.insert(sku) != 1){
                        throw new ServiceException("插入 sku 表 时失败");
                    }
                }
            }
            return new SwapData<>(SwapData.SUCCESS_CODE,"success");
        }catch (Exception e){
            BaseServiceImpl.rollback();
            return new SwapData<>("500",e.getMessage());
        }
    }


    @Override
    public SwapData<Goods> delete(Long id) {
        return null;
    }


    @Override
    public SwapData<Goods> update(Goods goods) {
        return null;
    }



    @Override
    @Transactional
    public SwapData<Goods> selectById(Long id) {
        try {
            // 查出 spu
            Example spuExample = new Example(Spu.class);
            spuExample.createCriteria().andEqualTo("id", id);
            Spu spu = spuMapper.selectOneByExample(spuExample);

            // 查出 sku
            Example skuExample = new Example(Sku.class);
            skuExample.createCriteria().andEqualTo("spuId", id);
            List<Sku> skuList = skuMapper.selectByExample(skuExample);

            return new SwapData<Goods>(SwapData.SUCCESS_CODE, "success", new Goods(spu, skuList));
        } catch (Exception e) {
            return new SwapData<Goods>("500", e.getMessage());
        }
    }




    @Override
    @Transactional
    public SwapData<Goods> select(PageInfo<Goods> pageInfo) {
        try {
            // 获取符合条件的 goods = spu
            PageInfo<Spu> spuPageInfo = new PageInfo<>();
            spuPageInfo.setPageCurrent(pageInfo.getPageCurrent());
            spuPageInfo.setPageSize(pageInfo.getPageSize());
            spuPageInfo.setOrderBy(pageInfo.getOrderBy());
            spuPageInfo.setSearchField(pageInfo.getSearchField());
            spuPageInfo.setSearchStr(pageInfo.getSearchStr());


            SwapData<Spu> SpuSwapData = spuService.select(spuPageInfo); // 查询
            pageInfo.setItemCount(SpuSwapData.getPageInfo().getItemCount());

            // 数据转存
            SwapData<Goods> swapData = new SwapData<>();
            swapData.setPageInfo(pageInfo);
            swapData.getPageInfo().setRows(new ArrayList<Goods>());

            for (Spu spu : SpuSwapData.getPageInfo().getRows()) {

                // 查出 sku
                Example skuExample = new Example(Sku.class);
                skuExample.createCriteria().andEqualTo("spuId", spu.getId());

                // 转存 spu
                swapData.getPageInfo().getRows().add(new Goods(spu,skuMapper.selectByExample(skuExample)));
            }

            return swapData.value(SwapData.SUCCESS_CODE,"success");
        }catch (Exception e){
            return new SwapData<>("500","");
        }

    }
}
