package com.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by yaohandong on  2019/6/24 14:34
 */
@Data
@TableName(value = "admin")
public class Admin implements Serializable {
    @TableId(value = "aid",type = IdType.AUTO)
    private Integer aid;
    private String aname;
    private String password;
    private String phone;
    private String email;
}
