package my.multimedia.service.impl

import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import my.multimedia.entity.DirEntity
import my.multimedia.entity.ImageFileEntity
import my.multimedia.service.DirService
import my.multimedia.utils.Result
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileFilter
import java.util.*
import kotlin.collections.ArrayList

@Service
class DirServiceImpl : DirService {

    @Value(value = "\${props.fileDirPath}")
    var fileDirPath: String = ""

    override fun queryDirList(dirParams: JSONObject): Result {
        val upPath = dirParams.getString("filePath")
        var localPath = File(fileDirPath)
        if (upPath != null && upPath != "") {
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
                                localPath.absolutePath,
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

    override fun queryDirImageFile(data: JSONObject): Result {
        val currentFile = File(data.getString("filePath"))
        if (currentFile.exists() && currentFile.isDirectory) {
            val include = data.getJSONArray("include")
            val exclude = data.getJSONArray("exclude")
            val imageList = currentFile.listFiles(ImageFileFilter(exclude, include))
            val imageResult = ArrayList<ImageFileEntity>()
            if(imageList != null){
                for (index in imageList.indices){
                    val image = imageList[index]
                    imageResult.add(ImageFileEntity.newInstance(image.name,image.absolutePath,index))
                }
            }
            return Result.success(imageResult)
        }
        return Result.error("Not found dir, maybe this is a file path")
    }

}

/**
 * 图片过滤器
 */
class ImageFileFilter(exclude: JSONArray?, include: JSONArray?) : FileFilter {
    /**
     * 排除在外
     */
    private val exclude: JSONArray? = exclude

    private val defaultFilter: JSONArray = JSONArray()

    init {
        defaultFilter.add(".jpg")
        defaultFilter.add(".jpeg")
        defaultFilter.add(".png")
        defaultFilter.add(".gif")
        defaultFilter.add(".bmp")
        if (include != null && include.size > 0) {
            defaultFilter.addAll(include)
        }
    }

    override fun accept(pathname: File): Boolean {
        if (pathname.isDirectory) {
            return false
        }
        val fileName = pathname.name
        val fileType = fileName.substring(fileName.lastIndexOf(".")).lowercase(Locale.getDefault())
        if(defaultFilter.contains(fileType)){
            if (exclude != null && exclude.size > 0) {
                if(exclude.contains(fileType)){
                    return false
                }
            }
            return true
        }

        return false
    }
}
