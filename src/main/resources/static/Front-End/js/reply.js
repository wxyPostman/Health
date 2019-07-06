var vuetest = new Vue({
    el: '#vuetest',
    data: {
        qtitle: "",
        nosolved: "",
        solved: "",
        issolve: "",
        psrc: "",
        uname: "",
        time: "",
        qcontent: "",
        type: ""
    }
})

window.onload = function () {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }

    $.ajax({
        data: {
            qid: $.getUrlParam('qid')
        },
        url: "../detail_question",
        type: "POST",
        datatype: "json",
        success: function (data) {

            vuetest.qtitle = data.qtitle,
                vuetest.psrc = data.psrc,
                vuetest.uname = data.uname,
                vuetest.time = data.time,
                vuetest.qcontent = data.qcontent,
                vuetest.type = data.type

            if (data.adopt == 0) {
                vuetest.nosolved = true;
                vuetest.solved = false;
                vuetest.issolve = '待解决';
            } else {
                vuetest.nosolved = false;
                vuetest.solved = true;
                vuetest.issolve = '已解决';
            }

            if (data.adopt != 0) {
                $("#radopt").css("display", "block");
                $.ajax({
                    data: {
                        rid: data.adopt
                    },
                    url: "../detail_reply",
                    type: "POST",
                    dataType: "json",
                    success: function (data2) {
                        $("#adoptreply").empty();
                        $("#adoptreply").append("<div class='clearfix'><div class='icon pull-left'><a href='#'><img src='" + data2.psrc + "' /></a></div><small class='pull-left'><strong><a href='#'>" + data2.uname + "</a></strong><divclass='visible-xs-inline-block visible-sm-inline-block visible-md-inline-block visible-lg-inline-block'><a class='permalink' href='#'><span class='timeago'> " + data2.rtime + "</span></a></div></small></div><br /><div class='content'>" + data2.content + "</div><div class='clearfix'><small class='pull-right post-tools'><a href='#' data-toggle='modal' data-target='#report'><i class='icon-flag'></i>举报</a></small></div><hr />");
                    }
                })
            } else {
                $("#radopt").css("display", "none");
            }

        }
    })

    $.ajax({
        data: {
            qid: $.getUrlParam('qid')
        },
        url: "../find_replys",
        type: "POST",
        dataType: "json",
        success: function (data) {
            $("#rlist").empty();
            if (data.length > 0) {
                $("#havemore").empty();
                $("#havemore").append("<nav aria-label='Page navigation' style='text-align: center'><ul class='pagination' id='qpage'></ul></nav>");
                $("#qpage").append("<li class='disabled'><a href='#' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>");
                for (var i = 0; i < (data.length / 5); i++) {
                    $("#qpage").append("<li id='qpage" + (i + 1) + "'><a href='javascript:void(0)' onclick='changepage(" + (i + 1) + ")'>" + (i + 1) + "</a></li>");
                }
                $("#qpage").append("<li class='disabled'><a href='#' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>");

                $.ajax({
                    data: {
                        qid: $.getUrlParam('qid')
                    },
                    url: "../isasker",
                    type: "POST",
                    dataType: "json",
                    success: function (data2) {
                        if (data2 == 200) {
                            for (var i = 0; i < data.length && i < 5; i++) {
                                $("#rlist").append("<div class='clearfix'><div class='icon pull-left'><a href='#'><img src='" + data[i].psrc + "' /></a></div><small class='pull-left'><strong><a href='#'>" + data[i].uname + "</a></strong><divclass='visible-xs-inline-block visible-sm-inline-block visible-md-inline-block visible-lg-inline-block'><a class='permalink' href='#'><span class='timeago'> " + data[i].rtime + "</span></a></div></small></div><br /><div class='content'>" + data[i].content + "</div><div class='clearfix'><small class='pull-right post-tools'><a href='javascript:void(0)' onclick='adoptr(" + data[i].rid + ")'><i class='icon-pushpin'></i>采纳</a><a href='#' data-toggle='modal' data-target='#report'><i class='icon-flag'></i>举报</a></small></div><hr />");
                            }
                        } else {
                            for (var i = 0; i < data.length && i < 5; i++) {
                                $("#rlist").append("<div class='clearfix'><div class='icon pull-left'><a href='#'><img src='" + data[i].psrc + "' /></a></div><small class='pull-left'><strong><a href='#'>" + data[i].uname + "</a></strong><divclass='visible-xs-inline-block visible-sm-inline-block visible-md-inline-block visible-lg-inline-block'><a class='permalink' href='#'><span class='timeago'> " + data[i].rtime + "</span></a></div></small></div><br /><div class='content'>" + data[i].content + "</div><div class='clearfix'><small class='pull-right post-tools'><a href='#' data-toggle='modal' data-target='#report'><i class='icon-flag'></i>举报</a></small></div><hr />");
                            }

                        }
                    }
                })
            } else {
                $("#havemore").empty();
                $("#rlist").append("<li class='row' style='text-align: center'><h1>暂时还没有此类问题哦!</h1></li>");
            }
        }
    })
}

