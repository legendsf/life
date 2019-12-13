package com.sf.jkt.k.comp.connection.im.protocol.request;

import lombok.Data;
import com.sf.jkt.k.comp.connection.im.protocol.Packet;

import static com.sf.jkt.k.comp.connection.im.protocol.command.Command.*;

@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return QUIT_GROUP_REQUEST;
    }
}
