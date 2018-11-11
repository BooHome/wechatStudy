package club.ihere.wechat.configuration.shiro;

import club.ihere.wechat.bean.pojo.shiro.SysUser;
import club.ihere.wechat.common.enums.ShiroEnums;
import club.ihere.wechat.service.shiro.UserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author:
 * @date: 2018/5/25
 * @description: 登陆次数限制
 */
public class RetryLimitHashedCredentialsMatcher extends SimpleCredentialsMatcher {

    private static final Logger logger = LoggerFactory.getLogger(RetryLimitHashedCredentialsMatcher.class);

    public static final String DEFAULT_RETRYLIMIT_CACHE_KEY_PREFIX = "shiro:cache:retrylimit:";

    private String keyPrefix = DEFAULT_RETRYLIMIT_CACHE_KEY_PREFIX;

    @Autowired
    private UserService userService;

    private RedisManager redisManager;

    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

    private String getRedisKickoutKey(String username) {
        return this.keyPrefix + username;
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //获取用户名
        String username = (String)token.getPrincipal();
        //获取用户登录次数
        AtomicInteger retryCount = (AtomicInteger)redisManager.get(getRedisKickoutKey(username));
        if (retryCount == null) {
            //如果用户没有登陆过,登陆次数加1 并放入缓存
            retryCount = new AtomicInteger(0);
        }
        if (retryCount.incrementAndGet() > 5) {
            //如果用户登陆失败次数大于5次 抛出锁定用户异常  并修改数据库字段
            SysUser user = userService.findByUserName(username);
            if (user != null && ShiroEnums.UserStatusEnum.OPEN_STATUS.getValue().equals(user.getUserEnable())){
                //数据库字段 默认为 0  就是锁定状态
                //修改数据库的状态字段为锁定
                user.setUserEnable(ShiroEnums.UserStatusEnum.CLOSE_STATUS.getValue());
                userService.update(user);
            }
            logger.info("锁定用户" + user.getUserName());
            //抛出用户锁定异常
            throw new LockedAccountException();
        }
        //判断用户账号和密码是否正确
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            //如果正确,从缓存中将用户登录计数 清除
            redisManager.del(getRedisKickoutKey(username));
        }else{
            redisManager.set(getRedisKickoutKey(username), retryCount);
        }
        return matches;
    }

    /**
     * 根据用户名 解锁用户
     * @param username
     * @return
     */
    public void unlockAccount(String username){
        SysUser user = userService.findByUserName(username);
        if (user != null){
            //修改数据库的状态字段为正常
            user.setUserEnable(ShiroEnums.UserStatusEnum.OPEN_STATUS.getValue());
            userService.update(user);
            redisManager.del(getRedisKickoutKey(username));
        }
    }

}

