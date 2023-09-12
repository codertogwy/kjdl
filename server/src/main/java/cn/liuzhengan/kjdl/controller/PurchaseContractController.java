package cn.liuzhengan.kjdl.controller;


import cn.liuzhengan.kjdl.dao.PurchaseContractDao;
import cn.liuzhengan.kjdl.dao.SupplierDao;
import cn.liuzhengan.kjdl.entity.PurchaseContract;
import cn.liuzhengan.kjdl.entity.Supplier;
import cn.liuzhengan.kjdl.utils.ResponseResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("purchaseContract")
public class PurchaseContractController extends BaseController {
    private final PurchaseContractDao dao;

    public PurchaseContractController(PurchaseContractDao dao) {
        this.dao = dao;
    }

    @RequestMapping("findAll")
    public ResponseResult findAll() {
        return success(dao.findAll());
    }

    @RequestMapping("save")
    public ResponseResult save(@RequestBody PurchaseContract purchaseContract) {
        return success(dao.save(purchaseContract));
    }

    @RequestMapping("delete")
    public ResponseResult delete(@RequestBody PurchaseContract purchaseContract) {
        dao.delete(purchaseContract);
        return success();
    }

}
