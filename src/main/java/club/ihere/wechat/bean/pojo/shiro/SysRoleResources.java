package club.ihere.wechat.bean.pojo.shiro;

import java.io.Serializable;

public class SysRoleResources implements Serializable {

    private static final long serialVersionUID = 123456782L;
    private Integer id;

    private Integer roleId;

    private Integer resourcesId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(Integer resourcesId) {
        this.resourcesId = resourcesId;
    }
}