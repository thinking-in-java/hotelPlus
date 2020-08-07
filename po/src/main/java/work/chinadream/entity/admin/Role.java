package work.chinadream.entity.admin;


import java.io.Serializable;

/**
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */
public class Role implements Serializable {

    private static final long serialVersionUID = -1756241579303707517L;
    private Long id;

    private String name;

    private Integer seq;

    private String description;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RoleManage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seq=" + seq +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}