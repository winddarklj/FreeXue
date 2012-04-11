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
	var temp1="";
	var temp2="";
$(".item").mouseenter(function(){

	temp1=$(this).find("img").attr("title");
	temp2=$(this).find("img").attr("src");
	$(this).find("img").attr("src",temp1);
	$(this).find("img").attr("title",temp2);
	//alert($(this).find("img").attr("title"));
		  // $(this).html("<img src=\"img/nav/like_leave.png\" width=\"40\" height=\"55\" />");
});


$(".item").mouseleave(function(){

	temp1=$(this).find("img").attr("title");
	temp2=$(this).find("img").attr("src");
	$(this).find("img").attr("src",temp1);
	$(this).find("img").attr("title",temp2);
	//alert($(this).find("img").attr("title"));
		  // $(this).html("<img src=\"img/nav/like_leave.png\" width=\"40\" height=\"55\" />");
});



});


