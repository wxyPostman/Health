var getId = getQueryString("id");
window.onload = function () {


    $.ajax({
        url: "../findByPid",
        type: 'post',
        dataType: 'json',
        data: {
            pid: getId
        },
        success: function (data) {
            for(var i=0;i<data.length;i++){
                $("#list-content").append("<div class='media'>" +
                    "<div class='media-body'>" +
                    "<h4 class='media-heading'>" +
                    data[i].uname +
                    "</h4>" +
                    data[i].content  +
                    "</div>" +
                    "</div>")
            }
        },
    })

    $("#subBtn").click(function () {
        if (($("#num_show").val() - 1) <= 1) {
            $("#num_show").val(1);
        } else {
            $("#num_show").val($("#num_show").val() - 1);
        }
    })
    $("#addBtn").click(function () {
        $("#num_show").val(parseInt($("#num_show").val()) + 1);
    })

    console.log(getId);
    $.ajax({
        url: "../findProductById",
        type: 'post',
        dataType: 'json',
        data: {
            id: getId
        },
        success: function (data) {
            // console.log(data);
            $("#product_img").append(" <img id='purl' src='" + data.purl + "' width='90%'/>");
            $("#product_title").append(" <h3 id='pname'>" +
                data.pname +
                "</h3>" +
                "<p class = 'title' >" +
                data.introduction +
                "</p>"
            );
            $("#summary").append("<p class='activity'><span>活动价</span><strong class='price' id='price'>" + data.price + "</strong></p>" +
                "<p><span>库&nbsp;&nbsp;&nbsp;&nbsp;存</span><strong class='address'>" + data.volume + "</strong></p>");
            $("#product_introduction").append(data.introduction);
        },
        error: function () {
            console.log('请求失败！');
        },
    })

    $("#addShopCar").click(function () {
        $.ajax({
            url: "../check_login",
            type: 'post',
            dataType: 'json',
            success: function (data) {
                if (data.uid != undefined) {
                    addShopingCar(data.uid);
                } else {
                    alert("请先登录!");
                }
            },
            error: function () {
                alert("请先登录!");
            }
        })
    })

}


function addShopingCar(id) {
    $.ajax({
        url: "../saveShoppingCar",
        type: 'post',
        dataType: 'json',
        data: {
            productId: getId,
            userId: id,
            productNum: $("#num_show").val(),
            productname: $("#pname").text(),
            producturl: $("#purl")[0].src,
            productprice: parseFloat($("#price").text())
        },
        success: function (data) {
            if (data == 200) {
                alert("添加成功")
            } else {
                alert("添加失败");
            }
        },
        error: function () {
            console.log('添加失败！');
        },
    })



}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = decodeURI(window.location.search).substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}