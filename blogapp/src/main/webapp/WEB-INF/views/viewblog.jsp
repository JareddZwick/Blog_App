<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><c:out value="${b.blogTitle}"></c:out></title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body><nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="home">My Blogs</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="home">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
      <a class="nav-link" href="profile">Profile</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-expanded="false">
          Sort by
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="home">Most Recent</a>
          <div class="dropdown-divider"></div>          
          <a class="dropdown-item" href="sortBy?crit=likes">Most Likes</a>
          <a class="dropdown-item" href="sortBy?crit=dislikes">Most Dislikes</a>
          <a class="dropdown-item" href="sortBy?crit=comments">Most Comments</a>
        </div>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-expanded="false">
          View By Language
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a href="viewbylang?lang=English">English Posts</a><br>
			<a href="viewbylang?lang=Hindi">Hindi Posts</a><br>
			<a href="viewbylang?lang=Spanish">Spanish Posts</a><br>
			<a href="viewbylang?lang=German">German Posts</a>
        </div>
      </li>
      <li class="nav-item ml-auto">
      <a class="nav-link" href="logout">Log Out</a>
      </li>
    </ul>
  </div>
</nav>
<div>
<h1 style="text-decoration: underline;"><c:out value="${blog.blogTitle}"></c:out></h1>

</div>
<h3>
<c:out value="${blog.blogContent}"></c:out></h3>
<br><br>
<a href="like?blog=${blog.blogId}">
<br><br><br>

<i class="fa fa-thumbs-up"></i>
 <c:out value="${blog.likes}"></c:out>
 </a>
<a href="dislike?blog=${blog.blogId}">
 <i class="fa fa-thumbs-down"></i>
 <c:out value="${blog.dislikes}"></c:out><br>
</a><br>
<c:out value="Comments: ${blog.comments}"></c:out>
<hr>
<br>
<c:forEach items="${comment}" var="com">
<div>
<h6><c:out value="${com.content} "></c:out></h6>
<p><c:out value="${com.datetime} - ${com.userId}"></c:out></p>
<a href="likeC?com=${com.commentId}&blog=${blog.blogId}">
<i class="fa fa-thumbs-up"></i>
 <c:out value="${com.likes}"></c:out>
 </a>
<a href="dislikeC?com=${com.commentId}&blog=${blog.blogId}">
 <i class="fa fa-thumbs-down"></i>
 <c:out value="${com.dislikes}"></c:out><br>
</a><br>
<br></div></c:forEach>
<form action="insertcomment?blog=${blog.blogId}" method="post">
Comment: <br><textarea name="comment" rows="5" cols="30"></textarea><br><br>
<input type="submit" value="Post Comment">
</form>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>

</body>
</html>