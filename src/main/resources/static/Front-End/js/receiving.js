//判断登录状态
$.ajax({
    url: "../check_login",
    type: 'post',
    dataType: 'json',
    success: function (data) {
        if (data.uid != undefined) {
            getAllProduct(data.uid);
        } else {
            alert("请先登录!");
        }
    },
    error: function () {
        alert("请先登录!");
    }
})

function getAllProduct(uid){

    $.ajax({
        url: "../findByUserid",
        type: "POST",
        dataType: "json",
        data:{
            uid:uid
        },
        success: function (req) {
            console.log(req);
            $("#tbodys").empty()
            for (var i = 0; i < req.length; i++) {
                if(req[i].productState==1){
                    $("#tbodys").append("<tr>" +
                        "<td>" +
                        "<p class='receivingImg'>" +
                        "<img src='" + req[i].productUrl + "'>" +
                        "</p>" +
                        "<p>" +
                        req[i].productName +
                        "</p>" +
                        "</td>" +
                        "<td>" +
                        req[i].productPrice+
                        "</td>"+
                        "<td>" +
                        "<button type='button' class='btn btn-info' onclick='state("+req[i].id+")'>确认收货</button>" +
                        "<button data-toggle='modal'  disabled='disabled'  onclick='saveComment("+req[i].productId +")'  data-target='#comment' type='button' class='btn btn-warning'>" +
                        "立即评价" +
                        " </button> </td> </tr>"
                    )
                }
                if(req[i].productState==2){
                    $("#tbodys").append("<tr>" +
                        "<td>" +
                        "<p class='receivingImg'>" +
                        "<img src='" + req[i].productUrl + "'>" +
                        "</p>" +
                        "<p>" +
                        req[i].productName +
                        "</p>" +
                        "</td>" +
                        "<td>" +
                        req[i].productPrice+
                        "</td>"+
                        "<td>" +
                        "<button type='button' class='btn btn-info' disabled='disabled' >已完成</button>" +
                        "<button data-toggle='modal' onclick='saveComment("+req[i].productId +")'  data-target='#comment' type='button' class='btn btn-warning'>" +
                        "立即评价" +
                        " </button> </td> </tr>"
                )
                }


            }
        },
        error: function () {
            console.log("请求出错")
        }
    })

}

function state(id) {
    $.ajax({
        url: "../updateId",
        type: "POST",
        dataType: "json",
        data:{
            id:id
        },
        success:function(data) {
            if(data==200){
                location.reload();
            }
        },
        error:function() {
            console.log("加载出错");
        }
    })
}

function saveComment(productId) {
    $("#btn-comment").click(function() {
        $.ajax({

            url: "../saveComment",
            type: "POST",
            dataType: "json",
            data:{
                content:$("#textComment").val(),
                pid:productId
            },
            success:function(data) {
                if(data==200){
                    alert("评论成功");
                    location.reload();
                }
            },
            error:function() {
                console.log("加载出错");
            }
        })
    })
}
