package work.chinadream.entity.admin;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 13:07
 */
@ToString
@Getter
@Setter
public class Account {
    private Long id;//客户id
    private String name;//客户登录名
    private String password;//客户登录密码
    private String realName;//真实姓名
    private String idCard;//身份证号码
    private String mobile;//手机号
    private String address;//联系地址
    @TableField(exist = false)
    private String validateCode;//验证码
    private Integer status;//状态：0：可用，-1：冻结
    private Integer qid;//qq的id
}



