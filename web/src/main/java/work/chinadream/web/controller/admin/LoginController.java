package work.chinadream.web.controller.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import work.chinadream.entity.common.Result;
import work.chinadream.shiro.ShiroUtils;
import work.chinadream.web.common.BaseController;

import javax.servlet.http.HttpSession;

/**             登录/退出
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */

@Controller
public class LoginController extends BaseController {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @param txtCode
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result loginPost(String username, String password, String txtCode, HttpSession session) {
        Result result = new Result();
        // 比对验证码是否正确
        if ( !(txtCode.equals(session.getAttribute("code") ) ) ) {
            result.setMsg("验证码错误");
            return result;
        }
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token =
                new UsernamePasswordToken(username, password);
//        token.setRememberMe(true);
        try {
            user.login(token);
        } catch (UnknownAccountException e) {
            result.setMsg("账号不存在");
            return result;
        } catch (DisabledAccountException e) {
            result.setMsg("账号未启用");
            return result;
        } catch (IncorrectCredentialsException e) {
            result.setMsg("密码错误");
            return result;
        } catch (RuntimeException e) {
            result.setMsg("未知错误,请刷新界面重新登录！重复登录没用请加群联系作者带上报错截图"+e.getMessage());
//            result.setMsg(e.getMessage());
            return result;
        }
        result.setSuccess(true);
        session.setAttribute("sex" ,getUserSex());
        return result;
    }

    /**
     * 退出
     *
     * @return
     */
    @RequestMapping("/logout")
//    @ResponseBody
    public String logout() {
        ShiroUtils.logout();
        return "redirect:/" ;
    }
}
