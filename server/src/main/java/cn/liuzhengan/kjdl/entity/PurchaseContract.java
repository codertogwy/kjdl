package cn.liuzhengan.kjdl.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class PurchaseContract extends BaseEntity {

    //合同号
    private String code;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //签订日期
    private Date date;
    //合同金额
    private Double amount;
    //税率
    private Integer rate;
    //不含税金额
    private Double noTax;
    //收到
    private boolean receive;

    //上传合同
    @OneToMany(cascade = CascadeType.ALL)
    private List<File> fileList;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private Supplier supplier;


    @OneToMany(cascade = CascadeType.ALL)
    private List<PurchaseDetail> purchaseDetailList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PurchasePayment> purchasePaymentList;

}
