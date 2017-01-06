package com.midian.qualitycloud.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public class MapBean{
    private String ret;
    private List<Content> content;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    public class Content{
        private String facility_id;
        private String use_company;
        private String lon;
        private String lat;

        public String getFacility_id() {
            return facility_id;
        }

        public void setFacility_id(String facility_id) {
            this.facility_id = facility_id;
        }

        public String getUse_company() {
            return use_company;
        }

        public void setUse_company(String use_company) {
            this.use_company = use_company;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }
    }
}
