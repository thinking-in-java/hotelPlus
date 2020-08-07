package work.chinadream.entity.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */
@Getter
@Setter
@ToString
public class RoleResource implements Serializable {

    private static final long serialVersionUID = -7250242744961556986L;

    private Long id;

    private Long roleId;

    private Long resourceId;


}