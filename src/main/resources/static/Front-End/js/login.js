$("#login-btn").click(function() {
    login()
})
function login(){
    var uname = $("#login_name").val();
    var password = $("#login_password").val();
    if (!/^[a-zA-Z0-9\u4e00-\u9fa5]{2,6}$/.test(uname)) {
        $("#login_name_Top").text("用户名不能少于2位");
        $("#login_name_Top").css("display", "block");
    } else {
        $("#login_name_Top").text("");
        $("#login_name_Top").css("display", "block");
        if (!/^[a-zA-Z0-9]{6,16}$/.test(password)) {
            $("#login_password_Top").text("密码为 6-16 位");
            $("#login_password_Top").css("display", "block");
        } else {
            $("#login_password_Top").text("");
            $("#login_password_Top").css("display", "block");
            $.ajax({
                data: {
                    uname: uname,
                    password: password
                },
                url: "../user_login",
                type: "POST",
                dataType: 'json',
                success: function(data) {
                    if (data == 200) {
                        $("#login").modal("hide")
                        $("#success_login").css("display", "none");
                        $("#success_register").css("display", "none");
                        $("#login_success_show").css("display", "block");
                        location.reload();
                        alert("登录成功")
                    } else if (data == 500) {
                        alert("没有该账号,请前往注册!")
                        $("#login").modal("hide");
                    } else {
                        alert("用户名或密码错误")
                        $("#success_login").css("display", "block");
                        $("#success_register").css("display", "block");
                        $("#login_success_show").css("display", "none");
                    }
                }
            })
        }
    }
}
$("#login_password").click(function () {
    if(event.keyCode==13){
        login()
    }
})
//退出
function exit() {
    $.ajax({
        url: "../login_exit",
        type: "POST",
        async: true,
        cache: false,
        dataType: "json",
        success: function(get) {
            location.reload();
            $("#success_login").css("display", "block");
            $("#success_register").css("display", "block");
            $("#login_success_show").css("display", "none");

        }
    })
}

function exita() {
    $.ajax({
        url: "../login_exit",
        type: "POST",
        async: true,
        cache: false,
        dataType: "json",
        success: function(get) {
            window.location.href = "index.html";

        }
    })
}

var vm = new Vue({
    el: '#content',
    data: {
        username: '',
        email: '',
        phone: '',
        aboutme: 'This is me',
        signature: 'This is My Signature',
        length: 0,
        length2: 0,
        psrc: '',
    }
})

var headp = new Vue({
    el: '#headp',
    data: {
        psrc: '',
    }
})

var password = new Vue({
    el: '#edit-pw',
    data: {
        old_password: '',
        new_password: '',
        checknew: '',
        text_old: '密码错误',
        text_check_new: '两次密码不一致',
    },
    methods: {
        check_old: function(obj) {
            $.ajax({
                type: 'post',
                url: '../checkpassword',
                data: {
                    uname: vm.username,
                    password: password.old_password,
                },
                dataType: 'json',
                success: function(data) {
                    console.log(data)
                    if (data == 200) {
                        password.text_old = '';
                    }
                }
            })

        },
        check_new: function(obj) {
            if (password.new_password == password.checknew) {
                password.text_check_new = '';
            }
        },
        update_password: function() {
            if (password.text_old == '' && password.text_check_new == '') {
                $.ajax({
                    type: 'post',
                    url: '../updatepassword',
                    data: {
                        password: password.new_password,
                    },
                    dataType: 'json',
                    success: function(data) {
                        if (data == 200) {
                            password.old_password = '';
                            password.new_password = '';
                            password.checknew = '';
                            alert("密码修改成功")
                        }
                    }
                })
            } else {
                alert("请正确填写密码")
            }

        }
    }
})
$.ajax({
    url: "../check_login",
    type: "GET",
    dataType: "json",
    success: function(data) {
        var phone = data.phone;
        vm.username = data.uname;
        vm.phone = data.phone;
        vm.email = data.email;
        vm.psrc = data.psrc;
        headp.psrc = data.psrc;
        if (data.aboutme != null) {
            vm.aboutme = data.aboutme;
        }
        if (data.signature != null) {
            vm.signature = data.signature;
        }
        if (data != 400) {
            $("#login_success_name").text(data.uname);

            $("#success_login").css("display", "none");
            $("#success_register").css("display", "none");
            $("#login_success_show").css("display", "block");

            $("#isLoginBtn").css("display", "block");
            $("#notLoginBtn").css("display", "none");

            $("#reply-btn").removeClass().addClass("btn btn-primary main-btn btn-quiz");
        } else {
            $("#login_success_show").css("display", "none");
            $("#success_login").css("display", "block");
            $("#success_register").css("display", "block");

            $("#isLoginBtn").css("display", "none");
            $("#notLoginBtn").css("display", "block");

            $("#reply-btn").removeClass().addClass("btn btn-primary main-btn");
        }
    }
})

function oninput_area(obj) {
    vm.length = $(obj).val().length;
    vm.aboutme = $(obj).val();
}

function oninput_area2(obj) {
    vm.length2 = $(obj).val().length;
    vm.signature = $(obj).val();
}

function updateself(obj) {
    $.ajax({
        type: 'get',
        url: '../userupdate',
        dataType: 'json',
        data: {
            uname: vm.username,
            phone: vm.phone,
            email: vm.email,
            aboutme: vm.aboutme,
            signature: vm.signature,
            psrc: vm.psrc,
        },
        success: function(data) {
            if (data == 200) {
                alert("更新成功")
            } else {
                alert("更新失败")
            }
        }
    })
}

var update_p = new Vue({
    el:'#edit-avatar',
    data:{
        otherp:[],
        progress:0,
        flag:false,
    }
})


function uploadhead(obj) {
    var file = document.getElementById('userPhotoInput').files[0];
    if (file.size>20*1024*1024) {
        alert("图片大小不能超过20MB");
        return;
    }
    var form = new FormData();
    form.append('file',file);
    $.ajax({
        url: '../uploadhead',//上传地址
        async: true,//异步
        type: 'post',//post方式
        data: form,//FormData数据
        processData: false,//不处理数据流 !important
        contentType: false,//不设置http头 !important
        xhr:function(){//获取上传进度
            myXhr = $.ajaxSettings.xhr();
            if(myXhr.upload){
                console.log("这在获取进度");
                update_p.flag=true;
                console.log(update_p.flag);
                myXhr.upload.addEventListener('progress',function(e){//监听progress事件
                    var loaded = e.loaded;//已上传
                    var total = e.total;//总大小
                    var percent = Math.floor(100*loaded/total);//百分比
                    // processNum.text(percent+"%");//数显进度
                    update_p.progress = percent;
                    $("#processBar").css("width",percent+"%");
                    // processBar.css("width",percent+"px");//图显进度}, false);
                })
                return myXhr;
            }
        },
        success: function(data){//上传成功回调
            console.log("文档当前位置是："+data);//获取文件链接
            if (data == "400"){
                alert("文件上传失败")
            }else{
                vm.psrc = data;
                headp.psrc = data;
                update_p.progress = 0;
                $("#processBar").css("width",0+"px");
                update_p.flag = false;
            }
        }
    })
}