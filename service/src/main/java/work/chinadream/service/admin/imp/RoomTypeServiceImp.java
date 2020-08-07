package work.chinadream.service.admin.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.chinadream.dao.admin.RoomTypeMapper;
import work.chinadream.entity.admin.RoomType;
import work.chinadream.service.admin.IRoomTypeService;

import java.util.List;
import java.util.Map;

/**
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/19 23:56
 */
@Service
public class RoomTypeServiceImp extends ServiceImpl<RoomTypeMapper, RoomType> implements IRoomTypeService {
    @Autowired
    private RoomTypeMapper roomTypeMapper;

    @Override
    public int addRoomType(RoomType roomType) {
        return roomTypeMapper.insert(roomType);
    }

    @Override
    public int editRoomType(RoomType roomType) {
        return roomTypeMapper.update(roomType, new QueryWrapper<RoomType>().eq("id", roomType.getId()));
    }

    @Override
    public int deleteRoomType(Long id) {
        return roomTypeMapper.deleteById(id);
    }

    @Override
    public List<RoomType> findRoomTypeList(String name, Integer status) {
        QueryWrapper<RoomType> qr = new QueryWrapper<>();
        if (name != null) {
            qr.like("name", name);
        }
        if (status != null) {
            qr.eq("status", status);
        }
        return roomTypeMapper.selectList(qr);
    }

    @Override
    public List<RoomType> findAllRoomType() {
        return roomTypeMapper.selectList(new QueryWrapper<RoomType>());
    }

    @Override
    public RoomType findRoomType(Long id) {
        return roomTypeMapper.selectById(id);
    }

    @Override
    public int updateRoomTypeNum(RoomType roomType) {


        return roomTypeMapper.update(roomType,new QueryWrapper<RoomType>().eq("id",roomType.getId()));
    }








}
