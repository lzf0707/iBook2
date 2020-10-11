var loading = false;
//允许分页最大条数
var maxItems = 999;
//一页显示3条
var pageSize = 10;
//页码
var pageNum = 1;
var isMax = false;

$(function(){
	$("#my-input").calendar({
	    value: [new Date().Format('yyyy-MM-dd')]
	});	
	addItems(pageSize,pageNum);
	//下滑屏幕自动分页搜索
	$(document).on('infinite','.infinite-scroll-bottom',function(){
		if(loading){
			return;
		}
		addItems(pageSize,pageNum);
	});
	
	
	
})

function addItems(pageSize,pageIndex) {	
	//debugger;
	var url = 'p/getSigninRecode?page=' + pageIndex + '&pageSize=' 
	+ pageSize + '&createTime=' + $("#my-input").val();
	if(isMax){
		
		return;
	}
	//设置加载符，若还在后台取数据，则不能再次访问后台，避免对此重复获取
	loading = true;
	$.getJSON(url,function(data){
		if(data.success){
			$(".title").text("签到记录(总积分:" + data.totalPoint + ")");
			//获取当前书籍总条数
			maxItems = data.count;
			var html = '';
			data.signinList.map(function(item,index) {
				html += ''
					 + '<div class="card">'
					 + '	<div class="card-content">'
					 + ' 		<div class="card-content-inner">签到日期：'+ item.createTime +' (积分：' + item.point + ')</div>'
					 + '	</div>'
					 + '</div>';
				
				
			});
			html += '';
			// 将卡片集合添加到目标HTML组件里
			$('.row').append(html);	
			//获取目前为止已显示的卡片总数，包含之前已加载
			
			var total = $('.card').length;
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
		}else{
			$.toast(data.errMsg);
		}
	})
	
	// 点击后打开右侧栏
	$('#me').click(function() {
		$.openPanel('#panel-right-demo');
	});
}
function dateChange(){
	//debugger;
	isMax = false;
	$('.row').empty();	
	pageNum = 1;
	addItems(pageSize,pageNum);
}