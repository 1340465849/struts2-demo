<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%> 
<html>
	<head>
		<link href="<s:url value='/public/stylesheets/globel.css'/>" rel="stylesheet" type="text/css"/>
		<script src="<s:url value='/public/jquery/js/jquery-1.4.2.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/public/javascripts/public.js'/>" type="text/javascript"></script>
		<title><decorator:title default="sitemesh" /></title>
		<decorator:head />
<script type="text/javascript">
// <![CDATA[
$(document).ready(function() {
    if ($.browser.msie){
     	$("input[type='text'], input[type='password'], textarea, select")
     		.focus(function(){$(this).addClass("ie_focus");})
     		.blur(function(){$(this).removeClass("ie_focus");});
    }
});
// ]]></script>
	</head>
	<body>
		<div id="container">
		  <div id="header">
		  </div>
		  <div id="login"><span><a href="?request_locale=en_US">English</a>|<a href="?request_locale=zh_CN">Chinese</a></span></div>
		  <div id="main">
		    <div id="sidebar"></div>
		    <div id="content"><decorator:body /></div>
		  </div>
		  <hr/>
		  <div id="footer"><center>Power by Bulain</center></div>
		</div>
	</body>
</html>