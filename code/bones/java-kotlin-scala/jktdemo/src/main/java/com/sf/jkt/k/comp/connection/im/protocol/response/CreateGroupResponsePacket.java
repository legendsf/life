package com.sf.jkt.k.comp.connection.im.protocol.response;

import com.sf.jkt.k.comp.connection.im.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.sf.jkt.k.comp.connection.im.protocol.command.Command.CREATE_GROUP_RESPONSE;


@Data
public class CreateGroupResponsePacket extends Packet {
    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {

        return CREATE_GROUP_RESPONSE;
    }
}
