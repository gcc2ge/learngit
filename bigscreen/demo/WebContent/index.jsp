<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Demo</title>
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="./css/jquery.gridster.css" />
  <link rel="stylesheet" type="text/css" href="css/demo.css"/>
  <link rel="stylesheet" type="text/css" href="css/smartMenu.css"/>
  <script type="text/javascript" src="js/jquery.js"></script>
  <script type="text/javascript" src="js/jquery-smartMenu.js"></script>
  <script src="./js/jquery.gridster.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript" src="js/jquery-ui.js"></script>      
  <script>
	$(function(){
		$(".window").draggable({
			helper: "clone"
		});
		$("#rightdiv").droppable({
			accept: '.window',
			drop:function(event,ui){
				//alert(ui.helper.remove());
			//alert(gridster.next_position (1,1).col);
			if(ui.helper.find("input[name='classname']").val()=='pic'){//picture、
				gridster.add_widget($('<li>'+
				'<div style="width:100%;height:10px;" onmouseover="showDiv(this)" onmouseout="hiddenDiv(this)">'+
				  '<div class="moveable"></div>'+				
				'</div><div style="width:100%;height=100%">'+ui.helper.html()+
				'</div></li>'),function(){
//					console.info($(this).html());
					$(".moveable",this).smartMenu([
// 						[{
// 							text:"添加标题",
// 							func: function() {
// 								console.log($(this).html());
// 								var sResult=prompt("请在下面输入你的姓名", "梦之都");
// 								$(this).find("img").attr()
// 							}
// 						}],
						[{
							text:"添加链接",
							func: function() {
								var urlstr=prompt("请在下面输入链接地址", "");
								//console.info($(this).parents("li").html());
								$(this).parents("li").find("img").attr("src",urlstr);
							}
						}],[{
							text:"移除",
							func:function(){
								gridster.remove_widget($(this).parents("li"));
							}
						}]
					]);
				});
				
			}else if(ui.helper.find("input[name='classname']").val()=='website'){//website
				//ui.helper.find("iframe").removeAttr("scrolling");
				//ui.helper.empty();
				gridster.add_widget($('<li>'+
				'<div style="width:100%;height:10px;" onmouseover="showDiv(this)" onmouseout="hiddenDiv(this)">'+
				  '<div class="moveable"></div>'+				
				'</div><div style="width:100%;height=100%">'+ui.helper.html()+
				'</div></li>'),function(){
					//console.info($(this).html());
					$(".moveable",this).smartMenu([
// 						[{
// 							text:"添加标题",
// 							func: function() {
// 								alert("aa");
// 							}
// 						}],
						[{
							text:"添加连接",
							func: function() {
								var urlstr=prompt("请在下面输入链接地址", "");
								//alert($(this).find("iframe").attr("scrolling"));
								$(this).parents("li").find("iframe").attr("src",urlstr);
							}
						}],[{
							text:"移除",
							func:function(){
								console.info($(this).parents("li").html());
								gridster.remove_widget($(this).parents("li"));
							}
						}]
					]);
				});
				
			}
			
			
			//add_widget ( html  [size_x]  [size_y]  [col]  [row]  [max_size]  [min_size] ) 
			}
		});
	});
