package work.chinadream.service.admin;


import com.baomidou.mybatisplus.extension.service.IService;
import work.chinadream.entity.admin.Checkin;

import java.util.List;
import java.util.Map;

/**
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/21 12:31
 */
public interface ICheckinService extends IService<Checkin> {
    /**
     * 添加入住
     * @param checkin
     * @return
     */
    public int addCheckin(Checkin checkin);

    /**
     * 修改入住
     * @param checkin
     * @return
     */
    public int editCheckin(Checkin checkin);

    /**
     * 删除入住
     * @param id
     * @return
     */
    public int deleteCheckin(Long id);

    /**
     * 带条件查询入住
     * @param checkin
     * @return
     */
    public List<Checkin> findCheckinList(Checkin checkin);


    /**
     * 查询单个入住信息
     * @param id
     * @return
     */
    public Checkin findCheckin(Long id);

    public List<Map> getStatsByMonth();
    public List<Map> getStatsByDay();
}
