$("#btn-register").click(function () {
 register();
})
function register(){
    var uname = $("#reg_name").val();
    var password = $("#reg_password").val();
    var phone = $("#phone").val();
    var email = $("#email").val();
    var capts=$("#captcha").val();
    if(!/^[a-zA-Z0-9\u4e00-\u9fa5]{2,6}$/.test(uname)){
        $("#reg_name_Top").text("用户名不能少于2位");
        $("#reg_name_Top").css("display", "block");
    }else {
        $("#reg_name_Top").text("");
        $("#reg_name_Top").css("display", "block");
        if (!/^[a-zA-Z0-9]{6,16}$/.test(password)) {
            $("#reg_password_Top").text("密码为 6-16 位");
            $("#reg_password_Top").css("display", "block");
        } else {
            $("#reg_password_Top").text("");
            $("#reg_password_Top").css("display", "block");
            if (!/^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\d{8}$/.test(phone)) {
                $("#phone_Top").text("电话号码不正确");
                $("#phone_Top").css("display", "block");
            } else {
                $("#phone_Top").text("");
                $("#phone_Top").css("display", "block");
                if (!/^\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}$/.test(email)) {
                    $("#email_Top").text("邮箱号码不正确");
                    $("#email_Top").css("display", "block");
                } else {
                    $("#email_Top").text("");
                    $("#email_Top").css("display", "block");
                    if(cap!=capts){
                        $("#captcha_Top").text("验证号不正确");
                        $("#captcha_Top").css("display", "block");
                    }else{
                        $("#captcha_Top").text("");
                        $("#captcha_Top").css("display", "block");
                        $.ajax({
                            data: {
                                uid: 0,
                                uname: uname,
                                password: password,
                                phone: phone,
                                email: email
                            },
                            url: "../user_reg",
                            type: "POST",
                            dataType: 'json',
                            success: function (data) {
                                if (data == 200) {
                                    alert("注册成功")
                                    setValNull();
                                } else {
                                    alert("注册失败")
                                }

                            }
                        })
                    }

                }
            }
        }
    }
}

$("#captcha").click(function () {
    if (event.keyCode==13){
        register();
    }
})
$("#reg_name").blur(function () {
    var uname = $("#reg_name").val();
    if (!/^[a-zA-Z0-9\u4e00-\u9fa5]{2,6}$/.test(uname)) {
         $("#reg_name_Top").text("用户名为 2-6 位");
         $("#reg_name_Top").css("display", "block");
    } else {
        $.ajax({
            data: {
                uname: uname
            },
            url: "../check_name",
            type: "POST",
            dataType: "json",
            success: function (data) {
                if (data == 200) {
                    $("#reg_name_Top").text("");
                    $("#reg_name_Top").css("display", "block");
                } else {
                    $("#reg_name_Top").text("用户名被占用，请更换！");
                    $("#reg_name_Top").css("display", "block");
                }

            }
        })
    }
})
var phoness = "";
$("#phone").blur(function () {
    var phone = $("#phone").val();
    if (!/^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\d{8}$/.test(phone)) {
       $("#phone_Top").text("电话号码不正确");
         $("#phone_Top").css("display", "block");
    } else {
        $.ajax({
            data: {
                phone: phone
            },
            url: "../check_phone",
            type: "POST",
            dataType: "json",
            success: function (data) {
                if (data == 200) {
                    phoness=data;
                    $("#phone_Top").text("");
                    $("#phone_Top").css("display", "block");
                } else {
                    phoness=data;
                    $("#phone_Top").text("电话号码被占用，请更换!");
                    $("#phone_Top").css("display", "block");

                }
            }
        })
    }
})
$("#get_phone_num").click(function () {
    if (phoness == 400) {
        $("#captcha_Top").text("电话号码被占用，请更换!");
        $("#captcha_Top").css("display", "block");
    } else {
        Captcha();
        sendemail();
    }
});
var cap = "";
function Captcha() {
    var phone = $("#phone").val();
    $.ajax({
        data: {
            phone: phone,
        },
        url: "../captcha",
        type: "POST",
        success: function (data) {
            //alert("发送成功！")
            cap = data;
        }
    })
    return cap;
}

function setValNull() {
    $("#reg_name").val("");
    $("#reg_password").val("");
    $("#phone").val("");
    $("#email").val("");
    $("#captcha").val("");
}

//倒计时
var countdown = 60;

function sendemail() {
    var obj = $("#get_phone_num");
    settime(obj);
}

function settime(obj) { //发送验证码倒计时
    if (countdown == 0) {
        obj.attr('disabled', false);
        //obj.removeattr("disabled");
        obj.text("获取验证码");
        countdown = 60;
        return;
    } else {
        obj.attr('disabled', true);
        obj.text("重新发送(" + countdown + ")");
        countdown--;
    }
    setTimeout(function () {
        settime(obj)
    }, 1000)
}