package work.chinadream.dao.admin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import work.chinadream.entity.admin.RoleResource;

import java.util.List;
/**         角色,资源
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */
public interface RoleResourceMapper extends BaseMapper<RoleResource> {


    /**
     * 批量添加角色资源关联
     * @param roleResource
     * @return
     */
    int batchInsert(List<RoleResource> roleResource);





}
