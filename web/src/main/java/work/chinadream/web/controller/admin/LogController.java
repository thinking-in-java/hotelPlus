package work.chinadream.web.controller.admin;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import work.chinadream.common.aop.SysLog;
import work.chinadream.entity.admin.Log;
import work.chinadream.entity.admin.LogVo;
import work.chinadream.entity.common.Result;
import work.chinadream.service.admin.ILogService;
import work.chinadream.util.PageInfo;
import work.chinadream.web.common.BaseController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
@RequestMapping("log")
public class LogController extends BaseController {

    @Autowired
    private ILogService logService;

    /**
     * 日志页
     * @return
     */
    @SysLog(value = "日志信息查看")
    @RequiresPermissions("log:list")
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "admin/log/log";
    }

    /**
     * 日志列表
     * @param logVo
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequiresPermissions("log:list")
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(LogVo logVo, Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows , sort , order);
        Map<String, Object> condition = new HashMap<String, Object>();

        if (StringUtils.isNoneBlank(logVo.getOperationName())) {
            condition.put("operationName", logVo.getOperationName());
        }
        if (logVo.getCreatedateStart() != null) {
            condition.put("startTime", logVo.getCreatedateStart());
        }
        if (logVo.getCreatedateEnd() != null) {
            condition.put("endTime", logVo.getCreatedateEnd());
        }
        pageInfo.setCondition(condition);
        logService.findDataGrid(pageInfo);
        return pageInfo;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @SysLog(value = "日志信息批量删除")
    @RequiresPermissions("log:batchDelete")
    @RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
    @ResponseBody
    public Result batchDelete(String ids){
        Result result = new Result();
//        String ids = idS.toString();
        List<String> cons=new ArrayList<String>();
        String[] arrays=ids.split(",");
        for(String id:arrays){
            if (!id.trim().equals(""))
                cons.add(id);
        }
        try {
            logService.batchDelete(cons);
            result.setMsg("删除成功！");
            result.setSuccess(true);
            return result;
        } catch (RuntimeException e) {
            result.setMsg("删除失败！");
            return result;
        }
    }

    /**
     * 日志详情页
     * @return
     */
    @RequestMapping(value = "/detailPage", method = RequestMethod.GET)
    public String detail(Long id , HttpServletRequest request) {
        Log log = logService.selectById(id) ;
        request.setAttribute("log" , log);
        return "admin/log/logDetail";
    }

}
