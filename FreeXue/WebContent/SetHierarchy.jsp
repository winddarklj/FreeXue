<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form name=form method=post action = "SetHierarchy" >
		<table>
            <tr>
                <td height="40" colspan=2>输入</td>  
            </tr>
            <tr>
                <td width="100">Parent</td>
                <td width="385"><input type=text name=parent size=16></td>
            </tr>
            <tr>
                <td width="100">Children::</td>
                <td><textarea rows="5" cols="20" name=children>
				</textarea></td>
            </tr>
            <tr>
                <td width="100">验证码:</td>
                <td><img src="VerifycodesServlet" id="identify" onload="btn.disabled=false; " /></td>
                <td></td>
                
            </tr>
             	<td colspan=2><input name="submit" type=submit value=submit>
            <tr>
            </tr>
        </table>
        </form>
</body>
</html>