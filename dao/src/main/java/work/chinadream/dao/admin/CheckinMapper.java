package work.chinadream.dao.admin;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import work.chinadream.entity.admin.Checkin;

import java.util.List;
import java.util.Map;

/**             用户入住
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/21 12:30
 */
public interface CheckinMapper extends BaseMapper<Checkin> {

    List<Map> selectStatsByMonth();

    List<Map> selectStatsByDay();
}
