<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<style>
text-align: center;
</style>
<meta charset="ISO-8859-1">
<title>Home page</title>
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
    </ul>
  </div>
</nav>
<h1 style="text-align: center;">Welcome to my Blog Maintenance System!</h1>
<br><br>
<div style="text-align: center;">
<a href="register">
<button type="button" class="btn btn-secondary">Register</button>
</a>
<a href="login">
<button type="button" class="btn btn-success">Sign In</button>
</a>
</div>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>

</body>
</html>