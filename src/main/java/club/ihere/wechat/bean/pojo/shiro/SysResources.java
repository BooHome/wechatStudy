package club.ihere.wechat.bean.pojo.shiro;

import java.io.Serializable;

/**
 * @author
 */
public class SysResources implements Serializable {

    private static final long serialVersionUID = 8841433872811285796L;

    private Integer id;

    private String userName;

    private String resUrl;

    private Integer userType;

    private Integer parentId;

    private Integer userSort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl == null ? null : resUrl.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getUserSort() {
        return userSort;
    }

    public void setUserSort(Integer userSort) {
        this.userSort = userSort;
    }
}