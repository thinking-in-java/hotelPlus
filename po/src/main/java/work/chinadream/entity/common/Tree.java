package work.chinadream.entity.common;

import java.util.List;
/**
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */
// 根据 Tree 要具备的属性创建
public class Tree implements java.io.Serializable {

    private static final long serialVersionUID = 980682543891282923L;
    private Long id;
    private String text;
    private String state = "open"; // open,closed
    private boolean checked = false;
    private Object attributes;
    private List<Tree> children;
    private String iconCls;
    private String pid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

}
