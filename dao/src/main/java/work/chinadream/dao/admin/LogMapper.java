package work.chinadream.dao.admin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import work.chinadream.entity.admin.Log;
import work.chinadream.util.PageInfo;

import java.util.List;
/**         日志
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */
public interface LogMapper extends BaseMapper<Log> {
    List findDataGrid(PageInfo pageInfo);
    int findDataGridCount(PageInfo pageInfo);
    void delByDate(String date);
    int delLogCount(String date);
}
