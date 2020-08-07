package work.chinadream.entity.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 9:19
 */
@ToString
@Getter
@Setter
public class Room {
    private Long id;//房间id
    private String photo;//房间图片
    private String sn;//房间编号
    private Long roomTypeId;//房型id
    private Long floorId;//所属楼层id
    private Integer status;//房型状态，0：可入住,1:已入住,2:打扫中
    private String remark;//房型备注
}
