<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/commons/global.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=0.95, user-scalable=no" />
    <title>黄山醉温泉 - 会员中心</title>
    <meta name="description" content="说明" />
    <meta name="keywords" content="关键字" />
    <link rel="stylesheet" href="${staticPath}/static/home/css/user.css" type="text/css" />
    <script type="text/javascript" src="${staticPath}/static/home/js/jquery-1.8.2.min.js"></script>


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
<!--头-->
<div class="top">
    <div class="header clearfix">
        <div class="header_l">
            <a href="${staticPath}/home/index"><img src="${staticPath}/static/home/images/userlogo.png" /></a>
            <h2>会员中心</h2>
        </div>

        <div class="header_r">
            <div class="img_link" id="login_after">

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
        </div>

        <div class="clears"></div>
    </div>
</div>
<!--头-->

</div>
</div>


<!--会员中心-->
<div class="member_center_wrap">
    <div class="member_center clearfix">
        <div class="member_center_l">
            <div class="body_center_main_tree">
                <ul>
                    <li><dl><dt><a href="${staticPath}/home/account/index"  >会员信息</a></dt>
                    </dl>
                    </li>
                    <li><dl><dt><a href="${staticPath}/home/account/acc_order" class="cur">订单管理</a></dt>
                    </dl>
                    </li>



                    <li><dl><dt><a href="${staticPath}/home/account/updatePwd">修改密码</a></dt></dl>
                    </li>

                </ul>
            </div>
        </div>
        <div class="member_center_r">
            <div class="center_main1">
                <div class="center_main_tab1">
                    <span>订单信息管理</span>
                </div>

                <div class="center_main_table">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="m9">
                        <tr class="tr1">
                            <td align="left">房型图片</td>
                            <td width="10%" align="center">房型</td>
                            <td width="10%" align="center">账号</td>
                            <td width="12%" align="center">手机号</td>
                            <td width="12%" align="center">身份证号</td>
                            <td width="16%" align="center">状态</td>
                            <td width="12%" align="center">下单时间</td>
                            <td width="16%" align="center">备注</td>
                            <td width="16%" align="center">支付状态</td>
                        </tr>
                        <c:forEach items="${bookOrderList }" var="bookOrder">
                            <tr onmouseover="this.bgColor='#f2fbff'" onmouseout="this.bgColor='#ffffff'">
                                <c:forEach items="${roomTypeList }" var="roomType">
                                    <c:if test="${roomType.id == bookOrder.roomTypeId }">
                                        <c:set var="orderPrices" value="${roomType.price}"/>
                                        <c:set var="roomTypeName" value="${roomType.name}"/>
                                        <td align="left"><div class="ipl">
                                            <img src="${roomType.photo }" width="90" height="90" border="0" /></div>
                                        </td>
                                        <td width="9%" align="center" valign="top">${roomType.name }</td>
                                    </c:if>
                                </c:forEach>
                                <td width="9%" align="center" valign="top"><span class="orange1"><strong>${bookOrder.name }</strong></span></td>
                                <td width="13%" align="center" valign="top">
                                        ${bookOrder.mobile }</td>
                                <td width="6%" align="center" valign="top">${bookOrder.idCard }</td>
                                <td width="16%" align="center" valign="top">
                                    <c:if test="${bookOrder.status == 0 }">
                                        <font color="red">预定中</font>
                                    </c:if>
                                    <c:if test="${bookOrder.status == 1 }">
                                        已入住
                                    </c:if>
                                    <c:if test="${bookOrder.status == 2 }">
                                        已结算离店
                                    </c:if>
                                </td>
                                <td width="16%" align="center" valign="top">
                                    <fmt:formatDate value="${bookOrder.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
                                </td>
                                <td width="16%" align="center" valign="top">
                                        ${bookOrder.remark }
                                </td>
                                <td width="16%" align="center" valign="top">
                                    <c:if test="${bookOrder.payStatus == 0 }">
                                       &nbsp;<a href="${staticPath}/orderPay/index?book_orderId=${bookOrder.id}&book_orderName=${roomTypeName }&order_Prices=${orderPrices}" style="text-decoration: none;color: #dc322f">待支付</a>
                                    </c:if>
                                    <c:if test="${bookOrder.payStatus == 1 }">
                                        已支付
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>


                    </table>
                </div>


            </div>
        </div>
    </div>
</div>


<!---尾部-->
<div class="footer">
    <div class="link">
        <div class="copyright"> 地址：四川省成都市郫都区四川托普信息职业技术学院旁边的小山沟&nbsp;&nbsp;&nbsp;&nbsp;邮编:666666<br/>
            电话：6666-66666&nbsp;&nbsp;666666&nbsp;&nbsp;&nbsp;&nbsp;免费预订电话：666-6666-666<br/>
            Copyright &copy; 2020-2021  Mr &middot; Yi Quan&nbsp;&nbsp;All rights reserved.<a href="http://www.beian.miit.gov.cn" target="_blank" style="text-decoration:none;color:#666">蜀ICP备20005824号-1</a>
        </div>
    </div>

</div>
</body>
</html>
