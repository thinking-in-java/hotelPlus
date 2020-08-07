<%--
  Created by IntelliJ IDEA.
  User: Mr. Yi Quan
  Date: 2020/8/6
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/commons/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!-- saved from url=(0018)http://gdzl.la/404 -->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=450, user-scalable=no">
    <title>Insufficient authority</title>

    <link href="${staticPath}/static/error/no_permission/cajs/application-one.css" media="screen, projection" rel="stylesheet" type="text/css">
    <link href="${staticPath}/static/error/no_permission/cajs/application-mobile.css" media="only screen and (max-device-width: 480px)" rel="stylesheet" type="text/css">
    <script src="${staticPath}/static/error/no_permission/cajs/application.js" type="text/javascript"></script>


</head>

<body class="error_page">
<div id="wrap" class="clearfix">
    <div id="header"></div>

    <div id="smash_page">
        <h2>Insufficient authority</h2>
        <h2>(权限不足,请联系管理员)</h2>
    </div>

    <div id="smash" style="bottom: -135px;">
        <div class="skull" style="cursor: pointer;">
            <div class="eyes">
                <img alt="Eyes01" class="pupils" src="${staticPath}/static/error/no_permission/img/eyes.png" style="left: 29px; top: 6px;">
                <img alt="Hilites02" class="hilites" src="${staticPath}/static/error/no_permission/img/hilites.png" style="left: 20px; top: 10px;">
            </div>
            <img alt="Face04" class="face" src="${staticPath}/static/error/no_permission/img/face.png">
        </div>
    </div>
</div> <!-- /#wrap -->