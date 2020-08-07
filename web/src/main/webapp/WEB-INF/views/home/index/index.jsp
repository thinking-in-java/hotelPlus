<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/commons/global.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=0.95, user-scalable=no" />
    <title>Mr.Yi Quan酒店</title>
    <link href="${staticPath}/static/home/css/base.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${staticPath}/static/home/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${staticPath}/static/home/js/base.js"></script>
    <!--[if IE 6]><script type="text/javascript" src="${staticPath}/static/home/js/DD_belatedPNG.js"></script>
<script type="text/javascript">
    window.onload = function(){
        DD_belatedPNG.fix("img, div, a");
    }
</script>
    <![endif]-->
    <!--[if lt IE 9]>
    <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
                <%--看板娘--%>
    <style>
        #github svg {
            transition: all 1s;
            fill: #222;
            color: #fff;
            position: absolute;
            top: 0;
            right: 0;
            border: 0;
            width: 80px;
            height: 80px;
        }
        #github:hover svg {
            width: 160px;
            height: 160px;
        }
    </style>
    <script src="${staticPath}/static/home/2D/js/autoload.js"></script>
</head>

<body>
<!--HEADER-->
<div class="header">
    <div class="mar1000 clearfix">
        <div class="logo"></div>
        <div class="header_right">
            <div class="homeinfo">
                <label><span>免费预订电话：<strong>666-666-666</strong></span>&nbsp;&nbsp;&nbsp;&nbsp;|</label>
                <<label><c:if test="${account == null }">
                <a href="${staticPath}/home/login">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                <a href="${staticPath}/home/reg">注册</a>&nbsp;
            </c:if>
                <c:if test="${account != null }">
                    <font color="red">欢迎您：${account.name }&nbsp;&nbsp;|&nbsp;&nbsp;</font>
                    <a href="${staticPath}/home/account/index">用户中心</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                    <a href="${staticPath}/home/logout">注销登录</a>&nbsp;
                </c:if>&nbsp;|</label>
                <label><a href="">设为首页</a></label>
            </div>
            <div class="nav">
                <a href="${staticPath}/home/index" class="hover">首  页</a>
                <a href="">温泉预定</a>
                <a href="${staticPath}/home/order">酒店预定</a>
                <a href="">醉悦旅居</a>
                <a href="">养生餐饮</a>
                <a href="">会议中心</a>
                <a href="">促销活动</a>
                <a href="">企业文化</a>
            </div>
        </div>
    </div>
</div>
<!--HEADER-->


<!--BIG PIC-->
<div class="banner">
    <div class="slider">
        <div class="flexslider">
            <ul class="slides">
                <li><div class="img bg1">&nbsp;</div></li>
                <li><div class="img bg2">&nbsp;</div></li>
                <li><div class="img bg3">&nbsp;</div></li>
            </ul>
        </div>
    </div>
    <div class="clears"></div>
    <script defer src="${staticPath}/static/home/js/slider.js"></script>
    <script type="text/javascript">
        $(function(){
            $('.flexslider').flexslider({
                animation: "slide",
                start: function(slider){
                    $('body').removeClass('loading');
                }
            });
        });
    </script>
</div>
<!--BIG PIC-->


