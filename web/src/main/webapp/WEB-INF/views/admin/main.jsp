<%--
  Created by IntelliJ IDEA.
  User: Mr.YiQuan
  Date: 2020-06-08
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>后台首页</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" type="text/css" href="${path}/static/admin/layui/css/layui.css">
    <link rel="stylesheet" href="${path}/static/admin/css/main.css">
    <script type="text/javascript" src="${staticPath }/static/admin/js/jquery-3.2.1.min.js" charset="utf-8"></script>
</head>
<body>
<div class="layui-fluid">
    <div class="larry-container animated fadeIn">
        <div style="margin-top: 30px;margin-bottom: 30px" >
            欢迎您： <span class="x-red"><shiro:principal property="name"></shiro:principal></span> <span id="sex"></span>
        </div>
        <fieldset class="layui-elem-field">
            <legend>源码下载(<span style="color:red;font-size: 14px;font-weight: bold">点击相应位置即可跳转</span>)</legend>
            <div class="layui-field-box">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body">
                            <div class="layui-carousel x-admin-carousel x-admin-backlog"
                                 lay-anim="" lay-indicator="inside" lay-arrow="none"
                                 style="width: 100%; height: 90px;">
                                <div carousel-item="">
                                    <ul class="layui-row layui-col-space10 layui-this">
                                        <li class="layui-col-xs2">
                                            <a target="_blank" href="https://gitee.com/thinking_in_java/hotel" class="x-admin-backlog-body">
                                                <h3>下载链接</h3>
                                                <p><cite>码云</cite></p>
                                            </a>
                                        </li>

                                        <li class="layui-col-xs2">
                                            <a target="_blank" href="https://github.com/thinking-in-java/hotelPlus.git" class="x-admin-backlog-body">
                                                <h3>下载链接</h3>
                                                <p><cite>gayHub</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs2">
                                            <a target="_blank" href="https://download.csdn.net/download/qq_36856983/12690286" class="x-admin-backlog-body">
                                                <h3>下载链接</h3>
                                                <p><cite>CSDN</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>下载链接</h3>
                                                <p><cite>...</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>上次登陆时间</h3>
                                                <p><cite style="font-size: 17px">
                                                    <shiro:principal property="lastlogintime"></shiro:principal>
                                                </cite></p>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
        <fieldset class="layui-elem-field">
            <legend>Info</legend>
            <div class="layui-field-box changHeight">
                <table class="layui-table" lay-skin="line">
                    &nbsp;&nbsp;&nbsp;
                    <span style="line-height: 38px;font-size: 16px;color: black;font-weight: bold">
                       QQ：<span style="font-size: 20px;color: red;font-weight: bold">24092024</span>
                    </span>
                    &nbsp;&nbsp;&nbsp;
                    <span style="line-height: 38px;font-size: 16px;color: black;font-weight: bold">
                       Email：<span style="font-size: 20px;color: red;font-weight: bold">thinking_in_java@qq.com</span>
                    </span>
                </table>
            </div>
        </fieldset>

        <!-- 系统信息 -->
        <fieldset class="layui-elem-field">
            <legend>使用技术</legend>
            <div class="layui-elem-quote layui-quote-nm">
                <ul class="layui-timeline">
                    <li class="layui-timeline-item">
                        <i class="layui-icon layui-timeline-axis" style="color: rgb(47,143,250);">&#xe756;</i>
                        <div class="layui-timeline-content layui-text">
                            <div class="layui-timeline-title">
                                <h3 class="layui-inline">V2.0.5正式版发布</h3>
                                <span class="layui-badge-rim">2020-08-7</span>
                            </div>
                            <ul>
                                <li>2020-07-17&nbsp;v1.0.0版:Mybatis+Spring+SpringMvc+EasyUi版本基于maven拆分聚合后台脚手架发布.</li>
                                <li>2020-07-24&nbsp;v1.1.1版:使用v1.0.0脚手架快速开发了酒店管理系统后台CRUD功能.</li>
                                <li>2020-07-25&nbsp;v1.2.1版:使用html+css+jquery+ajax开发酒店系统前台页面.</li>
                                <li>2020-07-26&nbsp;v1.3.1版:整合第三方支付宝沙箱,上线支付功能.</li>
                                <li>2020-07-27&nbsp;v1.3.1版:整合第三方短信验证码模块,注册验证模块</li>
                                <li>2020-07-28&nbsp;v1.5.1版:优化前台登录注册校验模块,优化后台表单校验</li>
                                <li>2020-07-28&nbsp;v1.6.6版:前台:注册,登录,酒店查询,订单,订单支付,用户信息修改,密码修改.后台:管理员,系统,楼层,房型,房间,客户,预定,入住,统计分析模块</li>
                                <br>
                                <li style="color: red">V2.0.0版大改革:Mybatis Plus+Spring+SpringMVC+PageHelper+Shiro+EasyUi+LayUi版基于Maven拆分聚合后台脚手架发布</li>
                                <li>2020-07-30&nbsp;v2.0.1版:整体项目修改持久层框架,删除繁琐的mapper文件,简化开发</li>
                                <li>2020-07-31&nbsp;v2.0.2版:使用shiro进行权限控制,MD5密码加密,角色控制</li>
                                <br>
                                <li>2020-08-01&nbsp;v2.0.3版:优化前端用户登录页面,优化后端页面与后台数据的双重权限控制</li>
                                <li>2020-08-03&nbsp;v2.0.4版:加入日志切面类,进行用户日志操作记录</li>
                                <li>2020-08-03&nbsp;v2.0.5版:优化部分权限不足异常页面,优化错误页面提示,正式版发布</li>
                                <li></li>
                                <br>
                                <li>2020-08-07&nbsp;v3.0.0版:新增QQ快捷登录失败,待解决session失效问题</li>
                                <li>2020-08-07&nbsp;<font color="red">12:39</font>&nbsp;v3.0.1版:新增QQ快捷登录,造成session丢失原因是shiro配置代码问题,<font color="red"> 已追踪修复</font></li>
                            </ul>
                        </div>
                    </li>
                    <li class="layui-timeline-item">
                        <i class="layui-icon layui-timeline-axis" style="color: rgb(47,143,250);">&#xe63f;</i>
                        <div class="layui-timeline-content layui-text">
                            <div class="layui-timeline-title">
                                <h3 class="layui-inline">酒店管理管理系统(End)</h3>
                                <span class="layui-badge-rim">2020-08-07</span>
                            </div>
                        </div>
                    </li>


                </ul>
            </div>
        </fieldset>
    </div>

    <!-- 开发团队 -->
    <fieldset class="layui-elem-field">
        <legend>开发团队</legend>
        <div class="layui-field-box">
            <table class="layui-table">
                <tbody>
                <tr>
                    <th>版权所有</th>
                    <td>Mr.Yi Quan</td>
                </tr>
                <tr>
                    <th>开发者</th>
                    <td>Mr.Yi Quan</td>
                </tr>
                </tbody>
            </table>
        </div>
    </fieldset>
</div>
</body>
<script>
    $(function () {
        $("#sex").text(sex());
        function sex() {
            switch (${sessionScope.sex}){
                case 0:{
                    return "先生";
                }
                case 1:{
                    return "女士";
                }
            }
        }
    });
</script>
</html>
