package com.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yaohandong on  2019/6/24 14:32
 */
@Data
@TableName(value = "articles")
public class Articles implements Serializable {
    @TableId(value = "tid",type = IdType.AUTO)
    private Integer tid;
    private String title;
    private String txtsrc;
    private String psrc;
    private Date time;
    private String type;
    private String introduction;
}
