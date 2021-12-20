<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
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

<form action="updateprofile" method="post">
Email Id: <br><h5>${prof.emailId}</h5>
Email Id: <br><h5>${prof.mobile}</h5>
Gender: <br><input type="text" name="gender" value="${prof.gender}"><br><br>
Location:<br><input type="text" name="location" value="${prof.location}"><br><br>
<input type="submit" value="Update Profile">
</form>
<br>
<c:set var = "admin" value="${prof.admin}" />
<c:choose>
<c:when test="${admin}">
<a href="admindash">
<button class="btn btn-primary">Admin Dashboard</button>
</a>
</c:when>
<c:otherwise>
</c:otherwise>
</c:choose>
<br><br>
<img style ="width:100%; max-width: 150px;"src="${prof.imgsrc}">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
</body>
</html>