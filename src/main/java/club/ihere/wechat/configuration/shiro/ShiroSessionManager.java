package club.ihere.wechat.configuration.shiro;

import club.ihere.wechat.common.config.ConstantConfig;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author:
 * @date: 2018/6/23
 * @description: 解决单次请求需要多次访问redis
 */
public class ShiroSessionManager extends DefaultWebSessionManager {
    private static Logger logger = LoggerFactory.getLogger(DefaultWebSessionManager.class);

    private CacheManager cacheManager;

    public ShiroSessionManager() {
        super();
    }

    /**
     * 获取session
     * 优化单次请求需要多次访问redis的问题
     *
     * @param sessionKey
     * @return
     * @throws UnknownSessionException
     */
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        // 获取sessionId
        Serializable sessionId = getSessionId(sessionKey);
        ServletRequest request = null;
        // 在 Web 下使用 shiro 时这个 sessionKey 是 WebSessionKey 类型的
        // 若是在web下使用，则获取request
        if (sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }
        // 尝试从request中获取session
        if (request != null && sessionId != null) {
            Object sessionObj = request.getAttribute(sessionId.toString());
            if (sessionObj != null) {
                logger.info("从request获取到session:{}", sessionId);
                return (Session) sessionObj;
            }
        }
        // 若从request中获取session失败,则从redis中获取session,并把获取到的session存储到request中方便下次获取
        Session session = super.retrieveSession(sessionKey);
        if (request != null && sessionId != null) {
            logger.info("存储session到request中:{}", sessionId);
            request.setAttribute(sessionId.toString(), session);
        }
        return session;
    }

    @Override
    public void validateSessions() {
        if (logger.isInfoEnabled()) {
            logger.info("Validating all active sessions...");
        }
        int invalidCount = 0;
        Collection<?> activeSessions = getActiveSessions();
        if (activeSessions != null && !activeSessions.isEmpty()) {
            for (Iterator<?> i$ = activeSessions.iterator(); i$.hasNext(); ) {
                Session session = (Session) i$.next();
                try {
                    SessionKey key = new DefaultSessionKey(session.getId());
                    validate(session, key);
                } catch (InvalidSessionException e) {
                    if (cacheManager != null) {
                        SimpleSession s = (SimpleSession) session;
                        if (s.getAttribute(ConstantConfig.gerStringVal("shiro.setPrincipalIdFieldName")) != null) {
                            cacheManager.getCache(null).remove(
                                    s.getAttribute(ConstantConfig.gerStringVal("shiro.setPrincipalIdFieldName")));
                        }
                    }
                    if (logger.isDebugEnabled()) {
                        boolean expired = e instanceof ExpiredSessionException;
                        String msg = (new StringBuilder()).append(
                                "Invalidated session with id [").append(
                                session.getId()).append("]").append(
                                expired ? " (expired)" : " (stopped)")
                                .toString();
                        logger.debug(msg);
                    }
                    invalidCount++;
                }
            }

        }
        if (logger.isInfoEnabled()) {
            String msg = "Finished session validation.";
            if (invalidCount > 0) {
                msg = (new StringBuilder()).append(msg).append("  [").append(
                        invalidCount).append("] sessions were stopped.")
                        .toString();
            } else {
                msg = (new StringBuilder()).append(msg).append(
                        "  No sessions were stopped.").toString();
            }
            logger.info(msg);
        }
    }

    @Override
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;

    }
}
