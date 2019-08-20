package com.qinjian.philomel.domain;

public class MemInfo {
        private String available;
        private String total;
        private String percent;
        private String free;
        private String used;

        public String getAvailable() {
            return available;
        }

        public void setAvailable(String available) {
            this.available = available;
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
            return "[available:" + available +",total:" + total + ",percent:" + percent +",free:" + free +",used:" + used + "]";
        }
}
