package cn.liuzhengan.kjdl.controller;

import cn.liuzhengan.kjdl.dao.MaterialDao;
import cn.liuzhengan.kjdl.entity.Material;
import cn.liuzhengan.kjdl.utils.ResponseResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("material")
public class MaterialController extends BaseController {

    private final MaterialDao dao;

    public MaterialController(MaterialDao dao) {
        this.dao = dao;
    }

    @RequestMapping("findAll")
    public ResponseResult findAll() {
        return success(dao.findAll());
    }

    @RequestMapping("save")
    public ResponseResult save(@RequestBody Material material) {
        return success(dao.save(material));
    }

    @RequestMapping("delete")
    public ResponseResult delete(@RequestBody Material material) {
        dao.delete(material);
        return success();
    }

}
