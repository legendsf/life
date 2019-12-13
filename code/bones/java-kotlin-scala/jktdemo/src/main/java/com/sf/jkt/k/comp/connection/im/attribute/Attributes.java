package com.sf.jkt.k.comp.connection.im.attribute;

import com.sf.jkt.k.comp.connection.im.session.Session;
import io.netty.util.AttributeKey;

public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
