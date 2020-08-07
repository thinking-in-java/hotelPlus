package work.chinadream.entity.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 21:25
 */
@Getter
@Setter
@ToString
public class BookOrder {
    private Long id;//预定订单id
    private Long accountId;//客户id
    private Long roomTypeId;//房型id
    private String name;//预定者姓名
    private String idCard;//身份证号码
    private String mobile;//手机号
    private Integer status;//状态：0：预定中，1：已入住,2:已结算离店
    private Integer payStatus;//支付状态    0:未支付   1:已支付
    private String arriveDate;//入住日期
    private String leaveDate;//离店日期
    private Date createTime;//预定日期
    private String remark;
}




