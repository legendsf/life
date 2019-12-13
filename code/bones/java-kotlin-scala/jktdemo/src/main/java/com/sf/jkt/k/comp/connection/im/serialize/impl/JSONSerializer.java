package com.sf.jkt.k.comp.connection.im.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.sf.jkt.k.comp.connection.im.serialize.Serializer;
import com.sf.jkt.k.comp.connection.im.serialize.SerializerAlgorithm;


public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {

        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSON.parseObject(bytes, clazz);
    }
}
