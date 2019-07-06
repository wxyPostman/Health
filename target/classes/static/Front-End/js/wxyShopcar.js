window.onload = function demo() {

    //判断登录状态
    $.ajax({
        url: "../check_login",
        type: 'post',
        dataType: 'json',
        success: function (data) {
            if (data.uid != undefined) {
                optionUser(data.uid);
            } else {
                alert("请先登录!");
            }
        },
        error: function () {
            alert("请先登录!");
        }
    })

    //增加
    $(document).on('click', '.addCss', function (ev) {
        var flag = $(this).prev().val();
        var rmb = $(this).parent().prev().text();
        $(this).prev().val(parseInt(flag) + 1);
        $(this).parent(".count").next().text($(this).parent(".count").prev().text() * $(this).prev().val());
        // getAll($(this).parent().prev().prev().prev().find(".checkCss"));
        if ($($(this).parent().prev().prev().prev().find(".checkCss")).is(':checked')){
            price += parseInt(rmb);
            number +=1;
            $("#priceTotal").text(price);
            $("#countTotal").text(number);
        }
    });

    //减少
    $(document).on('click', '.reduceCss', function (ev) {
        var flag = $(this).next().val();
        var rmb = $(this).parent().prev().text();
        if (flag <= 1) {
            $(this).next().val(1);
            $(this).parent(".count").next().text($(this).parent(".count").prev().text() * $(this).next().val())
            return;
        } else {
            $(this).next().val(parseInt(flag) - 1);
            $(this).parent(".count").next().text($(this).parent(".count").prev().text() * $(this).next().val())
        }
        // getAll($(this).parent().prev().prev().prev().find(".checkCss"));
        if ($($(this).parent().prev().prev().prev().find(".checkCss")).is(':checked')){
            price -= parseInt(rmb);
            number -=1;
            $("#priceTotal").text(price);
            $("#countTotal").text(number);
        }
    });


    //全选
    $("#checkAll").change(function () {
        if ($("#checkAll").is(":checked")) {
            $(".checkCss").prop("checked", true);
        } else {
            $(".checkCss").prop("checked", false);
        }
    });

    $(document).on('click', '.checkCss', function () {
        getAll(this);
    });


}

var price = 0;
var number = 0;
var num = [];
var id = [];
var names = '';
function getAll(getClass) {

    if ($(getClass).is(":checked")) {
        // $("#checkAll").prop("checked",true);
        // console.log($('.checkCss').prevAll(".checkCss").is(":checked"));
        console.log($(getClass).parent().parent().find(".showTotalPrice").text());
        console.log($(getClass).parent().parent().find(".inputCountCss").val());
        price += parseInt($(getClass).parent().parent().find(".showTotalPrice").text());
        number += parseInt($(getClass).parent().parent().find(".inputCountCss").val());
        $("#priceTotal").text(price);
        $("#countTotal").text(number);


        console.log($(getClass).parent().parent().find(".getId").val())

        // isChecked(($(getClass).parent().parent().find(".inputCountCss").val()),getClass);
    } else {
        //取消选中
        price -= parseInt($(getClass).parent().parent().find(".showTotalPrice").text());
        number -= parseInt($(getClass).parent().parent().find(".inputCountCss").val());
        $("#priceTotal").text(price);
        $("#countTotal").text(number);
    }
}





    $("#btn-userinfo").click(function() {
        var checked = $("input[name='productCheck']:checked");
        for (var i = 0;i<checked.length;i++){
            id.push(parseInt($(checked[i]).parent().prev().children().val()));
            num.push(parseInt($(checked[i]).parent().next().next().next().find("input[class='inputCountCss']").val()));
            names += $("input[name='productCheck']:checked").parent().next().children()[1].innerText+',';
        }
        $.ajax({
            type:'post',
            url:"../order/alipay",
            dataType:'html',
            data:{
                uname:$("#userinfo_name").val(),
                address:$("#userinfo_address").val(),
                phone:$("#userinfo_phone").val(),
                "id": JSON.stringify(id),
                "num": JSON.stringify(num),
                'name':names,
                'amount':$("#priceTotal").text(),
            },
            success:function (data) {
                $("#content").append(data)

            }
        })
    })



function deleteById(productId){
    $.ajax({
        url: "../deleteById",
        type: "post",
        dataType: "text",
        data: {
            productId: productId
        },
        success: function (req) {
            if(req==200){
                $("#tbody").empty();
                window.location.reload();
            }else{
                alert("删除失败");
            }
        },
        error: function () {
            alert("错误");
        }
    })

}


function optionUser(id) {
    $.ajax({
        url: "../findShoppingByUser",
        type: "post",
        dataType: "json",
        data: {
            userId: id
        },
        success: function (req) {
            console.log(req);
            for (var i = 0; i < req.length; i++) {
                $("#tbody").append("<tr><td class='hidden'> <input class='getID' type='text' value='"+ req[i].id +"'/></td> <td><input name='productCheck' type='checkbox' class='checkCss' /></td>" +
                    "<td><p><img src='" + req[i].productUrl + "' /></p>" +
                    "<p>" + req[i].productName + "</p>" +
                    "</td><td class='showOnePrice'>" + req[i].productPrice + "</td>" +
                    "<td class='count'>" +
                    "<input class='reduceCss' value='-' type='button' />" +
                    "<input type='text' class='inputCountCss' value='1' size='8' />" +
                    "<input class='addCss' value='+' type='button' />" +
                    "</td><td class='showTotalPrice'>" +
                    req[i].productPrice +
                    "</td><td><button class='btn' onclick='deleteById("+req[i].id+")'>删除</button></td></tr>");
            }
        },
        error: function () {
            alert("添加商品到购物车吧")
        }
    })
}