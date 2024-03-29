package work.chinadream.web.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import work.chinadream.entity.admin.Account;
import work.chinadream.entity.admin.BookOrder;
import work.chinadream.entity.admin.RoomType;
import work.chinadream.service.admin.IAccountService;
import work.chinadream.service.admin.IBookOrderService;
import work.chinadream.service.admin.IRoomTypeService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/22 9:29
 */
@RequestMapping("/home/account")
@Controller
public class HomeAccountController {

    @Autowired
    private IRoomTypeService roomTypeService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IBookOrderService bookOrderService;

    /**
     * 前台用户中心首页
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/acc_order",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model, HttpServletRequest request
    ){
        Account account = (Account)request.getSession().getAttribute("account");
        Map<String,Object> queryMap = new HashMap<String, Object>();
        BookOrder bookOrder = new BookOrder();
        bookOrder.setAccountId(account.getId());
        model.addObject("bookOrderList", bookOrderService.findBookOrderList(bookOrder));
        model.addObject("roomTypeList", roomTypeService.findAllRoomType());
        model.setViewName("home/account/acc_order");
        return model;
    }

    /**
     * 预定房间页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/book_order",method = RequestMethod.GET)
    public ModelAndView bookOrder(ModelAndView model, Long roomTypeId
    ){
        model.addObject("roomType", roomTypeService.findRoomType(roomTypeId));
        model.setViewName("home/product/book_order");
        return model;
    }

    /**
     * 跳转个人信息页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView add_acc(ModelAndView model, Long roomTypeId
    ){
        model.setViewName("home/account/index");
        return model;
    }

    /**
     * 修改密码
     * @param model
     * @return
     */
    @RequestMapping(value = "/updatePwd",method = RequestMethod.GET)
    public ModelAndView update_pwd(ModelAndView model, Long roomTypeId
    ){
        model.setViewName("home/account/change_pwd");
        return model;
    }

    /**
     * 预定信息提交
     * @param bookOrder
     * @param request
     * @return
     */
   @RequestMapping(value = "/book_order",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> bookOrderAct(BookOrder bookOrder, HttpServletRequest request){
        Map<String, String> ret = new HashMap<String, String>();
        if(bookOrder == null){
            ret.put("type", "error");
            ret.put("msg", "请填写正确的预定订单信息!");
            return ret;
        }
        Account account = (Account)request.getSession().getAttribute("account");
        if(account == null){
            ret.put("type", "error");
            ret.put("msg", "客户不能为空!");
            return ret;
        }
        bookOrder.setAccountId(account.getId());
        if(bookOrder.getRoomTypeId() == null){
            ret.put("type", "error");
            ret.put("msg", "房型不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(bookOrder.getName())){
            ret.put("type", "error");
            ret.put("msg", "预定订单联系人名称不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(bookOrder.getMobile())){
            ret.put("type", "error");
            ret.put("msg", "预定订单联系人手机号不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(bookOrder.getIdCard())){
            ret.put("type", "error");
            ret.put("msg", "联系人身份证号不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(bookOrder.getArriveDate())){
            ret.put("type", "error");
            ret.put("msg", "到达时间不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(bookOrder.getLeaveDate())){
            ret.put("type", "error");
            ret.put("msg", "离店时间不能为空!");
            return ret;
        }
        bookOrder.setCreateTime(new Date());
        bookOrder.setStatus(0);
        if(bookOrderService.addBookOrder(bookOrder) <= 0){
            ret.put("type", "error");
            ret.put("msg", "添加失败，请联系管理员!");
            return ret;
        }
        RoomType roomType = roomTypeService.findRoomType(bookOrder.getRoomTypeId());
        //预定成功后去修改该房型的预定数
        if(roomType != null){
            roomType.setBookNum(roomType.getBookNum() + 1);
            roomType.setAvilableNum(roomType.getAvilableNum() - 1);
            roomTypeService.updateRoomTypeNum(roomType);
            //如果可用的房间数为0，则设置该房型状态已满
            if(roomType.getAvilableNum() == 0){
                roomType.setStatus(0);
                roomTypeService.editRoomType(roomType);
            }
        }
        ret.put("type", "success");
        ret.put("msg", "预定成功!");
        return ret;
    }

    /**
     * 修改个人信息提交
     * @param account
     * @return
     */
    @RequestMapping(value = "/update_info",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> updateInfoAct(Account account, HttpServletRequest request){
        Map<String,String> retMap = new HashMap<String, String>();
        if(account == null){
            retMap.put("type", "error");
            retMap.put("msg", "请填写正确的用户信息！");
            return retMap;
        }

        Account loginedAccount = (Account)request.getSession().getAttribute("account");

        loginedAccount.setAddress(account.getAddress());
        loginedAccount.setIdCard(account.getIdCard());
        loginedAccount.setMobile(account.getMobile());
        loginedAccount.setRealName(account.getRealName());
        if(accountService.editAccount(loginedAccount) <= 0){
            retMap.put("type", "error");
            retMap.put("msg", "修改失败，请联系管理员！");
            return retMap;
        }
        request.getSession().setAttribute("account", loginedAccount);
        retMap.put("type", "success");
        retMap.put("msg", "修改成功！");
        return retMap;
    }

    /**
     * 修改密码提交
     * @param oldPassword
     * @param newPassword
     * @param request
     * @return
     */
    @RequestMapping(value = "/update_pwd",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> updatePwdAct(String oldPassword, String newPassword,String code, HttpServletRequest request){
        Map<String,String> retMap = new HashMap<String, String>();
        if(StringUtils.isEmpty(code)){
            retMap.put("type", "error");
            retMap.put("msg", "请填写验证码！");
            return retMap;
        }
        if(StringUtils.isEmpty(oldPassword)){
            retMap.put("type", "error");
            retMap.put("msg", "请填写原来的密码！");
            return retMap;
        }
        if(StringUtils.isEmpty(newPassword)){
            retMap.put("type", "error");
            retMap.put("msg", "请填写新密码！");
            return retMap;
        }
        Account loginedAccount = (Account)request.getSession().getAttribute("account");

        if(!oldPassword.equals(loginedAccount.getPassword())){
            retMap.put("type", "error");
            retMap.put("msg", "原密码错误！");
            return retMap;
        }
        if ( !(code.equals(request.getSession().getAttribute("code") ) ) ) {
            retMap.put("type", "error");
            retMap.put("msg", "验证码错误！");
            return retMap;
        }

        loginedAccount.setPassword(newPassword);
        if(accountService.editAccount(loginedAccount) <= 0){
            retMap.put("type", "error");
            retMap.put("msg", "修改失败，请联系管理员！");
            return retMap;
        }
        retMap.put("type", "success");
        retMap.put("msg", "修改密码成功！");
        return retMap;
    }

    /**
     * 判断用户是否存在
     * @param name
     * @param id
     * @return
     */
    private boolean isExist(String name,Long id){
        Account account = accountService.findAccountByName(name);
        if(account == null)return false;
        if(account != null && account.getId().longValue() == id)return false;
        return true;
    }
}
