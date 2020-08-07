package work.chinadream.service.admin.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.chinadream.dao.admin.LogMapper;
import work.chinadream.entity.admin.Log;
import work.chinadream.service.admin.ILogService;
import work.chinadream.util.PageInfo;

import java.util.List;
/**
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */
@Service
public class LogServiceImp extends ServiceImpl<LogMapper, Log>   implements ILogService {

    @Autowired
    private LogMapper logDao;

    public void insertLog(Log sysLog) {
        logDao.insert(sysLog);
    }

    public void findDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(logDao.findDataGrid(pageInfo));
        pageInfo.setTotal(logDao.findDataGridCount(pageInfo));
    }



    public void batchDelete(List ids){
        logDao.deleteBatchIds(ids);
    }

   public Log selectById(Long id){
        return logDao.selectById(id) ;
    }

     public int delByDate(String date){
        int count = logDao.delLogCount(date);
        logDao.delByDate(date);
        return count ;
    }

}
