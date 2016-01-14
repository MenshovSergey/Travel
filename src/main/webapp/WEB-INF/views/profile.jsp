<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile</title>
</head>
<body>
<table cols = 2>
<tr>
    <td><table cols = 1><tr>
    <img  src = "${pageContext.request.contextPath}${userForm.photo}" width=200 height=300 >
    </td></tr>
    <tr>
        <td>
        <form action="edit" method="get">
                                <input type = submit value = "Edit">
                            </form>
        </td>
        </tr>
        </table>
        </td>
    <td><table cols = 1>
        <tr>
                  <td>  ${userForm.username}</td>
                </tr>
                <tr>
                   <td> ${userForm.yearsOld}</td>
                </tr>
                <tr>
                    <td>Прошлые акт</td>
                </tr>
                <tr>
                   <td> Город </td>
                </tr>
                <tr>
                   <td> О себе </td>
                </tr>
                <tr>
                    <td> Увлечения </td>
                </tr>
                <tr>
                   <td> <form action="close" method="get">
                        <input type = submit value = "Log out">
                    </form> </td>
                </tr>
                <tr>
                    <td> <form action="find" method="get" commandName="userForm">
                         <input type = submit value = "FIND">
                     </form> </td>
                 </tr>
                </table>
    </td>

</tr>
</table>
</body>

</html>
