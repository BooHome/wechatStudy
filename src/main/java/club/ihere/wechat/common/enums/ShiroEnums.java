package club.ihere.wechat.common.enums;

import lombok.Data;

/**
 * @author fengs
 */
public class ShiroEnums {

    /**
     *
     */
    public enum UserStatusEnum {

        /**
         * 用户状态禁用
         */
        CLOSE_STATUS(0,"禁用"),
        /**
         * 用户启用
         */
        OPEN_STATUS(1,"启用");

        UserStatusEnum(Integer value, String description) {
            this.value = value;
            this.description = description;
        }

        private Integer value;
        private String description;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

}
