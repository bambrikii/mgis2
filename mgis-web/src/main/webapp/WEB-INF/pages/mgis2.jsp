<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

	<link type="text/css" rel="stylesheet" href="app2/app.css">
	<link type="text/css" rel="stylesheet" href="bower_components/jquery-ui/themes/base/jquery-ui.css">
	<link type="text/css" rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap-theme.css">
	<link type="text/css" rel="stylesheet"
		  href="bower_components/bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css">
	<link type="text/css" rel="stylesheet" href="bower_components/bootstrap-treeview/src/css/bootstrap-treeview.css">
	<link type="text/css" rel="stylesheet" href="bower_components/backgrid/lib/backgrid.css">
	<link type="text/css" rel="stylesheet" href="bower_components/angular-bootstrap/ui-bootstrap-csp.css">

	<link type="text/css" rel="stylesheet" href="bower_components/select2/select2.css">
	<link type="text/css" rel="stylesheet" href="bower_components/angular-ui-select/dist/select.css">

	<script type="text/javascript" src="bower_components/angular/angular.js"></script>
	<script type="text/javascript" src="bower_components/angular-resource/angular-resource.js"></script>
	<!-- <script type="text/javascript" src="bower_components/angular-route/angular-route.js"></script> -->
	<script type="text/javascript" src="bower_components/angular-ui-router/release/angular-ui-router.js"></script>
	<script type="text/javascript" src="bower_components/jquery/jquery.js"></script>
	<script type="text/javascript" src="bower_components/angular-bootstrap/ui-bootstrap.js"></script>
	<script type="text/javascript" src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
	<script type="text/javascript" src="bower_components/angular-translate/angular-translate.js"></script>
	<script type="text/javascript"
			src="bower_components/angular-translate-loader-static-files/angular-translate-loader-static-files.js"></script>
	<script type="text/javascript" src="bower_components/angular-utils-ui-breadcrumbs/uiBreadcrumbs.js"></script>
	<script type="text/javascript" src="bower_components/angular-bootstrap-multiselect/angular-bootstrap-multiselect.js"></script>
	<script type="text/javascript" src="bower_components/ng-file-upload/ng-file-upload-shim.js"></script>
	<script type="text/javascript" src="bower_components/ng-file-upload/ng-file-upload.js"></script>
	<!-- <script type="text/javascript" src="bower_components/angular-sanitize/angular-sanitize.js"></script> -->
	<!-- <script type="text/javascript" src="bower_components/angular-translate/angular-translate.js"></script> -->
	<!-- <script type="text/javascript" src="bower_components/angular-dialog-service/dist/dialogs-default-translations.js"></script> -->
	<!-- <script type="text/javascript" src="bower_components/angular-dialog-service/dist/dialogs.js"></script> -->
	<script type="text/javascript" src="bower_components/select2/select2.js"></script>
	<script type="text/javascript" src="bower_components/angular-ui-select/dist/select.js"></script>
	<script type="text/javascript" src="bower_components/jquery/jquery.js"></script>
	<script type="text/javascript" src="bower_components/jquery-ui/jquery-ui.js"></script>
	<script type="text/javascript" src="bower_components/angular-sanitize/angular-sanitize.js"></script>

	<link type="text/css" rel="stylesheet" href="bower_components/leaflet/dist/leaflet.css">
	<script type="text/javascript" src="bower_components/leaflet/dist/leaflet-src.js"></script>
	<%--<script type="text/javascript" src="bower_components/leaflet.activelayers/src/ActiveLayers.js"></script>--%>
	<link type="text/css" rel="stylesheet" href="bower_components/leaflet-groupedlayercontrol/src/leaflet.groupedlayercontrol.css">
	<script type="text/javascript" src="bower_components/leaflet-groupedlayercontrol/src/leaflet.groupedlayercontrol.js"></script>
	<%--<script type="text/javascript" src="bower_components/leaflet-plugins/layer/tile/Google.js"></script>--%>
	<%--<script type="text/javascript" src="bower_components/leaflet-plugins/layer/tile/Bing.js"></script>--%>
	<%--<script type="text/javascript" src="bower_components/leaflet-plugins/layer/tile/Yandex.js"></script>--%>
	<link type="text/css" rel="stylesheet" href="bower_components/leaflet.draw/dist/leaflet.draw.css">
	<script type="text/javascript" src="bower_components/leaflet.draw/dist/leaflet.draw-src.js"></script>

	<script type="text/javascript" src="app2/app.js"></script>
	<script type="text/javascript" src="app2/menu/app-menu.js"></script>
	<script type="text/javascript" src="app2/commons/app-confirmation.js"></script>
	<script type="text/javascript" src="app2/commons/common-modules.js"></script>
	<script type="text/javascript" src="app2/commons/app-error-handling-service.js"></script>
	<script type="text/javascript" src="app2/isogd/isogd.js"></script>
	<script type="text/javascript" src="app2/isogd/section/isogd-sections-service.js"></script>
	<script type="text/javascript" src="app2/isogd/section/isogd-sections-module.js"></script>
	<script type="text/javascript" src="app2/isogd/volume/isogd-volumes-service.js"></script>
	<script type="text/javascript" src="app2/isogd/volume/isogd-volumes-module.js"></script>
	<script type="text/javascript" src="app2/isogd/book/isogd-books-service.js"></script>
	<script type="text/javascript" src="app2/isogd/book/isogd-books-module.js"></script>
	<script type="text/javascript" src="app2/isogd/document/isogd-documents-service.js"></script>
	<script type="text/javascript" src="app2/isogd/document/isogd-documents-module.js"></script>
	<script type="text/javascript" src="app2/isogd/classifiers/classifiers.js"></script>
	<script type="text/javascript" src="app2/isogd/classifiers/documents/structure/isogd-docs-structure-service.js"></script>
	<script type="text/javascript" src="app2/isogd/classifiers/documents/structure/isogd-docs-structure-module.js"></script>
	<script type="text/javascript" src="app2/isogd/classifiers/documents/types/isogd-docs-types-service.js"></script>
	<script type="text/javascript" src="app2/isogd/classifiers/documents/types/isogd-docs-types-module.js"></script>
	<script type="text/javascript" src="app2/isogd/classifiers/documents/representation/isogd-docs-representation-service.js"></script>
	<script type="text/javascript" src="app2/isogd/classifiers/documents/representation/isogd-docs-representation-module.js"></script>
	<script type="text/javascript" src="app2/isogd/document-selector/document-selector-component.js"></script>
	<script type="text/javascript" src="app2/admin/admin.js"></script>
	<script type="text/javascript" src="app2/admin/users/admin-users-service.js"></script>
	<script type="text/javascript" src="app2/admin/users/admin-users-module.js"></script>
	<script type="text/javascript" src="app2/admin/privileges/admin-privileges-service.js"></script>
	<script type="text/javascript" src="app2/admin/privileges/admin-privileges-module.js"></script>
	<script type="text/javascript" src="app2/oks/oks.js"></script>
	<link type="text/css" rel="stylesheet" href="app2/lands/land/lands-leaflet-control.css">
	<script type="text/javascript" src="app2/lands/land/lands-leaflet-control.js"></script>
	<script type="text/javascript" src="app2/lands/lands.js"></script>
	<script type="text/javascript" src="app2/lands/land/land-services.js"></script>
	<script type="text/javascript" src="app2/lands/land/land-map-module.js"></script>
	<script type="text/javascript" src="app2/lands/land/land-modules.js"></script>
	<script type="text/javascript" src="app2/lands/land/land-maps-module.js"></script>
	<script type="text/javascript" src="app2/terr-zones/terr-zones.js"></script>
	<script type="text/javascript" src="app2/terr-zones/zone/zone-service.js"></script>
	<script type="text/javascript" src="app2/terr-zones/zone/zone-module.js"></script>

	<script type="text/javascript" src="app2/nc/nc-services.js"></script>
	<script type="text/javascript" src="app2/nc/nc-caching-module.js"></script>
	<script type="text/javascript" src="app2/commons/common-services.js"></script>

	<script type="text/javascript" src="app2/oks/natural-person/natural-person-service.js"></script>
	<script type="text/javascript" src="app2/oks/natural-person/natural-person-module.js"></script>
	<script type="text/javascript" src="app2/oks/legal-person/legal-person-service.js"></script>
	<script type="text/javascript" src="app2/oks/legal-person/legal-person-module.js"></script>
	<script type="text/javascript" src="app2/oks/person-selector/person-selector-component.js"></script>

	<script type="text/javascript" src="app2/oks/address/address-service.js"></script>
	<script type="text/javascript" src="app2/oks/address/address-module.js"></script>
	<script type="text/javascript" src="app2/oks/address-selector/address-selector-component.js"></script>

</head>
<body>

<div class="main-container" ng-app="mgis">

	<div ng-include="'app2/menu/menu.htm'"></div>
	<div class="container-fluid" ui-view></div>

</div>

</body>
</html>
