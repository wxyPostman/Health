package com.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by yaohandong on  2019/6/24 15:32
 */
@Data
@TableName(value = "reply")
public class Reply  implements Serializable {
    @TableId(value = "rid",type = IdType.AUTO)
    private Integer rid;
    private String content;
    private Integer qid;
    private String uname;
    private String psrc;
    private String rtime;
}
