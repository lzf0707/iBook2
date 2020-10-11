$(function() {
	var loading = false;
	var bookId = getQueryString('bookId');
	var searchDivUrl = 'listBookDetailPageInfo?bookId=' + bookId;
	var allPoint = getUserAllPoint();
	// 渲染出书籍基本信息
	getSearchDivData();
	//订阅渲染
	isSubscribe();
	var bookId = getQueryString('bookId');
	var listUrl = 'listArticleByBook';
	var sortFlag = 'asc';
	addItems();

	//定位观看章节
	function setCurrentArticle() {
		var url = 'p/getUserHistoryList?page=1&pageSize=10&bookId=' + bookId;
		$.getJSON(url, function(data) {
			if (data.status == 200) {
				//debugger;
				data = data.content;
				data.list.map(function(item, index) {
					var str = "$(\"li[data-id='" + item.article.articleId + "']\").attr(\"style\",\"background-color: bisque\")";
					eval(str);
				});
			}
		});
	}
		
	// 获取本店铺信息以及商品类别信息列表
	function getSearchDivData() {
		var url = searchDivUrl;
		$.getJSON(url,function(data) {
			if (data.success) {
				var book = data.book;
				$("#book-name").text(book.bookName);
				$("#author").text(book.author);
				$("#bookCategoryName").text(book.bookCategory.bookCategoryName);
				$("#areaName").text(book.area.areaName);
				var endStatusName = "连载中"
				if(book.endStatus == 1){
					endStatusName = "已完结"
				}
				$("#endStatus").text(endStatusName);
				$("#lastEditTime").text(book.lastEditTime);
				$(".pic img").attr("src",book.bookImg);
				$(".pic img").attr("title",book.bookName);
				
				$("#simple-des").text(book.bookDesc);
			}
		});
	}
	
	/*
	 * 获取所有章节列表
	 * */
	function addItems() {
		var url = listUrl + '?bookId=' + bookId + '&sortFlag=' + sortFlag;
		loading = true;
		$.getJSON(url,function(data){
			if(data.success){
				
				maxItems = data.bookArticleList.length;
				
				$("#new-article").text('第' + maxItems + '话');
				
				var html = '';
				data.bookArticleList.map(function(item,index){
					//开始阅读章节id渲染
					if(sortFlag == 'asc'){
						if(index == 0){
							$("#continusRead").attr('data-id',item.articleId);
							$("#continusRead").attr('data-point',item.point);
						}
					}else{
						if(index == maxItems){
							$("#continusRead").attr('data-id',item.articleId);
							$("#continusRead").attr('data-point',item.point);
						}
					}
					
					//最新一话章节id渲染
					if(index = maxItems){
						$("#new-article").attr('data-id',item.articleId);
						$("#new-article").attr('data-point',item.point);
					}
					var img_html = '<img class="icon-lock" src="/iBook/resources/images/lock_pic2.png"/>';
					html += '<li data-point=' + item.point + ' data-id='+item.articleId+'>'
						 +  '	<a href="#" class="">'
					     +  '		<span>第'+ item.priority +'话' + (item.lockFlag == '-'?img_html:'') + '</span>'
					     +  '   </a>'
					     +  '</li>';
					
				});
				//将动态li添加到html组件中
				$("#chapter-list-1").append(html);
        		var total = $('#chapter-list-1 li').length;
        		if(total >= maxItems){
        			// 隐藏提示符
					$('.infinite-scroll-preloader').hide();
				} else {
					$('.infinite-scroll-preloader').show();
				}
        		// 刷新页面，显示新加载的店铺
				$.refreshScroller();
				setCurrentArticle();
			}
		});
	}

	// 订阅漫画
	$('#subscribe').click(function () {
		var url = 'p/subscribe';
		if($('#subscribe').text() == '已订阅') {
			var url = 'p/unSubscribe';
		}
		$.ajax({
			async: false,
			cache: false,
			type: 'POST',
			data: {
				bookId : bookId,
			},
			url : url,
			error : function () {
				$.toast("系统异常");
			},
			success : function (data) {
				if(data.status == 403){ //未登录
					$.toast("用户未登录，请登录!");
				}else if (data.status == 200){ //成功处理
					isSubscribe();
					$.toast(data.content);
				}else {
					$.toast(data.content);
				}
			}
		});
	});

	//判断是否订阅,订阅则修改文字 id
	function isSubscribe(){
		$.ajax({
			async: false,
			cache: false,
			type: 'POST',
			data: {
				bookId : bookId,
			},
			url : 'p/isSubscribe',
			error : function () {
				$.toast("系统异常");
			},
			success : function (data) {
				if(data.status == 403){ //未登录,不做处理
					$.toast("用户未登录，请登录!");
				}else if (data.status == 200){ //成功处理
					if(data.content.indexOf("已订阅") > 0){
						$('#subscribe').text("已订阅");
						$('#subscribe').attr("style","color:blue");
					}else {
						$('#subscribe').text("订阅漫画");
						$('#subscribe').attr("style","");
					}
				}
			}
		});
	}

	$(".pull-right").on('click','.chapter-sort',function(e){
		//debugger;
		sortFlag = e.target.dataset.order;
		if(sortFlag == 'asc'){
			$(".active").addClass("asc");
			$(".reverse").removeClass("asc");
		}else{
			$(".active").removeClass("asc");
			$(".reverse").addClass("asc");
		}
		$("#chapter-list-1").empty();
		addItems();
	});
	
	// 点击后打开右侧栏
	$('#me').click(function() {
		$.openPanel('#panel-right-demo');
	});
	
	//点击章节进行阅读
	$('#chapter-list-1').on('click','li',function(e){
		var articleId =  e.currentTarget.dataset.id;
		var point = e.currentTarget.dataset.point;
		readArticle(articleId,point);
	});
	
	$('#new-article').on('click',function(e){
		//debugger;
		var articleId =  e.currentTarget.dataset.id;
		var point = e.currentTarget.dataset.point;
		readArticle(articleId,point);
	});
	
	$('#continusRead').on('click',function(e){
		//debugger;
		var articleId =  e.currentTarget.dataset.id;
		var point = e.currentTarget.dataset.point;
		readArticle(articleId,point);
	});

	$('#unLockAll').click(function () {
		$.ajax({
			async: false,
			cache: false,
			type: 'POST',
			data: {
				bookId : bookId,
			},
			url : 'p/getUnLockArticleTotalPoint',
			error : function () {
				$.toast("系统异常");
			},
			success : function (data) {
				if(data.status == 200){ //未登录,不做处理
					if(data.content != 0){
						$.confirm("解锁所有章节需花费" + data.content + "积分，是否解锁？","温馨提示(总积分：" + allPoint + ")",function () {
							unLockArticle("addAll",1);
						});
					}else{
						$.toast("章节已全部解锁~");
					}
				}else{
					$.toast(data.content);
				}
			}
		});
	});

	/**
	 * 判断章节可读状态
	 */
	function readArticle(articleId,point){
		$.ajax({
			async: false,
			cache: false,
			type: 'POST',
			data: {
				articleId : articleId,
			},
			url : 'getArticleStatus',
			error : function () {
				$.toast("系统异常");
			},
			success : function (data) {
				if(data.status == 200){ //未登录,不做处理
					window.location.href = 'articledetail?articleId='+articleId;
				}else { //成功处理
					if(data.content.indexOf("登录") > 0){
						$.toast(data.content);
					}else{
						$.confirm("非免费章节,需花费" + point + "积分，是否解锁？","温馨提示(总积分：" + allPoint + ")",function () {
							unLockArticle("add",articleId);
						});
					}
				}
			}
		});
	}

	function unLockArticle(flag,articleId) {
		$.ajax({
			async: false,
			cache: false,
			type: 'POST',
			data: {
				articleId:articleId,
				bookId:bookId,
				flag:flag
			},
			url : 'p/unLockArticle',
			error : function () {
				$.toast("系统异常");
			},
			success : function (data) {
				if(data.status == 200){ //未登录,不做处理
					if(flag == "add"){
						window.location.href = 'articledetail?articleId='+articleId;
					}
					if(flag == "addAll"){
						$.toast(data.content);
						$("#chapter-list-1").empty();
						addItems();
						allPoint = getUserAllPoint();
					}
				}else {
					$.toast(data.content);
				}
			}
		});
	}

	function getUserAllPoint(){
		var point = 0;
		$.ajax({
			async: false,
			cache: false,
			type: 'POST',
			data: null,
			url : 'p/getUserAllPoint',
			error : function () {
				$.toast("系统异常");
			},
			success : function (data) {
				if(data.status == 200){ //未登录,不做处理
					point = data.content.point;
				}
			}
		});
		return point
	}
	
	$.init();
})