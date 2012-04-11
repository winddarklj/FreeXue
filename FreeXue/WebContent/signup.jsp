<%@ page language="java" contentType="text/html; charset=utf-8"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册界面</title>
</head>

<script>
		function reloadImage() {
			
			dpcument.getElementById('btn').disabled = true;
			document.getElementById('identify').src = 'VerifycodesServlet';
			
		}
</script>

<body>
      <form name=form method=post action = "Signup" >
			 <table>
            <tr>
                <td height="40" colspan=2>登录界面</td>  
            </tr>
            <tr>
                <td width="120">用户名(邮箱):</td>
                <td width="385"><input type=text name=user size=16></td>
            </tr>
            <tr>
                <td width="120">密码:</td>
                <td><input type=password  name=pwd size=16></td>
            </tr>
             <tr>
                <td width="120">来，确认一下:</td>
                <td><input type=password  name=confirm size=16></td>
            </tr>
            <tr>
            	   <td width="100">验证码:</td>
                   <td width="385"><input type=text name=verify size=16></td>
                   <td><img src="VerifycodesServlet" id="identify" onload="btn.disabled=false; " /></td>
            </tr>
            <tr>
                <td width="120">昵称:</td>
                <td><input type=text  name=username size=16></td>
            </tr>
            <tr>
                <td colspan=2><input name="提交" type=submit value=submit>
                </td>
            </tr>
        </table>
      </form>
</body>
</html>