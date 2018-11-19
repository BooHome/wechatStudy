package club.ihere.wechat.controller;

import club.ihere.common.message.BaseMsg;
import club.ihere.common.message.TextMsg;
import club.ihere.common.message.req.TextReqMsg;
import club.ihere.controller.BaseWechatController;
import club.ihere.wechat.common.config.WeChatConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fengshibo
 * @date: 2018/11/19 17:32
 * @description:
 */
@RestController
@RequestMapping("wechat")
public class WechatController extends BaseWechatController {

    private static Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Override
    protected String getToken() {
        return WeChatConfig.getToken();
    }

    @Override
    protected BaseMsg handleTextMsg(TextReqMsg msg) {
        String content = msg.getContent();
        logger.debug("用户发送到服务器的内容:{}", content);
        return new TextMsg("服务器回复用户消息!");
    }
}
