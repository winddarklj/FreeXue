$(document).ready(function(){
var orginal="";
function jsonget(){
	$.get("http://localhost:8080/FreeXue/AllTagsServlet?",function call(data){
		//alert(data);
		wirteHtml(data);
	  });
}

function wirteHtml(data){ 
	var dataset = $.parseJSON(data);
	var result="";
	for(i=0;i<dataset.tags.length;i++){

$(".parentlist").append("<tr><td height=\"20\" bgcolor=\"#FFFFFF\" class=\"STYLE1\"><a title=\"edit\" id=\"test1\" href=\"#\">"+dataset.tags[i]+"</a></td><td height=\"20\" bgcolor=\"#FFFFFF\" ><img class=\"edit\" alt=\"修改\" src=\"images/33.gif\" width=\"14\" height=\"14\" /><img src=\"images/11.gif\" width=\"14\" height=\"14\" /></td></tr>");
//$(".parent").append("<td height=\"20\" bgcolor=\"#FFFFFF\"></td><td height=\"20\" bgcolor=\"#FFFFFF\" class=\"STYLE1\" ><a title=\"edit\" id=\"test1\" href=\"#\"></td>"+dataset.tags[i]+"</a>");
	}
	//$(".parent").append("<a title=\"edit\" id=\"test1\" href=\"#\">aaaa</a>");
	//alert(dataset.tags[1]);

    
}
jsonget();
$(".STYLE1").live("mouseenter",function(){
	$(this).css({
			  "background-color":"#51b2f6"});
	});
	
$(".STYLE1").live("mouseleave ",function(){
	$(this).css({
			  "background-color":"#fff"});
	});

$("#test1").live("click",function(){
	//alert($(this).attr("title"));
	//alert($(this).find("#test").val());
	//alert($("#test1").parent().html());
	orginal=$(this).parent().html();
	$(this).parent().html("<input size=\"8\" type=\"text\" value=\""+$(this).html()+"\" id=\"child\"/><input type=\"button\" value=\"cancel\" id=\"cancel\">");
	});
$("#test2").live("click",function(){
	//alert($(this).attr("title"));
	//alert($(this).find("#test").val());
	//alert($("#test1").parent().html());
	orginal=$(this).parent().html();
	$(this).parent().html("<input size=\"8\" type=\"text\" value=\""+$(this).html()+"\" id=\"child\"/><input type=\"button\" id=\"submit\" value=\"submit\"><input type=\"button\" value=\"cancel\" id=\"cancel\">");
	});	

$("#submit").live("click",function(){
	//alert("do");
	$.post("http://localhost:8080/FreeXue/SetHierarchy?",{parent:"1_1",oldchild:"2_1",newchild:"2_8"},function call(data){
		//alert(data);
	  });
	$(this).parent().html(orginal);
	
	});

$("#cancel").live("click",function(){
	$(this).parent().html(orginal);
	});
	
$(".STYLE1").live("click",function(){
	//alert($(this).find("#test1").html());
	setchildren($(this).find("#test1").html());
	
	});



function setchildren(parentname){
	$.get("http://localhost:8080/FreeXue/GetHierarchy?",{parent: parentname},function call(data){
		var dataset = $.parseJSON(data);
$("#childrenlist").html(dataset.Parent);
		var result="";
		for(i=0;i<dataset.Children.length;i++){ 
			result=result+"<tr><td height=\"20\" bgcolor=\"#FFFFFF\" ><a title=\"edit\" id=\"test2\" href=\"#\">"+dataset.Children[i]+"</a></td><td height=\"20\" bgcolor=\"#FFFFFF\" ><img class=\"edit\" alt=\"修改\" src=\"images/33.gif\" width=\"14\" height=\"14\" /><img src=\"images/11.gif\" width=\"14\" height=\"14\" /></td></tr>";
			}
		$(".childrenlist").html(result);
	  });
	}
	



	
});


