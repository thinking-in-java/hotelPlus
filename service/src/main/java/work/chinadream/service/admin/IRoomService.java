package work.chinadream.service.admin;


import com.baomidou.mybatisplus.extension.service.IService;
import work.chinadream.entity.admin.Room;

import java.util.List;
import java.util.Map;

/**
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 9:22
 */
public interface IRoomService extends IService<Room> {
    /**
     * 添加房间
     * @param room
     * @return
     */
    public int addRoom(Room room);

    /**
     * 编辑房间
     * @param room
     * @return
     */
    public int editRoom(Room room);

    /**
     * 删除房间
     * @param id
     * @return
     */
    public int deleteRoom(Long id);

    /**
     * 带条件查询房间
     * @param room
     * @return
     */
    public List<Room> findListRoom(Room room);

    /**
     * 查询所有房间
     * @return
     */
    public List<Room> findAllRoom();


    /**
     * 查询某个房间
     * @param id
     * @return
     */
    public Room findRoom(Long id);

    /**
     *根据房间编号获取单个房间
     * @param sn
     * @return
     */
    public Room findBySn(String sn);
}
