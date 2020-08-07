package work.chinadream.dao.admin;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import work.chinadream.entity.admin.UserRole;

import java.util.List;
/**         用户角色
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<Long> findUserRoleIdByUserId(Long userId);
    // 批量插入
    int batchInsert(List userRolesList);

    List<Long> findRoleIdListByUserId(Long userId);


}