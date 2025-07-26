package model;

public class ContactUs {

	private int mid;
	private int cid;
    private String cfname;
    private String cgmail;
    private String cmobile;
    private String cmessage;
    private String datetime;
    
    public ContactUs(int mid, int cid, String cfname, String cgmail, String cmobile, String cmessage, String datetime) {
        this.mid = mid;
        this.cid = cid;
        this.cfname = cfname;
        this.cgmail = cgmail;
        this.cmobile = cmobile;
        this.cmessage = cmessage;
        this.datetime = datetime;
    }


	public int getMid() {
		return mid;
	}

	public int getCid() {
		return cid;
	}

	public String getCfname() {
		return cfname;
	}

	public String getCgmail() {
		return cgmail;
	}

	public String getCmobile() {
		return cmobile;
	}

	public String getCmessage() {
		return cmessage;
	}

	public String getDatetime() {
		return datetime;
	}
    	
}
