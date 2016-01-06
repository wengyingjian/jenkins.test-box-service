/**
 * Copyright (c) 2016, 59store. All rights reserved.
 */
package com.store59.box.helper;

import com.store59.box.canstants.BoxConstant;
import com.store59.box.canstants.BoxMessageEnum;
import com.store59.box.helper.RabbitTemplateClient;
import com.store59.box.model.message.MessageBean;
import com.store59.box.model.message.MessageContent;
import com.store59.box.model.message.PersistenceObjectBean;
import com.store59.kylin.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author <a href="mailto:zhangwangy@59store.com">阿勇</a>
 * @version 1.0 16/1/4
 * @since 1.0
 */
@Repository
public class SendMQMessage {

    @Autowired
    private RabbitTemplateClient rabbitTemplateClient;

    // 推送消息状态（未读）
    private final int MESSAGE_STATUS = 0;

    public void sendMessage(BoxMessageEnum boxMessageEnum, Long uid, Map<String, String> params) {
        rabbitTemplateClient.sendMessgeToMq(buildMessageBean(buildPersistenceObjectBean(boxMessageEnum, uid, params)));
    }

    /**
     * 构建消息持久化对象
     *
     * @param boxMessageEnum
     * @param uid
     * @param params
     * @return
     */
    private PersistenceObjectBean buildPersistenceObjectBean(BoxMessageEnum boxMessageEnum, Long uid, Map<String, String> params) {

        PersistenceObjectBean persistenceObjectBean = new PersistenceObjectBean();
        persistenceObjectBean.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        // 消息设置为未读
        persistenceObjectBean.setStatus(MESSAGE_STATUS);
        persistenceObjectBean.setActionId(boxMessageEnum.getActionId());
        persistenceObjectBean.setCreateTime(System.currentTimeMillis() / 1000);
        persistenceObjectBean.setUid(uid);
        persistenceObjectBean.setTitle(boxMessageEnum.getTitle());
        String content = boxMessageEnum.getMessage().replace("{repoName}",params.get("repoName"));
        persistenceObjectBean.setContent(content);
        return persistenceObjectBean;
    }

    /**
     * 构建推送的消息主体信息
     *
     * @param persistenceObjectBean
     * @return
     */
    private MessageBean buildMessageBean(PersistenceObjectBean persistenceObjectBean) {
        MessageBean messageBean = new MessageBean();
        messageBean.setKey(BoxConstant.RABBIT_MESSAGE_KEY);
        messageBean.setType(BoxConstant.RABBIT_MESSAGE_TYPE);
        messageBean.setTarget(BoxConstant.RABBIT_MESSAGE_TARGET);
        // 构建消息内容
        MessageContent messageContent = new MessageContent();
        messageContent.setMsg(persistenceObjectBean.getContent());
        messageContent.setCode(persistenceObjectBean.getActionId());
        messageContent.setType(BoxConstant.RABBIT_MESSAGE_TARGET);
        messageContent.setData(StringUtils.isEmpty(persistenceObjectBean.getParam()) ? new HashMap<String, Object>()
                : JsonUtil.getObjectFromJson(persistenceObjectBean.getParam(), Map.class));
        messageBean.setContent(JsonUtil.getJsonFromObject(messageContent));
        messageBean.setUid(persistenceObjectBean.getUid());
        messageBean.setPersistence(persistenceObjectBean);
        return messageBean;
    }
}
