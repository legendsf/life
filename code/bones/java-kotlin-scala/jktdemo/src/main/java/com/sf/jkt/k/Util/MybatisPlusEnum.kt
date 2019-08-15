package com.sf.jkt.k.Util

import com.baomidou.mybatisplus.annotation.EnumValue
import com.baomidou.mybatisplus.core.enums.IEnum

enum class GradeEnum(code: Int, var desc: String) {
    PRIMARY(1, "小学"), SECONDORY(2, "中学"), HIGH(3, "高中");

    @EnumValue
    var code: Int = 1
}

enum class AgeEnum(var code: Int, var desc: String) : IEnum<Int> {
    ONE(1, "一岁"),
    TWO(2, "二岁"),
    THREE(3, "三岁");

    override fun getValue(): Int {
        return this.code
    }
}