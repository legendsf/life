package com.sf.jwt.infrastructures;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.time.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
public class JwtUtil {
    /**
     * 过期时间一天，
     * 1000 * 24 * 60 * 60 一天
     * 1000 * 60 * 60 一小时
     * 15 * 60 * 1000 15分钟
     */
    private static final long EXPIRE_TIME = 15 * 60 * 1000;
    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "f26e587c28064d0e855e72c0a6a0e631";
    static {
//        EXPIRE_TIME=20000;//
    }

    /**
     * 校验token是否正确
     *
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.error("verify error: ",e);
            return false;
        }
    }

    public static DecodedJWT verifyGet(String token){
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt;
    }



    public static Map<String, String> verifyGetMap(String token){
        Map<String,String> result=new HashMap<>();
        DecodedJWT jwt=null;
        try {
            jwt = verifyGet(token);
            result.put("STATE","true");
            jwt.getClaims().forEach((k,v)->{
                if(k.equals("exp")){
                    result.put(k,v.asDate().toString());
                }else {
                    result.put(k,v.asString());
                }
            });
            return  result;
        } catch (SignatureVerificationException e){
            result.put("JWT_MSG","无效签名");
        }catch (TokenExpiredException e){
            result.put("JWT_MSG","签名过期");
        }catch (AlgorithmMismatchException e){
            result.put("JWT_MSG","token 算法不一致");
        }catch (Exception e){
            result.put("JWT_MSG","签名异常");
        }
        result.put("STATE","false");
        return result;
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getPayloadStr(String token, String dataName) {
            return getJwtPayload(token).getClaim(dataName).asString();
    }

    public static DecodedJWT getJwtPayload(String token){
        try {
            return JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build().verify(token);
        }catch (Exception e){
            log.error("getJwtPayload error: ",e);
            return null;
        }
    }

    /**
     * 获取登陆用户ID
     *
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        return getPayloadStr(token, "userId");
    }

    /**
     * 生成签名,15min后过期
     *
     * @return 加密的token
     */

    public static String getToken(Map<String, String> map) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            Map<String, Object> header = new HashMap<>();
            header.put("type", "JWT");
            header.put("alg", "HS256");
            JWTCreator.Builder builder=JWT.create().withHeader(header);
            map.forEach((k,v)->{
                if(k.contains("password")){
                   throw new RuntimeException("jwt pload not allowed sensitive data");
                }
                builder.withClaim(k,v);
            });
            String sign=builder.withExpiresAt(date)
                    .sign(algorithm);
            return sign;
        } catch (Exception ex) {
            log.error("sign error: ",ex);
            return null;
        }
    }

    /**
     * 不验证签名直接拿到 payload 数据，
     * 前提条件是网关或者拦截器已经验证过 token 了
     * @param token
     * @return
     */
    public static DecodedJWT  decodeNoVerify(String token){
       return   JWT.decode(token);
    }

    public static String decodeStrNoVerify(String token,String name){
        DecodedJWT jwt = decodeNoVerify(token);
        return jwt.getClaim(name).asString();
    }

    public static Map<String,String> decodeMapNoVerify(String token){
        Map<String,String> map= new HashMap<>();
        DecodedJWT jwt = decodeNoVerify(token);
        jwt.getClaims().forEach((k,v)->{
            if("exp".equals(k)){
                map.put(k,v.asDate().toString());
            }
            map.put(k,v.asString());
        });
        return map;
    }

    public static void main(String[] args) throws Exception{
        testJwt();
    }

    public static void testDate(){
        log.info("jwt-----");
        Map<String, String> map=new HashMap<>();
        map.put("k","v");
        System.out.println(map);
        System.out.println(TimeZone.getDefault());
        ZonedDateTime zonedDateTime=ZonedDateTime.now();
        System.out.println(ZoneId.systemDefault());
        System.out.println(zonedDateTime.getZone());
        String  sec="token!#12";
        LocalDateTime lt = LocalDateTime.now();
        System.out.println("------------");
        System.out.println(ZonedDateTime.of(lt, ZoneId.of("UTC+8")));
//        System.out.println(lt.atZone(ZoneId.of("Asia/Shanghai")));
        System.out.println(lt.atZone(ZoneId.of("UTC+8")));
        System.out.println(lt);
        System.out.println("------------");
        Date date1=Date.from(lt.toInstant(ZoneOffset.of("+8")));
        LocalDateTime lt2= lt.plusSeconds(60);
        Date date2=Date.from(lt2.toInstant(ZoneOffset.of("+8")));
        System.out.println(date1);
        System.out.println(date2);
        Date.from(zonedDateTime.toInstant());
    }

    public static void testJwt()throws  Exception{
        String sec="token!#12";
        ZonedDateTime zonedDateTime=ZonedDateTime.now();
        Date expire=Date.from( zonedDateTime.plusSeconds(60).toInstant());
        String token=JWT.create().withClaim("userId",21)
                .withClaim("userName","gaodi")
//                .withClaim("exp","mexp")
                .withExpiresAt(expire)
                .sign(Algorithm.HMAC256(sec));
        System.out.println(token);
       String[] tokenStrArr= token.split("\\.");
        System.out.print( new String( Base64.getDecoder().decode(tokenStrArr[0].getBytes(StandardCharsets.UTF_8)))+" . ");
        System.out.println(new String( Base64.getDecoder().decode(tokenStrArr[1].getBytes(StandardCharsets.UTF_8)))+" . ");
//        System.out.println(Base64.getDecoder().decode(tokenStrArr[2].getBytes(StandardCharsets.UTF_8)));
//        TimeUnit.SECONDS.sleep(10); //验证过期
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(sec+"")).build();//秘钥多一个空格也通不过
        DecodedJWT verify = jwtVerifier.verify(token+"");//token 字符串必须一致，不一致通不过，末尾空格不影响
        //放的时候是 int 取的时候必须也是 asInt
        System.out.println(verify.getClaim("userId").asInt());
        System.out.println(verify.getClaim("userName").asString());
        //内置了一个 过期时间，所以 key 不要有 exp 不然取不出来
        System.out.println(verify.getClaim("exp").asDate());
        System.out.println(JWT.decode(token).getClaim("exp").asDate());
    }

}
