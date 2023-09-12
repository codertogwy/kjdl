package cn.liuzhengan.kjdl.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Supplier extends BaseEntity {

    //企业名称
    private String name;
    //统一社会信用代码
    private String code;
    //法人
    private String legal;
    //所在地区
    private String region;
    //详细地址
    private String address;
    //联系人
    private String contact;
    //联系电话
    private String phone;
    //电子邮箱
    private String email;
    //开户银行
    private String bank;
    //银行账号
    private String account;

}