function changepage(number) {
    $.ajax({
        data: {
            qid: $.getUrlParam('qid')
        },
        url: "../find_replys",
        type: "GET",
        dataType: "json",
        success: function (data) {
            $("#havemore").empty();
            $("#havemore").append("<nav aria-label='Page navigation' style='text-align: center'><ul class='pagination' id='qpage'></ul></nav>");
            $("#qpage").append("<li class='disabled'><a href='#' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>");
            for (var i = 0; i < (data.length / 5); i++) {
                $("#qpage").append("<li id='qpage" + (i + 1) + "'><a href=javascript:void(0)' onclick='changepage(" + (i + 1) + ")'>" + (i + 1) + "</a></li>");
            }
            $("#qpage").append("<li class='disabled'><a href='#' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>");

            $("#qpage" + number).addClass("active");

            $("#rlist").empty();

            $.ajax({
                data: {
                    qid: $.getUrlParam('qid')
                },
                url: "../isasker",
                type: "POST",
                dataType: "json",
                success: function (data2) {
                    if (data2 == 200) {
                        for (var i = (number - 1) * 5; i < data.length && i < 5 * number; i++) {
                            $("#rlist").append("<div class='clearfix'><div class='icon pull-left'><a href='#'><img src='" + data[i].psrc + "' /></a></div><small class='pull-left'><strong><a href='#'>" + data[i].uname + "</a></strong><divclass='visible-xs-inline-block visible-sm-inline-block visible-md-inline-block visible-lg-inline-block'><a class='permalink' href='#'><span class='timeago'> " + data[i].rtime + "</span></a></div></small></div><br /><div class='content'>" + data[i].content + "</div><div class='clearfix'><small class='pull-right post-tools'><a href='javascript:void(0)' onclick='adoptr(" + data[i].rid + ")'><i class='icon-pushpin'></i>采纳</a><a href='#' data-toggle='modal' data-target='#report'><i class='icon-flag'></i>举报</a></small></div><hr />");
                        }
                    } else {
                        for (var i = (number - 1) * 5; i < data.length && i < 5 * number; i++) {
                            $("#rlist").append("<div class='clearfix'><div class='icon pull-left'><a href='#'><img src='" + data[i].psrc + "' /></a></div><small class='pull-left'><strong><a href='#'>" + data[i].uname + "</a></strong><divclass='visible-xs-inline-block visible-sm-inline-block visible-md-inline-block visible-lg-inline-block'><a class='permalink' href='#'><span class='timeago'> " + data[i].rtime + "</span></a></div></small></div><br /><div class='content'>" + data[i].content + "</div><div class='clearfix'><small class='pull-right post-tools'><a href='#' data-toggle='modal' data-target='#report'><i class='icon-flag'></i>举报</a></small></div><hr />");
                        }

                    }
                }
            })
        }
    })
}

function reportreply() {
    alert("举报成功，请耐心等待客服处理！");
    $('#report').modal('hide');
}

$("#reply-add").click(function () {
    if ($("#qcontent").val() == null || $("#qcontent").val() == "") {
        alert("内容不能为空！");
        return false;
    } else {
        $.ajax({
            data: {
                content: $("#qcontent").val(),
                qid: $.getUrlParam('qid'),
            },
            url: "../add_reply",
            type: "POST",
            dataType: "json",
            success: function (data) {
                if (data != 400) {
                    alert("回复成功！");
                    location.reload();
                } else {
                    alert("回复失败！");
                }
            }
        })
    }
})

function adoptr(rid) {

    if (confirm("只有一次采纳机会，你确定采纳吗？")) {
        $.ajax({
            data: {
                qid: $.getUrlParam('qid'),
                rid: rid
            },
            url: "../adopt_reply",
            type: "POST",
            dataType: "json",
            success: function (data) {
                if (data != 400) {
                    alert("采纳成功！");
                    location.reload();
                } else {
                    alert("采纳失败！");
                }
            }
        })
    }

}