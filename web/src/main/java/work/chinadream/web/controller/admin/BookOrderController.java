package work.chinadream.web.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import work.chinadream.common.aop.SysLog;
import work.chinadream.entity.admin.BookOrder;
import work.chinadream.entity.admin.RoomType;
import work.chinadream.service.admin.IAccountService;
import work.chinadream.service.admin.IBookOrderService;
import work.chinadream.service.admin.IRoomTypeService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 22:42
 */
@RequestMapping("/book_order")
@Controller

public class BookOrderController {

    @Autowired
    private IAccountService accountService;
    @Autowired
    private IRoomTypeService roomTypeService;
    @Autowired
    private IBookOrderService bookOrderService;


    /**
     * 预定订单管理列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        model.addObject("roomTypeList", roomTypeService.findAllRoomType());
        model.addObject("accountList", accountService.findAllAccount());
        model.setViewName("admin/book_order/list");
        return model;
    }

    /**
     * 预定订单信息添加操作
     * @param bookOrder
     * @return
     */
    @SysLog(value = "添加预定信息")
    @RequiresPermissions("book_order:add")
   @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(BookOrder bookOrder){
        Map<String, String> ret = new HashMap<String, String>();
        if(bookOrder == null){
            ret.put("type", "error");
            ret.put("msg", "请填写正确的预定订单信息!");
            return ret;
        }
        if(bookOrder.getAccountId() == null){
            ret.put("type", "error");
            ret.put("msg", "客户不能为空!");
            return ret;
        }
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
        ret.put("msg", "添加成功!");
        return ret;
    }

    /**
     * 预定订单信息编辑操作
     * @param bookOrder
     * @return
     */
    @SysLog(value = "修改预定信息")
    @RequiresPermissions("book_order:edit")
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(BookOrder bookOrder){
        Map<String, String> ret = new HashMap<String, String>();
        if(bookOrder == null){
            ret.put("type", "error");
            ret.put("msg", "请填写正确的预定订单信息!");
            return ret;
        }
        if(bookOrder.getAccountId() == null){
            ret.put("type", "error");
            ret.put("msg", "客户不能为空!");
            return ret;
        }
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
        BookOrder existBookOrder = bookOrderService.findBookOrder(bookOrder.getId());
        if(existBookOrder == null){
            ret.put("type", "error");
            ret.put("msg", "请选择正确的数据进行编辑!");
            return ret;
        }
        if(bookOrderService.editBookOrder(bookOrder) <= 0){
            ret.put("type", "error");
            ret.put("msg", "编辑失败，请联系管理员!");
            return ret;
        }
        //判断房型是否发生变化
        if(existBookOrder.getRoomTypeId().longValue() != bookOrder.getRoomTypeId().longValue()){
            //房型发生了变化
            //首先恢复原来房型的预定数及可用数
            RoomType oldRoomType = roomTypeService.findRoomType(existBookOrder.getRoomTypeId());
            oldRoomType.setAvilableNum(oldRoomType.getAvilableNum() + 1);
            oldRoomType.setBookNum(oldRoomType.getBookNum() - 1);
            roomTypeService.updateRoomTypeNum(oldRoomType);
            if(oldRoomType.getStatus() == 0){
                //旧的房间原来是满房，现在不满房的话，恢复状态
                if(oldRoomType.getAvilableNum() > 0){
                    //设置成状态可用
                    oldRoomType.setStatus(1);
                    roomTypeService.editRoomType(oldRoomType);
                }
            }
            //修改新的房型的可用数和预定数
            RoomType newRoomType = roomTypeService.findRoomType(bookOrder.getRoomTypeId());
            newRoomType.setAvilableNum(newRoomType.getAvilableNum() - 1);
            newRoomType.setBookNum(newRoomType.getBookNum() + 1);
            roomTypeService.updateRoomTypeNum(newRoomType);
            if(newRoomType.getAvilableNum() <= 0){
                //没有可用房间数
                newRoomType.setStatus(0);//设置成满房
                roomTypeService.editRoomType(newRoomType);
            }
        }
        ret.put("type", "success");
        ret.put("msg", "修改成功!");
        return ret;
    }

    /**
     * 分页查询预定订单信息
     * @return
     */
    @SysLog(value = "查询预定信息")
   @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> list(
            BookOrder bookOrder,
            @RequestParam(name="page",defaultValue= "1") Integer page ,
            @RequestParam(name="rows",defaultValue= "20") Integer rows){
        Map<String,Object> ret = new HashMap<String, Object>();
       PageHelper.startPage(page,rows);
       PageInfo pageInfo = new PageInfo(bookOrderService.findBookOrderList(bookOrder));
       List list = pageInfo.getList();
       ret.put("rows",list);
       ret.put("total", pageInfo.getTotal());
        return ret;
    }

}
