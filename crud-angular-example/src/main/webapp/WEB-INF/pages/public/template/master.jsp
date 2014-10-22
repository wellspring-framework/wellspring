<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="pt-BR" id="ng-app" ng-app="">
<head>
        <title><spring:message  code="project.title" /></title>
        <link href="<c:url value='/assets/css/bootstrap.min.css'  />" rel="stylesheet"/>
        <!-- <link href="<c:url value='/assets/css/bootstrap-responsive.min.css'  />" rel="stylesheet"/> -->
        <link href="<c:url value='/assets/css/project_style.css'  />" rel="stylesheet"/>
        <script src="<c:url value='/assets/js/jquery-2.1.1.min.js' />"></script>
        <script src="<c:url value='/assets/js/angular.min.js' />"></script>
    </head>
    <body>
        <div class="container">
            <tiles:insertAttribute name="header" />
 
            <tiles:insertAttribute name="body" />
        </div>
 
        <!--[if IE]>
            <script src="<c:url value='/assets/js/bootstrap.min.ie.js' />"></script>
        <![endif]-->
        <!--[if !IE]><!-->
            <script src="<c:url value='/assets/js/bootstrap.min.js' />"></script>
        <!--<![endif]-->
 
        <tiles:insertAttribute name="footer" />
    </body>
</html>