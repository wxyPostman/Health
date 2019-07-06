package com.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yaohandong on  2019/6/24 15:32
 */
@Data
@TableName(value = "question")
public class Question  implements Serializable {
    @TableId(value = "qid",type = IdType.AUTO)
    private  Integer qid;
    private String qtitle;
    private String qcontent;
    private String type;
    private String time;
    private String uname;
    private  Integer adopt;
    private String psrc;
}
