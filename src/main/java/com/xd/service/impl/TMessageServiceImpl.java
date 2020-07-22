package com.xd.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xd.entity.TMessage;
import com.xd.entity.TUser;
import com.xd.entityVO.MessageVo;
import com.xd.mapper.TMessageMapper;
import com.xd.service.TMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Malloc
 * @since 2020-07-20
 */
@Service
public class TMessageServiceImpl extends ServiceImpl<TMessageMapper, TMessage> implements TMessageService {

    @Value("${message.avatar}")
    private String avatar;

//    Y的表示有ParentNickName
//    N表示没有ParentNickName
    @Override
    public List<MessageVo> getAllMessage() {
//        找出所有的一级留言
        QueryWrapper<TMessage> messageQueryWrapper = new QueryWrapper<>();
        messageQueryWrapper.eq("parent_message_id",-1);
        List<TMessage> highMessage = this.list(messageQueryWrapper);
        List<MessageVo> messageVoList = new ArrayList<>();
        for (TMessage message : highMessage){
            MessageVo messageVo = new MessageVo();
            BeanUtil.copyProperties(message,messageVo);
            findreplayMessage(messageVo,messageVo.getReplyMessages());
            messageVoList.add(messageVo);
        }

        return messageVoList;
    }

    @Override
    public List<MessageVo> saveMessage(TMessage message, TUser user) {
        message.setCreateTime(new Date());
        if (user != null){
            message.setAvatar(user.getAvatar());
            message.setAdminMessage(true);
        } else{
            message.setAvatar(avatar);
        }
        this.save(message);
        return getAllMessage();
    }

    //            查询此留言的一级留言以及二级留言等等和父留言
    private MessageVo findreplayMessage(MessageVo message,List<MessageVo> replayMessage){
        if (message.getParentMessageId() != -1){
//                查询此留言的父留言
            QueryWrapper<TMessage> messageQueryWrapper2 = new QueryWrapper<>();
            messageQueryWrapper2.eq("id",message.getParentMessageId());
            TMessage parentMessage = this.getOne(messageQueryWrapper2);
            message.setParentNickname(parentMessage.getNickname());
            message.setParentMessageId(parentMessage.getId());
        }
//        查询设置此留言的子留言
        QueryWrapper<TMessage> messageQueryWrapper = new QueryWrapper<>();
        messageQueryWrapper.eq("parent_message_id",message.getId());
        List<TMessage> replayMessageList_N = this.list(messageQueryWrapper);
        if (replayMessageList_N != null){
            for (TMessage replayMessageN : replayMessageList_N){
                MessageVo replayMessageY = new MessageVo();
                BeanUtil.copyProperties(replayMessageN,replayMessageY);
                replayMessageY.setParentNickname(message.getNickname());
                replayMessage.add(replayMessageY);
                findreplayMessage(replayMessageY,replayMessage);
            }
        }
        return message;
    }
}
