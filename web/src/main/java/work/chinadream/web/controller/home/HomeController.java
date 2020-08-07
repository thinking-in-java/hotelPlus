package work.chinadream.web.controller.home;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import work.chinadream.entity.admin.Account;
import work.chinadream.entity.admin.RoomType;
import work.chinadream.entity.common.Msg;
import work.chinadream.service.admin.IAccountService;
import work.chinadream.service.admin.IRoomTypeService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/21 23:04
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private IRoomTypeService roomTypeService;
    @Autowired
    private IAccountService accountService;

    // 验证码发送地址
    private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
    // 存放验证码,和手机号
    private Map<String,String> map = new HashMap<>();


    /**
     * 前台首页
     * @param model
     * @param name
     * @return
     */
    @RequestMapping(value = "/order",method = RequestMethod.GET)
    public ModelAndView list(@RequestParam(value = "pn",defaultValue = "1") Integer pn, ModelAndView model, @RequestParam(name="name",defaultValue="") String name){

        PageHelper.startPage(pn, 5);
        List<RoomType> roomTypeList = roomTypeService.findRoomTypeList(name, null);
        PageInfo pageInfo = new PageInfo(roomTypeList,5);
        model.addObject("pageInfo", pageInfo);
        model.setViewName("home/product/order");
        model.addObject("kw", name);
        return model;
    }

    /**
     * 登录页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login(ModelAndView model
    ){
        model.setViewName("home/index/login");
        return model;
    }

    /**
     * 登录页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(ModelAndView model
    ){
        model.setViewName("home/index/index");
        return model;
    }


    /**
     * 验证码
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/getCode",method = RequestMethod.POST)
    @ResponseBody
    public Msg sendCode(String mobile) {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);

        client.getParams().setContentCharset("GBK");
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=GBK");

        int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
        String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");

        NameValuePair[] data = { // 提交短信
                new NameValuePair("account", "C99656365"), // 查看用户名是登录用户中心->验证码短信->产品总览->APIID
                new NameValuePair(
                        "password", "ee2b4417d148339eace309ae152d5558"), // 查看密码请登录用户中心->验证码短信->产品总览->APIKEY
                // new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
                new NameValuePair("mobile", mobile),
                new NameValuePair("content", content),
        };
        method.setRequestBody(data);
        try {
            client.executeMethod(method);
            String SubmitResult = method.getResponseBodyAsString();
            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();
            String code = root.elementText("code");
            String msg = root.elementText("msg");
            String smsid = root.elementText("smsid");
            if ("2".equals(code)) {
                map.put(mobile,mobile_code+"");
                return Msg.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.fail();
    }
    @RequestMapping(value = "/checkValidateCode",method = RequestMethod.POST)
    @ResponseBody
    public Msg checkValicateCode(Account account){
        if(map.get(account.getName()).equals(account.getValidateCode())){
            return Msg.success();
        }
        return Msg.fail();
    }


    /**
     * 注册页面
     * @param model
     * @return
     */
  /* @RequestMapping(value = "/reg",method = RequestMethod.GET)
    public ModelAndView reg(ModelAndView model
    ){
        model.setViewName("home/index/reg");
        return model;
    }*/

    /**
     * 登录信息提交
     * @param account
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> loginAct(Account account, String vcode, HttpServletRequest request){
        Map<String,String> retMap = new HashMap<String, String>();
        if(account == null){
            retMap.put("type", "error");
            retMap.put("msg", "请填写正确的用户信息！");
            return retMap;
        }
        if(StringUtils.isEmpty(account.getName())){
            retMap.put("type", "error");
            retMap.put("msg", "用户名不能为空！");
            return retMap;
        }
        if(StringUtils.isEmpty(account.getPassword())){
            retMap.put("type", "error");
            retMap.put("msg", "密码不能为空！");
            return retMap;
        }

        Account findByName = accountService.findAccountByName(account.getName());
        if(findByName == null){
            retMap.put("type", "error");
            retMap.put("msg", "用户名不存在！");
            return retMap;
        }
        if(!account.getPassword().equals(findByName.getPassword())){
            retMap.put("type", "error");
            retMap.put("msg", "密码错误！");
            return retMap;
        }
        if(findByName.getStatus() == -1){
            retMap.put("type", "error");
            retMap.put("msg", "该用户已被禁用，请联系管理员！");
            return retMap;
        }
        request.getSession().setAttribute("account", findByName);
        retMap.put("type", "success");
        retMap.put("msg", "登录成功！");
        retMap.put("code","200");
        return retMap;
    }

    /**
     * 注册信息提交
     * @param account
     * @return
     */
   @RequestMapping(value = "/reg",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> regAct(Account account){
        Map<String,String> retMap = new HashMap<String, String>();
       // 对比短信验证码来激活该用户
       if (!map.get(account.getName()).equals(account.getValidateCode())) {
           retMap.put("type", "error");
           retMap.put("msg", "验证码错误,请重新获取！");
           return retMap;
       }
        if(account == null){
            retMap.put("type", "error");
            retMap.put("msg", "请填写正确的用户信息！");
            return retMap;
        }
        if(StringUtils.isEmpty(account.getName())){
            retMap.put("type", "error");
            retMap.put("msg", "用户名不能为空！");
            return retMap;
        }
        if(StringUtils.isEmpty(account.getPassword())){
            retMap.put("type", "error");
            retMap.put("msg", "密码不能为空！");
            return retMap;
        }
        if(isExist(account.getName())){
            retMap.put("type", "error");
            retMap.put("code", "400");
            retMap.put("msg", "该用户名已经存在！");
            return retMap;
        }
        if(accountService.addAccount(account) <= 0){
            retMap.put("type", "error");
            retMap.put("msg", "注册失败，请联系管理员！");
            return retMap;
        }
        retMap.put("type", "success");
        retMap.put("msg", "注册成功！");
        return retMap;
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().setAttribute("account", null);
        return "redirect:login";
    }

    private boolean isExist(String name){
        Account account = accountService.findAccountByName(name);
        if(account == null)return false;
        return true;
    }
}
