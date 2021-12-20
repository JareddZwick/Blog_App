<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="updatesubmit?blog=${blog.blogId}" method="post">
Title: <br><input type="text" name="title" value="${blog.blogTitle}"><br><br>
Content: <br><textarea name="content" rows="5" cols="30">${blog.blogContent}</textarea><br><br>
Language: <br><select name="lang"><option value="English">English</option>
<option value="Spanish">Spanish</option>
<option value="Hindi">Hindi</option>
<option value="German">German</option>
</select><br><br>
<input type="submit" value="Update Blog">
</form><br>
<a href="delete?blog=${blog.blogId}"><button class="btn btn-danger">Delete Blog</button></a>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>

</body>
</html>