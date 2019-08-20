package com.qinjian.philomel.mapper;

import com.qinjian.philomel.domain.DiskInfo;
import com.qinjian.philomel.domain.MemInfo;
import org.apache.ibatis.annotations.Insert;

import java.util.Date;
import java.util.List;

public interface UploadMapper {

    @Insert("insert into cpu_info(uuid,type,cpu_percent,update_time) values(#{uuid},#{type},#{value},#{date})")
    int addCpuInfo(String uuid, String type, String date, String value, Date addTime);

    @Insert("insert into mem_info(uuid,type,update_time,available,total,percent,free,used) values(#{uuid},#{type},#{date},#{memInfo.available},#{memInfo.total},#{memInfo.percent},#{memInfo.free},#{memInfo.used})")
    int addMemInfo(String uuid, String type, String date, MemInfo memInfo);

    int addDiskInfo(String uuid, String type, String date, List<DiskInfo> diskInfos);
}
