package testone;

public class ServeyVO {
	private String serveyname;
	private long serveycode;
	private long serveycount;
	
	public ServeyVO() {
		
	}
	
	@Override
	public String toString() {
		return "ServeyVO [장르명=" + serveyname + ", 좋아요 횟수=" + serveycount+ "]";
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
