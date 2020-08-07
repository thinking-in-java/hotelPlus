package work.chinadream.service.admin.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.chinadream.dao.admin.CheckinMapper;
import work.chinadream.entity.admin.Checkin;
import work.chinadream.service.admin.ICheckinService;

import java.util.List;
import java.util.Map;

/**
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/21 12:31
 */
@Service("checkin")
public class CheckinServiceImp extends ServiceImpl<CheckinMapper, Checkin> implements ICheckinService {
@Autowired
private CheckinMapper checkinMapper;

    /**
     * 添加
     * @param checkin
     * @return
     */
    @Override
    public int addCheckin(Checkin checkin) {
        return checkinMapper.insert(checkin);
    }

    /**
     * 修改
     * @param checkin
     * @return
     */
    @Override
    public int editCheckin(Checkin checkin) {
        return checkinMapper.updateById(checkin);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public int deleteCheckin(Long id) {
        return checkinMapper.deleteById(id);
    }

    /**
     * 待条件查询
     * @param checkin
     * @return
     */
    @Override
    public List<Checkin> findCheckinList(Checkin checkin){
        QueryWrapper<Checkin> queryWrapper = new QueryWrapper<>();
        if (checkin.getName()!=null){
            queryWrapper.like("name",checkin.getName());
        }
        if (checkin.getStatus()!=null){
            queryWrapper.eq("status",checkin.getStatus());
        }
        if (checkin.getRoomId()!=null){
            queryWrapper.eq("room_id",checkin.getRoomId());
        }
        if (checkin.getRoomTypeId()!=null){
            queryWrapper.eq("room_type_id",checkin.getRoomTypeId());
        }
        if (checkin.getIdCard()!=null){
            queryWrapper.like("id_card",checkin.getIdCard());
        }
        if (checkin.getMobile()!=null){
            queryWrapper.like("mobile",checkin.getMobile());
        }
        return checkinMapper.selectList(queryWrapper);
    }


    /**
     * 查询某个 入住信息
     * @param id
     * @return
     */
    @Override
    public Checkin findCheckin(Long id) {
        return checkinMapper.selectById(id);
    }



    @Override
    public List<Map> getStatsByMonth() {
        return checkinMapper.selectStatsByMonth();
    }

    @Override
    public List<Map> getStatsByDay() {
        return  checkinMapper.selectStatsByDay();
    }
}