</script>
<script type="text/javascript">
      var gridster;

      $(function(){

        gridster = $(".gridster ul").gridster({
          widget_base_dimensions: [100, 55],
          widget_margins: [5, 5],
		  draggable: {
            handle: '.moveable'
          },
          helper: 'clone',
           resize: {
            enabled: true,
            start: function(e, ui, $widget) {
            },

            resize: function(e, ui, $widget) {
				console.info($widget.html());
				$widget.find(".sizeable").eq(0).width($widget.width());
				$widget.find(".sizeable").eq(0).height($widget.height());
			  
            },

            stop: function(e, ui, $widget) {
				//$widget.find("div").eq(0).width($widget.width()-20);
				//$widget.find("div").eq(0).height($widget.height()-20);
            }
         }
        }).data('gridster');
		//
		showcase();
      });
	  function addWidget(){
	  	
	  }
    </script>
	<script>
		///保存方案
		function savecase(caseobj){
// 			 var s = gridster.serialize();
// 			 var name=prompt("请在下面输入方案名称", "");
            //console.info(JSON.stringify(s));		
			 var name=caseobj.name;
			//console.info(document.getElementById("rightdiv").innerHTML);	
			var content=document.getElementById("rightdiv").innerHTML;
			 $.ajax({
             type: "POST",
             url: "http://localhost:8080/demo/savecase",
             data: {"content":content,"name":name},
             dataType: "json",
             success: function(data){
            	 alert(data);
            	 casesavepost(data.id,data.name);
// 				$("#casename").empty();					
// 			 	$.each(data,function(k,v){
// 					//console.info("K: "+k+" v.id: "+v.id+" v.content: "+v.content);
// 					$("#casename").append("<div><a href=\"javascript:void(0)\" onclick=\"holdcase('"+k+"')\" >"+v.name+"</a></div>");
// 				});
             }
         });
		}
		//获取所有的方案名称
		function showcase(){
			 $.ajax({
             type: "POST",
             url: "http://localhost:8080/demo/holdallcase",
            // data: {"content":content,"name":name},
             dataType: "json",
             success: function(data){
				$("#case ul").empty();
			 	$.each(data,function(k,v){
					//console.info("K: "+k+" v.id: "+v.id+" v.content: "+v.content);
					casesavepost(v.id,v.name);
// 					$("#casename").append("<div><a href=\"javascript:void(0)\" onclick=\"holdcase('"+k+"')\" >"+v.name+"</a></div>");
				});
             }
         });
		}
		//获取一个方案名称
		function holdcase(id){ 
			$.ajax({
             type: "POST",
             url: "http://localhost:8080/demo/holdcase",
             data: {"id":id},
             dataType: "json",
             success: function(data){
            	 	//加判断 是否有方案内容
					$("#rightdiv").html(data.content);
					//alert(data.content);
             },error:function(){
			 	alert("error");
			 }
         });
		}
		/**$(function(){
			$(".txtmenu").smartMenu([
				[{
					text:"添加标题",
					func: function() {
						alert("aa");
					}
				}],
				[{
					text:"添加连接"
				}]
			]);
		});*/
	</script>
	<script>
		$(function(){
			//删除方案
			$(".removeable").bind("click",function(){
				//
				$(this).parents("li").remove();
			});
			/*$("#case").find("ul li").each(function(k,v){
				//console.info($(this).html());
				$(this).find("div").bind("click",function(){
					var clzzname=$(this).attr("class");
					if(clzzname=="selected"){
						$(this).removeClass("selected");
						//$(this).addClass("unselected");
						//$(this).parent().siblings().find("div").attr("class","selected");
					}else{
						$(this).removeClass("unselected");
						$(this).addClass("selected");
						$(this).parent().siblings().find("div").attr("class","unselected");
					}
					
				});
			});*/
			casenamebindfunc();
		});
		function casenamebindfunc(){
			$("#case ul li div").bind("click",function(){
				var clzzname=$(this).attr("class");
				if(clzzname=="selected"){
					$(this).removeClass("selected");
					//$(this).addClass("unselected");
					//$(this).parent().siblings().find("div").attr("class","selected");
				}else{
					//方案选中
					casenameclick($(this).find("span").eq(0).attr("caseid"));
					$(this).removeClass("unselected");
					$(this).addClass("selected");
					$(this).parent().siblings().find("div").attr("class","unselected");
				}	
			});
		}
		//点击方案名称
		function casenameclick(caseid){
			//显示方案内容
// 			alert(caseid);
			holdcase(caseid);
		}
    	function showDiv(t) {
           $(".moveable",t).css("display","block");
        }
		function hiddenDiv(t) {
           $(".moveable",t).css("display","none");
        }
		//弹出新建方案界面
		function savecasefunc(){
			$('#caseform').css({ 
				position:'absolute', 
				left: ($(window).width() - $('#caseform').outerWidth())/2, 
				top: ($(window).height() - $('#caseform').outerHeight())/2 + $(document).scrollTop() ,
				display:"block"
			}); 
			//确认新建
			$('#caseform').find("input[name='okbtn']").bind('click',function(){
				//console.info($("input[name='casename']","#caseform").val());
				var casename=$("input[name='casename']","#caseform").val();
				//清空工作空间
				clearworkspace();
				//保存到后台
				savecase({"name":casename});
			});
			//取消新建
			$('#caseform').find("input[name='canclebtn']").bind('click',function(){
				$('#caseform').css("display","none");
				unbindclick();
			});
		}
		function clearworkspace(){
			$(".gridster ul").empty();
		}
		function casesavepost(caseid,casename){
			alert(caseid+"	"+casename);
			var $li=$('<li><div><span caseid="'+caseid+'">'+casename+'</span>&nbsp;&nbsp;<span class="removeable">X</span></div></li>');
			$("#case ul").append($li);
			$li.find("div").bind("click",function(){
				var clzzname=$(this).attr("class");
				if(clzzname=="selected"){
					$(this).removeClass("selected");
					//$(this).addClass("unselected");
					//$(this).parent().siblings().find("div").attr("class","selected");
				}else{
					//方案选中
					casenameclick($(this).find("span").eq(0).attr("caseid"));
					$(this).removeClass("unselected");
					$(this).addClass("selected");
					$(this).parent().siblings().find("div").attr("class","unselected");
				}	
			});
			$li.find(".removeable").bind("click",function(){
				$(this).parents("li").remove();	
			});
			$li.find("div").trigger("click");
			unbindclick();
			$('#caseform').css("display","none");
		}
		function unbindclick(){
			$('#caseform').find("input[name='okbtn']").unbind("click");
			$('#caseform').find("input[name='canclebtn']").unbind("click");
		}
		
    </script>
