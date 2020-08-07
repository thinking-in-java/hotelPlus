package work.chinadream.entity.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/19 21:49
 */
@Getter
@Setter
@ToString
public class Floor {
    private Long id;//楼层Id
    private String name;//楼层名称
    private String remark;//楼层备注
}
