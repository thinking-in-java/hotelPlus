<%--
  Created by IntelliJ IDEA.
  User: Mr . YiQuan
  Date: 2020/8/6
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/commons/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>404</title>
    <link href="${staticPath}/static/error/404/css/404.css" rel="stylesheet" type="text/css" />
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <script type="text/javascript">
        $(function() {
            var h = $(window).height();
            $('body').height(h);
            $('.mianBox').height(h);
            centerWindow(".tipInfo");
        });

        //2.将盒子方法放入这个方，方便法统一调用
        function centerWindow(a) {
            center(a);
            //自适应窗口
            $(window).bind('scroll resize',
                function() {
                    center(a);
                });
        }

        //1.居中方法，传入需要剧中的标签
        function center(a) {
            var wWidth = $(window).width();
            var wHeight = $(window).height();
            var boxWidth = $(a).width();
            var boxHeight = $(a).height();
            var scrollTop = $(window).scrollTop();
            var scrollLeft = $(window).scrollLeft();
            var top = scrollTop + (wHeight - boxHeight) / 2;
            var left = scrollLeft + (wWidth - boxWidth) / 2;
            $(a).css({
                "top": top,
                "left": left
            });
        }
    </script>
</head>
<body>
<div class="mianBox">
    <img src="${staticPath}/static/error/404/images/yun0.png" alt="" class="yun yun0" />
    <img src="${staticPath}/static/error/404/images/yun1.png" alt="" class="yun yun1" />
    <img src="${staticPath}/static/error/404/images/yun2.png" alt="" class="yun yun2" />
    <img src="${staticPath}/static/error/404/images/bird.png" alt="" class="bird" />
    <img src="${staticPath}/static/error/404/images/san.png" alt="" class="san" />
    <div class="tipInfo">
        <div class="in">
            <div class="textThis">
                <h2>出错啦404！</h2>
                <p><span>页面自动<a id="href" href="">跳转登录页面</a></span><span>等待<b id="wait">6</b>秒</span></p>
                <script type="text/javascript">                            (function() {
                    var wait = document.getElementById('wait'), href = document.getElementById('href').href;
                    var interval = setInterval(function() {
                        var time = --wait.innerHTML;
                        if (time <= 0) {

                            clearInterval(interval);
                            location.href = "${path}/home/login";
                        }
                        ;
                    }, 1000);
                })();
                </script>
            </div>
        </div>
    </div>
</div>

</body>
</html>
