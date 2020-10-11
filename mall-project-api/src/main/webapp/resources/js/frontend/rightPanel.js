$(function(){
	var html = '';
	$.ajax({
		async : false,
		cache : false,
		type : 'GET',
		headers: {'Authorization': document.cookie.split(";")[0]},
		dataType : "json",
		data : {},
		url : 'p/getLoginStatus',// 请求的action路径
		error : function(data) {// 请求失败处理函数
			alert('请求失败');	
		},
		success : function(data) {
			if(data.status == "401"){
				html += '<p><a href="login" class="close-panel" id="login" external>本地登录</a></p>'
			}else{
				html += ''
					//+ '<p><a href="accountBind" class="close-panel" id="account-bind" external>绑定本地账号</a></p>'
					+ '<p><a href="changePwd" class="close-panel" id="change-pwd" external>修改密码</a></p>'
					+ '<p><a href="myExchange" class="close-panel" id="award-record" external>兑换记录</a></p>'
					+ '<p><a href="myPoint" class="close-panel" id="my-point" external>我的积分</a></p>'
					+ '<p><a href="myHistory" class="close-panel" id="my-history" external>观看历史</a></p>'
					+ '<p><a href="mySubscribe" class="close-panel" id="my-subscribe" external>我的订阅</a></p>'
					+ '<p><a href="#" class="close-panel" id="sign-in" external>每日签到</a></p>'
					+ '<p><a href="signinList" class="close-panel" id="signin-list" external>签到记录</a></p>'
					+ '<p><a href="#" class="close-panel" id="log-out" external>登出系统</a></p>'
			}
			$('.content-block').append(html);
		}
	});	
	
	getSigninStatus();
	
	
	//每日签到
	$('#sign-in').click(function(){
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType : "json",
			data : {},
			url : 'p/signIn',// 请求的action路径
			error : function() {// 请求失败处理函数
				alert('请求失败');
			},
			success : function(data) {
				if(data.success){
					$.toast(data.msg);
					initSignin(true);
				}else{
					$.toast(data.errMsg);
					initSignin(false);
				}
			}
		});	
	});
	
	//登出系统
	$('#log-out').click(function(){
		//$(this).removeClass("close-panel");
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType : "json",
			data : {},
			url : getBaseUrl() + 'logout',// 请求的action路径
			error : function() {// 请求失败处理函数
				alert('请求失败');
			},
			success : function(data) {
				if(data.status == "200"){
					window.location.href = "login";
				}else{
					$.toast(data.content);
				}
			}
		});	
	});
	
})

//获取今日签到状态
function getSigninStatus(){
	//debugger;
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		//headers: {'Authorization': document.cookie.split(";")[0]},
		data : {},
		url : 'p/getTodaySignin',// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		success : function(data) {
			//debugger;
			initSignin(data.success);
		}
	});	
}

function initSignin(status){
	if(status){ //已签到
		$("#signin-list").parent().show();
		$("#sign-in").parent().hide();
	}else{ //未签到
		$("#signin-list").parent().hide();
		$("#sign-in").parent().show();
	}	
}