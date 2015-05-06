<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>

<link type="text/css" rel="stylesheet" href="app/app.css"></link>
<link type="text/css" rel="stylesheet" href="bower_components/jquery-ui/themes/base/jquery-ui.css"></link>
<link type="text/css" rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.css"></link>
<link type="text/css" rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap-theme.css"></link>

</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<h2>MGIS</h2>
				<hr>
				<div class="clearfix">
					<form id="form" action="<c:url value='/login'/>" method="POST" class="form-horizontal">
						<fieldset>
							<legend>Login</legend>
							<c:if test="${not empty param.out}">
								<div>You've logged out successfully.</div>
							</c:if>
							<c:if test="${not empty param.time}">
								<div>You've been logged out due to inactivity.</div>
							</c:if>

							<!-- Username input -->
							<div class="form-group">
								<label class="col-md-4 control-label" for="username">Username</label>
								<div class="col-md-4">
									<input id="username" name="username" placeholder="placeholder" class="form-control input-md" type="text" value="" /> <span
										class="help-block">User name</span>
								</div>
							</div>

							<!-- Password input -->
							<div class="form-group">
								<label class="col-md-4 control-label" for="password">password</label>
								<div class="col-md-4">
									<input id="password" name="password" placeholder="placeholder" class="form-control input-md" type="password"> <span
										class="help-block">help</span>
								</div>
							</div>

							<!-- Button -->
							<div class="form-group">
								<label class="col-md-4 control-label" for="submitButton"></label>
								<div class="col-md-4">
									<button id="submitButton" name="" type="submit" class="btn btn-primary">Login</button>
								</div>
							</div>

						</fieldset>
					</form>
				</div>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>

</body>
</html>