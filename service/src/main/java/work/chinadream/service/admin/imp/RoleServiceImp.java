package work.chinadream.service.admin.imp;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.chinadream.dao.admin.RoleMapper;
import work.chinadream.dao.admin.RoleResourceMapper;
import work.chinadream.dao.admin.UserMapper;
import work.chinadream.dao.admin.UserRoleMapper;
import work.chinadream.entity.admin.Role;
import work.chinadream.entity.admin.RoleResource;
import work.chinadream.entity.admin.User;
import work.chinadream.entity.common.Tree;
import work.chinadream.service.admin.IRoleService;
import work.chinadream.util.PageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */
@Service
public class RoleServiceImp extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    RoleMapper roleDao ;
    @Autowired
    RoleResourceMapper roleResourceDao ;
    @Autowired
    UserRoleMapper userRoleDao ;

    /**
     * 查询角色列表
     * @param pageInfo
     */
    public void findDataGrid(PageInfo pageInfo) {
        //查询角色信息
        pageInfo.setRows(roleDao.findRolePageCondition(pageInfo));
        //查询 信息总条数
        pageInfo.setTotal(roleDao.findRolePageCount(pageInfo));
    }

    /**
     * 根据权限id查询资源集合
     *
     * @param id
     * @return
     */
   public List<Long> findResourceIdListByRoleId(Long id) {
        return roleDao.findResourceIdListByRoleId(id);
    }

    /**
     * 更新权限和资源的关联关系
     *
     * @param id
     * @param resourceIds
     */
    public void updateRoleResource(Long id, String resourceIds) {
        // 删除之前的的资源
        List<Long> roleResourceIdList = roleDao.findRoleResourceIdListByRoleId(id);
        if (roleResourceIdList != null && (!roleResourceIdList.isEmpty())) {
            roleResourceDao.deleteBatchIds(roleResourceIdList);
        }
        // 类型不同, 虽然名称一样, 但是不会报错
        // 添加现在已经选择的资源
        List<RoleResource> batch = new ArrayList<>();
        String[] resourcesIds = resourceIds.split(",");
        for (String resourcesId : resourcesIds) {
            RoleResource roleResource = new RoleResource();
            roleResource.setRoleId(id);
            roleResource.setResourceId(Long.parseLong(resourcesId));
            batch.add(roleResource);
        }
        roleResourceDao.batchInsert(batch);
    }

    /**
     * 添加 角色
     * @param role
     */
    public void addRole(Role role) {
        roleDao.insert(role);
    }

    /**
     * 根据id查询角色
     * @param id
     */
    public  Role findRoleById(Long id){
        return  roleDao.selectById(id);
    }

    /**
     * 更新角色信息
     * @param role
     */
  public void updateRole(Role role){
        roleDao.update(role,new QueryWrapper<Role>().eq("id",role.getId()));
    }

    /**
     * 删除角色信息
     * @param id
     */
    public void deleteRoleById(Long id){
        // 删除角色信息的同时 把 角色资源表 中的所对应的信息删除
        List<Long> roleResourceIdList = roleDao.findRoleResourceIdListByRoleId(id);
        if (roleResourceIdList != null && (!roleResourceIdList.isEmpty())) {
            roleResourceDao.deleteBatchIds(roleResourceIdList);
        }
        roleDao.deleteById(id);
    }

    /**
     * 加载权限树
     */
    public List<Tree> findTree() {
        List<Tree> trees = new ArrayList<Tree>();
        List<Role> roles = roleDao.selectList(new QueryWrapper<Role>());
        for (Role role : roles) {
            Tree tree = new Tree();
            tree.setId(role.getId());
            tree.setText(role.getName());
            trees.add(tree);
        }
        return trees;
    }

    /**
     * 根据用户查询id查询权限集合
     *
     * @param userId
     * @return
     */
  public List<Long> findRoleIdListByUserId(Long userId){
        return userRoleDao.findRoleIdListByUserId( userId ) ;
    };

    /**
     * 根据权限查询id查询资源路径集合
     *
     * @param roleId
     * @return
     */
    public List<Map<Long, String>> findRoleResourceListByRoleId(Long roleId){
        return roleDao.findRoleResourceListByRoleId(roleId);
    };
}