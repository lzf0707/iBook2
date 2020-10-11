$(function() {

	var loading = false;
	//允许分页最大条数
	var maxItems = 999;
	//一页显示3条
	var pageSize = 9;
	//页码
	var pageNum = 1;
	var listUrl = 'p/queryUserBookList';

	var isMax = false;

	addItems(pageSize,pageNum);
	
	/*
	 * 获取分页展示书籍列表信息
	 * @return
	 * */
	function addItems(pageSize,pageIndex) {
		if(isMax){
			return;
		}
		var url = listUrl + '?pageIndex=' + pageIndex + '&pageSize='
			+ pageSize;
		//设置加载符，若还在后台取数据，则不能再次访问后台，避免对此重复获取
		loading = true;
		$.getJSON(url,function(data){
			if(data.status == 200){
				//获取当前书籍总条数
				data = data.content;
				maxItems = data.count;
				var html = '';
				data.list.map(function(item,index) {
						html +=''
							+ '<li class="list-comic" data-book-id="'+item.bookId+'">'
							+ '<a class="ImgA" href="#" external> '
							+ '		<img src="'+item.bookImg+'" width="100%" alt>'
							+ '</a>'
							+ '<a class="txtA" href="#" external>'+item.bookName+'</a>'
							+ '<span class="info">作者:'+item.author+'</span>'
							+ '</li>';
						
				});
				html += '';
				// 将卡片集合添加到目标HTML组件里
				$('.list-div ul').append(html);
				//获取目前为止已显示的卡片总数，包含之前已加载
				
				var total = $('ul .list-comic').length;
				console.log(total);
				//若总数到达此前查询条件的总数，则停止后台加载
				if(total >= maxItems){
					//隐藏提示符
					$('.infinite-scroll-preloader').hide();
					isMax = true;
				}else{
					$('.infinite-scroll-preloader').show();
				}
				//否则页码加1，继续加载出新的书籍
				
				pageNum += 1;
				loading = false;
				//刷新页面，显示新加载的书籍
				$.refreshScroller();
			}
		})
	}
	
	//下滑屏幕自动分页搜索
	$(document).on('infinite','.infinite-scroll-bottom',function(){
		if(loading){
			return;
		}
		addItems(pageSize,pageNum);
	});
	

	// 点击后打开右侧栏
	$('#me').click(function() {
		$.openPanel('#panel-right-demo');
	});

	//点击书籍进入书籍详情
	$("ul").on('click','li[class="list-comic"]',function(e){
		//debugger;
		var bookId = e.currentTarget.dataset.bookId;
		window.location.href = 'bookdetail?bookId='+bookId;
	})
	
	// 初始化页面
	$.init();
	
});