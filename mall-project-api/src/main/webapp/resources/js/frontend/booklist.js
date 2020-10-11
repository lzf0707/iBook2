$(function() {
	var searchDivUrl = 'listShopsPageInfo';
	var parentId = ''
	
	getearchDivData();
	
	
	var loading = false;
	//允许分页最大条数
	var maxItems = 999;
	//一页显示3条
	var pageSize = 9;
	//页码
	var pageNum = 1;
	var listUrl = 'listbooks';
	//是否选择子类
	var selectParent = false;
	if(parentId){
		selectParent = true;
	}
	
	//默认为未完结
	var endStatus = 1;
	//默认已发布时间排序
	var publishFlag = 1;
	var updateFlag = '';
	
	var areaId = '';
	var bookCategoryId = getQueryString('parentId');
	var bookName = '';
	var isMax = false;
	
	
	addItems(pageSize,pageNum);
	
	
	/*
	 * 获取列别信息及区域信息
	 * @return
	 * */
	function getearchDivData() {
		var url = searchDivUrl + '?' + 'parentId=' + parentId;
		$.getJSON(url,function(data){
			if(data.success){
				var areaList = data.areaList;
				var html = '';
				html += '<a href="#" class="button" data-area-id="">全部区域</a>';
				areaList.map(function(item,index) {
					html += '<a href="#" class="button" data-area-id='
						+ item.areaId+'>'
						+ item.areaName
						+ '</a>';
				});	
				// 将拼接好的类别标签嵌入前台的html组件里
				$('#booklist-search-div').html(html);
				
				var selectOptions = '<option value="">全部类别</option>';
				var bookCategoryList = data.bookCategoryList;
				bookCategoryList.map(function(item,index) {
					selectOptions += '<option value="'+item.bookCategoryId+'">'+item.bookCategoryName+'</option>'
				});
				//将拼接好的区域标签嵌入前台html组件里
				$('#category-search').html(selectOptions);
			}
		})
	}
	
	/*
	 * 获取分页展示书籍列表信息
	 * @return
	 * */
	function addItems(pageSize,pageIndex) {
		
		if(isMax){
			
			return;
		}
		var url = listUrl + '?pageIndex=' + pageIndex + '&pageSize=' 
			+ pageSize + '&parentId=' + parentId + '&areaId=' 
			+ areaId + '&bookCategoryId=' + bookCategoryId 
			+ '&bookName=' + bookName + '&updateFlag=' 
			+ updateFlag + '&publishFlag=' + publishFlag 
			+ '&endStatus=' + endStatus;
		//设置加载符，若还在后台取数据，则不能再次访问后台，避免对此重复获取
		loading = true;
		$.getJSON(url,function(data){
			if(data.success){
				//获取当前书籍总条数
				maxItems = data.count;
				var html = '';
				data.bookList.map(function(item,index) {

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
	
	//查询书籍名字发生变化后，重置页码，清空原来的列表，按照新的名字查询
	$("#search").on('change',function(e){
		isMax = false;
		bookName = e.target.value;
		$('.list-div ul').empty();
		pageNum = 1;
		addItems(pageSize,pageNum);
	})
	
	//根据发布/更新排序 + 已完结/未完结查询
	$("a").on('click',function(e){	
		if(e.target.dataset.sort == "update"){
			updateFlag = 1;
			publishFlag = '';
			$('a[data-sort="post"]').removeClass("asc");
			$('a[data-sort="update"]').addClass("asc");
		}
		if(e.target.dataset.sort == "post"){
			updateFlag = '';
			publishFlag = 1;
			$('a[data-sort="update"]').removeClass("asc");
			$('a[data-sort="post"]').addClass("asc");
		}
		if(e.target.dataset.sort == "none-endStatus"){
			endStatus = 0;		
			$('a[data-sort="endStatus"]').removeClass("asc");
			$('a[data-sort="none-endStatus"]').addClass("asc");
		}
		if(e.target.dataset.sort == "endStatus"){
			endStatus = 1;
			$('a[data-sort="none-endStatus"]').removeClass("asc");
			$('a[data-sort="endStatus"]').addClass("asc");
		}
		isMax = false;
		$('.list-div ul').empty();
		pageNum = 1;
		addItems(pageSize,pageNum);
	})
	
	//按类别进行查询
	$('#category-search').on('change',function(){
		bookCategoryId = $('#category-search').val();
		isMax = false;
		$('.list-div ul').empty();
		pageNum = 1;
		addItems(pageSize,pageNum);
	})
	
	//按区域进行筛选
	$('#booklist-search-div').on('click','.button',function(e){
		areaId = e.target.dataset.areaId;
		isMax = false;
		$('.list-div ul').empty();
		pageNum = 1;
		addItems(pageSize,pageNum);
	})
	
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