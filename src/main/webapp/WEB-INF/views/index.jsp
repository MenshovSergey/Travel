<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body>
	<div align="center">
	<c:url value="/j_spring_security_check" var="loginUrl" />
	<form:form action="profile" method="post" commandName="userForm">
    			<table border="0">
    				<tr>
    					<td colspan="2" align="center"><h2>Log in</h2></td>
    				</tr>
    				<tr>
                    	<td>Login:</td>
                    	<td><form:input path="login" /></td>
                    </tr>
    				<tr>
    					<td>Password:</td>
    					<td><form:password path="password" /></td>
    				</tr>
    				<tr>
    					<td colspan="2" align="center"><input type="submit" value="Log in" /></td>
    				</tr>
    			</table>
    </form:form>
	<form:form action="registration" method="get">
	    <td colspan="2" align="center"><input type="submit" value="Sign up" /></td>
	</form:form>


	</div>
</body>
</html>