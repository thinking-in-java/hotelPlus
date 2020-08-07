package work.chinadream.web.controller.admin;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import work.chinadream.common.aop.SysLog;
import work.chinadream.entity.admin.Account;
import work.chinadream.service.admin.IAccountService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 17:33
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IAccountService accountService;

    /**
     * 客户管理列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model) {
        model.setViewName("admin/account/list");
        return model;
    }

    /**
     * 客户信息添加操作
     *
     * @param account
     * @return
     */
    @SysLog(value = "添加用户")
    @RequiresPermissions("account:add")
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map<String, String> add(Account account) {
        Map<String, String> ret = new HashMap<String, String>();
        if (account == null) {
            ret.put("type", "error");
            ret.put("msg", "请填写正确的客户信息!");
            return ret;
        }
        if (StringUtils.isEmpty(account.getName())) {
            ret.put("type", "error");
            ret.put("msg", "客户名称不能为空!");
            return ret;
        }
        if (StringUtils.isEmpty(account.getPassword())) {
            ret.put("type", "error");
            ret.put("msg", "客户密码不能为空!");
            return ret;
        }
        if (isExist(account.getName(), 0L)) {
            ret.put("type", "error");
            ret.put("msg", "该用户名已经存在!");
            return ret;
        }
        if (accountService.addAccount(account) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "添加失败，请联系管理员!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "添加成功!");
        return ret;
    }

    /**
     * 客户信息编辑操作
     *
     * @param account
     * @return
     */
    @SysLog(value = "修改用户信息")
    @RequiresPermissions("account:edit")
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Map<String, String> edit(Account account) {
        Map<String, String> ret = new HashMap<String, String>();
        if (account == null) {
            ret.put("type", "error");
            ret.put("msg", "请填写正确的客户信息!");
            return ret;
        }
        if (StringUtils.isEmpty(account.getName())) {
            ret.put("type", "error");
            ret.put("msg", "客户名称不能为空!");
            return ret;
        }
        if (StringUtils.isEmpty(account.getPassword())) {
            ret.put("type", "error");
            ret.put("msg", "客户密码不能为空!");
            return ret;
        }
        if (isExist(account.getName(), account.getId())) {
            ret.put("type", "error");
            ret.put("msg", "该用户名已经存在!");
            return ret;
        }
        if (accountService.editAccount(account) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "添加失败，请联系管理员!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "修改成功!");
        return ret;
    }

    /**
     * 分页查询客户信息
     *
     * @param name
     * @param page
     * @return
     */
    @SysLog(value = "查看用户信息")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Map<String, Object> list(Account account,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "rows", defaultValue = "20") Integer rows) {
        Map<String, Object> ret = new HashMap<String, Object>();
        PageHelper.startPage(page, rows);
        PageInfo pageInfo = new PageInfo(accountService.findListAccount(account));
        List list = pageInfo.getList();
        ret.put("rows", list);
        ret.put("total", pageInfo.getTotal());
        return ret;
    }

    /**
     * 客户信息删除操作
     *
     * @param id
     * @return
     */
    @SysLog(value = "删除用户信息")
    @RequiresPermissions("account:delete")
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Map<String, String> delete(Long id) {
        Map<String, String> ret = new HashMap<String, String>();
        if (id == null) {
            ret.put("type", "error");
            ret.put("msg", "请选择要删除的信息!");
            return ret;
        }
        try {
            if (accountService.deleteAccount(id) <= 0) {
                ret.put("type", "error");
                ret.put("msg", "删除失败，请联系管理员!");
                return ret;
            }
        } catch (Exception e) {
            // TODO: handle exception
            ret.put("type", "error");
            ret.put("msg", "该客户下存在订单信息，请先删除该客户下的所有订单信息!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "删除成功!");
        return ret;
    }

    /**
     * 判断用户名是否存在
     *
     * @param name
     * @param id
     * @return
     */
    private boolean isExist(String name, Long id) {
        Account findByName = accountService.findAccountByName(name);
        if (findByName == null) {
            return false;
        }
        if (findByName.getId().longValue() == id.longValue()) {
            return false;
        }
        return true;
    }

}
