package com.sf.jkt.k.comp.connection.im.protocol.response;

import com.sf.jkt.k.comp.connection.im.protocol.Packet;
import com.sf.jkt.k.comp.connection.im.session.Session;
import lombok.Data;

import static com.sf.jkt.k.comp.connection.im.protocol.command.Command.GROUP_MESSAGE_RESPONSE;

@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand() {

        return GROUP_MESSAGE_RESPONSE;
    }
}
