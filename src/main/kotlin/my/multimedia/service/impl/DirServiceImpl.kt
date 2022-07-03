package my.multimedia.service.impl

import com.alibaba.fastjson.JSONObject
import my.multimedia.entity.DirEntity
import my.multimedia.service.DirService
import my.multimedia.utils.Result
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File

@Service
class DirServiceImpl : DirService {

    @Value(value = "\${props.fileDirPath}")
    var fileDirPath: String = ""

    override fun queryDirList(dirParams: JSONObject): Result {
        val upPath = dirParams.getString("filePath")
        var localPath =  File(fileDirPath)
        if(upPath != null && upPath != ""){
            localPath = File(upPath)
        }
        if (localPath.exists()) {
            if (localPath.isDirectory) {
                val dirList = localPath.listFiles()?.asList()
                val resultDirList = ArrayList<DirEntity>()
                if (dirList != null) {
                    for (file in dirList) {
                        resultDirList.add(
                            DirEntity.newInstance(
                                file.name,
                                file.absolutePath,
                                file.isDirectory
                            )
                        )
                    }
                }
                return Result.success(resultDirList)
            }
            return Result.error("$fileDirPath is a filePath")
        }
        return Result.error("Not found dir : $fileDirPath")
    }
}