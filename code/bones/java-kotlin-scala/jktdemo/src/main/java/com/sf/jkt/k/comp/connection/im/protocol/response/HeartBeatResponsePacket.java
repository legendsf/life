package com.sf.jkt.k.comp.connection.im.protocol.response;

import com.sf.jkt.k.comp.connection.im.protocol.Packet;

import static com.sf.jkt.k.comp.connection.im.protocol.command.Command.HEARTBEAT_RESPONSE;

public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
