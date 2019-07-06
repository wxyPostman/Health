window.onload = function () {
    $.ajax({
        url: "../pall",
        type: 'get', //请求的方式
        dataType: 'json', //设置返回的数据类型
        success: function (data) { //请求成功后返回的数据会封装在回调函数的第一个参数中
            for (var i = 0; i < data.length; i++) {
                $("#product_total").append(
                    "<div class='col-xs-6 col-sm-4 col-md-3 ' style='height: 450px'>" +
                    "<a class='thumbnail' href='resource-detal.html?id=" + data[i].pid + "'>" +
                    "<img src='" + data[i].purl + "'/>" +
                    "<div class='caption'>" +
                    "<div class='title'>" +
                    data[i].pname +
                    "</div>" +
                    "<div class='caption-content'>" +
                    data[i].introduction + "</div>" +
                    "<div>价格" +
                    "<span class='download-num'>" +
                    data[i].price +
                    "</div>" +
                    "<div class='clearfix'>" +
                    "<span class='integral'>库存：" +
                    data[i].volume + "</span>" +
                    "</div>" +
                    "</div>" +
                    "</a>" +
                    "</div>")
            }
        },
        error: function () { //响应不成功时返回的函数
            console.log('请求失败！')
        },
    })

    $("#btn-search").click(function () {
        console.log($("#search_input").val());
        if ($("#search_input").val() == null || $("#search_input").val() == undefined || $("#search_input").val() == "") {
            return
        } else {
            $.ajax({
                url: "../findByPname",
                type: 'post',
                dataType: 'json',
                data: {
                    pname: $("#search_input").val(),
                },
                success: function (data) {
                    $("#product_total").empty();
                    for (var i = 0; i < data.length; i++) {
                        $("#product_total").append(
                            "<div class='col-xs-6 col-sm-4 col-md-3' style='height: 450px'>" +
                            "<a class='thumbnail' href='resource-detal.html?id=" + data[i].pid + "'>" +
                            "<img src='" + data[i].purl + "'/>" +
                            "<div class='caption'>" +
                            "<div class='title'>" +
                            data[i].pname +
                            "</div>" +
                            "<div class='caption-content'>" +
                            data[i].introduction + "</div>" +
                            "<div>价格" +
                            "<span class='download-num'>" +
                            data[i].price +
                            "</div>" +
                            "<div class='clearfix'>" +
                            "<span class='integral'>库存：" +
                            data[i].volume + "</span>" +
                            "</div>" +
                            "</div>" +
                            "</a>" +
                            "</div>")
                    }
                },
                error: function () { //响应不成功时返回的函数
                    console.log('请求失败！');
                },
            })
        }

    })
}