package com.sf.jkt.k.auto.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.extension.activerecord.Model
import com.baomidou.mybatisplus.annotation.TableId
import java.time.LocalDateTime
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableLogic
import com.sf.jkt.k.Util.AgeEnum
import com.sf.jkt.k.Util.GradeEnum
import java.io.Serializable

/**
 * <p>
 * 
 * </p>
 *
 * @author songfei
 * @since 2019-08-14
 */
class User : Model<User>() {


    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null

    @TableField("name")
    var name: String? = null

    @TableField("sex")
    var sex: Int? = null

    @TableField("tenant_id")
    var tenantId: Long? = null

    @TableField("age")
    var age: AgeEnum? = null


    @TableField("grade")
    var grade: GradeEnum? = null

    @TableField("delete_flag")
    @TableLogic
    var deleteFlag: Int? = null

    @TableField("createTime")
    var createTime: LocalDateTime? = null

    @TableField("updateTime")
    var updateTime: LocalDateTime? = null


    companion object {

        const val ID : String = "id"



        const val NAME : String = "name"



        const val SEX : String = "sex"



        const val TENANT_ID : String = "tenant_id"



        const val AGE : String = "age"



        const val GRADE : String = "grade"



        const val DELETE_FLAG : String = "delete_flag"



        const val CREATETIME : String = "createTime"



        const val UPDATETIME : String = "updateTime"


    }

    override fun pkVal(): Serializable? {
        return id
    }

    override fun toString(): String {
        return "User{" +
        "id=" + id +
        ", name=" + name +
        ", sex=" + sex +
        ", tenantId=" + tenantId +
        ", age=" + age +
        ", grade=" + grade +
        ", deleteFlag=" + deleteFlag +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}"
    }
}
