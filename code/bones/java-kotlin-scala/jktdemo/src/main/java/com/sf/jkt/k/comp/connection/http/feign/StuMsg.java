package com.sf.jkt.k.comp.connection.http.feign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StuMsg {
    int id;
    String msg;
}
