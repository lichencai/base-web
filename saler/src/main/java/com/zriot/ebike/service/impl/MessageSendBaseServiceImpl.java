package com.zriot.ebike.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.SmsAttributes;
import com.aliyun.mns.model.TopicMessage;
import com.zriot.ebike.service.MessageSendBaseService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageSendBaseServiceImpl implements MessageSendBaseService {

    @Autowired
    MNSClient  mnsClient;
    /**
     * 阿里云发送短信的topic
     */
    private static final String topicInfo = "sms.topic-cn-shanghai";
    
    /**
     * @param phoneNumber 手机号码
     * @param paramMap 参数
     * @param 短信标题
     * @param templateCode 模板
     */
    @SuppressWarnings("rawtypes")
	@Override
    public TopicMessage sendMessage(String phoneNumber, Map<String, String> paramMap, String freeSignName, String templateCode) {
        CloudTopic topic = mnsClient.getTopicRef(topicInfo);

        //设置SMS消息体,其实没什么用，但不允许为空
        RawTopicMessage msg = new RawTopicMessage();
        msg.setMessageBody("sms-message");

        //设置短信签名和短信模板
        MessageAttributes messageAttributes = new MessageAttributes();
        SmsAttributes smsAttributes = new SmsAttributes();
        smsAttributes.setFreeSignName(freeSignName);
        smsAttributes.setTemplateCode(templateCode);

        //设置短信参数
        Set set = paramMap.keySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            String key = (String)iterator.next();
            smsAttributes.setSmsParam(key, paramMap.get(key));
        }

        smsAttributes.setReceiver(phoneNumber);
        messageAttributes.setSmsAttributes(smsAttributes);
        return send(topic, msg, messageAttributes);
    }

    @SuppressWarnings("rawtypes")
	@Override
    public TopicMessage batchSendMessageForIndependentParams(Map<String, Map<String, String>> phoneMap, String freeSignName, String templateCode) {
        CloudTopic topic = mnsClient.getTopicRef(topicInfo);

        //设置SMS消息体,其实没什么用，但不允许为空
        RawTopicMessage msg = new RawTopicMessage();
        msg.setMessageBody("sms-message");

        //设置短信签名和短信模板
        MessageAttributes messageAttributes = new MessageAttributes();
        BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
        batchSmsAttributes.setFreeSignName(freeSignName);
        batchSmsAttributes.setTemplateCode(templateCode);

        Set phoneSet = phoneMap.keySet();
        Iterator phoneIterator = phoneSet.iterator();
        while (phoneIterator.hasNext()) {
            String phone = (String)phoneIterator.next();

            //设置手机号码和参数
            Map<String, String> paramMap = phoneMap.get(phone);
            Set paramSet = paramMap.keySet();
            Iterator paramIterator = paramSet.iterator();
            BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
            while (paramIterator.hasNext()) {
                String key = (String)paramIterator.next();
                smsReceiverParams.setParam(key, paramMap.get(key));
            }
            batchSmsAttributes.addSmsReceiver(phone, smsReceiverParams);
        }
        messageAttributes.setBatchSmsAttributes(batchSmsAttributes);

        return send(topic, msg, messageAttributes);
    }

    @SuppressWarnings("rawtypes")
	@Override
    public TopicMessage batchSendMessageForSameParams(List<String> phoneNumberList, Map<String, String> paramMap, String freeSignName, String templateCode) {
        CloudTopic topic = mnsClient.getTopicRef(topicInfo);

        //设置SMS消息体,其实没什么用，但不允许为空
        RawTopicMessage msg = new RawTopicMessage();
        msg.setMessageBody("sms-message");

        //设置短信签名和短信模板
        MessageAttributes messageAttributes = new MessageAttributes();
        BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
        batchSmsAttributes.setFreeSignName(freeSignName);
        batchSmsAttributes.setTemplateCode(templateCode);

        //设置参数
        BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
        Set set = paramMap.keySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            String key = (String)iterator.next();
            smsReceiverParams.setParam(key, paramMap.get(key));
        }
        //设置手机号码和参数
        for (String phone : phoneNumberList) {
            batchSmsAttributes.addSmsReceiver(phone, smsReceiverParams);
        }

        messageAttributes.setBatchSmsAttributes(batchSmsAttributes);
        return send(topic, msg, messageAttributes);
    }
    /**
     * 发送短信
     */
    private TopicMessage send(CloudTopic topic, RawTopicMessage msg, MessageAttributes messageAttributes) {
        try {
            TopicMessage ret = topic.publishMessage(msg, messageAttributes);
            log.info("MessageId: " + ret.getMessageId());
            log.info("MessageMD5: " + ret.getMessageBodyMD5());
            return ret;
        } catch (ServiceException se) {
            log.error(se.getErrorCode() + se.getRequestId());
            log.error(se.getMessage());
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            log.error("发送短信异常{}", e);
            return null;
        }
    }
}
