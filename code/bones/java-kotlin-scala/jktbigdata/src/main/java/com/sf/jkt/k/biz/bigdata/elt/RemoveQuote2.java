package com.sf.jkt.k.biz.bigdata.elt;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class RemoveQuote2 extends UDF {
    public Text evaluate(Text string) {
        // 过滤
        if (null == string) {
            return null;
        }
        // 用来保存最后结果
        Text result;
        // 替换字符串的双引号为空
        String s = string.toString().replaceAll("\"", "");
        // 用中间结果生成返回值
        result = new Text(s);
        return result;
    }
}
