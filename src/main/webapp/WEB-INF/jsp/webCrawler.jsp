<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Bootstrap, from Twitter</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="../../resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../resources/css/bootstrap-responsive.min.css" rel="stylesheet">

</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#">Scrawler</a>

            <div class="nav-collapse">
                <ul class="nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="#about">About</a></li>
                </ul>
            </div>
            <!--/.nav-collapse -->
        </div>
    </div>
</div>
<div class="container">
    <!-- Main hero unit for a primary marketing message or call to action -->
    <div class="hero-unit clearfix">
        <div class="pull-left span5">
            <h1>Welcome to your scrawler</h1>
            <p>This is one example for the uses in the open source project could be found here:</p>
            <p><a id="action_startScrap" class="btn btn-large disabled">Start Scapping!</a></p>
            <p id="status">Status: </p>

        </div>
        <div class="pull-right">
            <label>Username</label>
            <input id="loginForm_userName" type="text" class="span3" value="">
            <label>Password</label>
            <input id="loginForm_password" type="password" class="span3" value="">
            <%--<label class="checkbox">--%>
            <%--<input type="checkbox">Remember me--%>
            <%--</label>--%>
            <a id="loginForm_Submit" class="btn">Log me in</a>
        </div>
    </div>

</div>
<!-- /container -->

<!-- Le javascript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="http://code.jquery.com/jquery.min.js"></script>
<%--<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.7.1.js"/>"></script>--%>
<script type="text/javascript" src="<c:url value="/resources/js/javascript.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
</body>
</html>
