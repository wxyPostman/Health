package com.health.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Userinfo  implements Serializable {
    @TableId("hid")
    private int hid;
    private String uname;
    private String address;
    private String phone;
    private int pid;
    private int number;

}
