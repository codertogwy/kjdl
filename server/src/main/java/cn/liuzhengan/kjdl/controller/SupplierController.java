package cn.liuzhengan.kjdl.controller;


import cn.liuzhengan.kjdl.dao.SupplierDao;
import cn.liuzhengan.kjdl.entity.Supplier;
import cn.liuzhengan.kjdl.utils.ResponseResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("supplier")
public class SupplierController extends BaseController {

    private final SupplierDao dao;

    public SupplierController(SupplierDao dao) {
        this.dao = dao;
    }


    @RequestMapping("findAll")
    public ResponseResult findAll() {
        return success(dao.findAll());
    }

    @RequestMapping("save")
    public ResponseResult save(@RequestBody Supplier supplier) {
        return success(dao.save(supplier));
    }

    @RequestMapping("delete")
    public ResponseResult delete(@RequestBody Supplier supplier) {
        dao.delete(supplier);
        return success();
    }


}
