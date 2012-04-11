<%@ page language="java" contentType="text/html; charset=utf-8"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<script>
		function reloadImage() {
			
			dpcument.getElementById('btn').disabled = true;
			document.getElementById('identify').src = 'VerifycodesServlet';
			
		}
</script>


<body>
    <form name=form method=post action = "Login" >
        <table>
            <tr>
                <td height="40" colspan=2>登录界面</td>  
            </tr>
            <tr>
                <td width="100">用户名(邮箱):</td>
                <td width="385"><input type=text name=user size=16></td>
            </tr>
            <tr>
                <td width="100">密码:</td>
                <td><input type=password  name=pwd size=16></td>
            </tr>
            <tr>
                <td width="100">验证码:</td>
                <td width="385"><input type=text name=verify size=16></td>
                <td><img src="VerifycodesServlet" id="identify" onload="btn.disabled=false; " /></td>
                <td><input type=button value="Chage Picture" onclick="reloadImage()" id="btn" /></td>
            </tr>
            <tr>
                <td colspan=2><input name="submit" type=submit value=submit>
                <a href="signup.jsp">呀哈居然没账号 要不哥注册一个?</a>
                </td>
            </tr>
        </table>
    </form>
 </body>
</html>