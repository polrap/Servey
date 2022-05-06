package testone;

public class SongVO {
	private long serveyCode;
	private String songName;
	private long songCount;
	public SongVO(String songName,long serveyCode) {
		super();
		this.serveyCode = serveyCode;
		this.songName = songName;
	}
	@Override
	public String toString() {
		return "SongDAO [serveyCode=" + serveyCode + ", songName=" + songName + ", songCount=" + songCount + "]";
	}
	public long getServeyCode() {
		return serveyCode;
	}
	public void setServeyCode(long serveyCode) {
		this.serveyCode = serveyCode;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public long getSongCount() {
		return songCount;
	}
	public void setSongCount(long songCount) {
		this.songCount = songCount;
	}
	
}
