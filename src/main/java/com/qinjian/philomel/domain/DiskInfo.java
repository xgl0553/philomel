package com.qinjian.philomel.domain;

public class DiskInfo {
    private String mountpoint;
    private String total;
    private String percent;
    private String free;
    private String used;

    public String getMountpoint() {
        return mountpoint;
    }

    public void setMountpoint(String mountpoint) {
        this.mountpoint = mountpoint;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }


    public String toString(){
        return "[mountpoint:" + mountpoint + ",total=" + total + ",percent="  + percent + ",free=" + free + ",used=" + used +"]";

    }
}
