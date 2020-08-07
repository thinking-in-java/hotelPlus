package work.chinadream.service.admin.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.chinadream.dao.admin.OrganizationMapper;
import work.chinadream.entity.admin.Organization;
import work.chinadream.entity.common.Tree;
import work.chinadream.service.admin.IOrganizationService;

import java.util.ArrayList;
import java.util.List;
/**
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */
@Service
public class OrganizationServiceImp extends ServiceImpl<OrganizationMapper, Organization> implements IOrganizationService {

    @Autowired
    private OrganizationMapper organizationDao ;

    /**
     * @description
     * 查找用户管理中组织机构的资源树
     * 第一步: 先加载父资源
     * 第二步: 通过父资源的 id 查询子资源
     * 加入到 实体层中
     * @return
     */
    public List<Tree> findTree(){
        List<Tree> trees = new ArrayList<Tree>();
        // 查找父资源的信息 ;
        List<Organization> organizationFather = organizationDao.findOrganizationAllByPidNull();
        if (organizationFather != null){
            for (Organization organizationOne : organizationFather){
                Tree treeOne = new Tree();
                treeOne.setId(organizationOne.getId());
                treeOne.setText(organizationOne.getName());
                treeOne.setIconCls(organizationOne.getIcon());

                List<Organization> organizationSon = organizationDao.findOrganizationAllByPid(organizationOne.getId());
                if (organizationSon != null){
                    List<Tree> tree = new ArrayList<Tree>();
                    for (Organization organizationTwo : organizationSon ){
                        Tree treeTwo = new Tree();
                        treeTwo.setId(organizationTwo.getId());
                        treeTwo.setText(organizationTwo.getName());
                        treeTwo.setIconCls(organizationTwo.getIcon());
                        tree.add(treeTwo);
                    }
                    treeOne.setChildren(tree);
                } else {
                    treeOne.setState("closed");
                }
                trees.add(treeOne);
            }
        }
        return trees ;
    }

    public List<Organization> findTreeGrid() {
        return organizationDao.selectList(new QueryWrapper<Organization>().orderByAsc("seq"));
    }


    public void addOrganization(Organization organization) {
        organizationDao.insert(organization);
    }

   public Organization findOrganizationById(Long id) {
        return organizationDao.selectById(id);
    }

  public void updateOrganization(Organization organization) {
        organizationDao.updateById(organization);
    }
      public void deleteOrganizationById(Long id) {
        organizationDao.deleteById(id);
    }



}