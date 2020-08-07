package work.chinadream.web.controller.home;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import work.chinadream.entity.admin.Account;
import work.chinadream.entity.admin.QqAccount;
import work.chinadream.service.IQqService;
import work.chinadream.service.admin.IAccountService;
import work.chinadream.util.OauthQQ;
import work.chinadream.util.RandomPhone;
import work.chinadream.util.TokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/api/qq")
public class OauthQqController {
    //OAuth2.0标准协议建议，利用state参数来防止CSRF攻击。可存储于session或其他cache中
    private static final String SESSION_STATE = "_SESSION_STATE_QQ_";
    private static Logger log = LoggerFactory.getLogger(OauthQqController.class);
    @Autowired
    private IQqService service;
    @Autowired
    private IAccountService accountService;


    @RequestMapping("/callback")
    public String callback(HttpServletRequest request) {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        // 取消了授权
        if (StringUtils.isBlank(state) || StringUtils.isBlank(code)) {
            return "取消了授权";
        }
        //清除state以防下次登录授权失败
        HttpSession session = request.getSession();
        //session.removeAttribute(SESSION_STATE);
        //获取用户信息
        try {
            JSONObject userInfo = OauthQQ.me().getUserInfoByCode(code);
            log.error(userInfo.toString());

            String openid = userInfo.getString("openid");
            String nickname = userInfo.getString("nickname");
            String photoUrl = userInfo.getString("figureurl_2");
            String access_token = userInfo.getString("access_token");

            System.out.println(nickname);
            // 将相关信息存储数据库...

            QqAccount find = service.selectQq(openid);
            if (find != null) {
                //老用户
                find.setAccess_token(access_token);
                service.updateQq(find);
                Account accountByQq = accountService.findAccountByQq(find.getId());

                //查到该用户并放入session中
                session.setAttribute("account", accountByQq);
            } else {
                //新用户
                QqAccount qqAccount = new QqAccount();
                qqAccount.setOpenid(openid);
                qqAccount.setPhotoUrl(photoUrl);
                qqAccount.setAccess_token(access_token);
                qqAccount.setNickname(nickname);
                qqAccount.setAccess_token(access_token);

                service.insertQq(qqAccount);

                int qqId= qqAccount.getId();
                Account createAcc = new Account();
                createAcc.setName(RandomPhone.getTel());
                createAcc.setPassword("123456");
                createAcc.setQid(qqId);

                accountService.addAccount(createAcc);
                //创建成功放一份到session
                session.setAttribute("account", createAcc);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "errors/404";
        }
        //这里你们可以自己修改，授权成功后，调到首页
        return "home/index/index";
    }

    /**
     * 构造授权请求url
     *
     * @return void    返回类型
     * @throws
     */
    @RequestMapping("/login")
    public String index(HttpServletRequest request) {
        //state就是一个随机数，保证安全
        String state = TokenUtil.randomState();
        try {
            String url = OauthQQ.me().getAuthorizeUrl(state);
            return "redirect:" + url;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "redirect:/index.jsp";
    }
}
