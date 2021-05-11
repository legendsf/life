package com.sf.netflix.sc.gateway.webflux;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class User {
    public int id;
    public String name;
}
