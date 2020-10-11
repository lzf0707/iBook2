$(function() {
	//定义访问活态，获取头条列表及一级类别列表的URL
	var url = 'listMainMageInfo';
	//访问后台，获取头条列表及一级类别
	$.getJSON(url,function(data){
		console.log(data);
		if(data.success){
			var headLineList = data.headLineList;
			var swiperHtml = '';
	
			headLineList.map(function(item,index) {
				swiperHtml += ''
					+ '<div class="swiper-slide img-wrap">'
					+ '		<a href="'+item.lineLink+'" external>'
					+ '			<img class="banner-img" src="'+item.lineImg+'" alt="'+item.lineName+'">'
					+ '		</a>'
					+ '		<div class="text-wrap">'+item.lineName+'</div>'
					+ '</div>';
			});
			//将轮播图赋值给前端HTML控件
			$('.swiper-wrapper').html(swiperHtml);
			// 设定轮播图轮换时间为3秒
			$(".swiper-container").swiper({
				autoplay : 3000,
				// 用户对轮播图进行操作时，是否自动停止autoplay
				autoplayDisableOnInteraction : false
			});
			
			//获取后台传递的一类列表
			var list = data.list;
			var listHtml = '';
			//遍历大类列表，拼接行类别
			list.map(function(item,index) {
				listHtml += ''
					+ '<div class="total-book-button"><a href="#">' + item.title + '</a></div>'
					+ '<div class="row">';

				var bookList = item.bookList;
				bookList.map(function(bookItem,bookIndex) {
					listHtml += ''
						+ '<div class="col-50 book-classify" data-bookName='+bookItem.bookName+' data-bookId='+bookItem.bookId+'>'
						+ '		<div class="word">'
						+ '			<p class="book-title"><a style="color: blueviolet;" href="bookdetail?bookId=' + bookItem.bookId +'" external>' + bookItem.bookName + '</a></p>'
						+ '			<p class="book-desc">' + (bookItem.ower == undefined ? '无' : bookItem.ower)  + '</p>'
						+ '		</div>'
						+ '		<div class="book-classify-img-warp">'
						+ '			<a href="bookdetail?bookId=' + bookItem.bookId +'" external>'
						+ '				<img class="book-img" src="'+bookItem.bookImg+'"/>'
						+ '			</a>'
						+ '		</div>'
						+ '</div>';
				});
				listHtml += '</div>';
			});
			//将拼接好的类别复制给前端HTML控件进行展示
			$('.content').append(listHtml);
			$('.content').append("<div class='dev-info'>开发者信息:lzf,欢迎吐槽~</div>");

		}
	})

	//点击'我的'，显示
	$('#me').click(function() {
		$.openPanel('#panel-right-demo');
	})
})
