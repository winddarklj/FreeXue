<%@ page language="java" contentType="text/html; charset=utf-8"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <script src="js/jquery-1.7.1.js"></script>
  <script src="js/json2.js"></script>
  <script type="text/javascript">
  
$(document).ready(function(){
	//alert($("option:selected").html());
		$("#submit").live("click",function(){ 
			var sourceTitle=$("[name='sourceTitle']").val();
			var sourceURL=$("[name='sourceURL']").val();
			var description=$("[name='description']").val();
			var level1=$("#1 option:selected").val();
			var level2=$("#2 option:selected").val();
			var level3=$("#3 option:selected").val();
			var level4=$("#4 option:selected").val();
			var level5=$("#5 option:selected").val();
			var level6=$("#6 option:selected").val();

	    $.post("http://www.freexue.com/FreeXue/InputURL?",{sourceTitle : sourceTitle, sourceURL : sourceURL, description : description, level1 : level1, level2 : level2, level3 : level3, level4 : level4, level5 : level5, level6 : level6,},function call(data){
	    		alert(data);
	    	  });
		});
	$(".hr option:selected").live("click",function(){ 
		var num=$(this).parent().attr("id");
		
		if(num=="2"){
			$("#3").html("");
			$("#4").html("");
			$("#5").html("");
			$("#6").html("");
			}
		jsonget($(this).attr("value"),num);
		});
	$(".nohr option:selected").live("click",function(){ 
		jsonget1($(this).attr("value"));
		
	});
	function jsonget(getit,num){
    	$.get("http://www.freexue.com/FreeXue/GetHierarchy?",{parent : getit},function call(data){
    		wirteHtml1(data,num);
    	  });
    }
	
	function wirteHtml1(data,num){ 
    	var dataset = $.parseJSON(data);
    	var result="";
    	for(i=0;i<dataset.children.length;i++)
    		{ result=result+"<option value=\""+dataset.children[i].id+"\">"+dataset.children[i].name+"</option>";}
    	num=parseInt(num)+1;
    	var level="#"+num;
    	$(level).html(result);
    }	
	function jsonget1(getit){
    	$.get("http://www.freexue.com/FreeXue/GetHierarchy?",{parent : getit},function call(data){
        	//alert(data);
			$("#4").html("");
			$("#5").html("");
			$("#6").html("");
    		var dataset = $.parseJSON(data);
        	for(i=0;i<dataset.children.length;i++)
    		{
        		result="<option value=\""+dataset.children[i].id+"\">"+dataset.children[i].name+"</option>";
        		//alert(dataset.children[i].id.substring(1,2));
        		switch(dataset.children[i].id.substring(1,2))
        		{
        		case "4": 
				$("#4").append(result);
					break;
        		case "5": 
        		$("#5").append(result);
				break;
        		case "6": 
        		$("#6").append(result);
				break;
        		}
        		}
    	  });
    }
		
	});

</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
			Please Input Your Information Here:
			
			<form method = "post"  action = "InputURL" >
			<p>资料的Title: <input type="text" name="sourceTitle" /></p>
			<p>资料的网址: <input type="text" name="sourceURL" /></p>
			<p>资料描述: <input type="text" name="description" /></p>
			  <p> Level 1 : <select name="level1" size = "1" id="1" class="hr">
											<option value="s10000"> 考试</option>
											</select>
			 <p> Level 2 : <select name="level2" size = "1" id="2" class="hr">

											</select>
			  <p> Level 3 : <select name="level3" size = "1" id="3" class="nohr">

											</select>
			  <p> Level 4 : <select name="level4" size = "1" id="4">

											</select>
											
			  <p> Level 5 : <select name="level5" size = "1" id="5">
	
											</select>
											
			  <p> Level 6 : <select name="level6" size = "1" id="6">

											</select>
 					 <input type="button" value="Submit" id="submit"/>		
			</form>
			
		<form name="myform" action="UploadServlet" method="post"
       												enctype="multipart/form-data">
	    	    <input type="file" name="myfile"><br>
	       		<input type="submit" name="submit" value="Commit">
	    </form>
			
</body>
</html>