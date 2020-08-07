package work.chinadream.service.admin;
import com.baomidou.mybatisplus.extension.service.IService;
import work.chinadream.entity.admin.Organization;
import work.chinadream.entity.common.Tree;
import java.util.List;
/**
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */
public interface IOrganizationService extends IService<Organization> {
    public List<Tree> findTree();
    public List<Organization> findTreeGrid() ;
    public void addOrganization(Organization organization) ;
     public Organization findOrganizationById(Long id) ;
     public void updateOrganization(Organization organization);
     public void deleteOrganizationById(Long id) ;
}