package work.chinadream.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import work.chinadream.service.admin.ICheckinService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/22 10:27
 */

@RequestMapping("/stats")
@Controller
public class StatsController {


    @Autowired
    private ICheckinService checkinService;

    /**
     * 统计页面
     * @param model
     * @return
     */
   @RequestMapping(value = "/stats",method = RequestMethod.GET)
    public ModelAndView stats(ModelAndView model){
        model.setViewName("admin/stats/stats");
        return model;
    }

    /**
     * 根据类型来获取统计数据
     * @param type
     * @return
     */
    @RequestMapping(value = "/get_stats",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getStats(String type){
        Map<String, Object> ret = new HashMap<String, Object>();
        if(StringUtils.isEmpty(type)){
            ret.put("type", "error");
            ret.put("msg", "请选择统计类型!");
            return ret;
        }
        switch (type) {
            case "month":{
                ret.put("type", "success");
                ret.put("content", getStatsValue(checkinService.getStatsByMonth()));
                return ret;
            }
            case "day":{
                ret.put("type", "success");
                ret.put("content", getStatsValue(checkinService.getStatsByDay()));
                return ret;
            }
            default:{
                ret.put("type", "error");
                ret.put("msg", "请选择正确的统计类型!");
                return ret;
            }
        }
    }

    /**
     * 把数据的键和值分开保存
     * @param statsValue
     * @return
     */
    private Map<String, Object> getStatsValue(List<Map> statsValue){
        Map<String, Object> ret = new HashMap<String, Object>();
        List<String> keyList = new ArrayList<String>();
        List<Float> valueList = new ArrayList<Float>();
        for(Map m:statsValue){
            keyList.add(m.get("stats_date").toString());
            valueList.add(Float.valueOf(m.get("money").toString()));
        }
        ret.put("keyList", keyList);
        ret.put("valueList", valueList);
        return ret;
    }
}
