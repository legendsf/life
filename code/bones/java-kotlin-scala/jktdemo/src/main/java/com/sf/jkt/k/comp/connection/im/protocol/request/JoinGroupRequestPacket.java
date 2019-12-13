package com.sf.jkt.k.comp.connection.im.protocol.request;

import lombok.Data;
import com.sf.jkt.k.comp.connection.im.protocol.Packet;

import static com.sf.jkt.k.comp.connection.im.protocol.command.Command.*;

@Data
public class JoinGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return JOIN_GROUP_REQUEST;
    }
}
