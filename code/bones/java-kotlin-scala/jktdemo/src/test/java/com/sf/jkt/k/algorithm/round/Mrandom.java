package com.sf.jkt.k.algorithm.round;

import java.util.*;

public class Mrandom {
    public static Integer pos=0;
    private static Map<String, Weight> weights=new HashMap<String,Weight>();
    /**
     * 随机
     * @return
     */
    public static final String getRandServer(){
        Random r=new Random();
        int rand=r.nextInt(ServerIps.LIST.size());
        return ServerIps.LIST.get(rand);
    }

    /**
     * 权重随机
     * 占用内存较大
     * @return
     */
    public static final String getWeightServer1(){
        List<String> ips=new ArrayList<>();
        for(Map.Entry<String, Integer> ent:ServerIps.WEIGHT_LIST.entrySet()){
            String ip=ent.getKey();
            Integer weight=ent.getValue();
            for(int i=0;i<weight;i++){
                ips.add(ip);
            }
        }
        int random=new Random().nextInt(ips.size());
        return ips.get(random);
    }

    /**
     * 权重随机
     * @return
     */
    public static final String getWeightServer(){
        int totalWeight=0;
        for(Integer weight:ServerIps.WEIGHT_LIST.values()){
            totalWeight +=weight;
        }
        int pos=new Random().nextInt(totalWeight);
        for(Map.Entry<String, Integer> ent:ServerIps.WEIGHT_LIST.entrySet()){
           String ip=ent.getKey();
           Integer weight= ent.getValue();
           if(pos <=weight){
              return ip;
           }
           pos= pos-weight;
        }
        return "";
    }

    /**
     * 轮询
     */

    public static final String getRoundRobinServer(){
       if(pos>=ServerIps.LIST.size()){
           pos=0;
       }
       String ip=ServerIps.LIST.get(pos);
       pos++;
       return ip;
    }

    /**
     * 加权轮询
     * @return
     */
    public static final String getWeightRoundRobinServer(){
        int totalWeight= ServerIps.WEIGHT_LIST.values().stream().mapToInt((s)->s).sum();
        int requestId=RequestId.getAndIncrement();
        Integer pos=requestId % totalWeight;
        for(Map.Entry<String, Integer> ent:ServerIps.WEIGHT_LIST.entrySet()){
            String ip=ent.getKey();
            int weight= ent.getValue();
            if(pos<weight){
                return ip;
            }
            pos=pos-weight;
        }
        return "";
    }

    /**
     * 平滑加权轮询算法
     * @return
     */
    public static final String getSmoothWeightRRServer(){
        int totalWeight=ServerIps.WEIGHT_LIST.values().stream().mapToInt((s)->s).sum();
        if(weights.isEmpty()){
            ServerIps.WEIGHT_LIST.forEach((ip,weight)->{
                weights.put(ip,new Weight(ip,weight,0));
            });
        }
        for(Weight weight:weights.values()){
            weight.setCurrentWeight(weight.getCurrentWeight()+weight.getWeight());
        }
        Weight maxCurrentWeight=null;
        for(Weight weight:weights.values()){
            if(maxCurrentWeight==null||weight.getCurrentWeight()> maxCurrentWeight.getCurrentWeight()){
                maxCurrentWeight=weight;
            }
        }
        maxCurrentWeight.setCurrentWeight(maxCurrentWeight.getCurrentWeight()-totalWeight);

       return maxCurrentWeight.getIp() ;
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(getWeightRoundRobinServer());
//        }
        for (int i = 0; i < 10; i++) {
            System.out.println(getSmoothWeightRRServer());
        }
    }







}
