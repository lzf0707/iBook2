$(function() {
	var loading = false;
	var articleId = getQueryString('articleId');
	var listUrl = 'listArticleDetailInfo';
	var page = 1;
	var pageSize = 10;
	var maxItems = 999;
	var isMax = false;
	addItems(page,pageSize);

	/*
	 * 获取所有章节列表
	 * */
	function addItems(pageNum,pageSize) {
		if(isMax){	
			return;
		}
		var url = listUrl + '?articleId=' + articleId + '&page='
			+ pageNum + '&pageSize=' + pageSize;
		loading = true;
		$.getJSON(url,function(data){
			if(data.success){
				$(".pull-left").attr('href','bookdetail?bookId=' + data.bookArticle.bookId);
				$("#article-name").text(data.bookArticle.articleName);
				$("#last").attr('data-id',data.bookArticle.lastId);
				$("#next").attr('data-id',data.bookArticle.nextId);
				maxItems = data.count;
				var contentList = data.bookArticle.contentList;
				var imgListHtml = '';
				// 遍历商品详情图列表，并生成批量img标签
				contentList.map(function(item, index) {
					if(item.priority == null){
						imgListHtml += '<div><p style="text-align: center;">此章节为空!</p></div>';	
						
					}else{
						imgListHtml += '<div> <img referrerpolicy="no-referrer" src="'
							 + item.bookContentImg
							+ '" width="100%" /></div>';
					}
					
				});
				$('#imgList').append(imgListHtml);
				//获取目前为止已显示的卡片总数，包含之前已加载
				
				var total = $('#imgList div').length;
				console.log(total);
				//若总数到达此前查询条件的总数，则停止后台加载
				if(total >= maxItems){
					//隐藏提示符
					$('.infinite-scroll-preloader').hide();
					isMax = true;
					//观看得积分
					addRecdeByFlag("addUserRecode",data.bookArticle.bookId,data.bookArticle.articleId);
					//历史记录添加
					addRecdeByFlag("addHistoryRecode",data.bookArticle.bookId,data.bookArticle.articleId);
				}else{
					$('.infinite-scroll-preloader').show();
				}
				//否则页码加1，继续加载出新的书籍
				
				page += 1;
				loading = false;
				//刷新页面，显示新加载的书籍
				$.refreshScroller();
			}else{
				$.toast(data.errMsg);
				setTimeout(function(){
					window.location.href = 'bookdetail?bookId='+data.bookId;
				},1500);

			}
		});
	}
	
	//下滑屏幕自动分页搜索
	$(document).on('infinite','.infinite-scroll-bottom',function(){
		//debugger;
		if(loading){
			return;
		}	
		addItems(page,pageSize);
	});
	
	// 点击后打开右侧栏
	$('#me').click(function() {
		$.openPanel('#panel-right-demo');
	});

	$("#action").on('click','a',function(e){
		//debugger;
		var tempId = e.currentTarget.dataset.id;
		if(tempId != -1){
			window.location.href = 'articledetail?articleId=' + tempId;
		}else{
			var msg = e.target.id == 'last' ? '此章节为第一章节!':'此章节为最后一章!';
			$.alert(msg); 
		}
	});
	
	//观看得积分、插入历史记录
	function addRecdeByFlag(flag,bookId,articleId){
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType : "json",
			data : {
				bookId:bookId,
				articleId:articleId
			},
			url : 'p/' + flag,// 请求的action路径
			error : function() {// 请求失败处理函数
				alert('请求失败');
			},
			success : function(data) {
				if(flag == "addHistoryRecode") {
					return;
				}
				if(data.success){
					$.toast(data.msg);
				}else{
					console.log(data.errMsg);
				}
			}
		});	
	}
	
	// 初始化页面
	$.init();
	
})