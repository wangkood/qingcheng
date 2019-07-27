package cn.wangxing.qing.controller.user;

import cn.wangxing.qing.controller.common.BaseController;
import cn.wangxing.qing.pojo.user.Address;
import cn.wangxing.qing.service.user.AddressService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/address")
public class AddressController extends BaseController<Address> {

    @Reference
    private AddressService addressService;



}
