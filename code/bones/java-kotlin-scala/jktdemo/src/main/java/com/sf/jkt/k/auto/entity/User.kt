package com.sf.jkt.k.auto.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.extension.activerecord.Model
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableField
import java.io.Serializable

/**
 * <p>
 * 
 * </p>
 *
 * @author songfei
 * @since 2019-08-13
 */
class User : Model<User>() {


    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null

    @TableField("name")
    var name: String? = null

    @TableField("sex")
    var sex: Int? = null


    companion object {

        const val ID : String = "id"



        const val NAME : String = "name"



        const val SEX : String = "sex"


    }

    override fun pkVal(): Serializable? {
        return id
    }

    override fun toString(): String {
        return "User{" +
        "id=" + id +
        ", name=" + name +
        ", sex=" + sex +
        "}"
    }
}
