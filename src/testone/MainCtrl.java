package testone;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainCtrl {
	ServeyDAO sdao= new ServeyDAO();
	SongDAO songDAO= new SongDAO();
	UserDAO udao=new UserDAO();
	Scanner sc;
	SongVO svo;
	UserVO uvo;
	int index;
	boolean serveyInput=false;
	boolean songInput=false;
	boolean infostat=true;
	public void inter() throws ScopeException{
		sc= new Scanner(System.in);
		loop1:while(infostat) {
		boolean mistat;
			System.out.println("음악 설문조사");
			System.out.println("1. 설문 참여");
			System.out.println("2. 설문 참여 현황");
			try {
				index=sc.nextInt();
				if(index==1) {
					System.out.println("설문에 참여해 주셔서 감사합니다!");
					System.out.println();
					System.out.println("자신의 연령 대를 입력해주세요\n Ex)20대 이면 20을 입력 부탁드립니다.");
					int age=sc.nextInt();
					if(mistat=ageTran(age)) {
						System.out.println("성별을 입력해주세요\n 1. 남성 2. 여성");
						int gender;
							gender=sc.nextInt();
							if(gender==1 ||gender==2) {
								while(mistat) {
									try {
										forPrint(age, gender);
									} catch (SQLException e) {
										e.printStackTrace();
									}
									break;
								}
							}else {
								System.out.println("보기 중 없는 번호를 입력하셨습니다.\n 첫 화면으로 돌아갑니다.뾰로롱\n ");
								break;
							}
							break;
					}
				}else if(index==2) {
					int mindex=0;
					System.out.println("1.장르 순위 보기");
					System.out.println("2.나이 대별 장르 순위 보기");
					System.out.println("3. 장르별  추천 노래 보기");
					mindex=sc.nextInt();
					if(mindex==1) {
						int selectquery=2;
						try {
							forPrint(selectquery);
						} catch (SQLException e) {
							}
					}else if(mindex==2) {
						}else {
						System.out.println("목록에 없는 값으로 돌아가요~\n");
							//continue loop1;
						}
				}else {
						System.out.println("목록에 없는 값으로 돌아가요~\n");
						continue loop1;
				}
		}
		catch(InputMismatchException e) {
			System.out.println("입력형식이 잘못되어 돌아갑니다.\n");
			inter();
		}
		}
	}
	
	
	
	public boolean ageTran(int age) {
		boolean agestat=true;
		if(age%10!=0) {
			System.out.println("잘못 입력하셨습니다 처음 안내로 돌아갑니다\n");
			agestat=false;
			return agestat;
		}
		return agestat;
	}
	public void forPrint(int age, int gender) throws SQLException {
		System.out.println("장르 보기 목록");
		int a=1;
		List<ServeyVO>ret=sdao.selectAll(a);
		int serveyIndex;
		for( serveyIndex=0; serveyIndex<ret.size(); serveyIndex++) {
			System.out.println(serveyIndex+1+".  "+ret.get(serveyIndex));
		}
		if(serveyIndex==ret.size()) {
			System.out.println(serveyIndex+1+"기타()");
			System.out.println();
		}
		serveyIndex++;
		turnTable(age, gender,serveyIndex);
		}

		public void forPrint(int a) throws SQLException{
			List<ServeyVO>ret=sdao.selectAll(a);
			int i=0;
			for(ServeyVO tmp: ret) {
				i++;
				System.out.println(i+"\t"+tmp.inString());
			}
		}
	
	public void turnTable(int age, int gender,int i) {
	System.out.println("장르를 골라주세요");
	int value=0;
	try {
		value=sc.nextInt();
	}catch(InputMismatchException e) {
		System.out.println("입력형식이 잘못되어 돌아갑니다.\n");
		try {
			inter();
		} catch (ScopeException e1) {
			e1.printStackTrace();
		}
	}
	if(value==i) {
		if(etc(age, gender, value)) {
			try {
				value=sdao.lastServey_Code(); 
				if(makeUser( value, age, gender)) {
					songIn( age,  gender, value);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}else if(value!=i){
		try {
			sdao.updateInfo(value);
			if(makeUser( value, age, gender)) {
				songIn( age,  gender, value);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	}
	public boolean etc(int age, int gender, int value) {
		boolean inputServeyName=true;
		System.out.println("현재 보기에 없고 즐겨듣는 장르를 특수문자 제외하여 추가해 주세요\n");
		String servey="";
		sc.nextLine();
		servey=sc.nextLine();
		songInput=true;
			try {
				if(sdao.selectServeyName(servey)) {
					sdao.insertServey(servey);
					inputServeyName=true;
					//songIn(age, gender,value );
				}else {
					System.out.println("현재 목록중 기재되어 있는 장르 입니다.\n 처음으로 돌아갑니다.\n");
					inputServeyName=false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return inputServeyName;
	}
	
	public boolean songIn(int age,int gender, int value) {
		boolean songInComplete=true;
		System.out.println("선택하신 장르 중 가장 좋아하시는 음악의 제목을 넣어주세요");
		String songname="";
		if(songInput==false) {
			sc.nextLine();songname=sc.nextLine();
		}else if(songInput==true) {
			songname=sc.nextLine();
		}
		svo=new SongVO(songname,value);
		try {
			if(songDAO.selectOne(songname, value)) {
				songDAO.insertSong(svo);
			}else if(!songDAO.selectOne(songname, value)) {
				songDAO.updateSongCount(songname,value);
			}
		} catch (SQLException e) {
			System.out.println("뭔가 잘못 되었네여");
			songInComplete=false;
		}
		System.out.println("설문에 참여해주셔서 감사합니다\n");
		System.out.println();
		return songInComplete;
	}
	
	public boolean makeUser(int value,int age,int gender) throws SQLException{
		boolean s=true;
		char cGender='n';
		if(gender==1) {
			cGender='M';
		}else if(gender==2) {
			cGender='F';
		}
		uvo=new UserVO(value, age, cGender);
		try {
			s=udao.insertUser(uvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
}
