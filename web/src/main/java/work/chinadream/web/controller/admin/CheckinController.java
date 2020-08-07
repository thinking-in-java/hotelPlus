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
import work.chinadream.entity.admin.Checkin;
import work.chinadream.entity.admin.Room;
import work.chinadream.entity.admin.RoomType;
import work.chinadream.service.admin.IBookOrderService;
import work.chinadream.service.admin.ICheckinService;
import work.chinadream.service.admin.IRoomService;
import work.chinadream.service.admin.IRoomTypeService;

import java.util.*;

/**
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/21 13:01
 */
@RequestMapping("/checkin")
@Controller
public class CheckinController {

    @Autowired
    private IRoomService roomService;
    @Autowired
    private IRoomTypeService roomTypeService;
    @Autowired
    private IBookOrderService bookOrderService;
    @Autowired
    private ICheckinService checkinService;

    /**
     * 入住管理列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        model.addObject("roomTypeList", roomTypeService.findAllRoomType());
        model.addObject("roomList", roomService.findAllRoom());
        model.setViewName("admin/checkin/list");
        return model;
    }

    /**
     * 入住信息添加操作
     * @param checkin
     * @return
     */
    @SysLog(value = "添加入住信息")
    @RequiresPermissions("checkin:add")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(Checkin checkin,
                                   @RequestParam(name="bookOrderId",required=false) Long bookOrderId
    ){
        Map<String, String> ret = new HashMap<String, String>();
        if(checkin == null){
            ret.put("type", "error");
            ret.put("msg", "请填写正确的入住信息!");
            return ret;
        }
        if(checkin.getRoomId() == null){
            ret.put("type", "error");
            ret.put("msg", "房间不能为空!");
            return ret;
        }
        if(checkin.getRoomTypeId() == null){
            ret.put("type", "error");
            ret.put("msg", "房型不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(checkin.getName())){
            ret.put("type", "error");
            ret.put("msg", "入住联系人名称不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(checkin.getMobile())){
            ret.put("type", "error");
            ret.put("msg", "入住联系人手机号不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(checkin.getIdCard())){
            ret.put("type", "error");
            ret.put("msg", "联系人身份证号不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(checkin.getArriveDate())){
            ret.put("type", "error");
            ret.put("msg", "到达时间不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(checkin.getLeaveDate())){
            ret.put("type", "error");
            ret.put("msg", "离店时间不能为空!");
            return ret;
        }
        checkin.setCreateTime(new Date());
        if(checkinService.addCheckin(checkin) <= 0){
            ret.put("type", "error");
            ret.put("msg", "添加失败，请联系管理员!");
            return ret;
        }
        RoomType roomType = roomTypeService.findRoomType(checkin.getRoomTypeId());

        if(bookOrderId != null){
            //从预定来的入住单(入住既可以是直接入住也可以是已经预定的人来入住)
            BookOrder bookOrder = bookOrderService.findBookOrder(bookOrderId);
            bookOrder.setStatus(1);
            bookOrderService.editBookOrder(bookOrder);
            //roomType.setBookNum(roomType.getBookNum() - 1);//预定数减1
        }else{
            roomType.setAvilableNum(roomType.getAvilableNum() - 1);
        }
        //入住成功后去修改该房型的预定数
        if(roomType != null){
            roomType.setLivedNum(roomType.getLivedNum() + 1);//入住数加1
            roomTypeService.updateRoomTypeNum(roomType);
            //如果可用的房间数为0，则设置该房型状态已满
            if(roomType.getAvilableNum() == 0){
                roomType.setStatus(0);
                roomTypeService.editRoomType(roomType);
            }
        }
        Room room = roomService.findRoom(checkin.getRoomId());
        if(room != null){
            //要把房间状态设置为已入住
            room.setStatus(1);
            roomService.editRoom(room);
        }
        ret.put("type", "success");
        ret.put("msg", "添加成功!");
        return ret;
    }

    /**
     * 入住信息编辑操作
     * @param checkin
     * @param bookOrderId
     * @return
     */
    @SysLog(value = "修改入住信息")
    @RequiresPermissions("checkin:edit")
   @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(Checkin checkin,
                                    @RequestParam(name="bookOrderId",required=false) Long bookOrderId
    ){
        Map<String, String> ret = new HashMap<String, String>();
        if(checkin == null){
            ret.put("type", "error");
            ret.put("msg", "请填写正确的入住信息!");
            return ret;
        }
        if(checkin.getRoomId() == null){
            ret.put("type", "error");
            ret.put("msg", "房间不能为空!");
            return ret;
        }
        if(checkin.getRoomTypeId() == null){
            ret.put("type", "error");
            ret.put("msg", "房型不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(checkin.getName())){
            ret.put("type", "error");
            ret.put("msg", "入住联系人名称不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(checkin.getMobile())){
            ret.put("type", "error");
            ret.put("msg", "入住联系人手机号不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(checkin.getIdCard())){
            ret.put("type", "error");
            ret.put("msg", "联系人身份证号不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(checkin.getArriveDate())){
            ret.put("type", "error");
            ret.put("msg", "到达时间不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(checkin.getLeaveDate())){
            ret.put("type", "error");
            ret.put("msg", "离店时间不能为空!");
            return ret;
        }
        Checkin existCheckin = checkinService.findCheckin(checkin.getId());
        if(existCheckin == null){
            ret.put("type", "error");
            ret.put("msg", "请选择正确的入住信息进行编辑!");
            return ret;
        }
        if(checkinService.editCheckin(checkin) <= 0){
            ret.put("type", "error");
            ret.put("msg", "编辑失败，请联系管理员!");
            return ret;
        }
        //编辑成功之后：1：判断房型是否发生变化，2：判断房间是否发生变化，3：判断是否是从预定订单来的信息
        //首先判断是否是从预定来的入住信息
        RoomType oldRoomType = roomTypeService.findRoomType(existCheckin.getRoomTypeId());
        RoomType newRoomType = roomTypeService.findRoomType(checkin.getRoomTypeId());

        //房型入住数不收预定订单的影响
        if(oldRoomType.getId().longValue() != newRoomType.getId().longValue()){
            //说明房型发生了变化，原来的房型入住数恢复，新的房型入住数增加
            oldRoomType.setLivedNum(oldRoomType.getLivedNum() - 1);
            newRoomType.setLivedNum(newRoomType.getLivedNum() + 1);
            if(bookOrderId == null){
                oldRoomType.setAvilableNum(oldRoomType.getAvilableNum() + 1);
                newRoomType.setAvilableNum(newRoomType.getAvilableNum() - 1);
            }
        }
        /**
         if(bookOrderId == null){
         //表示不是从预定订单来的，此时需判断原来的入住信息是否来源于预定
         if(existCheckin.getBookOrderId() == null){
         oldRoomType.setAvilableNum(oldRoomType.getAvilableNum() + 1);
         newRoomType.setAvilableNum(newRoomType.getAvilableNum() - 1);
         }
         if(existCheckin.getBookOrderId() != null){
         //表示原来的入住信息来源于预定，但是新的入住信息不是来源于预定,需恢复原来的预定状态
         BookOrder oldBookOrder = bookOrderService.find(existCheckin.getBookOrderId());
         oldBookOrder.setStatus(0);
         bookOrderService.edit(oldBookOrder);
         oldRoomType.setBookNum(oldRoomType.getBookNum() + 1);
         }
         }
         //表示此时的订单是来源于预定
         if(bookOrderId != null){
         //表示是从预定订单来的，此时需判断原来的入住信息是否来源于预定
         if(existCheckin.getBookOrderId() != null){
         //表示原来的入住信息来源于预定，但是新的入住信息不是来源于预定,需恢复原来的预定状态
         BookOrder oldBookOrder = bookOrderService.find(existCheckin.getBookOrderId());
         if(bookOrderId.longValue() != oldBookOrder.getId().longValue()){
         oldBookOrder.setStatus(0);
         bookOrderService.edit(oldBookOrder);
         //oldRoomType.setBookNum(oldRoomType.getBookNum() + 1);
         }
         }
         if(oldRoomType.getId().longValue() != newRoomType.getId().longValue()){
         newRoomType.setBookNum(newRoomType.getBookNum() - 1);

         if(existCheckin.getBookOrderId() == null){
         oldRoomType.setAvilableNum(oldRoomType.getAvilableNum() + 1);
         }else{
         oldRoomType.setBookNum(oldRoomType.getBookNum() + 1);
         }
         }
         BookOrder newBookOrder = bookOrderService.find(bookOrderId);
         newBookOrder.setStatus(1);
         bookOrderService.edit(newBookOrder);
         }**/
        roomTypeService.updateRoomTypeNum(newRoomType);
        roomTypeService.updateRoomTypeNum(oldRoomType);
        //判断房间是否发生变化
        if(checkin.getRoomId().longValue() != existCheckin.getRoomId().longValue()){
            //表示房间发生了变化
            Room oldRoom = roomService.findRoom(existCheckin.getRoomId());
            Room newRoom = roomService.findRoom(checkin.getRoomId());
            oldRoom.setStatus(0);//原来的房间可入住
            newRoom.setStatus(1);//现在的房间已入住
            roomService.editRoom(newRoom);
            roomService.editRoom(oldRoom);
        }
        ret.put("type", "success");
        ret.put("msg", "修改成功!");
        return ret;
    }

    /**
     * 分页查询入住信息
     * @return
     */
    @SysLog(value = "查询入住信息")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> list(
            Checkin checkin,
            @RequestParam(name="page",defaultValue= "1") Integer page ,
            @RequestParam(name="rows",defaultValue= "20") Integer rows
    ){
        Map<String,Object> ret = new HashMap<String, Object>();
        PageHelper.startPage(page,rows);
        PageInfo pageInfo = new PageInfo (checkinService.findCheckinList(checkin));
        List list = pageInfo.getList();
        ret.put("rows",list);
        ret.put("total", pageInfo.getTotal());
        return ret;
    }

    /**
     * 退房操作
     * @param checkId
     * @return
     */
    @SysLog(value = "退房")
    @RequiresPermissions("checkin:delete")
    @RequestMapping(value = "/checkout",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> checkout(Long checkId
    ){
        Map<String, String> ret = new HashMap<String, String>();
        if(checkId == null){
            ret.put("type", "error");
            ret.put("msg", "请选择数据!");
            return ret;
        }
        Checkin checkin = checkinService.findCheckin(checkId);
        if(checkin == null){
            ret.put("type", "error");
            ret.put("msg", "请选择正确的数据!");
            return ret;
        }
        checkin.setStatus(1);
        if(checkinService.editCheckin(checkin) <= 0){
            ret.put("type", "error");
            ret.put("msg", "退房失败，请联系管理员!");
            return ret;
        }
        //首先操作房间状态
        Room room = roomService.findRoom(checkin.getRoomId());
        if(room != null){
            room.setStatus(2);
            roomService.editRoom(room);
        }
        //其次修改房型可用数、入住数、状态
        RoomType roomType = roomTypeService.findRoomType(checkin.getRoomTypeId());
        if(roomType != null){
            roomType.setAvilableNum(roomType.getAvilableNum() + 1);
            if(roomType.getAvilableNum() > roomType.getRoomNum()){
                roomType.setAvilableNum(roomType.getRoomNum());
            }
            roomType.setLivedNum(roomType.getLivedNum() - 1);
            if(roomType.getStatus() == 0){
                roomType.setStatus(1);
            }
            if(checkin.getBookOrderId() != null){
                roomType.setBookNum(roomType.getBookNum() - 1);
            }
            roomTypeService.updateRoomTypeNum(roomType);
            roomTypeService.editRoomType(roomType);
        }
        //判断是否来自预定
        if(checkin.getBookOrderId() != null){
            BookOrder bookOrder = bookOrderService.findBookOrder(checkin.getBookOrderId());
            bookOrder.setStatus(2);
            bookOrderService.editBookOrder(bookOrder);

        }
        ret.put("type", "success");
        ret.put("msg", "退房成功!");
        return ret;
    }

    /**
     * 根据房间类型获取房间
     * @param roomTypeId
     * @return
     */
    @RequestMapping(value = "/load_room_list",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> load_room_list(Long roomTypeId){
        List<Map<String, Object>> retList = new ArrayList<Map<String,Object>>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        Room qrRoom = new Room();
        qrRoom.setRoomTypeId(roomTypeId);
        qrRoom.setStatus(0);

        List<Room> roomList = roomService.findListRoom(qrRoom);
        for(Room room:roomList){
            Map<String, Object> option = new HashMap<String, Object>();
            option.put("value", room.getId());
            option.put("text", room.getSn());
            retList.add(option);
        }
        return retList;
    }
}
