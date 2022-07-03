package my.multimedia.controller

import my.multimedia.service.DirService
import my.multimedia.utils.Params
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import my.multimedia.utils.Result
import org.springframework.beans.factory.annotation.Autowired

@RestController
@RequestMapping(value = ["/dirService"], method = [RequestMethod.POST])
class DirController {

    @Autowired
    lateinit var dirService: DirService

    /**
     * 获取文件列表
     */
    @RequestMapping(value = ["/queryDirList"])
    fun queryDirList(@RequestBody params: Params): Result {
        return dirService.queryDirList(params.data)
    }

}