package cn.liuzhengan.kjdl.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class PurchasePayment extends BaseEntity {

    //计划支付日期
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Temporal(TemporalType.DATE)
    private Date date;
    //计划支付金额
    private String amount;
    //支付比例
    private String proportion;
    //支付类型
    private String type;
    //支付条件
    private String conditions;
    //币种
    private String currency;
    //备注
    private String remark;

    @ManyToOne
    private PurchaseContract purchaseContract;

}
