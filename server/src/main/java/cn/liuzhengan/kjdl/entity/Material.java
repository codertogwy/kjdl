package cn.liuzhengan.kjdl.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Material extends BaseEntity {

    /*物资名称*/
    private String name;
    /*型号*/
    private String model;
    /*规格*/
    private String specification;
    /*材质*/
    private String material;
    /*执行标准*/
    private String standard;

}
