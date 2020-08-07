package work.chinadream.service.admin.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.chinadream.dao.admin.FloorMapper;
import work.chinadream.entity.admin.Floor;
import work.chinadream.service.admin.IFloorService;

import java.util.List;
import java.util.Map;

/**
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/19 17:53
 */
@Service
public class FloorServiceImp extends ServiceImpl<FloorMapper, Floor> implements IFloorService {
    @Autowired
    private FloorMapper floorMapper;

    /**
     * 添加楼层
     *
     * @param floor
     * @return
     */
    @Override
    public int addFloor(Floor floor) {
        return floorMapper.insert(floor);
    }

    /**
     * 编辑
     *
     * @param floor
     * @return
     */
    @Override
    public int editFloor(Floor floor) {
        return floorMapper.update(floor, new QueryWrapper<Floor>().eq("id", floor.getId()));
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteFloor(Long id) {
        return floorMapper.deleteById(id);
    }

    /**
     * 带条件查询
     *
     * @param name
     * @return
     */
    @Override
    public List<Floor> findFloorList(String name) {
        QueryWrapper<Floor> qr = new QueryWrapper<>();
        if (name!= null) {
            qr.like("name", name);
        }
        return floorMapper.selectList(qr);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Floor> findFloorAll() {
        return floorMapper.selectList(new QueryWrapper<Floor>());
    }

}
