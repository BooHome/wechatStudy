package club.ihere.wechat.bean.pojo.shiro;

import java.io.Serializable;

public class SysRole implements Serializable {

    private static final long serialVersionUID = 123456781L;
    private Integer id;

    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }
}