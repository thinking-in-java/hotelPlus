package work.chinadream.service.admin.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.chinadream.dao.admin.RoomMapper;
import work.chinadream.entity.admin.Room;
import work.chinadream.service.admin.IRoomService;

import java.util.List;
import java.util.Map;

/**
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 9:30
 */
@Service
public class RoomServiceImp extends ServiceImpl<RoomMapper, Room> implements IRoomService {
    @Autowired
    private RoomMapper roomMapper;
    /**
     * 添加
     * @param room
     * @return
     */
    @Override
    public int addRoom(Room room) {
        return roomMapper.insert(room);
    }

    /**
     * 修改
     * @param room
     * @return
     */
    @Override
    public int editRoom(Room room) {
        return roomMapper.updateById(room);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public int deleteRoom(Long id) {
        return roomMapper.deleteById(id);
    }

    /**
     * 待条件查询所有
     * @param room
     * @return
     */
    @Override
    public List<Room> findListRoom(Room room) {
        QueryWrapper<Room> queryWrapper = new QueryWrapper();
        if (room.getRoomTypeId()!=null){
            queryWrapper.eq("room_type_id",room.getRoomTypeId());
        }
        if(room.getFloorId()!=null){
            queryWrapper.eq("floor_id",room.getFloorId());
        }
        if (room.getSn()!=null){
            queryWrapper.like("sn",room.getSn());
        }
        if (room.getStatus()!=null){
            queryWrapper.eq("status",room.getStatus());
        }
        return roomMapper.selectList(queryWrapper);
    }



    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Room> findAllRoom() {
        return roomMapper.selectList(new QueryWrapper<Room>());
    }


    /**
     * 查询某个房间
     * @param id
     * @return
     */
    @Override
    public Room findRoom(Long id) {
        return roomMapper.selectById(id);
    }

    /**
     * 根据房间编号查询某个房间
     * @param sn
     * @return
     */
    @Override
    public Room findBySn(String sn) {
        return roomMapper.selectOne(new QueryWrapper<Room>().eq("sn",sn));
    }
}
