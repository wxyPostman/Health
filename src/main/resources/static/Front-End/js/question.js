window.onload = function () {
    $.ajax({
        url: "../find_questions",
        type: "POST",
        dataType: "json",
        success: function (data) {
            $("#qlist").empty();
            getdata(data);
        }
    })
}

authtitle = function () {
    var title = $("#qtitle").val();
    if (title == null || title == "") {
        $("#tip1").css("display", "block");
    } else {
        $("#tip1").css("display", "none");
    }
}

$("#quiz-add").click(function () {
    if ($("#qtitle").val() == null || $("#qtitle").val() == "") {
        alert("标题不能为空！");
        return false;

    } else if ($("#qcontent").val() == null || $("#qcontent").val() == "") {
        alert("内容不能为空！");
        return false;
    } else {
        $.ajax({
            data: {
                qtitle: $("#qtitle").val(),
                qcontent: $("#qcontent").val(),
                type: $("#type").val(),
            },
            url: "../add_question",
            type: "POST",
            dataType: "json",
            success: function (data) {
                if (data != 400) {
                    alert("提问成功！");
                    location.reload();
                } else {
                    alert("提问失败！");
                }
            }
        })
    }
})

function findall() {
    $.ajax({
        url: "../find_questions",
        type: "POST",
        dataType: "json",
        success: function (data) {
            $("#qtag").empty();
            $("#qtag").append("全部")
            $("#qlist").empty();
            getdata(data);
        }
    })
}

function findsolve() {
    $.ajax({
        data: {
            flag: 200
        },
        url: "../solved_questions",
        type: "POST",
        dataType: "json",
        success: function (data) {
            $("#qtag").empty();
            $("#qtag").append("已解决")
            $("#qlist").empty();
            getdata(data);
        }
    })
}

function findnosolve() {
    $.ajax({
        data: {
            flag: 400
        },
        url: "../solved_questions",
        type: "POST",
        dataType: "json",
        success: function (data) {
            $("#qtag").empty();
            $("#qtag").append("待解决")
            $("#qlist").empty();
            getdata(data);
        }
    })
}

function findbody() {
    $.ajax({
        data: {
            type: "身体问题"
        },
        url: "../type_questions",
        type: "POST",
        dataType: "json",
        success: function (data) {
            $("#qtag").empty();
            $("#qtag").append("身体问题")
            $("#qlist").empty();
            getdata(data);
        }
    })
}

function findpsychic() {
    $.ajax({
        data: {
            type: "心理问题"
        },
        url: "../type_questions",
        type: "POST",
        dataType: "json",
        success: function (data) {
            $("#qtag").empty();
            $("#qtag").append("心理问题")
            $("#qlist").empty();
            getdata(data);
        }
    })
}

function findspirit() {
    $.ajax({
        data: {
            type: "精神问题"
        },
        url: "../type_questions",
        type: "POST",
        dataType: "json",
        success: function (data) {
            $("#qtag").empty();
            $("#qtag").append("精神问题")
            $("#qlist").empty();
            getdata(data);
        }
    })
}


function findtitle() {
    $.ajax({
        data: {
            qtitle: $("#sqtitle").val()
        },
        url: "../find_title",
        type: "POST",
        dataType: "json",
        success: function (data) {
            $("#qtag").empty();
            $("#qtag").append($("#sqtitle").val() + "的查询结果")
            $("#qlist").empty();
            getdata(data);
        }
    })
}

