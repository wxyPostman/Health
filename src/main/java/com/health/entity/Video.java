package com.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by yaohandong on  2019/6/24 14:23
 */
@Data
@TableName("video")
public class Video implements Serializable {
    @TableId(value = "vid",type = IdType.AUTO)
    private Integer vid;
    private String title;
    private String introduction;
    private String src;
    private Integer cid;
}
