package testone;

import java.util.List;
import java.util.Scanner;

public class MainCtrl {
	private boolean infostat=true;
	ServeyDAO sdao= new ServeyDAO();
	SongDAO songDAO= new SongDAO();
	Scanner sc= new Scanner(System.in);
	SongVO svo;
	public void inter() {
			System.out.println("음악 설문조사");
			System.out.println("1. 설문 참여");
			System.out.println("2. 설문 참여 현황");
			if(sc.nextInt()==1) {
				System.out.println("설문에 참여해 주셔서 감사합니다!");
				System.out.println();
				System.out.println("자신의 연령 대를 입력해주세요\n Ex)20대 이면 20을 입력 부탁드립니다.");
			}
	}
	public void etc(int age, int gender, int value) {
		System.out.println("현재 보기에 없고 즐겨듣는 장르를 추가해 주세요");
		sc.nextLine();String servey=sc.nextLine();
		sdao.insertInfo(servey);
		value=sdao.lastServey_Code(); 
		songIn(age, gender,value );
	}
	public boolean ageTran(int age) {
		boolean agestat=true;
		if(age/10==0) {
			System.out.println("잘못 입력하셨습니다 처음 안내로 돌아갑니다");
			agestat=false;
			return agestat;
		}
		return agestat;
	}
	public void turnTable(int age, int gender,int i) {
		System.out.println("장르를 골라주세요");
		int value= sc.nextInt();
		if(value==i) {
			etc(age, gender, value);
		}else if(value!=i){
			sdao.updateInfo(value);
			songIn( age,  gender, value);
		}
	}
	public void forPrint(int age, int gender) {
		List<ServeyVO>ret=sdao.selectAll();
		int i;
		for( i=0; i<ret.size(); i++) {
			System.out.println(i+1+".  "+ret.get(i));
		}
		if(i==ret.size()) {
			System.out.println(i+1+"기타()");
			System.out.println();
		}
		i++;
		turnTable(age, gender,i);
	}
	public void songIn(int age,int gender, int value) {
		System.out.println("선택하신 장르 중 가장 좋아하시는 음악의 제목을 넣어주세요");
		String songname=sc.nextLine();
		svo=new SongVO(songname,value );
		if(songDAO.selectOne(songname, value)) {
			songDAO.insertSong(svo);
		}else if(!songDAO.selectOne(songname, value)) {
			songDAO.updateSongCount(songname,value);
		}
		System.out.println("설문에 참여해주셔서 감사합니다");
		System.out.println();
	}
	
}
