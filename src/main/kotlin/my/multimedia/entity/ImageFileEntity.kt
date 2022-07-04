package my.multimedia.entity

/**
 * 图片返回实体类
 */
class ImageFileEntity {
    /**
     * 图片名称
     */
    var imageName: String = ""

    /**
     * 图片路径
     */
    var imagePath: String = ""

    /**
     * 图片序号
     */
    var imageNumber: Int = 0

    constructor(imageName: String, imagePath: String, imageNumber: Int) {
        this.imageName = imageName
        this.imagePath = imagePath
        this.imageNumber = imageNumber
    }

    companion object {
        fun newInstance(imageName: String, imagePath: String, imageNumber: Int): ImageFileEntity {
            return ImageFileEntity(imageName, imagePath, imageNumber)
        }
    }
}