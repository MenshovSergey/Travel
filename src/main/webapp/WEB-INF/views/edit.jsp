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
		<form:form action="edit" method="post" commandName="userForm" enctype="multipart/form-data">
			<table border="0">
				<tr>
					<td colspan="2" align="center"><h2>Meeting registration</h2></td>
				</tr>
				<tr>
                	<td>User Name:</td>
                	<td><form:input path="username" /></td>
                </tr>
				<tr>
					<td>Years old:</td>
					<td><form:input path="yearsOld" /></td>
				</tr>
				<tr>
					<td>File to upload: <input type="file" name="file" multiple accept="image/*"><br /></td>

					</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Edit" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>