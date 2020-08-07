package work.chinadream.service.admin.imp;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.chinadream.dao.admin.UserMapper;
import work.chinadream.dao.admin.UserRoleMapper;
import work.chinadream.entity.admin.User;
import work.chinadream.entity.admin.UserRole;
import work.chinadream.entity.admin.UserVo;
import work.chinadream.service.admin.IUserService;
import work.chinadream.util.PageInfo;

import java.util.*;
/**
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */
@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userDao ;
    @Autowired
    private UserRoleMapper userRoleDao ;


    /**
     * 根据用户id查询用户
     *
     * @param id
     * @return
     */
//    @DataSource(dataSource = "dataSource-slave")
    public User findUserById(Long id){
        return super.getById(id);
    };

    /**
     * @description 用户管理列表
     */
    public void findDataGrid(PageInfo pageInfo) {
        //用户列表
        pageInfo.setRows(userDao.findUserPageCondition(pageInfo));
        //统计用户
        pageInfo.setTotal(userDao.findUserPageCount(pageInfo));
    }

    /**
     * 根据用户 Id 查询用户信息
     */
    public UserVo findUserVoById(Long id){
        return userDao.findUserVoById(id);
    }

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    public User findUserByLoginName(String username){
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("loginname",username);
        return super.getOne(qw);
    };

    /**
     * 修改用户
     *
     * @param userVo
     */
    public void updateUser(UserVo userVo){
        User user = new User();
        //  TO - DO
        try {
            // 赋值相同的属性到 User实体层
            PropertyUtils.copyProperties(user, userVo);
        } catch (Exception e) {
            throw new RuntimeException("类型转换异常：{}", e);
        }
        super.update(user,new QueryWrapper<User>().eq("id",user.getId()));
        // 先删除
        Long id = userVo.getId();
        List<Long> userRolesIdList = userRoleDao.findUserRoleIdByUserId(id);
        if (userRolesIdList != null && (!userRolesIdList.isEmpty())) {
            userRoleDao.deleteBatchIds(userRolesIdList);
        }
        // 再更新
        String[] roleIds = userVo.getRoleIds().split(",");
        List<UserRole> batch= new ArrayList<UserRole>();
        for (String roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setUserId(id);
            userRole.setRoleId(Long.valueOf(roleId));
            batch.add(userRole);
        }
        userRoleDao.batchInsert(batch);
    };

    /**
     *
     *根据用户 Id 删除用户
     */
   public void deleteUserById(Long id){
        // 删除用户信息的同时 把 用户角色表 中的 id 删了
        List<Long> userRolesIdList = userRoleDao.findUserRoleIdByUserId(id);

        if (userRolesIdList != null && (!userRolesIdList.isEmpty())) {
            System.out.println(userRolesIdList);
            userRoleDao.deleteBatchIds(userRolesIdList);
        }
        userDao.deleteById(id);
    }

    /**
     * 添加用户
     */
   public void addUser(UserVo userVo) {
        User user = new User();

        try {
            // 赋值 userVo 中相同的属性到 user 中
            PropertyUtils.copyProperties(user, userVo);
        } catch (Exception e) {
            throw new RuntimeException("类型转换异常：{}", e);
        }
        userDao.insert(user);

        Long id = user.getId();
        String[] roles = userVo.getRoleIds().split(",");
        UserRole userRole = new UserRole();

        for (String string : roles) {
            userRole.setUserId(id);
            userRole.setRoleId(Long.valueOf(string));
            userRoleDao.insert(userRole);
        }
    }





    /**
     * 修改用户密码
     * @param userId
     * @param pwd
     */
    public void updateUserPwdById(Long userId, String pwd) {
        userDao.updateUserPwdById(userId, pwd);
    }


    /**
     * 编辑个人资料
     * @param userVo
     */
   public void updatePersonFile(UserVo userVo){
        User user = new User();
        //  TO - DO
        try {
            // 赋值相同的属性到 User实体层
            PropertyUtils.copyProperties(user, userVo);
        } catch (Exception e) {
            throw new RuntimeException("类型转换异常：{}", e);
        }
        userDao.update(user,new QueryWrapper<User>().eq("id",user.getId()));
    };

    /**
     * 通过id修改登录时间
     * @param user
     */
    @Override
    public void updateLastLoginTime(User user) {
        userDao.updateById(user);
    }
}