package com.example.musicplayer_hezhao.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 11120555 on 2020/8/7 11:33
 */
public class findsongs implements Serializable{
    private int code;
    private boolean more;
    private List<Songs> songs;
    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public void setMore(boolean more) {
        this.more = more;
    }
    public boolean getMore() {
        return more;
    }

    public void setSongs(List<Songs> songs) {
        this.songs = songs;
    }
    public List<Songs> getSongs() {
        return songs;
    }
    public class H  implements Serializable{

        private long br;
        private int fid;
        private long size;
        private int vd;
        public void setBr(long br) {
            this.br = br;
        }
        public long getBr() {
            return br;
        }

        public void setFid(int fid) {
            this.fid = fid;
        }
        public int getFid() {
            return fid;
        }

        public void setSize(long size) {
            this.size = size;
        }
        public long getSize() {
            return size;
        }

        public void setVd(int vd) {
            this.vd = vd;
        }
        public int getVd() {
            return vd;
        }

    }
    public class ChargeInfoList implements  Serializable{

        private long rate;
        private String chargeUrl;
        private String chargeMessage;
        private int chargeType;
        public void setRate(long rate) {
            this.rate = rate;
        }
        public long getRate() {
            return rate;
        }

        public void setChargeUrl(String chargeUrl) {
            this.chargeUrl = chargeUrl;
        }
        public String getChargeUrl() {
            return chargeUrl;
        }

        public void setChargeMessage(String chargeMessage) {
            this.chargeMessage = chargeMessage;
        }
        public String getChargeMessage() {
            return chargeMessage;
        }

        public void setChargeType(int chargeType) {
            this.chargeType = chargeType;
        }
        public int getChargeType() {
            return chargeType;
        }

    }
    public class Ar implements Serializable{

