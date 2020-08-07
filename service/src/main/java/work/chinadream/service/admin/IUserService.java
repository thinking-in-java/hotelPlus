package work.chinadream.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import work.chinadream.entity.admin.User;
import work.chinadream.entity.admin.UserVo;
import work.chinadream.util.PageInfo;
/**
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */
public interface IUserService extends IService<User> {
    public User findUserById(Long id);
    public void findDataGrid(PageInfo pageInfo);
    public UserVo findUserVoById(Long id);
    public User findUserByLoginName(String username);
    public void updateUser(UserVo userVo);
    public void deleteUserById(Long id);
    public void addUser(UserVo userVo);
    public void updateUserPwdById(Long userId, String pwd);
    public void updatePersonFile(UserVo userVo);
    public void updateLastLoginTime(User user);
}
