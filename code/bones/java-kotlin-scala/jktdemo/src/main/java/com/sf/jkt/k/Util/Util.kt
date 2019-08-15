package com.sf.jkt.k.Util

import org.slf4j.LoggerFactory

 object Log{
        @JvmStatic
        public val log= LoggerFactory.getLogger(this.javaClass)
}

class Log2{
    companion object{
        @JvmStatic
        public val log= LoggerFactory.getLogger(this.javaClass)
    }
}
class Log3{
    companion object{
        @JvmStatic
        public val log= LoggerFactory.getLogger(this.javaClass)
    }
}
