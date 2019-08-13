package com.sf.jkt.k.entity

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * data class 正常使用,注意 ajax 中传递过来的一定是 JSON.stringify({'text':'text'})
 * 而不能是JSON.stringify($('#form').serialize())
 *
 */

data class Form5(var text: String? = null, var id: String? = null)

class Form6 {
    lateinit var text: String
    lateinit var id: String

    constructor() : this("", "") {

    }

    constructor(text: String, id: String) {
        this.text = text
        this.id = id
    }
}

