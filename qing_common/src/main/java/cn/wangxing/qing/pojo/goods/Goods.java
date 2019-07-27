package cn.wangxing.qing.pojo.goods;

import java.io.Serializable;
import java.util.List;

public class Goods implements Serializable {

    private static final long serialVersionUID = 001L;

    private Spu spu;
    private List<Sku> skuList;

    public Goods() {  }

    public Goods(Spu spu, List<Sku> skuList) {
        this.spu = spu;
        this.skuList = skuList;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "spu=" + spu +
                ", skuList=" + skuList +
                '}';
    }

    public Spu getSpu() {
        return spu;
    }

    public void setSpu(Spu spu) {
        this.spu = spu;
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
    }
}
