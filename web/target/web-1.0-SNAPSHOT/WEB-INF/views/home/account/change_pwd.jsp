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



<!--会员中心-->
<div class="member_center_wrap">
    <div class="member_center clearfix">
        <div class="member_center_l">
            <div class="body_center_main_tree">
                <ul>
                    <li><dl><dt><a href="${staticPath}/home/account/index" >会员信息</a></dt>
                    </dl>
                    </li>
                    <li><dl><dt><a href="${staticPath}/home/account/acc_order" >订单管理</a></dt>
                    </dl>
                    </li>



                    <li><dl><dt><a href="${staticPath}/home/account/updatePwd"  class="cur">修改密码</a></dt></dl>
                    </li>

                </ul>


            </div>
        </div>
        <div class="member_center_r">
            <div class="center_main1">
                <div class="center_main_tab1">
                    <span>修改密码</span>
                </div>
                <div class="tishi">
                    <div class="tishi_m">
                        带有 <font color="red">*</font> 的为必填项。QQ快速登录的账号初始化密码:<font color="red">123456</font>
                    </div>
                </div>

                <div class="form_list">
                    <ul>
                        <li class="clearfix">
                            <span class="s1">现在的密码:</span>
                            <span class="s2"><input id="old-password" type="password" class="valid_ipt" focustext="密码为6-16位字符的数字、英文字母或者符号(不能有空格！)" errortext="密码格式不正确" emptytext="请填写密码" regexp="all" length="6-16" />
</span>
                        </li>
                        <li class="clearfix">
                            <span class="s1">新的密码:</span>
                            <span class="s2"><input id="new-password" type="password" class="valid_ipt" focustext="密码为6-16位字符的数字、英文字母或者符号(不能有空格！)" errortext="密码格式不正确" emptytext="请填写密码" regexp="all" length="6-16" />
</span>
                        </li>
                        <li class="clearfix">
                            <span class="s1">确认新的密码:</span>
                            <span class="s2"><input id="renew-password" type="password" class="valid_ipt" focustext="这里要重复输入一下你的密码" errortext="请先输入正确的密码" emptytext="请再次填写密码" regexp="all" length="6-16" other_valifun="ensure_pass(this.id)" other_valifuntext="两次输入密码不一致" />
</span>
                        </li>
                        <li class="clearfix">
                            <span class="s1">验证码:</span>
                            <span class="s2 clearfix"><input type="text" id="validateCode" name="code"  class="valid_ipt ipt_short"  /><div class="yzm_store"><img src="${path}/imageCode/showCode" alt="点击切换" id="imgCode" onclick="refreshImg()"  width="57" height="26" /></div></span>
                        </li>
                    </ul>

                    <div class="btn_area"><button type="button" class="btn btn-success" id="update-password-btn" style="width:100px;">提交</button></div>
                </div>

                <script type="text/javascript">
                    //修改密码
                    $("#update-password-btn").click(function(){
                        var oldPassword = $("#old-password").val();
                        var newPassword = $("#new-password").val();
                        var renewPassword = $("#renew-password").val();
                        var validateCode = $("#validateCode").val();
                        if(oldPassword == ''){
                            alert('请填写原密码！');
                            return;
                        }
                        if(newPassword == ''){
                            alert('请填写新密码！');
                            return;
                        }
                        if(newPassword != renewPassword){
                            alert('两次密码不一致！');
                            return;
                        }

                        if(validateCode == ''){
                            alert('请填写验证码！');
                            return;
                        }
                        $.ajax({
                            url:'${staticPath}/home/account/update_pwd',
                            type:'post',
                            dataType:'json',
                            data:{oldPassword:oldPassword,newPassword:newPassword,code:validateCode},
                            success:function(data){
                                alert(data.msg)
                                window.location.href="${staticPath}/home/account/index"
                            }
                        });
                    });

                    // 刷新验证码
                    function  refreshImg() {
                        document.getElementById("imgCode").src = "${path}/imageCode/showCode?timestamp="+(new Date()).getTime();
                    }

                </script>
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
</body>
</html>
