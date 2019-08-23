package com.sf.jkt.k.web.config.secruity.xsscors;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.JavaType;
import org.springframework.web.util.HtmlUtils;


/**
 * 过滤json中的特殊字符避免xss攻击的消息解析器
 */
public class XSSMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

    /**
     * 读取json参数时处理
     *
     * @param type
     * @param contextClass
     * @param inputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    public Object read(Type type, Class contextClass,
                       HttpInputMessage inputMessage) throws IOException,
            HttpMessageNotReadableException {
        JavaType javaType = getJavaType(type, contextClass);
        Object obj = readJavaType(javaType, inputMessage);
        //得到请求json
        String json = super.getObjectMapper().writeValueAsString(obj);
        //过滤特殊字符
        String result = xssEncode(json);
        Object resultObj = super.getObjectMapper().readValue(result, javaType);
        return resultObj;
    }

    /**
     * @param javaType
     * @param inputMessage
     * @return
     */
    private Object readJavaType(JavaType javaType, HttpInputMessage inputMessage) {
        try {
            return super.getObjectMapper().readValue(inputMessage.getBody(), javaType);
        } catch (IOException ex) {
            throw new HttpMessageNotReadableException("Could not read JSON: " + ex.getMessage(), ex);
        }
    }


    /**
     * 输出json时处理
     *
     * @param object
     * @param outputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        //得到要输出的json
        String json = super.getObjectMapper().writeValueAsString(object);
        //过滤特殊字符
        String result = xssEncode(json.toString());
        // 输出
        outputMessage.getBody().write(result.getBytes());
    }

    private static String xssEncode(String s) {
        return xssEncode(s, 1);
    }

    private static String xssEncode(String s, int type) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (type == 0) {
                switch (c) {
                    case '\'':
                        // 全角单引号
                        sb.append('‘');
                        break;
                    case '\"':
                        // 全角双引号
                        sb.append('“');
                        break;
                    case '>':
                        // 全角大于号
                        sb.append('＞');
                        break;
                    case '<':
                        // 全角小于号
                        sb.append('＜');
                        break;
                    case '&':
                        // 全角&符号
                        sb.append('＆');
                        break;
                    case '\\':
                        // 全角斜线
                        sb.append('＼');
                        break;
                    case '#':
                        // 全角井号
                        sb.append('＃');
                        break;
                    // < 字符的 URL 编码形式表示的 ASCII 字符（十六进制格式） 是: %3c
                    case '%':
                        processUrlEncoder(sb, s, i);
                        break;
                    default:
                        sb.append(c);
                        break;
                }
            } else {
                switch (c) {
                    case '>':
                        // 全角大于号
                        sb.append('＞');
                        break;
                    case '<':
                        // 全角小于号
                        sb.append('＜');
                        break;
                    case '&':
                        // 全角&符号
                        sb.append('＆');
                        break;
                    case '\\':
                        // 全角斜线
                        sb.append('＼');
                        break;
                    case '#':
                        // 全角井号
                        sb.append('＃');
                        break;
                    // < 字符的 URL 编码形式表示的 ASCII 字符（十六进制格式） 是: %3c
                    case '%':
                        processUrlEncoder(sb, s, i);
                        break;
                    default:
                        sb.append(c);
                        break;
                }
            }

        }
        return sb.toString();
    }

    public static void processUrlEncoder(StringBuilder sb, String s, int index) {
        if (s.length() >= index + 2) {
            // %3c, %3C
            if (s.charAt(index + 1) == '3' && (s.charAt(index + 2) == 'c' || s.charAt(index + 2) == 'C')) {
                sb.append('＜');
                return;
            }
            // %3c (0x3c=60)
            if (s.charAt(index + 1) == '6' && s.charAt(index + 2) == '0') {
                sb.append('＜');
                return;
            }
            // %3e, %3E
            if (s.charAt(index + 1) == '3' && (s.charAt(index + 2) == 'e' || s.charAt(index + 2) == 'E')) {
                sb.append('＞');
                return;
            }
            // %3e (0x3e=62)
            if (s.charAt(index + 1) == '6' && s.charAt(index + 2) == '2') {
                sb.append('＞');
                return;
            }
        }
        sb.append(s.charAt(index));
    }


}