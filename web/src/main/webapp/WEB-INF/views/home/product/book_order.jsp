<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/commons/global.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=0.95, user-scalable=no" />
    <title>黄山醉温泉</title>
    <link href="${staticPath}/static/home/css/base.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${staticPath}/static/home/css/order.css">
    <link rel="stylesheet" href="${staticPath}/static/home/css/jquery-ui.min.css">
    <script type="text/javascript" src="${staticPath}/static/home/js/jquery-1.11.3.js"></script>
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
<div class="banners">
    &nbsp;
</div>
<!--BIG PIC-->


<!--CONTENT-->
<div class="content">
    <div class="mar1000 clearfix">
        <div class="path"><label>酒店预订</label><span>当前位置：首页 &gt; 酒店预订</span></div>


        <!--流程-->
        <div class="list_title">
            <ul>
                <li><label class="hott">在线预订</label></li>
            </ul>
            <div class="clears"></div>
        </div>


        <div class="lefts">
            <div class="tab_bs">
                <b>1</b>
                <h3>房间图片预览</h3>
                <ul>
                    <a href="#"><img src="${roomType.photo }" width="220"/></a>
                </ul>
            </div>

            <div class="tab_bs">
                <b>2</b>
                <h3>房间信息</h3>
                <ul>
                    <li><tt>${roomType.name }</tt></li>
                    <li>预定数：<tt>${roomType.bookNum }</tt></li>
                    <li>客房价格：<tt>¥ ${roomType.price }</tt></li>
                    <li>床位数：<tt>${roomType.bedNum }</tt></li>
                    <li>可住：<tt>${roomType.liveNum }</tt>人</li>
                    <li>其他：<tt>${roomType.remark }</tt></li>
                </ul>
            </div>
        </div>
        <div class="rights">

            <div class="path_l">填写入住人信息<tt>(以下信息为必填)</tt></div>
            <!--客房预订-->
            <div class="home_list">

                <!--单元-->
                <div class="gr_list">
                    <ul>
                        <li><label class="tname">入住人姓名：</label><label class="ta"><input type="text" name="name" id="name" value="${account.name}" class="inputs1" /></label></li>
                        <li><label class="tname">身份证号码：</label><label class="ta"><input type="text" name="idCard" id="idCard" value="${account.idCard}" class="inputs1" /><span class="msg"></span></label></li>
                        <li><label class="tname">手机号码：</label><label class="ta"><input type="text"  maxlength="11" name="mobile" id="mobile" value="${account.mobile}" class="inputs1" /><span class="msg"></span></label></li>
                        <li><label  class="tname">入住时间：</label><label class="ta"><input type="text" name="arriveDate" id="arriveDate"  class="datepicker inputs1"/></label></li>
                        <li><label class="tname">离店时间：</label><label class="ta"><input type="text" name="leaveDate" id="leaveDate"  class="datepicker inputs1"/>
                        </label></li>
                        <li><label class="tname">留言：</label><label class="ta">
                            <textarea id="remark" name="remark" style="width:200px;"></textarea>
                        </label></li>
                        <li><label class="tname">&nbsp;</label><label class="ta"><div class="gr_btn"><a id="btn_booking"  class="next">提交订单</a></div></label></li>
                    </ul>
                    <div class="msg">
                        预定须知:请携带好本人的身份证，办理入住手续，本店办理入住需要在前台缴费押金 ￥500
                    </div>
                    <div style="clear:left;"></div>
                </div>


                <div class="clears"></div>
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
<script src="${staticPath}/static/home/js/jquery-1.11.3.js"></script>
<script src="${staticPath}/static/home/js/jquery-ui.min.js"></script>
</body>
<script>
    $(function() {
        $(".datepicker").datepicker({"dateFormat":"yy-mm-dd"});
        $("#btn_booking").click(function(){
            var arriveDate = $("#arriveDate").val();
            var leaveDate = $("#leaveDate").val();
            if(arriveDate == '' || leaveDate == ''){
                alert('请选择时间!');
                return;
            }
            var name = $("#name").val();
            if(name == ''){
                $("#name").next("span.msg").text('请填写入住人!');
                return;
            }
            $("#name").next("span.msg").text('');
            var mobile = $("#mobile").val();
            if(mobile == ''){
                $("#mobile").next("span.msg").text('请填写手机号!');
                return;
            }
            $("#mobile").next("span.msg").text('');
            var idCard = $("#idCard").val();
            if(idCard == ''){
                $("#idCard").next("span.msg").text('请填写身份证号!');
                return;
            }
            $("#idCard").next("span.msg").text('');
            var remark = $("#remark").val();
            $.ajax({
                url:'${staticPath}/home//account/book_order',
                type:'post',
                dataType:'json',
                data:{roomTypeId:'${roomType.id }',name:name,mobile:mobile,idCard:idCard,remark:remark,arriveDate:arriveDate,leaveDate:leaveDate},
                success:function(data){
                    if(data.type == 'success'){
                        $(".malog").show();
                        setTimeout(function(){
                            window.location.href = '${staticPath}/home/index';
                        },1000)
                    }else{
                        alert(data.msg);
                    }
                }
            });
        })
    });
</script>
</html>