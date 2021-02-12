package com.sf.ddd.request;

import com.sf.ddd.dto.UserInfoDto;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserCommandRequest {
    public UserInfoDto userInfoDto;
}
