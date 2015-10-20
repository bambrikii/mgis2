<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>

	<link type="text/css" rel="stylesheet" href="app2/app.css">

	<script type="text/javascript" src="bower_components/angular/angular.js"></script>

	<script type="text/javascript" src="bower_components/jquery/jquery.js"></script>

	<link type="text/css" rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap-theme.css">
	<script type="text/javascript" src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
	<script type="text/javascript" src="bower_components/angular-bootstrap/ui-bootstrap.js"></script>

	<link type="text/css" rel="stylesheet" href="bower_components/angular-bootstrap/ui-bootstrap-csp.css">
	<script type="text/javascript" src="bower_components/angular-bootstrap/ui-bootstrap.js"></script>


	<script type="text/javascript" src="bower_components/angular-translate/angular-translate.js"></script>
	<script type="text/javascript" src="bower_components/angular-translate-loader-static-files/angular-translate-loader-static-files.js"></script>

	<script type="text/javascript" src="app2/login-app.js"></script>
	<script type="text/javascript" src="app2/auth/auth-module.js"></script>
	<script type="text/javascript" src="app2/auth/auth-service.js"></script>

</head>
<body>
<div class="container" ng-app="mgis.auth.app">
	<div class="row clearfix">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<h2 translate>ApplicationName</h2>
					<hr>
					<div class="clearfix">
						<form id="loginForm" name="loginForm" class="form-horizontal" ng-controller="MGISLoginController">
							<fieldset>
								<legend translate>Login</legend>

								<div class="form-group row"
									 ng-class="{'has-error': loginForm.username.$invalid && (!loginForm.username.$pristine || loginForm.username.$touched)}">
									<label class="col-md-4 control-label" for="username" translate>Username</label>

									<div class="col-md-8">
										<input id="username" name="username" placeholder="" class="form-control input-md" type="text" value=""
											   ng-model="username" required/>

										<p ng-show="loginForm.username.$invalid && (!loginForm.username.$pristine || loginForm.username.$touched)"
										   class="help-block" translate>Username.Required</p>
									</div>
								</div>

								<div class="form-group row"
									 ng-class="{'has-error': loginForm.password.$invalid && (!loginForm.password.$pristine || loginForm.password.$touched)}">
									<label class="col-md-4 control-label" for="password" translate>Password</label>

									<div class="col-md-8">
										<input id="password" name="password" placeholder="" class="form-control input-md" type="password"
											   ng-model="password" required>

										<p ng-show="loginForm.password.$invalid && (!loginForm.password.$pristine || loginForm.password.$touched)"
										   class="help-block" translate>Password.Required</p>
									</div>
								</div>

								<!-- Button -->
								<div class="form-group row">
									<label class="col-md-4 control-label" for="submitButton"></label>

									<div class="col-md-8">
										<button id="submitButton" ng-disabled="loginForm.$invalid" class="btn btn-primary btn-block"
												ng-click="login(username, password)"
												translate>Login
										</button>
									</div>
								</div>

								<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
									<div class="form-group">
										<div class="alert" style="color: red;">
											<strong translate>LoginAttemptFailedDueTo</strong> <c:out
												value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
										</div>
									</div>
								</c:if>
								<div class="form-group" ng-if="loginExceptionMessage">
									<div class="alert" style="color: red;">
										<strong translate>LoginAttemptFailedDueTo</strong>

										<div ng-bind-html="loginExceptionMessage"></div>
										.
									</div>
								</div>


							</fieldset>
						</form>
					</div>
				</div>
				<div class="col-md-1"></div>
			</div>

		</div>
	</div>
	<div class="col-md-2"></div>
</div>
</div>

</body>
</html>
