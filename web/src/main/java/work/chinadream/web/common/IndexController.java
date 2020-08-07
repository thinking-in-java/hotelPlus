package work.chinadream.web.common;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */
@Controller
public class IndexController {

    // 后台首页
    @RequestMapping("/")
    public  String  index(){
        return "admin/index";
    }
    // 后台首页
    @RequestMapping("/index")
    public  String  index2(){
        return "admin/index";
    }
    // 后台主页面
    @RequestMapping("/main")
    public  String  main(){
        return "admin/main" ;
    }
    // 登录页面
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public  String  login(){
        return "admin/login" ;
    }

    //前端登录页面
    // 后台首页
    @RequestMapping("/home/login")
    public  String  homeLogin(){
        return "home/index/login";
    }

    /**
     * 未授权
     */
    @RequestMapping(value = "/unAuth")
    public String unAuth() {
        if (SecurityUtils.getSubject().isAuthenticated() == false) {
            return "redirect:admin/login";
        }
        return "errors/unAuth";
    }
}
