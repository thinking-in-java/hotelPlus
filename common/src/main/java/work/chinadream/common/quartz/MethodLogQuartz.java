package work.chinadream.common.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import work.chinadream.entity.admin.Log;
import work.chinadream.service.admin.ILogService;
import work.chinadream.util.DateUtil;
import work.chinadream.util.GlobalUtil;
import work.chinadream.util.MyUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**         定时清除系统日志任务
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */

@Component
public class MethodLogQuartz {

    @Autowired
    private ILogService logService;

    private static final Logger logger = LoggerFactory.getLogger(work.chinadream.common.quartz.MethodLogQuartz.class);
    private static final String LOGDAYS = "log.days";

    /** 
     * cron表达式：* * * * * *（共6位，使用空格隔开，具体如下） 
     * cron表达式：*(秒0-59) *(分 钟0-59) *(小时0-23) *(日期1-31) *(月份1-12或是JAN-DEC) *(星期1-7或是SUN-SAT) 
     */
    // 每天中午12点钟 触发
    @Scheduled(cron=" 0 0 12 * * ?")
    public void clearLog() {
        System.out.println("定时任务执行");
        try {
            // 获取 10天前的时间  天数可以在 global.properties 文件中设置
            int logDays = Integer.valueOf(GlobalUtil.getValue(LOGDAYS));
            Date date = DateUtil.getDate(DateUtil.getDate(), -logDays);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str=sdf.format(date);
            int count =logService.delByDate(str);
            Log log=new Log();
            log.setOperationName("定时删除日志："+count+"条");
            log.setOperationClass("");
            log.setOperationAddress("");
            log.setParams("");
            log.setIp("");
            log.setLoginName("系统自动任务");
            log.setCreateTime( MyUtil.getNowDateStr2());
            logService.insertLog(log);
        }catch (RuntimeException e){
            logger.error("定时删除失败:" + e.getMessage() );
        }
    }
}
