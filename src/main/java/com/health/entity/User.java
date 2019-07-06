package com.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by yaohandong on  2019/6/24 14:21
 */
@Data
@TableName("user")
public class User implements Serializable {
    @TableId(value = "uid",type = IdType.AUTO)
    private  Integer uid;
    private String uname;
    private String password;
    private String phone;
    private String email;
    private String aboutme;
    private String signature;
    private String psrc;
}
