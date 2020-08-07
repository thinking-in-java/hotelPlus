package work.chinadream.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import work.chinadream.entity.admin.Log;
import work.chinadream.util.PageInfo;

import java.util.List;
/**
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */
public interface ILogService extends IService<Log> {
    public void insertLog(Log sysLog);

    public void findDataGrid(PageInfo pageInfo);

    public void batchDelete(List ids);

    public Log selectById(Long id);

    public int delByDate(String date);

}
