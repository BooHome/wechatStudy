package club.ihere.wechat.bean.pojo.shiro;

import java.io.Serializable;

public class SysUser implements Serializable {

    private static final long serialVersionUID = 123456783L;
    private Integer id;

    private String userName;

    private String passWord;

    private Integer userEnable;

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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public Integer getUserEnable() {
        return userEnable;
    }

    public void setUserEnable(Integer userEnable) {
        this.userEnable = userEnable;
    }
}