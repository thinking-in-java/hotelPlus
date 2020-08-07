package work.chinadream.service.admin;


import com.baomidou.mybatisplus.extension.service.IService;
import work.chinadream.entity.admin.RoomType;

import java.util.List;
import java.util.Map;

/**             房型
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/19 23:49
 */
public interface IRoomTypeService  extends IService<RoomType> {
    /**
     * 添加
     * @param roomType
     * @return
     */
    public int addRoomType(RoomType roomType);

    /**
     * 编辑
     * @param roomType
     * @return
     */
    public int editRoomType(RoomType roomType);

    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteRoomType(Long id);

    /**
     * 带条件查询
     * @param queryMap
     * @return
     */
    public List<RoomType> findRoomTypeList(String name,Integer status);

    /**
     * 查询所有
     * @return
     */
    public List<RoomType> findAllRoomType();


    /**
     * 查询某个房间
     * @param id
     * @return
     */
    public RoomType findRoomType(Long id);

    /**
     *房间类型 可用数量修改
     * @param roomType
     * @return
     */
    public int updateRoomTypeNum(RoomType roomType);
}
