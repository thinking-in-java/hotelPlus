package work.chinadream.dao.admin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import work.chinadream.entity.admin.Resource;
import work.chinadream.entity.admin.Role;
import work.chinadream.util.PageInfo;

import java.util.List;
import java.util.Map;
/**         角色
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查询角色列表
     *
     * @param pageInfo
     * @return
     */
    List findRolePageCondition(PageInfo pageInfo);

    /**
     * 角色统计
     *
     * @param pageInfo
     * @return
     */
    int findRolePageCount(PageInfo pageInfo);
    /**
     * 根据角色查询资源id列表
     *
     * @param id
     * @return
     */
    List<Long> findResourceIdListByRoleId(Long id);

    /**
     * 根据角色id查询资源角色关联id列表
     * @param
     * @return
     */
   List<Long> findRoleResourceIdListByRoleId(Long id);




    /**
     * 通过角色 Id 资源路径
     * @param roleId
     * @return
     */
    List<Map<Long, String>> findRoleResourceListByRoleId(Long roleId);

    /**
     * 查询角色下的菜单列表
     *
     * @param i
     * @return
     */
    List<Resource> findResourceIdListByRoleIdAndType(Long i);

}
