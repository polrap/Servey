package testone;

public class JoinVO {
	private String stringcolumn1;
	private String stringcolumn2;
	private long longcolumn1;
	private long longcolumn2;
	
	public JoinVO(long longcolumn1, String stringcolumn1, long longcolumn2) {
		super();
		this.longcolumn1 = longcolumn1;
		this.stringcolumn1 = stringcolumn1;
		this.longcolumn2 = longcolumn2;
	}
	public JoinVO(String stringcolumn1,String stringcolumn2,  long longcolumn1) {
		super();
		this.stringcolumn1 = stringcolumn1;
		this.stringcolumn2=stringcolumn2;
		this.longcolumn1= longcolumn1;
	}
	public JoinVO(String stringcolumn1, String stringcolumn2, long longcolumn1, long longcolumn2) {
		super();
		this.stringcolumn1 = stringcolumn1;
		this.stringcolumn2 = stringcolumn2;
		this.longcolumn1 = longcolumn1;
		this.longcolumn2 = longcolumn2;
	}
	@Override
	public String toString() {
		return "JoinVO [stringcolumn1=" + stringcolumn1 + ", stringcolumn2=" + stringcolumn2 + ", longcolumn1="
				+ longcolumn1 + ", longcolumn2=" + longcolumn2 + "]";
	}
	public String secondQueryString() {
		return "[연령대=" +  longcolumn1 +  
		",\t 장르명=" + stringcolumn1 + "  \t"+"투표 수=\t" + longcolumn2 + "]";
	}
	public String threeQueryString() {
		return "[장르명=" +  stringcolumn1 +  
		",\t 곡 명=" +stringcolumn2  + "\t"+"투표 수=\t" + longcolumn1 + "]";
	}
	public String getStringcolumn1() {
		return stringcolumn1;
	}
	public void setStringcolumn1(String stringcolumn1) {
		this.stringcolumn1 = stringcolumn1;
	}
	public String getStringcolumn2() {
		return stringcolumn2;
	}
	public void setStringcolumn2(String stringcolumn2) {
		this.stringcolumn2 = stringcolumn2;
	}
	public long getLongcolumn1() {
		return longcolumn1;
	}
	public void setLongcolumn1(long longcolumn1) {
		this.longcolumn1 = longcolumn1;
	}
	public long getLongcolumn2() {
		return longcolumn2;
	}
	public void setLongcolumn2(long longcolumn2) {
		this.longcolumn2 = longcolumn2;
	}
	
	
}