function getdata (data){
    if (data.length > 0) {
        $("#havemore").empty();
        $("#havemore").append("<nav aria-label='Page navigation' style='text-align: center'><ul class='pagination' id='qpage'></ul></nav>");
        $("#qpage").append("<li class='disabled'><a href='#' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>");
        for (var i = 0; i < (data.length / 10); i++) {
            $("#qpage").append("<li id='qpage" + (i + 1) + "'><a href=javascript:void(0)' onclick='changepage(" + (i + 1) + ")'>" + (i + 1) + "</a></li>");
        }
        $("#qpage").append("<li class='disabled'><a href='#' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>");

        for (var i = 0; i < data.length && i < 10; i++) {
            if (data[i].adopt == 0) {
                $("#qlist").append("<li class='row'><div class='col-md-7 col-sm-9 col-xs-12'><div class='avatar pull-left'><a href='#' class='pull-left' data-toggle='tooltip' title='"+data[i].uname+"'><img src='"+ data[i].psrc +"' class='user-img' /></a></div><h2><a href='quiz-detal.html?qid=" + data[i].qid + "'>" + data[i].qtitle + "</a><br /><small class='hidden-xs'><span>" + data[i].time + "</span></small><small class='visible-xs-inline'><span>" + data[i].time + "</span></small></h2></div><div class='col-md-2 hidden-sm hidden-xs stats'><span class='human-readable-number'>" + data[i].type + "</span><br /><small>分类</small></div><div class='col-md-3 col-sm-3 teaser hidden-xs'><div class='card' style='padding-top: 15px'><span class='glyphicon glyphicon-question-sign'></span> 待解决<br /></div></div></li>");
            } else {
                $("#qlist").append("<li class='row'><div class='col-md-7 col-sm-9 col-xs-12'><div class='avatar pull-left'><a href='#' class='pull-left' data-toggle='tooltip' title='"+data[i].uname+"'><img src='"+ data[i].psrc +"' class='user-img' /></a></div><h2><a href='quiz-detal.html?qid=" + data[i].qid + "'>" + data[i].qtitle + "</a><br /><small class='hidden-xs'><span>" + data[i].time + "</span></small><small class='visible-xs-inline'><span>" + data[i].time + "</span></small></h2></div><div class='col-md-2 hidden-sm hidden-xs stats'><span class='human-readable-number'>" + data[i].type + "</span><br /><small>分类</small></div><div class='col-md-3 col-sm-3 teaser hidden-xs'><div class='card' style='padding-top: 15px'><span class='glyphicon glyphicon-ok-sign'></span> 已解决<br /></div></div></li>");
            }
        }
    } else {
        $("#havemore").empty();
        $("#qlist").append("<li class='row' style='text-align: center'><h1>暂时还没有此类问题哦!</h1></li>");
    }
}

function changepage(number) {
    $.ajax({
        url: "../get_questions",
        type: "GET",
        dataType: "json",
        success: function (data) {
            $("#havemore").empty();
            $("#havemore").append("<nav aria-label='Page navigation' style='text-align: center'><ul class='pagination' id='qpage'></ul></nav>");
            $("#qpage").append("<li class='disabled'><a href='#' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>");
            for (var i = 0; i < (data.length / 10); i++) {
                $("#qpage").append("<li id='qpage" + (i + 1) + "'><a href=javascript:void(0)' onclick='changepage(" + (i + 1) + ")'>" + (i + 1) + "</a></li>");
            }
            $("#qpage").append("<li class='disabled'><a href='#' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>");

            $("#qpage" + number).addClass("active");

            $("#qlist").empty();
            for (var i = (number - 1) * 10; i < data.length && i < 10 * number; i++) {
                if (data[i].adopt == 0) {
                    $("#qlist").append("<li class='row'><div class='col-md-7 col-sm-9 col-xs-12'><div class='avatar pull-left'><a href='#' class='pull-left' data-toggle='tooltip' title='"+data[i].uname+"'><img src='"+ data[i].psrc +"' class='user-img' /></a></div><h2><a href='quiz-detal.html?qid=" + data[i].qid + "'>" + data[i].qtitle + "</a><br /><small class='hidden-xs'><span>" + data[i].time + "</span></small><small class='visible-xs-inline'><span>" + data[i].time + "</span></small></h2></div><div class='col-md-2 hidden-sm hidden-xs stats'><span class='human-readable-number'>" + data[i].type + "</span><br /><small>分类</small></div><div class='col-md-3 col-sm-3 teaser hidden-xs'><div class='card' style='padding-top: 15px'><span class='glyphicon glyphicon-question-sign'></span> 待解决<br /></div></div></li>");
                } else {
                    $("#qlist").append("<li class='row'><div class='col-md-7 col-sm-9 col-xs-12'><div class='avatar pull-left'><a href='#' class='pull-left' data-toggle='tooltip' title='"+data[i].uname+"'><img src='"+ data[i].psrc +"' class='user-img' /></a></div><h2><a href='quiz-detal.html?qid=" + data[i].qid + "'>" + data[i].qtitle + "</a><br /><small class='hidden-xs'><span>" + data[i].time + "</span></small><small class='visible-xs-inline'><span>" + data[i].time + "</span></small></h2></div><div class='col-md-2 hidden-sm hidden-xs stats'><span class='human-readable-number'>" + data[i].type + "</span><br /><small>分类</small></div><div class='col-md-3 col-sm-3 teaser hidden-xs'><div class='card' style='padding-top: 15px'><span class='glyphicon glyphicon-ok-sign'></span> 已解决<br /></div></div></li>");
                }
        }
        }
    })
}