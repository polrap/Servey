package testone;

public class ServeyVO {

	private String serveyname;
	private long serveycode;
	private long serveycount;
	public ServeyVO() {
	}
	public ServeyVO(String serveyname, long serveycode, long serveycount) {
		super();
		this.serveyname = serveyname;
		this.serveycode = serveycode;
		this.serveycount = serveycount;
	}

	@Override
	public String toString() {
		return "[" + serveyname + "]";
	}
	
	public String inString() {
		return "[장르명:\t" + serveyname + ", 투표 수:\t" + serveycount
				+ "]";
	}

	public String getServeyname() {
		return serveyname;
	}
	
	public void setServeyname(String serveyname) {
		this.serveyname = serveyname;
	}
	public long getServeycode() {
		return serveycode;
	}
	public void setServeycode(long serveycode) {
		this.serveycode = serveycode;
	}
	public long getServeycount() {
		return serveycount;
	}
	public void setServeycount(long serveycount) {
		this.serveycount = serveycount;
	}
	
}
