(function($) {
		
	$.fn.jPushMenu = function(customOptions) {
		var o = $.extend({}, $.fn.jPushMenu.defaultOptions, customOptions);
		
		/* add class to the body.*/
		
		$('body').addClass(o.bodyClass);
		$(this).addClass('jPushMenuBtn');
		$(this).click(function() {
			//目标DIV 滑动对象
			var target         = '',
			push_direction     = '';
			
			///根据按钮 滑动div
			if($(this).is('.'+o.showLeftClass)) {
				target         = '.cbp-spmenu-left';
				push_direction = 'toright';
			}
			else if($(this).is('.'+o.showRightClass)) {
				target         = '.cbp-spmenu-right';
				push_direction = 'toleft';
			}
			else if($(this).is('.'+o.showTopClass)) {
				target         = '.cbp-spmenu-top';
			}
			else if($(this).is('.'+o.showBottomClass)) {
				target         = '.cbp-spmenu-bottom';
			}
			
			//按钮激活状态
			$(this).toggleClass(o.activeClass);
			//按钮移动 self
			var direction_;
			if(push_direction=="toright"){
				if($(target).hasClass(o.menuOpenClass)){
					$(this).parent().css({
						"position":"absolute",
						 "left":"0px"
					});
				}else{
					$(this).parent().css({
						"position":"absolute",
						 "left":$(target).outerWidth()+"px"
					});
				}

			}else if(push_direction=="toleft"){//左侧
				if($(target).hasClass(o.menuOpenClass)){
					$(this).parent().css({
						"position":"absolute"
					});
					$(this).parent().animate({
						"right":"0px"
					},210);
					//div 收缩
					var rightdiv_width=$("#rightdiv").width();
//					$("#rightdiv").animate({
//						"width":rightdiv_width+240
//					});
					$("#rightdiv").width(rightdiv_width+240);
					//button
					$(this).attr("src","./img/left.jpg");
				}else{
					$(this).parent().css({
						"position":"absolute"						 
					});
					$(this).parent().animate({
						"right":$(target).outerWidth()+"px"
					},250);
					//div 收缩
					var rightdiv_width=$("#rightdiv").width();
					$("#rightdiv").animate({
						"width":rightdiv_width-240
					});
					//button
					$(this).attr("src","./img/right.jpg");
//					$("#rightdiv").width(rightdiv_width-240);	 
					
				}
			}
			
			
			//滑动DIV 打开或关闭
			//console.info(target);
			$(target).toggleClass(o.menuOpenClass);
			//push
			if($(this).is('.'+o.pushBodyClass)) {
				$('body').toggleClass( 'cbp-spmenu-push-'+push_direction );
			}
			
			/* disable all other button*/
			$('.jPushMenuBtn').not($(this)).toggleClass('disabled');
			
			return false;
		});
		var jPushMenu = {
			close: function (o) {
				$('.jPushMenuBtn,body,.cbp-spmenu').removeClass('disabled active cbp-spmenu-open cbp-spmenu-push-toleft cbp-spmenu-push-toright');
			}
		}

    
	};
 
   /* in case you want to customize class name,
   *  do not directly edit here, use function parameter when call jPushMenu.
   */
	$.fn.jPushMenu.defaultOptions = {
		bodyClass       : 'cbp-spmenu-push',
		activeClass     : 'menu-active',
		showLeftClass   : 'menu-left',
		showRightClass  : 'menu-right',
		showTopClass    : 'menu-top',
		showBottomClass : 'menu-bottom',
		menuOpenClass   : 'cbp-spmenu-open',
		pushBodyClass   : 'push-body',
		closeOnClickOutside: true,
		closeOnClickInside: true,
		closeOnClickLink: true
	};
})(jQuery);
