$(function(){
	var usertype = getQueryString('usertype');
	usertype = 1;
	
	$('#submit').click(function() {
		// 获取帐号
		var userName = $('#username').val();
		// 获取原密码
		var password = $('#psw').val();
		
		//添加表单数据
		var formData = new FormData();
		formData.append('userName', userName);
		formData.append('password', password);
		
		
		//获取验证码
		var verifyCodeActual = $('#j_captcha').val();
		if(!verifyCodeActual){
			$.toast('请输入验证码！');
			return;
		}
		formData.append("verifyCodeActual", verifyCodeActual);
		$.ajax({
			url : 'p/bindLocalAuth',
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				$('#captcha_img').attr("src","../Kaptcha?" + Math.floor(Math.random() * 100));
				$('#captcha_img').click();
				if (data.success) {
					$.toast('提交成功，请登录！');
//					if (usertype == 1) {
//						// 若用户在前端展示系统页面则自动退回到前端展示系统首页
//						window.location.href = 'login';
//					} else {
//						// 若用户是在店家管理系统页面则自动回退到店铺列表页中
//						window.history.go(-1);
//					}
				} else {
					$.toast('提交失败！' + data.errMsg);				
				}
			}		
		});
	});

	$.init();
})