</head>

<body>
<div id="container">
 
  <div id="mainContent">
    <div id="sidebar">
  <div id="case">
        	<span>方案</span><br/>
            <ul>
            	<li><div class="unselected"><span caseid="22222222">222222222222222222</span>&nbsp;&nbsp;<span class="removeable">X</span></div></li>
                <li><div><span>222222222222222222</span>&nbsp;&nbsp;<span class="removeable">X</span></div></li>
                <li><div><span>222222222222222222</span>&nbsp;&nbsp;<span class="removeable">X</span></div></li>
                <li><div><span>222222222222222222</span>&nbsp;&nbsp;<span class="removeable">X</span></div></li>
            </ul>
        </div>
        <div>
        	组件
        </div>
        <div>
        	<div class="window" >
            	<div>图片</div>
                <input type="hidden" name="classname" value="pic"></input>
				<img class="sizeable" src="http://www.baidu.com/img/baidu_jgylogo3.gif?v=13442422.gif" alt="到百度首页" title="到百度首页" width="100%" height="100%">
            </div>
			<div class="window" >
            	<div>网页</div>
                <input type="hidden" name="classname" value="website"></input>
				<iframe class="sizeable" width="100%" height="100%" src="http://www.baidu.com/" scrolling="no" frameborder="0"></iframe>
            </div>
            <div class="window" >
            	<div>网页</div>
                <input type="hidden" name="classname" value="website"></input>
				<iframe  class="sizeable" width="100%" height="100%" src="http://www.baidu.com/" scrolling="no" frameborder="0"></iframe>
            </div>
        </div>
    </div>
    <div id="content">
     	<div id="header">
        	<input type="button" value="新建方案" onclick="savecasefunc();" />
            <input type="button" value="保存方案" />
            <input type="button" value="预览方案"/>
        </div>
        <div id="rightdiv" >
        	<div class="gridster">
			    <ul style="width:500px">
			      <li data-row="1" data-col="1" data-sizex="2" data-sizey="2">
                  	<div style="width:100%;height:10px;" onmouseover="showDiv(this)" onmouseout="hiddenDiv(this)">
                        <div id="subDiv1" class="moveable"></div>
                    </div>
                    <div>
                    	<img src="http://www.baidu.com/img/baidu_jgylogo3.gif?v=13442422.gif" />
                    </div>
                  </li>
			      <li data-row="1" data-col="3" data-sizex="1" data-sizey="2">1</li>
			      <li data-row="1" data-col="4" data-sizex="1" data-sizey="1">2</li>
			      <li data-row="3" data-col="2" data-sizex="3" data-sizey="1">3</li>
			      <li data-row="4" data-col="1" data-sizex="1" data-sizey="1">4</li>
			      <li data-row="3" data-col="1" data-sizex="1" data-sizey="1">5</li>
			      <li data-row="4" data-col="2" data-sizex="1" data-sizey="1">6</li>
			      <li data-row="5" data-col="2" data-sizex="1" data-sizey="1">7</li>
			      <li data-row="4" data-col="4" data-sizex="1" data-sizey="1">8</li>
			      <li data-row="1" data-col="5" data-sizex="1" data-sizey="3">9</li>
			    </ul>
		  </div>
        </div>
    </div>
  </div>
</div>
<div id="caseform" style="display:none;position:absolute;background:#FFF">
	<table>
    	<tr>
        	<td colspan="2" align="center">新建方案</td>
        </tr>
    	<tr>
        	<td>方案名称</td>
            <td><input type="text" name="casename"/></td>
        </tr>
        <tr align="center">
            <td colspan="2" align="center"><input type="button" name="okbtn" value="确认"/>&nbsp;&nbsp;&nbsp;<input type="button" name="canclebtn" value="取消"/></td>
        </tr>
    </table>

    
    
</div>
</body>
</html>