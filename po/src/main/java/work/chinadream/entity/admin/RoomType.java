package work.chinadream.entity.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**             房间实体类
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/19 23:03
 */
@Getter
@Setter
@ToString
public class RoomType {
    private Long id;//房间类型id
    private String name;//房间名称
    private String photo;//房间类型图片
    private Float price;//房型价格
    private Integer liveNum;//可住人数
    private Integer bedNum;//床位数
    private Integer roomNum;//房间数
    private Integer avilableNum;//可住或可预定房间数
    private Integer bookNum;//预定数
    private Integer livedNum;//已经入住数
    private int status;//房型状态，0：房型已满,1:可预订可入住
    private String remark;//房型备注

}
