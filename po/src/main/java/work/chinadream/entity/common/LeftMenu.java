package work.chinadream.entity.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@ToString
/**         菜单
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/14 15:42
 */
public class LeftMenu implements Serializable {
    /**
     * "title" : "二级菜单演示", "icon" : "&#xe61c;", "href" : "", "spread" : false,
     * "children" : [
     */
    private Long id;
    private String title;
    private String icon;
    private String href;
    private Boolean spread;
    private List<LeftMenu> children;
}