<!--CONTENT-->
<div class="content">
    <div class="mar1000 clearfix">




        <!--优惠信息-->
        <div class="discount">
            <div class="d_title title01"><a href="">更多优惠</a></div>
            <div class="discount-list clearfix">
                <ul>
                    <li class="nor">
                        <div class="discount-itembg h300"></div>
                        <div class="discount-item">
                            <a href=""><img src="${staticPath}/static/home/images/4.jpg" alt=""/></a>
                            <h3><a href="">SPA会所圣诞价验五折</a></h3>
                            <p>为尊贵宾客打造的私人温泉会所。娱乐设施完备，KTV包房。专用电梯、独立的男女更衣室、沐浴间、桑拿房</p>
                        </div>
                    </li>
                    <li>
                        <div class="discount-itembg h300"></div>
                        <div class="discount-item">
                            <a href=""><img src="${staticPath}/static/home/images/1.jpg" alt=""/></a>
                            <h3><a href="">SPA会所圣诞价验五折</a></h3>
                            <p>为尊贵宾客打造的私人温泉会所。娱乐设施完备，KTV包房。专用电梯、独立的男女更衣室、沐浴间、桑拿房</p>
                        </div>
                    </li>
                    <li>
                        <div class="discount-itembg h300"></div>
                        <div class="discount-item">
                            <a href=""><img src="${staticPath}/static/home/images/2.jpg" alt=""/></a>
                            <h3><a href="">SPA会所圣诞价验五折</a></h3>
                            <p>为尊贵宾客打造的私人温泉会所。娱乐设施完备，KTV包房。专用电梯、独立的男女更衣室、沐浴间、桑拿房</p>
                        </div>
                    </li>
                    <li>
                        <div class="discount-itembg h300"></div>
                        <div class="discount-item">
                            <a href=""><img src="${staticPath}/static/home/images/3.jpg" alt=""/></a>
                            <h3><a href="">SPA会所圣诞价验五折</a></h3>
                            <p>为尊贵宾客打造的私人温泉会所。娱乐设施完备，KTV包房。专用电梯、独立的男女更衣室、沐浴间、桑拿房</p>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="clears"></div>
        </div>


        <!--视频及新闻-->
        <div class="v-news clearfix">

            <div class="news right">
                <div class="d_title title03"><a href="">更多</a></div>
                <div class="news-lay">
                    <div class="newsbg"></div>

                    <div class="news-item">
                        <img src="${staticPath}/static/home/images/v2.jpg" />
                        <div class="hot_news">
                            <h2><a href="">醉温泉全国旅游景区评定国家4A景区</a></h2>
                            <p>醉温泉2010年被全国旅游景区质量等级评定委员会列入国家4A景区，占地面积约650亩，总投资额约为10亿。其构成包括：醉温泉一期（华东地区最具特色...<a href="">[点击详细]</a></p>
                        </div>
                        <ul>
                            <li><span>20/07/30</span><a href="">聚金榜题名之状元、食府之美味 题名之状元、食府之美味</a></li>
                            <li><span>20/07/30</span><a href="">大酒店三八节营销活动方案 题名之状元、食府之美味</a></li>
                            <li><span>20/07/30</span><a href="">聚金榜题名之状元、食府之美味 题名之状元、食府之美味</a></li>
                            <li><span>20/07/30</span><a href="">大酒店三八节营销活动方案 题名之状元、食府之美味</a></li>
                            <li><span>20/07/30</span><a href="">聚金榜题名之状元、食府之美味 题名之状元、食府之美味</a></li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="video left">
                <div class="d_title title02"></div>
                <div class="video-lay">
                    <div class="videobg"></div>
                    <div class="video-item">
                        <img src="${staticPath}/static/home/images/v1.jpg" alt=""/>
                    </div>
                </div>
            </div>
        </div>
        <div class="clears"></div>


        <div class="H50"></div>




        <!--介绍1-->
        <div class="phone clearfix">
            <!--醉左-->
            <div class="pic_title01 pict01"></div>
            <div class="phones">
                <div class="pic01"></div>
                <div class="smbg"></div>
                <div class="picfont">
                    <a href=""></a>
                    <p>酒店餐厅设在大堂二层，能同时容纳400人，自助餐可同时容纳520人。设有豪华宴会厅，可提供中西自助餐、西餐、东南亚风味饮食及地方风味菜肴等，是宾客举行高档宴请及商务会客的理想场所，一层的樽月廊环境优雅，其轻松惬意的氛围，时尚精致的鸡尾酒与西式小点，是中、西风格结合的典范。</p>
                </div>
            </div>
        </div>



        <!--介绍2-->
        <div class="phone clearfix">
            <!--醉左-->
            <div class="pic_title02 pict02"></div>
            <div class="phones">
                <div class="pic02"></div>
                <div class="smbg"></div>
                <div class="picfont">
                    <a href=""></a>
                    <p>酒店餐厅设在大堂二层，能同时容纳400人，自助餐可同时容纳520人。设有豪华宴会厅，可提供中西自助餐、西餐、东南亚风味饮食及地方风味菜肴等，是宾客举行高档宴请及商务会客的理想场所，一层的樽月廊环境优雅，其轻松惬意的氛围，时尚精致的鸡尾酒与西式小点，是中、西风格结合的典范。</p>
                </div>
            </div>
        </div>



        <!--介绍3-->
        <div class="phone clearfix">
            <!--醉左-->
            <div class="pic_title01 pict03"></div>
            <div class="phones">
                <div class="pic03"></div>
                <div class="smbg"></div>
                <div class="picfont">
                    <a href=""></a>
                    <p>酒店餐厅设在大堂二层，能同时容纳400人，自助餐可同时容纳520人。设有豪华宴会厅，可提供中西自助餐、西餐、东南亚风味饮食及地方风味菜肴等，是宾客举行高档宴请及商务会客的理想场所，一层的樽月廊环境优雅，其轻松惬意的氛围，时尚精致的鸡尾酒与西式小点，是中、西风格结合的典范。</p>
                </div>
            </div>
        </div>



        <!--介绍4-->
        <div class="phone clearfix">
            <!--醉左-->
            <div class="pic_title02 pict04"></div>
            <div class="phones">
                <div class="pic04"></div>
                <div class="smbg"></div>
                <div class="picfont">
                    <a href=""></a>
                    <p>酒店餐厅设在大堂二层，能同时容纳400人，自助餐可同时容纳520人。设有豪华宴会厅，可提供中西自助餐、西餐、东南亚风味饮食及地方风味菜肴等，是宾客举行高档宴请及商务会客的理想场所，一层的樽月廊环境优雅，其轻松惬意的氛围，时尚精致的鸡尾酒与西式小点，是中、西风格结合的典范。</p>
                </div>
            </div>
        </div>



    </div>


</div>
<!--CONTENT-->


<!--FOOTER-->
<div class="bottom">
    <a href="">网站首页</a>|
    <a href="">温泉预定</a>|
    <a href="">酒店预定</a>|
    <a href="">醉悦旅居</a>|
    <a href="">养生餐饮</a>|
    <a href="">会议中心</a>|
    <a href="">促销活动</a>|
    <a href="">企业文化</a>
</div>
<div class="footer">
    地址：四川省成都市郫都区四川托普信息职业技术学院旁边的小山沟&nbsp;&nbsp;&nbsp;&nbsp;邮编:666666<br/>
    电话：6666-66666&nbsp;&nbsp;666666&nbsp;&nbsp;&nbsp;&nbsp;免费预订电话：666-6666-666<br/>
    Copyright &copy; 2020-2021  Mr &middot; Yi Quan&nbsp;&nbsp;All rights reserved.<a href="http://www.beian.miit.gov.cn" target="_blank" style="text-decoration:none;color:#666">蜀ICP备20005824号-1</a>
</div>
<!--FOOTER-->



<!--侧边栏-->
<div id="slide_bar">
    <div class="otherico">
        <a href="" class="icon01"></a>
        <a href="" class="icon02"></a>
        <a href="" class="icon03"></a>
        <a href="" class="icon04"></a>
        <a href="" class="icon05"></a>
        <a href="" class="icon06"></a>
    </div>
</div>
<script type="text/javascript">
    pos();
</script>

</body>
</html>