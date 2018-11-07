package club.ihere.wechat.bean.pojo.shiro;

import java.io.Serializable;

public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 8841433872811285710L;

    private Integer id;

    private Integer userId;

    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}