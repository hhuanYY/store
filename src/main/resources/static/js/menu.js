//修改这个变量为实际控制器的地址，如../showGoods.do
var reqpath = "search.html"
/*ajax获得的json对象*/
var typelist = [{
		"id": "1",
		"parentId": "0",
		"name": "电脑专区"
	}, {
		"id": "2",
		"parentId": "0",
		"name": "数码电视"
	}, {
		"id": "3",
		"parentId": "0",
		"name": "电脑办公"
	}, {
		"id": "4",
		"parentId": "0",
		"name": "汽车电子产品"
	}, {
		"id": "5",
		"parentId": "0",
		"name": "创新技术产品"
	}, {
		"id": "6",
		"parentId": "0",
		"name": "摄影器材"
	}, {
		"id": "7",
		"parentId": "0",
		"name": "家用电器"
	}, {
		"id": "8",
		"parentId": "0",
		"name": "娱乐产品"
	},
	{
		"id": "9",
		"parentId": "0",
		"name": "各种灯具"
	},
	{
		"id": "12",
		"parentId": "0",
		"name": "视频产品"
	},
	{
		"id": "14",
		"parentId": "0",
		"name": "办公设备"
	},
	{
		"id": "16",
		"parentId": "0",
		"name": "游戏产品"
	}, {
		"id": "17",
		"parentId": "0",
		"name": "通讯设备"
	}, {
		"id": "18",
		"parentId": "0",
		"name": "人工智能"
	}, {
		"id": "19",
		"parentId": "0",
		"name": "虚拟产品"
	}, {
		"id": "20",
		"parentId": "3",
		"name": ""
	}, {
		"id": "21",
		"parentId": "3",
		"name": "电脑配件"
	}, {
		"id": "22",
		"parentId": "3",
		"name": "外设产品"
	}, {
		"id": "23",
		"parentId": "3",
		"name": "网络产品"
	}, {
		"id": "24",
		"parentId": "3",
		"name": "办公设备"
	}, {
		"id": "25",
		"parentId": "3",
		"name": "文具耗材"
	}, {
		"id": "26",
		"parentId": "3",
		"name": "服务产品"
	}, {
		"id": "27",
		"parentId": "20",
		"name": "笔记本"
	}, {
		"id": "28",
		"parentId": "1",
		"name": "超极本"
	}, {
		"id": "29",
		"parentId": "1",
		"name": "游戏本"
	}, {
		"id": "30",
		"parentId": "1",
		"name": "平板电脑"
	}, {
		"id": "31",
		"parentId": "1",
		"name": "平板电脑配件"
	}, {
		"id": "32",
		"parentId": "1",
		"name": "台式机"
	}, {
		"id": "33",
		"parentId": "1",
		"name": "服务器工作站"
	}, {
		"id": "34",
		"parentId": "1",
		"name": "笔记本配件"
	},{
		"id": "35",
		"parentId": "2",
		"name": "华为电视"
	},
	{
		"id": "36",
		"parentId": "2",
		"name": "联想电视"
	},
	{
		"id": "37",
		"parentId": "2",
		"name": "戴尔电视"
	},
	{
		"id": "38",
		"parentId": "4",
		"name": "导航产品"
	},
	{
		"id": "39",
		"parentId": "4",
		"name": "电源"
	},
	{
		"id": "40",
		"parentId": "4",
		"name": "电池"
	},
	{
		"id": "41",
		"parentId": "5",
		"name": "可穿戴设备"
	},
	{
		"id": "42",
		"parentId": "5",
		"name": "3D打印机"
	},
	{
		"id": "43",
		"parentId": "5",
		"name": "电子商务"
	},
	{
		"id": "44",
		"parentId": "6",
		"name": "数码相机"
	},
	{
		"id": "45",
		"parentId": "6",
		"name": "摄像机"
	},
	{
		"id": "46",
		"parentId": "6",
		"name": "激光照排"
	},
	{
		"id": "47",
		"parentId": "6",
		"name": "扫描议"
	},
	{
		"id": "48",
		"parentId": "7",
		"name": "冰箱"
	},
	{
		"id": "49",
		"parentId": "7",
		"name": "空调"
	},
	{
		"id": "50",
		"parentId": "7",
		"name": "微波炉"
	},
	{
		"id": "51",
		"parentId": "7",
		"name": "吹风机"
	},
]
//加载json数据的到一级分类的方法
function initMenu() {
	for (var i = 0; i < typelist.length; i++) {

		if (typelist[i].parentId == "0") {

			$(".index-menu").append($("<li data='" + typelist[i].id + "'>" + typelist[i].name + "</li>"))
		}
	}
}
$(function() {
	initMenu();
	//根据轮播图片的高，导航的高
	//获得轮播图高
	var lunh = $("#myCarousel").height();
	var lih = (lunh - 10) / 19;
	//确定导航高度
	$(".index-menu li").css("height", lih + "px")
	//确定按钮位置	
	var btnt = Math.floor($("#myCarousel").height() / 2 - 30);
	$(".left").css("margin-top", btnt + "px");
	$(".right").css("margin-top", btnt + "px");
	/*左侧分类一级菜单控制二级菜单显示和隐藏*/
	$(".index-menu").hover(function() {
			$("#showIndex").show();
		}, function() {
			$("#showIndex").hide();
		})
		/*左侧分类二级菜单控制三级菜单显示和隐藏*/
	$(".second-menu").hover(function() {
		$("#showSecond").show();
	}, function() {
		$("#showSecond").hide();
	})
	/*二级菜单自己控制显示和隐藏*/
	$("#showIndex").hover(function() {
		$("#showIndex").show();
	}, function() {
		$("#showIndex").hide();
	})
	/*三级菜单自己控制显示和隐藏*/
	$("#showSecond").hover(function() {
		$("#showIndex").show();
		$("#showSecond").show();
	}, function() {
		$("#showIndex").hide();
		$("#showSecond").hide();
	})
	/*一级分类项li鼠标移入移出*/
	var offTop = -100;
	var offLeft = 0;
	$(".index-menu li").hover(function() {
		$(".second-menu").empty();
		/*加载json数据*/
		for (var i = 0; i < typelist.length; i++) {
			if ($(this).attr("data") == typelist[i].parentId) {
				$(".second-menu").append($("<li class='second-menu-li' data='" + typelist[i].id + "' >" + typelist[i].name + "</li>"))
			}
		}
		offLeft = $(this).width() + $(this).offset().left;
		offTop = $(this).offset().top;
		$("#showIndex").css("top", offTop - 2 + "px")
		$("#showIndex").css("left", offLeft - 1 + "px")
		$(this).css("background-color", "#f5f5f5");
		$(this).css("color", "#4288c3");
	}, function() {
		$(this).css("background-color", "");
		$(this).css("color", "");
	})
	/*二级分类项li鼠标移入移出*/
	$(".second-menu-li").live("mouseover", function() {
		$(".third-menu").empty();
		/*加载数据*/
		for (var i = 0; i < typelist.length; i++) {
			if ($(this).attr("data") == typelist[i].parentId) {
				$(".third-menu").append($("<li class='third-menu-li' data='" + typelist[i].id + "' ><a href='" + reqpath + "?typeid=" + typelist[i].id + "'>" + typelist[i].name + "</a></li>"))
			}
		}
		//alert($(document).scrollTop() +":"+$(this).offset().top)
		var ot = $(document).scrollTop() == $(this).offset().top ? offTop : $(this).offset().top;
		var ol = $(this).width() + $(this).offset().left;
		$("#showSecond").css("top", ot - 2 + "px");
		$("#showSecond").css("left", ol + "px")
		$(this).css("background-color", "#4288c3");
		$(this).css("color", "#f5f5f5");
	})
	$(".second-menu-li").live("mouseout", function() {
		$(this).css("background-color", "");
		$(this).css("color", "");
	})
	/*三级分类项li鼠标移入移出*/
	$(".third-menu-li").live("mouseover", function() {
		$(this).css("background-color", "#dddddd");
		$(this).css("color", "#000000");
	})
	$(".third-menu-li").live("mouseout", function() {
		$(this).css("background-color", "");
		$(this).css("color", "");
	})
})