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
import work.chinadream.entity.admin.Room;
import work.chinadream.service.admin.IFloorService;
import work.chinadream.service.admin.IRoomService;
import work.chinadream.service.admin.IRoomTypeService;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 9:47
 */
@Controller
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private IRoomTypeService roomTypeService;

    @Autowired
    private IRoomService roomService;

    @Autowired
    private IFloorService floorService;

    /**
     * 房间管理列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model) {
        model.addObject("roomTypeList", roomTypeService.findAllRoomType());
        model.addObject("floorList", floorService.findFloorAll());
        model.setViewName("admin/room/list");
        return model;
    }

    /**
     * 房间信息添加操作
     *
     * @param room
     * @return
     */
    @SysLog(value = "房间添加")
    @RequiresPermissions("room:add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(Room room) {
        Map<String, String> ret = new HashMap<String, String>();
        if (room == null) {
            ret.put("type", "error");
            ret.put("msg", "请填写正确的房间信息!");
            return ret;
        }
        if (StringUtils.isEmpty(room.getSn())) {
            ret.put("type", "error");
            ret.put("msg", "房间编号不能为空!");
            return ret;
        }
        if (room.getRoomTypeId() == null) {
            ret.put("type", "error");
            ret.put("msg", "请选择房间类型!");
            return ret;
        }
        if (room.getFloorId() == null) {
            ret.put("type", "error");
            ret.put("msg", "请选择房间所属楼层!");
            return ret;
        }
        if (isExist(room.getSn(), 0l)) {
            ret.put("type", "error");
            ret.put("msg", "该房间编号已经存在!");
            return ret;
        }
        if (roomService.addRoom(room) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "添加失败，请联系管理员!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "添加成功!");
        return ret;
    }

    /**
     * 房间信息编辑操作
     *
     * @param room
     * @return
     */
    @SysLog(value = "房间编辑")
    @RequiresPermissions("room:edit")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(Room room) {
        Map<String, String> ret = new HashMap<String, String>();
        if (room == null) {
            ret.put("type", "error");
            ret.put("msg", "请填写正确的房间信息!");
            return ret;
        }
        if (StringUtils.isEmpty(room.getSn())) {
            ret.put("type", "error");
            ret.put("msg", "房间编号不能为空!");
            return ret;
        }
        if (room.getRoomTypeId() == null) {
            ret.put("type", "error");
            ret.put("msg", "请选择房间类型!");
            return ret;
        }
        if (room.getFloorId() == null) {
            ret.put("type", "error");
            ret.put("msg", "请选择房间所属楼层!");
            return ret;
        }
        if (isExist(room.getSn(), room.getId())) {
            ret.put("type", "error");
            ret.put("msg", "该房间编号已经存在!");
            return ret;
        }
        if (roomService.editRoom(room) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "修改失败，请联系管理员!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "修改成功!");
        return ret;
    }

    /**
     * 分页查询房间信息
     *
     * @return
     */
    @SysLog(value = "房间查询")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(
            Room room,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "rows", defaultValue = "20") Integer rows) {
       /* Room room = new Room();
        room.setSn(sn);
        room.setStatus(status);
        room.setRoomTypeId(roomTypeId);
        room.setFloorId(floorId);*/
        Map<String, Object> ret = new HashMap<String, Object>();
        PageInfo pageInfo = new PageInfo(roomService.findListRoom(room));
        PageHelper.startPage(page, rows);

        ret.put("rows", pageInfo.getList());
        ret.put("total", pageInfo.getTotal());
        return ret;
    }

    /**
     * 房间信息删除操作
     *
     * @param id
     * @return
     */
    @SysLog(value = "房间删除")
    @RequiresPermissions("room:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete(Long id) {
        Map<String, String> ret = new HashMap<String, String>();
        if (id == null) {
            ret.put("type", "error");
            ret.put("msg", "请选择要删除的信息!");
            return ret;
        }
        try {
            if (roomService.deleteRoom(id) <= 0) {
                ret.put("type", "error");
                ret.put("msg", "删除失败，请联系管理员!");
                return ret;
            }
        } catch (Exception e) {
            // TODO: handle exception
            ret.put("type", "error");
            ret.put("msg", "该房间下存在订单信息，请先删除该房间下的所有订单信息!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "删除成功!");
        return ret;
    }

    /**
     * 判断房间编号是否存在
     *
     * @param sn
     * @param id
     * @return
     */
    private boolean isExist(String sn, Long id) {
        Room findBySn = roomService.findBySn(sn);
        if (findBySn == null) {
            return false;
        }
        if (findBySn.getId().longValue() == id.longValue()) {
            return false;
        }
        return true;
    }
}
