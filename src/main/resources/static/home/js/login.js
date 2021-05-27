$(function(){
	$('#switch_qlogin').click(function(){
		$('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
		$('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
		$('#switch_bottom').animate({left:'0px',width:'70px'});
		$('#qlogin').css('display','none');
		$('#web_qr_login').css('display','block');
		});
	
		$('#switch_login').click(function(){
		$('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
		$('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
		$('#switch_bottom').animate({left:'154px',width:'70px'});
		$('#qlogin').css('display','block');
		$('#web_qr_login').css('display','none');
		
		});
		
	if(getParam("a")=='0'){
		$('#switch_login').trigger('click');
	}
	
	});
	

function logintab(){
	scrollTo(0);
	$('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
	$('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
	$('#switch_bottom').animate({left:'154px',width:'96px'});
	$('#qlogin').css('display','none');
	$('#web_qr_login').css('display','block');
	
}


//根据参数名获得该参数 pname等于想要的参数名 
function getParam(pname) { 
    var params = location.search.substr(1); // 获取参数 平且去掉？ 
    var ArrParam = params.split('&'); 
    if (ArrParam.length == 1) { 
        //只有一个参数的情况 
        return params.split('=')[1]; 
    } 
    else { 
         //多个参数参数的情况 
        for (var i = 0; i < ArrParam.length; i++) { 
            if (ArrParam[i].split('=')[0] == pname) { 
                return ArrParam[i].split('=')[1]; 
            } 
        } 
    } 
}  


var reMethod = "GET",
	pwdmin = 6;

$(document).ready(function() {

    //登录
	$('#login-son').focus(function(){
		$('#loginCue').html("<font ><b>请输入学号</b></font>");
	}).blur(function(){
		if($('#login-son').val().length == 0){
			$('#loginCue').html("<font color='red'><b>学号不能为空</b></font>");
			return false;
		}else{
			$.ajax({
				type: "post" ,
				url: "checkSno",
				data: "sno=" + $("#login-son").val(),
                dataType: 'json',
				success: function(res) {
					if (res.data==true) {
						$('#login-son').css({
							border: "1px solid red",
							boxShadow: "0 0 2px red"
						});
						$("#loginCue").html("<font color='red'><b>学号不存在</b></font>");
						$("#login_button").attr("disabled","true");
						return false;
					} else {
						$('#login-son').css({
							border: "1px solid #D7D7D7",
							boxShadow: "none"
						});
						$("#loginCue").focus().html("<font ><b>请继续输入密码</b></font>");
						$("#login_button").removeAttr("disabled");
					}}
			});
		}
	})
	
	$('#login_button').click(function(){
		if($('#login-son').val().length == 0){
			$('#login-son').focus().css({
				border: "1px solid red",
				boxShadow: "0 0 2px red"
			});
			$('#loginCue').html("<font color='red'><b>学号不能为空</b></font>");
			return false;
		}else {
			$('#login-son').css({
				border: "1px solid #D7D7D7",
				boxShadow: "none"
			});
		}
		
		if($('#pwd').val().length == 0){
			$('#pwd').focus().css({
				border: "1px solid red",
				boxShadow: "0 0 2px red"
			});
			$('#loginCue').html("<font color='red'><b>密码不能为空</b></font>");
			return false;
		}else {
			$('#pwd').css({
				border: "1px solid #D7D7D7",
				boxShadow: "none"
			});
		}


        $.ajax({
            type: "post" ,
            url: "login",
            data: {sno:$('#login-son').val(),password:$('#pwd').val()},
            dataType: "json",
            success: function(data) {
                if (data.code == 0 ){
                    alert("登录成功！")
                    window.location.href='index';
                }else{
                    $("#loginCue").html("<font color='red'><b>"+data.msg+"</b></font>");
                }
            },
            error:function () {
                alert("网络错误！")
            }
        });
		
	});

    //注册
	$("#user-sno").blur(function(){
		$.ajax({
			type: "POST" ,
			url: "checkSno",
			data: "sno=" + $("#user-sno").val(),
            dataType: "json",
			success: function(res) {
				//alert("res:"+res+"\n"+"resLength:"+res.length)
                if(res.data==false){
                    $('#user-sno').css({
                        border: "1px solid red",
                        boxShadow: "0 0 2px red"
                    });
                    $("#userCue").html("<font color='red'><b>学号已存在</b></font>");
                    return false;
                }else {
					$('#user-sno').css({
						border: "1px solid #D7D7D7",
						boxShadow: "none"
					});
					$("#userCue").html("<font ><b>请继续输入</b></font>");
				}},
            error:function () {
                alert("网络错误！")
            }
		});
	});



	$('#reg_button').click(function() {

		var sqq = /^[1-9]{1}[0-9]{4,9}$/;
		regUserid=/^\d{12}$/;
		if ($('#user-sno').val().length!=12 || regUserid.test($('#user-sno').val())==false) {
			$('#user-sno').focus().css({
				border: "1px solid red",
				boxShadow: "0 0 2px red"
			});
			$('#userCue').html("<font color='red'><b>请输入12位数字学号</b></font>");
			return false;
		}else {
			$('#user-sno').css({
				border: "1px solid #D7D7D7",
				boxShadow: "none"
			});
		}
		
		
		if ($('#password').val().length < pwdmin) {
			$('#password').focus().css({
				border: "1px solid red",
				boxShadow: "0 0 2px red"
			});
			$('#userCue').html("<font color='red'><b>×密码不能小于" + pwdmin + "位</b></font>");
			return false;
		}else {
			$('#password').css({
				border: "1px solid #D7D7D7",
				boxShadow: "none"
			});
		}

		if ($('#password2').val() != $('#password').val()) {
			$('#password2').focus();
			$('#userCue').html("<font color='red'><b>×两次密码不一致！</b></font>");
			return false;
		}

		
		if (!sqq.test($('#qq').val()) || $('#qq').val().length < 5 || $('#qq').val().length > 12) {
			$('#qq').focus().css({
				border: "1px solid red",
				boxShadow: "0 0 2px red"
			});
			$('#userCue').html("<font color='red'><b>×QQ号码格式不正确</b></font>");return false;
		} else {
			$('#qq').css({
				border: "1px solid #D7D7D7",
				boxShadow: "none"
			});
		}

		var regdata = $('#regForm').serialize();
        $.ajax({
            type: "post" ,
            url: "register",
            data: regdata,
            dataType: "json",
            success: function(data) {
                if (data.code == 0 ){
                    alert("注册成功！")
                    window.location.href='index';
                }else{
                    $("#userCue").html("<font color='red'><b>"+data.msg+"</b></font>");
                }

            },
            error:function () {
                alert("网络错误！")
            }
        });

	});
	

});