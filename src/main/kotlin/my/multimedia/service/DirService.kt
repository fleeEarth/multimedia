package my.multimedia.service

import com.alibaba.fastjson.JSONObject
import my.multimedia.utils.Result

interface DirService {

    fun queryDirList(dirParams: JSONObject): Result
    fun queryDirImageFile(data: JSONObject): Result
}