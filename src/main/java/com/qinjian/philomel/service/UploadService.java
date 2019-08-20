package com.qinjian.philomel.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.qinjian.philomel.domain.DiskInfo;
import com.qinjian.philomel.domain.MemInfo;
import com.qinjian.philomel.mapper.UploadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UploadService {

    @Autowired
    private UploadMapper uploadMapper;

    ObjectMapper objectMapper = new ObjectMapper();

    public int addCPUInfo(String uuid, String type, String date, String value){

        this.uploadMapper.addCpuInfo(uuid,type,date,value,new Date());
        System.out.println(value);
        return 1;
    }

    public int addMemInfo(String uuid, String type, String date, String value){
        try {
            MemInfo memInfo = objectMapper.readValue(value, MemInfo.class);
            this.uploadMapper.addMemInfo(uuid,type,date,memInfo);
            System.out.println(memInfo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 1;
    }

    public int addDiskInfo(String uuid, String type, String date, String value){
        try {
            List<DiskInfo> diskInfos = objectMapper.readValue(value, new TypeReference<List<DiskInfo>>() {
            });
            for (DiskInfo disk : diskInfos) {
                System.out.println(disk);
            }
            this.uploadMapper.addDiskInfo(uuid,type,date,diskInfos);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }
}
