<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" pageEncoding="UTF-8" %>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

	<link rel="icon" type="image/png" href="app2/images/dino.png">

	<link type="text/css" rel="stylesheet" href="app2/app.css">
	<link type="text/css" rel="stylesheet" href="app2/app-desktop.css">
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
	<script type="text/javascript" src="bower_components/angular-i18n/angular-locale_ru-ru.js"></script>
	<!-- <script type="text/javascript" src="bower_components/angular-route/angular-route.js"></script> -->
	<script type="text/javascript" src="bower_components/angular-ui-router/release/angular-ui-router.js"></script>
	<script type="text/javascript" src="bower_components/jquery/jquery.js"></script>
	<script type="text/javascript" src="bower_components/jquery-ui/jquery-ui.js"></script>
	<script type="text/javascript" src="bower_components/angular-bootstrap/ui-bootstrap.js"></script>
	<script type="text/javascript" src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
	<script type="text/javascript" src="bower_components/angular-translate/angular-translate.js"></script>
	<script type="text/javascript"
			src="bower_components/angular-translate-loader-static-files/angular-translate-loader-static-files.js"></script>
	<script type="text/javascript" src="bower_components/angular-utils-ui-breadcrumbs/uiBreadcrumbs.js"></script>
	<script type="text/javascript"
			src="bower_components/angular-bootstrap-multiselect/angular-bootstrap-multiselect.js"></script>
	<script type="text/javascript" src="bower_components/ng-file-upload/ng-file-upload-shim.js"></script>
	<script type="text/javascript" src="bower_components/ng-file-upload/ng-file-upload.js"></script>
	<!-- <script type="text/javascript" src="bower_components/angular-sanitize/angular-sanitize.js"></script> -->
	<!-- <script type="text/javascript" src="bower_components/angular-translate/angular-translate.js"></script> -->
	<!-- <script type="text/javascript" src="bower_components/angular-dialog-service/dist/dialogs-default-translations.js"></script> -->
	<!-- <script type="text/javascript" src="bower_components/angular-dialog-service/dist/dialogs.js"></script> -->
	<script type="text/javascript" src="bower_components/select2/select2.js"></script>
	<script type="text/javascript" src="bower_components/angular-ui-select/dist/select.js"></script>
	<script type="text/javascript" src="bower_components/angular-sanitize/angular-sanitize.js"></script>

	<link type="text/css" rel="stylesheet" href="bower_components/leaflet/dist/leaflet.css">
	<script type="text/javascript" src="bower_components/leaflet/dist/leaflet-src.js"></script>
	<%--<script type="text/javascript" src="bower_components/leaflet.activelayers/src/ActiveLayers.js"></script>--%>
	<link type="text/css" rel="stylesheet"
		  href="bower_components/leaflet-groupedlayercontrol/src/leaflet.groupedlayercontrol.css">
	<script type="text/javascript"
			src="bower_components/leaflet-groupedlayercontrol/src/leaflet.groupedlayercontrol.js"></script>
	<%--<script type="text/javascript" src="bower_components/leaflet-plugins/layer/tile/Google.js"></script>--%>
	<%--<script type="text/javascript" src="bower_components/leaflet-plugins/layer/tile/Bing.js"></script>--%>
	<%--<script type="text/javascript" src="bower_components/leaflet-plugins/layer/tile/Yandex.js"></script>--%>
	<link type="text/css" rel="stylesheet" href="bower_components/leaflet.draw/dist/leaflet.draw.css">
	<script type="text/javascript" src="bower_components/leaflet.draw/dist/leaflet.draw-src.js"></script>
	<script type="text/javascript" src="bower_components/Leaflet.MousePosition/src/L.Control.MousePosition.js"></script>
	<script type="text/javascript" src="bower_components/terraformer/terraformer.js"></script>
	<script type="text/javascript" src="bower_components/terraformer-wkt-parser/terraformer-wkt-parser.js"></script>
	<script type="text/javascript" src="bower_components/moment/min/moment-with-locales.js"></script>
	<script type="text/javascript" src="bower_components/ngDraggable/ngDraggable.js"></script>

	<!-- leaflet-plugins -->
	<script src="http://maps.google.com/maps/api/js?v=3&sensor=false"></script>
	<script src="bower_components/leaflet-plugins/layer/tile/Google.js"></script>
	<script src="bower_components/leaflet-plugins/layer/tile/Bing.js"></script>

	<link type="text/css" rel="stylesheet"
		  href="bower_components/leaflet-layer-tree-plugin/src/leaflet-layer-tree-control.css">
	<script type="text/javascript"
			src="bower_components/leaflet-layer-tree-plugin/src/leaflet-layer-tree-control.js"></script>
	<script type="text/javascript"
			src="bower_components/leaflet-layer-tree-plugin/src/leaflet-layer-tree-control-wfs-zoom.js"></script>

	<!-- https://github.com/flowjs/ng-flow -->
	<%--<script type="text/javascript" src="bower_components/ng-flow/dist/ng-flow-standalone.min.js"></script>--%>
	<script src="bower_components/flow.js/dist/flow.js"></script>
	<script src="bower_components/ng-flow/dist/ng-flow.js"></script>

	<!-- app2 -->
	<script type="text/javascript" src="app2/app.js"></script>
	<script type="text/javascript" src="app2/menu/app-menu.js"></script>
	<script type="text/javascript" src="app2/commons/common-modules.js"></script>
	<script type="text/javascript" src="app2/commons/common-services.js"></script>
	<script type="text/javascript" src="app2/errors/error-module.js"></script>
	<script type="text/javascript" src="app2/errors/error-service.js"></script>

	<!-- National Classifiers -->
	<script type="text/javascript" src="app2/nc/nc-services.js"></script>
	<script type="text/javascript" src="app2/nc/nc-caching-module.js"></script>

	<!-- Property -->
	<script type="text/javascript" src="app2/property/property-service.js"></script>
	<script type="text/javascript" src="app2/property/property-module.js"></script>

	<!-- Indicators -->
	<script type="text/javascript" src="app2/indicators/indicator-services.js"></script>
	<script type="text/javascript" src="app2/indicators/indicator-module.js"></script>
	<script type="text/javascript" src="app2/indicators/price-indicator/price-indicator-module.js"></script>
	<script type="text/javascript" src="app2/indicators/technical-indicator/technical-indicator-module.js"></script>

	<!-- ISOGD -->
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
	<script type="text/javascript"
			src="app2/isogd/classifiers/documents/structure/isogd-docs-structure-service.js"></script>
	<script type="text/javascript"
			src="app2/isogd/classifiers/documents/structure/isogd-docs-structure-module.js"></script>
	<script type="text/javascript" src="app2/isogd/classifiers/documents/types/isogd-docs-types-service.js"></script>
	<script type="text/javascript" src="app2/isogd/classifiers/documents/types/isogd-docs-types-module.js"></script>
	<script type="text/javascript"
			src="app2/isogd/classifiers/documents/representation/isogd-docs-representation-service.js"></script>
	<script type="text/javascript"
			src="app2/isogd/classifiers/documents/representation/isogd-docs-representation-module.js"></script>
	<script type="text/javascript" src="app2/isogd/document-selector/document-selector-component.js"></script>
	<script type="text/javascript" src="app2/isogd/document-search/document-search-module.js"></script>
	<script type="text/javascript" src="app2/isogd/document-search/document-search-service.js"></script>
	<script type="text/javascript" src="app2/admin/admin.js"></script>
	<script type="text/javascript" src="app2/admin/users/admin-users-service.js"></script>
	<script type="text/javascript" src="app2/admin/users/admin-users-module.js"></script>
	<script type="text/javascript" src="app2/admin/privileges/admin-privileges-service.js"></script>
	<script type="text/javascript" src="app2/admin/privileges/admin-privileges-module.js"></script>

	<!-- Capital Constructs -->
	<script type="text/javascript" src="app2/capital-constructs/capital-construct-selector/capital-construct-selector-module.js"></script>
	<script type="text/javascript" src="app2/capital-constructs/capital-constructs.js"></script>
	<script type="text/javascript" src="app2/capital-constructs/characteristics/characteristics-module.js"></script>
	<script type="text/javascript" src="app2/capital-constructs/characteristics/characteristics-services.js"></script>
	<script type="text/javascript"
			src="app2/capital-constructs/constructive-element/constructive-element-module.js"></script>
	<script type="text/javascript"
			src="app2/capital-constructs/constructive-element-type/constructive-element-type-module.js"></script>
	<script type="text/javascript"
			src="app2/capital-constructs/constructive-element-type/constructive-element-type-service.js"></script>
	<script type="text/javascript" src="app2/capital-constructs/construct/construct-module.js"></script>
	<script type="text/javascript" src="app2/capital-constructs/construct/construct-service.js"></script>

	<link type="text/css" rel="stylesheet" href="app2/lands/land/lands-leaflet-control.css">
	<script type="text/javascript" src="app2/lands/land/lands-leaflet-control.js"></script>
	<script type="text/javascript" src="app2/lands/land-selector/land-selector-module.js"></script>
	<script type="text/javascript" src="app2/lands/lands.js"></script>
	<script type="text/javascript" src="app2/lands/land/land-services.js"></script>
	<script type="text/javascript" src="app2/lands/land/land-map-module.js"></script>
	<script type="text/javascript" src="app2/lands/land/land-modules.js"></script>
	<script type="text/javascript" src="app2/lands/land/land-maps-module.js"></script>
	<script type="text/javascript" src="app2/terr-zones/terr-zones.js"></script>
	<script type="text/javascript" src="app2/terr-zones/zone/zone-service.js"></script>
	<script type="text/javascript" src="app2/terr-zones/zone/zone-module.js"></script>


	<script type="text/javascript" src="app2/persons/natural-person/natural-person-certificate-service.js"></script>
	<script type="text/javascript" src="app2/persons/natural-person/natural-person-service.js"></script>
	<script type="text/javascript" src="app2/persons/natural-person/natural-person-module.js"></script>
	<script type="text/javascript" src="app2/persons/legal-person/legal-person-service.js"></script>
	<script type="text/javascript" src="app2/persons/legal-person/legal-person-module.js"></script>
	<script type="text/javascript" src="app2/persons/person-selector/person-selector-component.js"></script>

	<script type="text/javascript" src="app2/address/address-service.js"></script>
	<script type="text/javascript" src="app2/address/address-module.js"></script>
	<script type="text/javascript" src="app2/address/address-selector/address-selector-component.js"></script>

	<!-- GEO Modules -->
	<script type="text/javascript" src="app2/geo/geo.js"></script>
	<script type="text/javascript" src="app2/geo/geo-server/geo-server-module.js"></script>
	<script type="text/javascript" src="app2/geo/geo-server/geo-server-service.js"></script>
	<script type="text/javascript" src="app2/geo/coordinate-system/coordinate-system-module.js"></script>
	<script type="text/javascript" src="app2/geo/coordinate-system/coordinate-system-service.js"></script>
	<script type="text/javascript" src="app2/geo/spatial/spatial-data-module.js"></script>
	<script type="text/javascript" src="app2/geo/map/map-module.js"></script>
	<script type="text/javascript" src="app2/geo/map-layer/map-layer-module.js"></script>
	<script type="text/javascript" src="app2/geo/map-layer/map-layer-service.js"></script>

	<script type="text/javascript" src="app2/settings/settings.js"></script>

	<script type="text/javascript" src="app2/commons/crud/common-crud-module.js"></script>

	<!-- Data Exchange -->
	<script type="text/javascript" src="app2/data-exchange/data-exchange.js"></script>
	<script type="text/javascript" src="app2/data-exchange/import/import-module.js"></script>
	<script type="text/javascript" src="app2/data-exchange/export/export-module.js"></script>

	<!-- Reports -->
	<script type="text/javascript" src="app2/reports/reports.js"></script>
	<script type="text/javascript" src="app2/reports/report-service.js"></script>
	<script type="text/javascript" src="app2/reports/report-module.js"></script>

	<!-- About -->
	<script type="text/javascript" src="app2/about/about-module.js"></script>

	<!-- test pages -->
	<script type="text/javascript" src="app2/commons/forms/commons-forms-module.js"></script>
	<script type="text/javascript" src="app2/test/test-module.js"></script>

</head>
<body>

<div class="main-container" ng-app="mgis">

	<div ng-include="'app2/menu/menu.htm'"></div>
	<div class="container">
		<mgis-error-panel></mgis-error-panel>
	</div>
	<div class="container-fluid" ui-view></div>

</div>

</body>
</html>
