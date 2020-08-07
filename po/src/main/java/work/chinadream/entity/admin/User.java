package work.chinadream.entity.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**         后台管理员用户
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/14 15:42
 */
@Getter
@Setter
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 6700813629656881143L;

    private Long id;

    private String loginname;

    private String name;

    private String password;

    private Integer sex;

    private Integer age;

    private Integer usertype;

    private Integer status;

    private String organizationId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;

    private String phone;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastlogintime;
}