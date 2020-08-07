package work.chinadream.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import work.chinadream.entity.admin.Role;
import work.chinadream.entity.admin.User;
import work.chinadream.entity.common.Tree;
import work.chinadream.util.PageInfo;

import java.util.List;
import java.util.Map;
/**
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */
public interface IRoleService extends IService<Role> {
    public void findDataGrid(PageInfo pageInfo);
    public List<Long> findResourceIdListByRoleId(Long id);
    public void updateRoleResource(Long id, String resourceIds) ;
    public void addRole(Role role);
    public  Role findRoleById(Long id);
    public void updateRole(Role role);
    public void deleteRoleById(Long id);
    public List<Tree> findTree();
    public List<Long> findRoleIdListByUserId(Long userId);
    public List<Map<Long, String>> findRoleResourceListByRoleId(Long roleId);
}