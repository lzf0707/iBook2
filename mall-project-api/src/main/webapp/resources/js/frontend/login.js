$(function(){
	var loginCount = 0;
	$('#submit').click(function(){
		// 获取输入的帐号
		var userName = $('#username').val();
		// 获取输入的密码
		var password = $('#psw').val();
		// 获取验证码信息
		var verifyCodeActual = $('#j_captcha').val();
		var usertype = getQueryString('usertype');
		usertype = 1;
		// 是否需要验证码验证，默认为false,即不需要
		var needVerify = false;
		// 如果登录三次都失败
		if (loginCount >= 3) {
			// 那么就需要验证码校验了
			if (!verifyCodeActual) {
				$.toast('请输入验证码！');
				return;
			} else {
				needVerify = true;
			}
		}
		// 访问后台进行登录验证
		$.ajax({
			url : getBaseUrl() + 'authentication/logincheck',
			async : false,
			cache : false,
			type : "post",
			dataType : 'json',
			data :  {
				password: password,
				imageCode: verifyCodeActual,
				username: userName,
				//是否需要做验证码校验
				needVerify : needVerify
			},
			error:function(data){
				$.toast('登录失败！');
				loginCount++;
				if (loginCount >= 3) {
					// 登录失败三次，需要做验证码校验
					$('#verifyPart').show();
				}
			},
			success : function(data) {
				$.toast('登录成功！');
				// 若用户在前端展示系统页面则自动链接到前端展示系统首页
				window.location.href = 'index';
			}
		});
	});

	$.init();
})
