<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link type="text/css" rel="stylesheet" href="app/app.css"></link>
<link type="text/css" rel="stylesheet" href="bower_components/jquery-ui/themes/base/jquery-ui.css"></link>
<link type="text/css" rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.css"></link>
<link type="text/css" rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap-theme.css"></link>
<link type="text/css" rel="stylesheet" href="bower_components/bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css"></link>
<link type="text/css" rel="stylesheet" href="bower_components/backgrid/lib/backgrid.css"></link>

<script type="text/javascript" src="bower_components/angular/angular.js"></script>
<!-- <script type="text/javascript" src="bower_components/angular-route/angular-route.js"></script> -->
<script type="text/javascript" src="bower_components/angular-ui-router/release/angular-ui-router.js"></script>
<script type="text/javascript" src="bower_components/angular-bootstrap/ui-bootstrap.js"></script>

<script type="text/javascript" src="app2/app.js"></script>
<script type="text/javascript" src="app2/menu/app-menu.js"></script>
<script type="text/javascript" src="app2/isogd/isogd.js"></script>
<script type="text/javascript" src="app2/isogd/isogd-sections-service.js"></script>
<script type="text/javascript" src="app2/isogd/isogd-volumes-service.js"></script>
<script type="text/javascript" src="app2/isogd/isogd-books-service.js"></script>
<script type="text/javascript" src="app2/isogd/isogd-documents-service.js"></script>
<script type="text/javascript" src="app2/isogd/isogd.js"></script>
<script type="text/javascript" src="app2/oks/oks.js"></script>

</head>
<body>

	<div class="main-container" ng-app="mgis">


		<main-menu>
			<menu-item link="#/isogd">ISOGD</menu-item>
			<menu-item link="#/oks">OKS</menu-item>
			<menu-item link="#/">Item 3</menu-item>
			<menu-item link="/logout">Logout</menu-item>
		</main-menu>

		<div class="container" ui-view></div>


	</div>

</body>
</html>