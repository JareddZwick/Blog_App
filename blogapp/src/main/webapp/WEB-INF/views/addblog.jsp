<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="insertblog" method="post">
Title: <br><input type="text" name="title"><br><br>
Content: <br><textarea name="content" rows="5" cols="30"></textarea><br><br>
Language: <br><select name="lang"><option value="English">English</option>
<option value="Spanish">Spanish</option>
<option value="Hindi">Hindi</option>
<option value="German">German</option>
</select><br><br>
<input type="submit" value="Add Blog">
</form>

</body>
</html>