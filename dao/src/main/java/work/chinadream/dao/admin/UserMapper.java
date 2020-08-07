package work.chinadream.dao.admin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import work.chinadream.entity.admin.User;
import work.chinadream.entity.admin.UserVo;
import work.chinadream.util.PageInfo;

import java.util.List;
/**         后台用户
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 用户列表
     */
    List findUserPageCondition(PageInfo pageInfo);
    /**
     * 统计用户
     */
    int findUserPageCount(PageInfo pageInfo);

    /**
     * 根据 Id查找 用户
     */
    UserVo findUserVoById(Long id);

    /**
     * 修改用户密码
     * @param userId
     * @param pwd
     */
    void updateUserPwdById(@Param("userId") Long userId, @Param("pwd") String pwd) ;
}
