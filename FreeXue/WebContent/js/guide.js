function QueryString()
{
var name,value,i;
var str=location.href;
var num=str.indexOf("?")
str=str.substr(num+1);
var arrtmp=str.split("&");
for(i=0;i < arrtmp.length;i++){
num=arrtmp[i].indexOf("=");
if(num>0){
name=arrtmp[i].substring(0,num);
value=arrtmp[i].substr(num+1);
this[name]=value;
}
}
}

$(document).ready(function(){
	var Request=new QueryString();
	var level1=Request["level1"];
	var level2=Request["level2"];
	var level3=Request["level3"];
	var level4=Request["level4"];
	var level5=Request["level5"];
	var level6=Request["level6"];
	var page=Request["page"];
	var ip="";
	
	$.get("ip.txt", function(result){
		  ip=result;
	  });
	//============================================================================//
	$.get(ip+"/FreeXue/GetHierarchy?",{parent: level3},function call(data){
    		// alert(data);
    		 var dataset = $.parseJSON(data);
    		 var result="";
    		 for(i=0;i<dataset.children.length;i++)
     		{
         		//result="<option value=\""+dataset.children[i].id+"\">"+dataset.children[i].name+"</option>";
         		result="<li class=\"choice\" title=\""+dataset.children[i].id+"\">"+dataset.children[i].name+"</li>";
    			 //alert(dataset.children[i].id.substring(1,2));
         		switch(dataset.children[i].id.substring(1,2))
         		{
         		case "4": 
 				$("#level4").append(result);
 					break;
         		case "5": 
         		$("#level5").append(result);
 				break;
         		case "6": 
         		$("#level6").append(result);
 				break;
         		}
         		}
    	  });
	
	
	
	$(".exmenu .choice").live("click",function(){
	$(this).parent().find(".choice").css("color","#8bb200");
	$(this).parent().find(".choice").css("background-color","#fff");
	$(this).css("color","#fff");
	$(this).css("background-color","#8bb200");
				$("#loading").show();
				$("#contentlist").html("");
				switch($(this).attr("title").substring(1,2))
         		{
         		case "4": 
 				level4=$(this).attr("title");
				//alert("4");
 					break;
         		case "5": 
         		level5=$(this).attr("title");
				//alert("5");
 				break;
         		case "6": 
				//alert("6");
         		level6=$(this).attr("title");
 				break;
         		}
				 getcontentlist();
	});
	
	
	
	$(".level li").hide();
	$(".chosen a").toggle(
	function(){
		//  document.URL="guide.html?level1=s10000&level2=s20000&level3=s30001&page=1";
		$(this).parent().find(".level li").animate({height:"60px"});
		$(this).parent().find(".level li").show();
		$(this).css("color","#4497c0");
		$(this).parent().find(".level li:last").css({
		"border-bottom-width":"1px",
		"border-bottom-style":"solid",
		"border-bottom-color":"#e8b229"
		});
	},
 	 function(){
		$(this).css("color","#000");
		$(this).parent().find(".level li").animate({height:"0px"},function(){
		$(this).hide();
		});
	}
	);
	
function getcontentlist(){
	$.get(ip+"/FreeXue/GetSourceServlet?",{level1: level1 ,level2:level2,level3: level3,level4: level4,level5: level5,level6: level6,page:page},function call(data){
		//alert(data);
    	var dataset = $.parseJSON(data);
		//alert("level4="+level4+",level5="+level5+",level6="+level6);
    	//alert(dataset.sources[0].title);
		for(i=0;i<dataset.sources.length;i++){
			var tags="";
			for(j=0;j<dataset.sources[i].tags.length;j++){
				//	alert(dataset.sources[i].tags[j]);
				//tags=tags+" "+dataset.sources[i].tags[j];
				tags=tags+dataset.sources[i].tags[j].name+"&nbsp;&nbsp;";
				var num=j+1;
				//$("#nav").append("<	><a href=\"#\">"+dataset.sources[i].tags[j].id+"</a>");
				//$("#tag").append("<li><a href=\"#\">"+num+"."+dataset.sources[i].tags[j].id+"</a>");
				}
			var content="<div class=\"color1\"> <b class=\"b1\"></b><b class=\"b2\"></b><b class=\"b3\"></b><b class=\"b4\"></b> <div class=\"content\">   <a href=content.html?url="+dataset.sources[i].url+">"+dataset.sources[i].title+"</a>"+"<span id=\"like\"><img src=\"img/guide/like.png\" width=\"16\" height=\"14\" />("+dataset.sources[i].like+")"+"<img src=\"img/guide/dislike.png\" width=\"16\" height=\"14\" />("+dataset.sources[i].dislike+")</span>"+"<br><br><div>"+dataset.sources[i].description+"<br/><br/>"+tags+"<br/><br/><div><span id=\"comment\">评论()</span>&nbsp<span id=\"collect\">收藏()</span></div><br/><p style=\"text-align:right;\"></p></div></div><b class=\"b5\"></b><b class=\"b6\"></b><b class=\"b7\"></b><b class=\"b8\"></b>   </div><br><br>";
			$("#loading").hide();
			$("#contentlist").append(content);  
		}

	  });	
	  			$("#page").html("<li><a>上一页</a></li> <li><a>1</a></li><li><a>2</a></li><li><a>下一页</a></li>");      
	}
	if(level1==null||level2==null||level3==null){	
		alert("没有传值");
		}
	else{
		//alert(level1+level2+level3);
 getcontentlist();
	}
	
	
$(".choicelist").hide();




$("#tag li").mouseleave (function(){
 $(".choicelist").hide();
});


$("p").one("mouseenter",function(){
 $(".choicelist").show();
});


	var temp1="";
	var temp2="";
$(".button").mouseenter(function(){

	temp1=$(this).attr("title");
	temp2=$(this).attr("src");
	$(this).attr("src",temp1);
	$(this).attr("title",temp2);
	//alert($(this).find("img").attr("title"));
		  // $(this).html("<img src=\"img/nav/like_leave.png\" width=\"40\" height=\"55\" />");
});


$(".button").mouseleave(function(){

	temp1=$(this).attr("title");
	temp2=$(this).attr("src");
	$(this).attr("src",temp1);
	$(this).attr("title",temp2);
	//alert($(this).find("img").attr("title"));
		  // $(this).html("<img src=\"img/nav/like_leave.png\" width=\"40\" height=\"55\" />");
});


});


