package com.sf.jkt.k.comp.connection.im.protocol.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.sf.jkt.k.comp.connection.im.protocol.Packet;

import static com.sf.jkt.k.comp.connection.im.protocol.command.Command.*;

@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {
    private String toUserId;
    private String message;

    public MessageRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
