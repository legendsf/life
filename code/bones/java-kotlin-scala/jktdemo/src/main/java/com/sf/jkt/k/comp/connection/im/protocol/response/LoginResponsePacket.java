package com.sf.jkt.k.comp.connection.im.protocol.response;

import com.sf.jkt.k.comp.connection.im.protocol.Packet;
import lombok.Data;

import static com.sf.jkt.k.comp.connection.im.protocol.command.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {
    private String userId;

    private String userName;

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {

        return LOGIN_RESPONSE;
    }
}
