package work.chinadream.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import work.chinadream.entity.admin.Resource;
import work.chinadream.entity.admin.Role;
import work.chinadream.entity.admin.User;
import work.chinadream.entity.common.LeftMenu;
import work.chinadream.entity.common.Tree;

import java.util.List;
/**
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */
public interface IResourceService  extends IService<Resource> {

    /**
     * 查询树形菜单列表 (Layui)
     * @return
     */
    public List<LeftMenu> findMenuTree(User user) ;
    /**
     * 查询树形菜单列表 (Easyui)
     * @return
     */
    public List<Tree> findMenuTree2(User user) ;
    /**
     * 查找二级树
     * @return
     */
    public List<Tree> findAllTree() ;
    /**
     * 查找三级树
     * @return
     */
    public List<Tree> findAllTrees() ;
    /**
     * 查找所有资源
     * @return
     */
    public List<Resource> findResourceAll();
    /**
     * 根据id查询资源
     * @param id
     * @return
     */
    public Resource findResourceById(Long id) ;
    /**
     * 更新资源
     * @param resource
     */
    public void updateResource(Resource resource) ;
    /**
     * 根据id删除资源
     * @param id
     */
    public void deleteResourceById(Long id) ;
    /**
     * 添加资源
     * @param resource
     */
   public void addResource(Resource resource);

}