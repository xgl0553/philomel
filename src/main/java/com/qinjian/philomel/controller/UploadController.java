package com.qinjian.philomel.controller;

import com.qinjian.philomel.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("upload")
@Controller
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 处理agent客户端上传数据
     * @param uuid  客户端唯一标识
     * @param type  客户端上传数据类型
     * @param date  客户端时间
     * @param value 上次数据的json串
     * @return
     */
    @RequestMapping("uploadData")
    @ResponseBody
    public String uplodData(@RequestParam("uuid") String uuid, @RequestParam("type") String type,
                            @RequestParam("date") String date, @RequestParam("value") String value){

        if("cpu".equals(type)){
            uploadService.addCPUInfo(uuid, type, date, value);
        }else if("mem".equals(type)){
            uploadService.addMemInfo(uuid, type, date, value);
        }else if("disk".equals(type)){
            uploadService.addDiskInfo(uuid, type, date, value);
        }
        return "ok";
    }
}
