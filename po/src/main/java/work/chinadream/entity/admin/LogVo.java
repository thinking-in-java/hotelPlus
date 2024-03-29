package work.chinadream.entity.admin;

import java.io.Serializable;
import java.util.Date;
/**
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */
public class LogVo implements Serializable{

    private static final long serialVersionUID = -4388138342174328269L;

    private Long id;
    // 用户名
    private String loginName;
    // 请求名称
    private String operationName;
    // 请求类
    private String operationClass;
    // 请求路径
    private String operationAddress;
    // 请求参数
    private String params;
    // 地址 ip
    private String ip;
    // 时间
    private String createTime;
    // 起始时间
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonIgnore
    private Date createdateStart;
    // 终止时间
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonIgnore
    private Date createdateEnd;
    // 选择的日期(年 / 月 / 日)
    private String time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName == null ? null : operationName.trim();
    }

    public String getOperationClass() {
        return operationClass;
    }

    public void setOperationClass(String operationClass) {
        this.operationClass = operationClass == null ? null : operationClass.trim();
    }

    public String getOperationAddress() {
        return operationAddress;
    }

    public void setOperationAddress(String operationAddress) {
        this.operationAddress = operationAddress == null ? null : operationAddress.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Date getCreatedateStart() {
        return createdateStart;
    }

    public void setCreatedateStart(Date createdateStart) {
        this.createdateStart = createdateStart;
    }

    public Date getCreatedateEnd() {
        return createdateEnd;
    }

    public void setCreatedateEnd(Date createdateEnd) {
        this.createdateEnd = createdateEnd;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
