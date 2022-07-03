package my.multimedia.entity

import java.nio.file.Files
import java.nio.file.Path

class DirEntity {
    //文件名称
    var fileName: String = ""

    //文件路径
    var filePath: String = ""

    //文件类型
    var fileType: String? = null

    //是否是目录
    var isDirectory: Boolean = false

    constructor(fileName: String, filePath: String, fileType: String?, isDirectory: Boolean) {
        this.fileName = fileName
        this.filePath = filePath
        this.fileType = fileType
        this.isDirectory = isDirectory
    }

    companion object {
        fun newInstance(fileName: String, filePath: String, isDirectory: Boolean): DirEntity {
            var fileType:String? = null
            if(!isDirectory){
                fileType = fileName.substring(fileName.lastIndexOf("."))
            }
            return DirEntity(fileName, filePath, fileType, isDirectory)
        }
    }
}