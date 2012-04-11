$(document).ready(function(){
	var length=(document.documentElement.scrollHeight+600)/3;
	var ip="";
	$("#mid").height((length));
	 $.get("ip.txt", function(result){
		 ip=result;
	  });

//$("#showbox").hide();
$(".level2").hide();
$(".level3").hide();
$(".level1").fadeTo(0000,0);
var level1="";
var level2="";
var level3="";
var level4="";
var level5="";
var level6="";
function jsonget(getit,num){
    	$.get(ip+"/FreeXue/GetHierarchy?",{parent: getit},function call(data){
    		 wirteHtml1(data,num);
    	  });
    }

function wirteHtml1(data,num){ 
    	var dataset = $.parseJSON(data);
    	//alert(dataset.children[0].id);
    	var result="";
    	for(i=0;i<dataset.children.length;i++)
    		{ result=result+"<li class=\"level"+num+"\"><a title=\""+dataset.children[i].id+"\" href=\"#\">"+dataset.children[i].name+"</a></li>"}
    	//alert(result);
    	$("#showbox").html(result);
    	//alert($(".level1").get(0).innerHTML);
    	//$(".level1").get(0).html(dataset.Children[0])
    	//alert(dataset.Children);
    }
	


mousefirst("#first");


$("#first").live("click",function(){ 	
	jsonget("s00000","1");
	$("#showbox").css({
			  "background-image":"url(img/blank_1.png)"});
		$(".level3").hide();
		   $("#showbox").show();
		   $("#second").css({
			  "background-image":"url(img/button_orange_original.png)"
			  });
			  $("#third").css({
			  "background-image":"url(img/button_orange_original.png)"
			  });
			 
	        $("#showbox").animate({height:"250px"});
			$("#select").animate({marginTop:"0px"});
		//	$(".level").show("slow");
			$(".level1").fadeTo(1000,1);
	});

$(".level1").live("click",function(){	
		//alert($(this).find("a").attr("title"));
	 	level1=$(this).find("a").attr("title");
		$("#firstlevel").html($(this).text());
		jsonget($(this).find("a").attr("title"),"2");
		//alert($("#firstlevel").html());
		//$("#showbox").fadeTo(300,0,function(){ $("#showbox li").removeClass("level").addClass('level2');});
		$("#first").css({
		  "background-image":"url(img/button_orange_original.png)"
		  });
		$("#second").css({
		  "background-image":"url(img/button_blue_pressed.png)"
		  });
		 $("#showbox").fadeTo(0,0,function(){$("#showbox").css({
		  "background-image":"url(img/blank_2.png)"});});  
		$("#showbox").fadeTo(500,1);  
});


$(".level2").live("click",function(){
		level2=$(this).find("a").attr("title");
		$("#secondlevel").html($(this).text());
		jsonget($(this).find("a").attr("title"),"3");
		//$("#showbox").fadeTo(300,0,function(){ $("#showbox li").removeClass("level").addClass('level2');});

		$("#second").css({
		  "background-image":"url(img/button_orange_original.png)"
		  });
		$("#third").css({
		  "background-image":"url(img/button_blue_pressed.png)"
		  });
		 $("#showbox").fadeTo(0,0,function(){$("#showbox").css({
		  "background-image":"url(img/blank_3.png)"});});  
		$("#showbox").fadeTo(500,1);  
});

$(".level3").live("click",function(){
	level3=$(this).find("a").attr("title");
		$("#thirdlevel").html($(this).text());
		location.href="guide.html?"+"level1="+level1+"&level2="+level2+"&level3="+level3+"&level4="+level4+"&level5="+level5+"&level6="+level6+"&page=1";
});
});

$(".submit").live("click",function(){
	//document.location.href("3.html");
});



function mouse(tag){
	$(tag).mousedown(function(){
		$(tag).css({
		  "background-image":"url(img/button_orange_pressed.png)"
		  });
	});	
	$(tag).mouseup(function(){
		$(tag).css({
		  "background-image":"url(img/button_orange_original.png)"
		  });
	});
	$(tag).click(function(){
		$(tag).css({
		  "background-image":"url(img/button_blue_original.png)"
		  });
	});	
};


function mousefirst(tag)
{
	$(tag).mousedown(function(){
		$(tag).css({
		  "background-image":"url(img/button_blue_pressed.png)"
		});
	});
};
