package work.chinadream.web.controller.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import work.chinadream.common.aop.SysLog;
import work.chinadream.entity.admin.Role;
import work.chinadream.entity.common.Result;
import work.chinadream.entity.common.Tree;
import work.chinadream.service.admin.IRoleService;
import work.chinadream.util.PageInfo;
import work.chinadream.web.common.BaseController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */
@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

    @Autowired
    IRoleService roleService ;

    /**
     * 权限管理页
     * @return
     */
    @SysLog(value = "角色信息查看")
    @RequiresPermissions("role:list")
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "admin/role/role";
    }

    /**
     * 权限列表
     *
     * @param role // 表单信息
     * @param page //
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequiresPermissions("role:list")
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(Role role, Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);

        roleService.findDataGrid(pageInfo);
        return pageInfo;
    }

    /**
     * 授权页面
     *
     * @param request
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("role:grant")
    @RequestMapping("/grantPage")
    public String grantPage(HttpServletRequest request, Long id, Model model) {
        model.addAttribute("id", id);
        return "admin/role/roleGrant";
    }

    /**
     * 授权页面页面根据角色查询资源
     *
     * @param request
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/findResourceIdListByRoleId")
    @ResponseBody
    public Result findResourceByRoleId(HttpServletRequest request, Long id, Model model) {
        Result result = new Result();
        try {
            List<Long> resources = roleService.findResourceIdListByRoleId(id);
            result.setSuccess(true);
            result.setObj(resources);
            return result;
        } catch (RuntimeException e) {
            result.setMsg("资源查询失败");
            return result;
        }
    }

    /**
     * 授权
     *
     * @param id
     * @param resourceIds
     * @return
     */
    @SysLog(value = "角色授权")
    @RequiresPermissions("role:grant")
    @RequestMapping("/grant")
    @ResponseBody
    public Result grant(Long id, String resourceIds) {
        Result result = new Result();
        try {
            roleService.updateRoleResource(id, resourceIds);
            result.setMsg("授权成功！");
            result.setSuccess(true);
            return result;
        } catch (RuntimeException e) {
            result.setMsg("授权失败！");
            return result;
        }
    }

    /**
     * 添加角色页
     *
     * @return
     */
    @RequiresPermissions("role:add")
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "admin/role/roleAdd";
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @SysLog(value = "角色信息添加")
    @RequiresPermissions("role:add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(Role role) {
        Result result = new Result();
        try {
            roleService.addRole(role);
            result.setSuccess(true);
            result.setMsg("添加成功！");
            return result;
        } catch (RuntimeException e) {
            result.setMsg("添加失败！");
            return result;
        }
    }

    /**
     * 编辑角色页
     *
     * @param request
     * @param id
     * @return
     */
    @RequiresPermissions("role:edit")
    @RequestMapping("/editPage")
    public String editPage(HttpServletRequest request, Long id) {
        Role role = roleService.findRoleById(id);
        request.setAttribute("role", role);
        return "admin/role/roleEdit";
    }

    /**
     * 编辑角色
     *
     * @param role
     * @return
     */
    @SysLog(value = "角色信息编辑")
    @RequiresPermissions("role:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(Role role) {
        Result result = new Result();
        try {
            roleService.updateRole(role);
            result.setSuccess(true);
            result.setMsg("编辑成功！");
            return result;
        } catch (RuntimeException e) {
            result.setMsg("编辑失败！");
            return result;
        }
    }

    /**
     * 删除角色
     * @param id
     */
    @SysLog(value = "角色信息删除")
    @RequiresPermissions("role:delete")
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id) {
        Result result = new Result();
        try {
            roleService.deleteRoleById(id);
            result.setSuccess(true);
            result.setMsg("删除成功！");
            return result;
        } catch (RuntimeException e) {
            result.setMsg("删除失败！");
            return result;
        }
    }

    /**
     * 权限树
     * @return
     */
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public List<Tree> tree() {
        return roleService.findTree();
    }

}
