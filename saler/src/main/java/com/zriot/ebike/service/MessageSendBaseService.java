package com.zriot.ebike.service;

import java.util.List;
import java.util.Map;

import com.aliyun.mns.model.TopicMessage;

public interface MessageSendBaseService {

    /**
     * 发送单条信息
     * @param phoneNumber  电话号码
     * @param paramMap  发送的参数
     * @param freeSignName  短信签名
     * @param templateCode  短信模板
     * @return
     */
    TopicMessage sendMessage(String phoneNumber, Map<String, String> paramMap, String freeSignName, String templateCode);

    /**
     * 批量发送短信，每个号码发送不同的变量
     * @param phoneMap  发送的电话号码和参数
     * @param freeSignName  短信签名
     * @param templateCode  短信模板
     * @return
     */
    TopicMessage batchSendMessageForIndependentParams(Map<String, Map<String, String>> phoneMap, String freeSignName, String templateCode);

    /**
     * 批量发送短信，每个号码发送相同的变量
     * @param phoneNumberList  号码列表
     * @param paramMap  发送的参数
     * @param freeSignName  短信签名
     * @param templateCode  短信模板
     * @return
     */
    TopicMessage batchSendMessageForSameParams(List<String> phoneNumberList, Map<String, String> paramMap, String freeSignName, String templateCode);
}
