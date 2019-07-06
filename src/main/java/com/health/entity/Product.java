package com.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by yaohandong on  2019/6/24 14:26
 */
@Data
@TableName(value = "product")
public class Product  implements Serializable {
    @TableId(value = "pid",type = IdType.AUTO)
    private Integer pid;
    private String pname;
    private String introduction;
    private String rtime;
    private Double price;
    private String sort;
    private Integer volume;
    private  String purl;
}
