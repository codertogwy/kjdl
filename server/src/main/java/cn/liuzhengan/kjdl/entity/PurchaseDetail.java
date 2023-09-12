package cn.liuzhengan.kjdl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class PurchaseDetail extends BaseEntity {

    //计量单位
    private String unit;
    //订货数量
    private String quantity;
    //单价
    private String price;
    //金额
    private String amount;
    //申请单号
    private String code;
    //计量方式
    private String mode;
    //品牌及型号说明
    private String description;
    //备注
    private String remark;

    @OneToOne
    private Material material;

    @ManyToOne
    private PurchaseContract purchaseContract;

}
