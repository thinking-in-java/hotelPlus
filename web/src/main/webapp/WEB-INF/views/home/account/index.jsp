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
    <style>

        .btn-success {
            color: #fff;
            background-color: #5cb85c;
            border-color: #4cae4c;
        }

        .btn {
            display: inline-block;
            padding: 6px 12px;
            margin-bottom: 0;
            font-size: 14px;
            font-weight: 400;
            line-height: 1.42857143;
            text-align: center;
            white-space: nowrap;
            vertical-align: middle;
            -ms-touch-action: manipulation;
            touch-action: manipulation;
            cursor: pointer;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
            background-image: none;
            border: 1px solid transparent;
            border-radius: 4px;
        }
    </style>





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
<div class="clears"></div>


<!--导航-->
<!--会员中心-->
<div class="member_center_wrap">
    <div class="member_center">
        <div class="member_center_l">
            <div class="body_center_main_tree">


                <ul>
                    <li><dl><dt><a href="${staticPath}/home/account/index"  class="cur">会员信息</a></dt>
                    </dl>
                    </li>
                    <li><dl><dt><a href="${staticPath}/home/account/acc_order" >订单管理</a></dt>
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
                    <span>完善个人信息</span>
                </div>
                <div class="tishi">
                    <div class="tishi_m">
                        带有 <font color="red">*</font> 的为必填项。首次登录请<font color="red">务必</font>完善个人信息!
                    </div>
                </div>

                <div class="form_list">
                    <form id="update-info-form">
                        <ul>
                            <li class="clearfix">
<span class="s1">账号:
</span>
                                <span class="s2">${sessionScope.get("account").name}
</span>
                            </li>

                            <li class="clearfix">
                                <span class="s1">真实姓名:</span>
                                <span class="s2"><input type="text" class="valid_ipt" value="${account.realName}" name="realName"  emptytext="请填写真实姓名"/>
</span>
                                <span class="s3"><i class="red">*</i> </span>
                            </li>
                            <li class="clearfix">
                                <span class="s1">身份证号:</span>
                                <span class="s2"><input type="text" class="valid_ipt"  value="${account.idCard}" name="idCard" emptytext="请填写身份证号" length="4-20" />
</span>
                                <span class="s3"><i class="red">*</i> </span>
                            </li>

                            <li class="clearfix">
                                <span class="s1">联系手机:</span>
                                <span class="s2"><input type="text" class="normal_ipt"  value="${account.mobile}" name="mobile"  emptytext="请填写手机号" length="6-11"/>
</span>
                                <span class="s3"><i class="red">*</i> </span>
                            </li>
                            <li class="clearfix">
                                <span class="s1">地址:</span>
                                <span class="s2"><input type="text" class="normal_ipt"  value="${account.address}" name="address" emptytext="请填写联系地址"/>

</span>
                                <span class="s3"><i class="red">*</i></span>
                            </li>
                        </ul>
                    </form>

                    <div class="btn_area">
                        <input type="button" id="update-info-btn" class="btn btn-success" style="width:100px;" value="提交" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!---尾部-->
<div class="footer">
    <div class="link">
        <div class="copyright">地址：四川省成都市郫都区四川托普信息职业技术学院旁边的小山沟&nbsp;&nbsp;&nbsp;&nbsp;邮编:666666<br/>
            电话：6666-66666&nbsp;&nbsp;666666&nbsp;&nbsp;&nbsp;&nbsp;免费预订电话：666-6666-666<br/>
            Copyright &copy; 2020-2021  Mr &middot; Yi Quan&nbsp;&nbsp;All rights reserved.<a href="http://www.beian.miit.gov.cn" target="_blank" style="text-decoration:none;color:#666">蜀ICP备20005824号-1</a>
        </div>
    </div>

</div>

<script>

    //修改个人信息
    $("#update-info-btn").click(function(){
        $.ajax({
            url:'${staticPath}/home/account/update_info',
            type:'post',
            dataType:'json',
            data:$("#update-info-form").serialize(),
            success:function(data){
                alert(data.msg);
                window.location.href="${staticPath}/home/account/index"
            }
        });
    });
</script>

</body>
</html>
