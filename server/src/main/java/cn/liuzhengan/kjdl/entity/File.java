package cn.liuzhengan.kjdl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class File extends BaseEntity {

    private String name;
    private String cos;
    private Long size;
    private String type;

}
