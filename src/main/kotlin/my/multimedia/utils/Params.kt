package my.multimedia.utils

import com.alibaba.fastjson.JSONObject
import java.io.Serializable

class Params constructor() : Serializable {
    /**
     * 数据
     */
    var data: JSONObject = JSONObject()

    /**
     * 加密后的密钥
     */
    var key: String? = null


}