        private int id;
        private String name;
        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }


    }
    public class Al implements Serializable{

        private long id;
        private String name;
        private String picUrl;
        private List<String> tns;
        private String pic_str;
        private long pic;
        public void setId(long id) {
            this.id = id;
        }
        public long getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }
        public String getPicUrl() {
            return picUrl;
        }

        public void setTns(List<String> tns) {
            this.tns = tns;
        }
        public List<String> getTns() {
            return tns;
        }

        public void setPic_str(String pic_str) {
            this.pic_str = pic_str;
        }
        public String getPic_str() {
            return pic_str;
        }

        public void setPic(long pic) {
            this.pic = pic;
        }
        public long getPic() {
            return pic;
        }

    }
    public class Privilege implements Serializable{

        private long id;
        private int fee;
        private int payed;
        private int st;
        private long pl;
        private int dl;
        private int sp;
        private int cp;
        private int subp;
        private boolean cs;
        private long maxbr;
        private long fl;
        private boolean toast;
        private int flag;
        private boolean preSell;
        private long playMaxbr;
        private long downloadMaxbr;
        private List<ChargeInfoList> chargeInfoList;
        public void setId(long id) {
            this.id = id;
        }
        public long getId() {
            return id;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }
        public int getFee() {
            return fee;
        }

        public void setPayed(int payed) {
            this.payed = payed;
        }
        public int getPayed() {
            return payed;
        }

        public void setSt(int st) {
            this.st = st;
        }
        public int getSt() {
            return st;
        }

        public void setPl(long pl) {
            this.pl = pl;
        }
        public long getPl() {
            return pl;
        }

        public void setDl(int dl) {
            this.dl = dl;
        }
        public int getDl() {
            return dl;
        }

        public void setSp(int sp) {
            this.sp = sp;
        }
        public int getSp() {
            return sp;
        }

        public void setCp(int cp) {
            this.cp = cp;
        }
        public int getCp() {
            return cp;
        }

        public void setSubp(int subp) {
            this.subp = subp;
        }
        public int getSubp() {
            return subp;
        }

        public void setCs(boolean cs) {
            this.cs = cs;
        }
        public boolean getCs() {
            return cs;
        }

        public void setMaxbr(long maxbr) {
            this.maxbr = maxbr;
        }
        public long getMaxbr() {
            return maxbr;
        }

        public void setFl(long fl) {
            this.fl = fl;
        }
        public long getFl() {
            return fl;
        }

        public void setToast(boolean toast) {
            this.toast = toast;
        }
        public boolean getToast() {
            return toast;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }
        public int getFlag() {
            return flag;
        }

        public void setPreSell(boolean preSell) {
            this.preSell = preSell;
        }
        public boolean getPreSell() {
            return preSell;
        }

        public void setPlayMaxbr(long playMaxbr) {
            this.playMaxbr = playMaxbr;
        }
        public long getPlayMaxbr() {
            return playMaxbr;
        }

        public void setDownloadMaxbr(long downloadMaxbr) {
            this.downloadMaxbr = downloadMaxbr;
        }
        public long getDownloadMaxbr() {
            return downloadMaxbr;
        }

        public void setChargeInfoList(List<ChargeInfoList> chargeInfoList) {
            this.chargeInfoList = chargeInfoList;
        }
        public List<ChargeInfoList> getChargeInfoList() {
            return chargeInfoList;
        }

    }
    public class L implements Serializable{

        private long br;
        private int fid;
        private long size;
        private int vd;
        public void setBr(long br) {
            this.br = br;
        }
        public long getBr() {
            return br;
        }

        public void setFid(int fid) {
            this.fid = fid;
        }
        public int getFid() {
            return fid;
        }

        public void setSize(long size) {
            this.size = size;
        }
        public long getSize() {
            return size;
        }

        public void setVd(int vd) {
            this.vd = vd;
        }
        public int getVd() {
            return vd;
        }

    }
    public class M implements Serializable{

        private long br;
        private int fid;
        private long size;
        private int vd;
        public void setBr(long br) {
            this.br = br;
        }
        public long getBr() {
            return br;
        }

        public void setFid(int fid) {
            this.fid = fid;
        }
        public int getFid() {
            return fid;
        }

        public void setSize(long size) {
            this.size = size;
        }
        public long getSize() {
            return size;
        }

        public void setVd(int vd) {
            this.vd = vd;
        }
        public int getVd() {
            return vd;
        }

    }

    public class Songs  implements Serializable {

        private String name;
        private long id;
        private int pst;
        private int t;
        private List<Ar> ar;
        private List<String> alia;
        private int pop;
        private int st;
        private String rt;
        private int fee;
        private int v;
        private String crbt;
        private String cf;
        private Al al;
        private long dt;
        private String a;
        private String cd;
        private int no;
        private String rtUrl;
        private int ftype;
        private List<String> rtUrls;
        private int djId;
        private int copyright;
        private int s_id;
        private int mark;
        private int originCoverType;
        private int single;
        private String noCopyrightRcmd;
        private long mv;
        private int mst;
        private int cp;
        private int rtype;
        private String rurl;
        private long publishTime;
        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setId(long id) {
            this.id = id;
        }
        public long getId() {
            return id;
        }

        public void setPst(int pst) {
            this.pst = pst;
        }
        public int getPst() {
            return pst;
        }

        public void setT(int t) {
            this.t = t;
        }
        public int getT() {
            return t;
        }

        public void setAr(List<Ar> ar) {
            this.ar = ar;
        }
        public List<Ar> getAr() {
            return ar;
        }

        public void setAlia(List<String> alia) {
            this.alia = alia;
        }
        public List<String> getAlia() {
            return alia;
        }

        public void setPop(int pop) {
            this.pop = pop;
        }
        public int getPop() {
            return pop;
        }

        public void setSt(int st) {
            this.st = st;
        }
        public int getSt() {
            return st;
        }

        public void setRt(String rt) {
            this.rt = rt;
        }
        public String getRt() {
            return rt;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }
        public int getFee() {
            return fee;
        }

        public void setV(int v) {
            this.v = v;
        }
        public int getV() {
            return v;
        }

        public void setCrbt(String crbt) {
            this.crbt = crbt;
        }
        public String getCrbt() {
            return crbt;
        }

        public void setCf(String cf) {
            this.cf = cf;
        }
        public String getCf() {
            return cf;
        }

        public void setAl(Al al) {
            this.al = al;
        }
        public Al getAl() {
            return al;
        }

        public void setDt(long dt) {
            this.dt = dt;
        }
        public long getDt() {
            return dt;
        }


        public void setA(String a) {
            this.a = a;
        }
        public String getA() {
            return a;
        }

        public void setCd(String cd) {
            this.cd = cd;
        }
        public String getCd() {
            return cd;
        }

        public void setNo(int no) {
            this.no = no;
        }
        public int getNo() {
            return no;
        }

        public void setRtUrl(String rtUrl) {
            this.rtUrl = rtUrl;
        }
        public String getRtUrl() {
            return rtUrl;
        }

        public void setFtype(int ftype) {
            this.ftype = ftype;
        }
        public int getFtype() {
            return ftype;
        }

        public void setRtUrls(List<String> rtUrls) {
            this.rtUrls = rtUrls;
        }
        public List<String> getRtUrls() {
            return rtUrls;
        }

        public void setDjId(int djId) {
            this.djId = djId;
        }
        public int getDjId() {
            return djId;
        }

        public void setCopyright(int copyright) {
            this.copyright = copyright;
        }
        public int getCopyright() {
            return copyright;
        }

        public void setS_id(int s_id) {
            this.s_id = s_id;
        }
        public int getS_id() {
            return s_id;
        }

        public void setMark(int mark) {
            this.mark = mark;
        }
        public int getMark() {
            return mark;
        }

        public void setOriginCoverType(int originCoverType) {
            this.originCoverType = originCoverType;
        }
        public int getOriginCoverType() {
            return originCoverType;
        }

        public void setSingle(int single) {
            this.single = single;
        }
        public int getSingle() {
            return single;
        }

        public void setNoCopyrightRcmd(String noCopyrightRcmd) {
            this.noCopyrightRcmd = noCopyrightRcmd;
        }
        public String getNoCopyrightRcmd() {
            return noCopyrightRcmd;
        }

        public void setMv(long mv) {
            this.mv = mv;
        }
        public long getMv() {
            return mv;
        }

        public void setMst(int mst) {
            this.mst = mst;
        }
        public int getMst() {
            return mst;
        }

        public void setCp(int cp) {
            this.cp = cp;
        }
        public int getCp() {
            return cp;
        }

        public void setRtype(int rtype) {
            this.rtype = rtype;
        }
        public int getRtype() {
            return rtype;
        }

        public void setRurl(String rurl) {
            this.rurl = rurl;
        }
        public String getRurl() {
            return rurl;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }
        public long getPublishTime() {
            return publishTime;
        }

    }
}
