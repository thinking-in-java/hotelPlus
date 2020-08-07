package work.chinadream.web.common;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import work.chinadream.entity.admin.ShiroUser;
import work.chinadream.entity.admin.User;
import work.chinadream.service.admin.IUserService;
import work.chinadream.util.DateUtil2;
import work.chinadream.util.StringEscapeEditor;

import java.util.Date;


/**             基础 Controller
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */
public class BaseController {

    @Autowired
    private IUserService userService;

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        /**
//         * 自动转换日期类型的字段格式
//         */
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM"), true));
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy"), true));
//
//
//        /**
//         * 防止XSS攻击
//         */
//        binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
//    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 使用自定义的 DATE数据绑定类
        binder.registerCustomEditor(Date.class, new DateUtil2());

        /**
         * 防止XSS攻击
         */
        binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
    }

    /**
     * 获取当前登录用户对象
     * @return
     */
    public User getCurrentUser() {
        ShiroUser shiroUser= (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        User currentUser = userService.findUserById(shiroUser.id);
        return currentUser;
    }

    /**
     * 获取当前登录用户id
     * @return
     */
    public Long getUserId() {
        return this.getCurrentUser().getId();
    }

    /**
     * 获取当前登录用户名
     * @return
     */
    public String getStaffName() {
        return this.getCurrentUser().getName();
    }

    /**
     *  获取当前用户 性别
     */
    public Integer getUserSex(){
        return this.getCurrentUser().getSex();
    }
}
