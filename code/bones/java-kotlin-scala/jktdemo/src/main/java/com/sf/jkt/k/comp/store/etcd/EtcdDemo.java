package com.sf.jkt.k.comp.store.etcd;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.kv.GetResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EtcdDemo {
    public static void main(String[] args)throws Exception {
        testEtcd();
    }

    public static void testEtcd()throws Exception{
        Client client=Client.builder().endpoints("http://localhost:2379").build();
        KV kvClient= client.getKVClient();
        ByteSequence key=ByteSequence.from("test_key".getBytes());
        ByteSequence value=ByteSequence.from("test_value".getBytes());
        kvClient.put(key,value).get();
        CompletableFuture<GetResponse> getFuture=kvClient.get(key);
        GetResponse response=getFuture.get();
        List<KeyValue> list=response.getKvs();
        list.stream().forEach(it->{
            System.out.println("it.key:"+new String(it.getKey().getBytes())+" value:"+new   String (it.getValue().getBytes()));
        });
        kvClient.delete(key).get();

    }
}
