package com.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by wenxinyan on 2019/07/01 8:45
 */

@Data
@TableName(value = "shoppingcar")
public class ShoppingCar implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer productId;
    private Integer userId;
    private Integer productNum;
    private Integer productState;
    private String  productUrl;
    private String productName;
    private Double productPrice;
}
