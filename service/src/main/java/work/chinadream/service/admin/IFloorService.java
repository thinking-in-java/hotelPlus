package work.chinadream.service.admin;


import com.baomidou.mybatisplus.extension.service.IService;
import work.chinadream.entity.admin.Floor;
import work.chinadream.entity.admin.Log;

import java.util.List;
import java.util.Map;

/**
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/19 17:52
 */
public interface IFloorService extends IService<Floor> {
    /**
     * 添加楼层
     * @param floor
     * @return
     */
    public int addFloor(Floor floor);

    /**
     * 编辑楼层
     * @param floor
     * @return
     */
    public int editFloor(Floor floor);

    /**
     * 删除楼层
     * @param id
     * @return
     */
    public int deleteFloor(Long id);

    /**
     * 带条件查询所有楼层
     * @param name
     * @return
     */
    public List<Floor> findFloorList(String name);

    /**
     * 查询所有楼层
     * @return
     */
    public List<Floor> findFloorAll();

}
