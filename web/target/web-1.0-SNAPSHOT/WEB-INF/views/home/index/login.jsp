<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>登录</title>
	<script src="https://www.jq22.com/jquery/jquery-3.3.1.js"></script>
	<link rel="stylesheet" href="https://www.jq22.com/jquery/bootstrap-4.2.1.css">
	<script src="https://www.jq22.com/jquery/bootstrap-4.2.1.js"></script>
	<script src="${staticPath}/static/home/login/js/yz.js"></script>
	<script src="${staticPath}/static/home/login/js/modal_dialog.js"></script>
	<link rel="stylesheet" href="https://www.jq22.com/jquery/font-awesome.4.7.0.css">
	<link rel="stylesheet" type="text/css" href="${staticPath}/static/home/login/css/login.css" />
	<style>
		.icon{
			font-family: FontAwesome;
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

	<script src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
	<script type="text/javascript">
		// QQ登录
		var qqWindow;
		var wxWindow;
		function toQzoneLogin()
		{
			qqWindow = window.location.href = "${path}/api/qq/login";
		}

		function closeqqWindow()
		{
			qqWindow.close();
			window.location.reload()    //刷新页面
		}
		function toweChat() {
			wxWindow = window.location.href = "微信登录地址";
		}

		function closewxWindow()
		{
			wxWindow.close();
			window.location.reload()    //刷新页面
		}

	</script>

</head>

<body>
<div class="shadow mb-5 bg-light rounded login-top">
	<div class="head">
		<img src="${staticPath}/static/home/login/images/logo.png"><span>
                <h1>·</h1>登录
            </span>
	</div>
</div>
<div class="login-box">
	<div class="tree">
		<img src="${staticPath}/static/home/login/images/2.png" class="img-fluid">
	</div>
	<div class="slogan">
		克勤之致<br><span class="yellow">结青藤</span>之缘
	</div>
	<div id="earth" class="earth">
		<img src="${staticPath}/static/home/login/images/1.png" class="img-fluid">
	</div>
	<!-- 登录 -->
	<div class="login">
		<div class="login-logo">
			<img src="${staticPath}/static/home/login/images/logo.png" class="img-fluid">
		</div>
		<div class="login-other">
			<div onclick='toQzoneLogin()'><img src="${staticPath}/static/home/login/images/icon/QQ-line.png"></div>
			<div onclick='toweChat()'><img src="${staticPath}/static/home/login/images/icon/wx-line.png"></div>
			<div><img src="${staticPath}/static/home/login/images/icon/sina-line.png"></div>
		</div>
		<div class="hr"><span>或</span></div>
		<div class="login-form">
			<form method="POST">
				<code class="check_log_phone"></code>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
                            <span class="input-group-text">
                                <span class="fa fa-user-circle-o text-success"></span>
                            </span>
					</div>
					<input type="text" class="form-control" id="logphone" placeholder="手机号码">
				</div>
				<code class="check_log_pwd"></code>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
                            <span class="input-group-text">
                                <span class="fa fa-unlock text-success"></span>
                            </span>
					</div>
					<input type="password" class="form-control" id="logpwd" placeholder="密码">
				</div>
				<button type="button" id="login" class="btn btn-success btn-block">登录</button>
			</form>
		</div>
		<div class="login-regist">
			没有帐号？<span id="ToRegist">立即注册</span>
		</div>
	</div>
	<!-- 注册 -->
	<div class="regist">
		<div class="regist-title">
			手机注册<span><img src="${staticPath}/static/home/login/images/logo.png" height="40px"></span>
		</div>
		<div class="regist-form">
			<form method="POST" action="" id="account_info">
				<code class="check_reg_phone"></code>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
                            <span class="input-group-text">
                                <span class="fa fa-mobile text-success"></span>
                            </span>
					</div>
					<input type="text" class="form-control" name="name" id="regphone" placeholder="手机号码">
				</div>
				<code class="check_reg_pwd"></code>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
                            <span class="input-group-text">
                                <span class="fa fa-unlock-alt text-success"></span>
                            </span>
					</div>
					<input type="password" class="form-control" name="password" id="regpwd" placeholder="密码">
				</div>
				<code class="check_reg_pwd2"></code>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
                            <span class="input-group-text">
                                <span class="fa fa-unlock-alt text-success"></span>
                            </span>
					</div>
					<input type="password" class="form-control" id="regcheckpwd" placeholder="确认密码">
				</div>
				<code class="check_reg_code"></code>
				<div class="input-group mb-3">
					<input type="text" class="form-control" placeholder="手机验证码" id="code" name="validateCode">
					<div class="input-group-append">
						<button type="button" class="input-group-text text-success getcode">获取验证码</button>
					</div>
				</div>
				<button type="button" id="regist" class="btn btn-success btn-block">注册</button>
			</form>
		</div>
		<div class="otherLogin">
			<div class="qq">
				<img src="${staticPath}/static/home/login/images/icon/QQ.png" onclick='toQzoneLogin()' class="img-fluid">
			</div>
			<div class="wx">
				<img src="${staticPath}/static/home/login/images/icon/wx.png" onclick='toweChat()' class="img-fluid">
			</div>
		</div>
		<div class="login-login">
			<span id="otherLogin">快捷登录</span>
			<span id="phoneLogin">手机注册</span>
			<span id="ToLogin">返回登录</span>
		</div>
	</div>
</div>
<div class="login-footer">
	<div class="container">
		<div class="row">
			<div class="col text-center login-nav">
				<ul>
					<li><a href="${staticPath}/home/index">首页</a>
						<div class="border"></div>
					</li>
					<li><a href="#">关于我们</a>
						<div class="border"></div>
					</li>
					<li><a href="#">企业招聘</a>
						<div class="border"></div>
					</li>
					<li><a href="#">合作专区</a>
						<div class="border"></div>
					</li>
					<li><a href="${staticPath}/login">后台管理</a>
						<div class="border"></div>
					</li>

					<li><a href="#">意见反馈</a></li>
				</ul>
				<div class="foot">
					联系QQ：24092024&emsp;&emsp;邮箱：<a href="mailto:thinking_in_java@qq.com"
														  target="_blank">thinking_in_java@qq.com</a>&emsp;&emsp;<a href="http://www.beian.miit.gov.cn" target="_blank"
																													style="text-decoration:none;color:#666">蜀ICP备20005824号-1</a>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 验证弹窗 -->
<div class="box-bg">
	<div class="box">
		<img src="${staticPath}/static/home/login/images/cw.png" class="cuo">
		<span class="top-s">身份验证</span>
		<span class="top-x">拖动滑块，使图片角度为正</span>
		<div id="rotateWrap1">
			<div class="rotateverify-contaniner">
				<div class="rotate-can-wrap">
					<canvas class="rotateCan rotate-can" width="200" height="200"></canvas>
					<div class="statusBg status-bg"></div>
				</div>
				<div class="control-wrap slideDragWrap">
					<div class="control-tips">
						<p class="c-tips-txt cTipsTxt">滑动将图片转正</p>
					</div>
					<div class="control-bor-wrap controlBorWrap"></div>
					<div class="control-btn slideDragBtn">
						<i class="control-btn-ico"></i>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="${staticPath}/static/home/login/js/check.js"></script>
<script>
	setTimeout('changeState()', 1000)
	//地球动画
	function changeState() {
		// console.log('动画开始');
		$('#earth').removeClass('earth');
		$('#earth').addClass('move');
	}


	$(function () {
		// 验证弹窗
		// 点击按钮
		var phoneLog = $('#logphone');  //登录手机对象
		var pwdLog = $('#logpwd');      //登录密码对象
		var login = $('#login');    //登录按钮对象
		var regist = $('#regist');    //注册按钮对象
		var imgVerify = false;   //图片验证状态
		var phoneFlag   //手机号码验证状态
		var checkpwd1   //注册密码验证状态
		var checkpwd2   //注册确认密码验证状态
		regist.prop('disabled',true); //注册按钮不可点击

		$(".cuo").click(function () {
			$(".box-bg").css("display", "none");
			login.attr("disabled", false);
		})
		var myRotateVerify = new RotateVerify('#rotateWrap1', {
			initText: '滑动将图片转正', //默认
			slideImage: ['${staticPath}/static/home/login/images/1.png',
				'${staticPath}/static/home/login/images/2.png',
				'${staticPath}/static/home/login/images/4.png',
				'${staticPath}/static/home/login/images/5.png'
			], //arr  [imgsrc1,imgsrc2] 或者str 'imgsrc1'
			slideAreaNum: 10, // 误差范围角度 +- 10(默认)
			getSuccessState: function (res) { //验证通过 返回  true;
				if (res) {
					setTimeout(function () {
						$(".box-bg").css("display", "none");
					}, 800);
					//验证通过
					imgVerify =true;
					//登录函数
					loginCheck()
				}
			}
		})
		// console.log(myRotateVerify)


		//注册登录切换
		//登录
		$('#ToRegist').click(function () {
			// console.log('登录')
			$('.regist').css('display', 'block')
			$('.login').css('display', 'none')
		})
		//注册
		$('#ToLogin').click(function () {
			// console.log('注册')
			$('.regist').css('display', 'none')
			$('.login').css('display', 'block')
		})

		//注册页面注册方式切换
		$('#otherLogin').click(function () {
			$('.regist-form').css('display', 'none')
			$('.otherLogin').css('display', 'block')
			$('#phoneLogin').css('display', 'block')
			$('#otherLogin').css('display', 'none')
			$('.regist-title').html('快捷登录')
		})
		$('#phoneLogin').click(function () {
			$('.regist-form').css('display', 'block')
			$('.otherLogin').css('display', 'none')
			$('.regist-title').html('手机注册')
			$('#phoneLogin').css('display', 'none')
			$('#otherLogin').css('display', 'block')
		})

		// 输入验证
		// 登录验证
		//手机号码验证
		phoneLog.blur(function () {
			let phone = $("#logphone").val() //手机号码
			let obj = $('.check_log_phone')
			checkPhone({phone, obj,})
		})
		//密码验证
		pwdLog.blur(function () {
			let pwd1 = $("#logpwd").val()
			let obj1 = $('.check_log_pwd')
			checkPwd({obj1, pwd1,})
			// console.log(checkLogPwd)
		})

		//点击登录
		login.click(function () {
			if (!imgVerify){
				//验证通过
				$(".box-bg").css("display", "block");
				login.attr("disabled", true);
			}else {
				//登录函数
				loginCheck(0)
			}
		})



		//注册验证
		//手机号码验证
		$('#regphone').blur(function () {
			let phone = $('#regphone').val()
			let obj = $('.check_reg_phone')
			phoneFlag = checkPhone({
				phone,
				obj,
			})
			if(phoneFlag){
				//验证手机是否存在
				$.ajax({
					type: 'get',
					url: '',
					data: {phone},
					dataType: 'json',
					success: (res)=>{
						let code = res.code
						let msg = res.msg
						if(code == 400){
							//号码存在
							confirm({
								icon: 'warning',
								title: msg,
								content: '是否需要重置密码？',
								cancelText: '算了',
								confirmText: '需要',
								cancel: (close)=>{
									//点击了取消
									// console.log('算了')
									$('.getcode').prop('disabled',true)
									//处理你的业务逻辑


									close()

								},
								confirm: (close)=>{
									//点击了确认
									//处理你的业务逻辑
									// console.log('需要')
									close()
								},
							})
						}
					}
				})
			}
		})

		//密码验证
		$('#regpwd').blur(function () {
			let pwd = $('#regpwd').val()
			let obj = $('.check_reg_pwd')
			checkpwd1 = checkPwd({
				obj1: obj,
				pwd1:pwd
			})
		})

		//再次输入密码验证
		$('#regcheckpwd').blur(function () {
			let pwd1 = $('#regpwd').val()
			let obj1 = $('.check_reg_pwd')
			let pwd2 = $('#regcheckpwd').val()
			let obj2 = $('.check_reg_pwd2')
			checkpwd2 = checkPwd({
				obj1,
				obj2,
				pwd1,
				pwd2
			})
		})

		//获取验证码
		$('.getcode').click(function () {
			// console.log(123)
			let phone = $('#regphone').val()
			var mobile = phone
			//启用按钮
			$('.getcode').prop('disabled',false)

			var time= 60
			if (phoneFlag) {
				$.ajax({
					type: 'post',
					url: '${staticPath}/home/getCode',
					data: {mobile},
					dataType: 'json',
					success: (res) => {
						console.log(res)
						let code = res.code
						let content = res.msg
						// 倒计时
						if(code == 100){
							var interval = setInterval(function(){
								time--;
								if(time<=0){
									clearInterval(interval);
									var html = '获取验证码';
									$('.getcode').prop('disabled',false);
								} else{
									var html = time + ' 秒后再次获取';
									$('.getcode').prop('disabled',true);
								}
								$('.getcode').text(html);
							},1000);

							message({
								icon: 'success',
								content: '短信发送成功'
							})

						}else{

							message({
								icon: 'error',
								content
							})

						}

					},
				})
			} else {
				alert('请输入手机号码！')
			}
		})

		//短信验证码验证
		$('#code').blur(function () {
			let validateCode = $('#code').val();
			let name = $('#regphone').val();
			if (code == '') {
				$('.check_reg_code').html('验证码不能为空')
				regist.prop('disabled',true);
			}else{
				$('.check_reg_code').html('')
				$.ajax({
					type: 'post',
					url: '${staticPath}/home/checkValidateCode',
					data: {validateCode,name},
					dataType: 'json',
					success: (res) => {
						let flag = res.code
						let content = res.msg
						// console.log(res)
						if(flag == 100){
							//验证成功，弹窗提醒
							regist.prop('disabled',false);
							message({
								icon: 'success',
								content
							})

						}else{
							//验证失败，弹窗提醒
							message({
								icon: 'error',
								content
							})

						}
					}
				})
			}
		})


		//点击注册
		regist.click(function () {

			let check = phoneFlag && checkpwd1 && checkpwd2
			// console.log(check);
			if (check) {

				$.ajax({
					type: 'post',
					url: '${staticPath}/home/reg',
					data: $('#account_info').serialize(),
					dataType: 'json',
					success: (res)=>{
						let code = res.code
						let msg = res.msg
						if(code == 400){
							//验证失败，手机号码存在
							message({
								icon: 'error',
								content: msg,
							})
							$('#regphone').val('');
							$('#regphone').focus();
						}else{
							//验证成功，跳转首页
							message({
								icon: 'success',
								content: msg,
							})
							setTimeout(()=>{window.location.href="${staticPath}/home/index"},1500)
						}
					}
				})
			} else {

			}
		})

		//登录函数
		function loginCheck(timeout = 800) {
			// console.log(timeout)
			let phone = phoneLog.val()
			let pwd = pwdLog.val()
			var name = phone;
			var password = pwd;

			let flagPhone = checkPhone({phone,obj:phoneLog});
			let flagPwd =checkPwd({obj1:pwdLog,pwd1:pwd})
			if(flagPhone && flagPwd){
				login.text('登录中...')
				$.ajax({
					type: 'post',
					url: '${staticPath}/home/login',
					data: {name,password},
					dataType: 'json',
					success: responce=> {
						login.text('登录')
						let code = responce.code
						let msg = responce.msg
						if(code == 200){	//登录成功
							//取消登录按钮不可点击
							login.attr("disabled", false);
							setTimeout(()=>{
								message({icon: 'success', content: msg,})
							},timeout)
							window.location.href = "${staticPath}/home/index";
						}else{	//登录成功
							login.attr("disabled", false);
							setTimeout(()=>{
								message({icon: 'error', content: msg,})
							},timeout)
						}
					},
				})
			}
		}
	})
</script>
</body>

</html>