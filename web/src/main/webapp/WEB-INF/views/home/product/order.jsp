<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/commons/global.jsp" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="Author" content="Mr.YiQuan">
  <meta name="Keywords" content="Mr.YiQuan酒店管理系统">
  <meta name="Description" content="Mr.YiQuan酒店管理系统">
    <%--bootstrap--%>
    <link href="${staticPath}/static/home/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="${staticPath}/static/home/bootstrap/js/bootstrap.min.js"></script>

    <link href="${staticPath}/static/home/css/reservation.css" type="text/css" rel="Stylesheet"/>
  <link href="${staticPath}/static/home/css/index.css" type="text/css" rel="Stylesheet"/>
    <link href="${staticPath}/static/home/css/base.css" rel="stylesheet" type="text/css">
    <script src="${staticPath}/static/home/js/jquery-1.11.3.js"></script>

<title>Mr.YiQuan|酒店管理系统首页</title>
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
                <label><c:if test="${account == null }">
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
                <a href="${staticPath}/home/index" >首  页</a>
                <a href="">温泉预定</a>
                <a href="${staticPath}/home/order" class="hover">酒店预定</a>
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
<div class="banners">
    &nbsp;
</div>
<!--BIG PIC-->


<!--CONTENT-->
<div class="content">
    <div class="mar1000 clearfix">


        <div class="lefts">
            <div class="tree">
                <label>促销活动</label>
                <a href="" class="hover">待开发</a>
                <a href="">待开发</a>
                <a href="">待开发</a>
            </div>
        </div>
        <div class="rights">

            <div class="path path_nomar"><label>酒店预订</label><span>当前位置：首页 &gt; 酒店预订</span></div>

            <!--查询功能-->
            <div class="search"><input type="text" class="input01s" placeholder="关键字" value="${kw }" id="kw">&nbsp;&nbsp;<input type="button" value="搜索" class="btn01s" id="search-btn"></div>
            <!--查询功能-->

            <form style="display:none;" action="${staticPath}/home/order" method="get" id="search-form"><input type="hidden" name="name" id="search-name"></form>


            <!--客房预订-->
            <div class="home_list">


                <!--关于-->

                <!--客房-->
                <div id="guest_rooms">
                    <!--列表-->
                    <table id="pro_list" >
                        <thead>
                        <tr>
                            <th width="200px">客房</th>
                            <th>房型</th>
                            <th>可住人数</th>
                            <th>床位数</th>
                            <th>房价</th>
                            <th>房态</th>
                            <th>预订</th>
                        </tr>
                        </thead>
                        <tbody >
                        <c:forEach items="${pageInfo.list }" var="roomType">
                            <tr>
                                <td><a href="#"><img src="${roomType.photo }" alt=""></a>
                                </td>
                                <td align="center">
                                    <p>${roomType.name }</p>
                                    <p class="sub_txt">${roomType.remark }</p>
                                </td>
                                <td>${roomType.liveNum }</td>
                                <td>${roomType.bedNum }</td>
                                <td>${roomType.price }</td>
                                <td>
                                    <c:if test="${roomType.status == 0 }">
                                        已满房
                                    </c:if>
                                    <c:if test="${roomType.status == 1 }">
                                        可预订
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${roomType.status == 0 }">
                                        <input type="button" class="disable" value="满房" >
                                    </c:if>
                                    <c:if test="${roomType.status == 1 }">
                                        <input type="button" value="预订" onclick="window.location.href='${staticPath}/home/account/book_order?roomTypeId=${roomType.id }'" >
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <div id="pages"></div>
                    <!--  -列表菜单 -->
                    <div></div>
                </div>





            </div>


            <div class="page">




                <!--分页文字信息  -->
                <div class="col-md-6">当前 ${pageInfo.pageNum }页,总${pageInfo.pages }
                    页,总 ${pageInfo.total } 条记录</div>
                <!-- 分页条信息 -->
                <div class="col-md-6">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li><a href="${path }/home/order?pn=1">首页</a></li>
                            <c:if test="${pageInfo.hasPreviousPage }">
                                <li><a href="${path }/home/order?pn=${pageInfo.pageNum-1}"
                                       aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                                </a></li>
                            </c:if>


                            <c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
                                <c:if test="${page_Num == pageInfo.pageNum }">
                                    <li class="active"><a href="#">${page_Num }</a></li>
                                </c:if>
                                <c:if test="${page_Num != pageInfo.pageNum }">
                                    <li><a href="${path }/home/order?pn=${page_Num }">${page_Num }</a></li>
                                </c:if>

                            </c:forEach>
                            <c:if test="${pageInfo.hasNextPage }">
                                <li><a href="${path }/home/order?pn=${pageInfo.pageNum+1 }"
                                       aria-label="Next"> <span aria-hidden="true">&raquo;</span>
                                </a></li>
                            </c:if>
                            <li><a href="${path }/home/order?pn=${pageInfo.pages}">末页</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
            <!--结束-->



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
<script>
    $(document).ready(function(){
        $("#search-btn").click(function(){
            $("#search-name").val($("#kw").val());
            $("#search-form").submit();
        })
    });



</script>

</body>
</html>