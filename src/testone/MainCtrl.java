package testone;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainCtrl {
	ServeyDAO sdao= new ServeyDAO();
	SongDAO songDAO= new SongDAO();
	UserDAO udao=new UserDAO();
	JoinDAO jdao= new JoinDAO();
	Scanner sc;
	SongVO svo;
	UserVO uvo;
	int index;
	boolean serveyInput=false;
	boolean songInput=false;
	boolean infostat=true;
	
	public boolean inter() throws ScopeException{
		boolean startEnd=true;
		sc= new Scanner(System.in);
		loop1:while(infostat) {
		try {
			System.out.println("\t음악 설문조사");
			System.out.println("\t1. 설문 참여");
			System.out.println("\t2. 설문 참여 현황");
			System.out.printf("\t");
			index=sc.nextInt();
			if(index==1) {
			System.out.println("\t설문에 참여해 주셔서 감사합니다!\n\n\t자신의 연령 대를 입력해주세요\n\t Ex)20대 이면 20을 입력 부탁드립니다.\n");
			System.out.printf("\t");
			int age=sc.nextInt();
			if(ageTran(age)) {
				System.out.println("\t성별을 입력해주세요\n \t1. 남성 2. 여성\n");
				int gender;
				System.out.printf("\t");
				gender=sc.nextInt();
				if(gender==1 ||gender==2) {
						try {
							forPrint(age, gender);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
				}else {System.out.println("\t보기 중 없는 번호를 입력하셨습니다.\n \t첫 화면으로 돌아갑니다.뾰로롱\n ");
					break;
				}
			}
			}else if(index==2) {
				int mindex=0;
				int serveyCount=0;
				try {
					serveyCount=sdao.selectServeyCount();
					if(serveyCount==0) {
						System.out.println("------------------------------------------------------------------------");
						System.out.println("\n\t현재는 설문이 작성되지 않았습니다.\n \t추후 다시 확인 부탁드립니다.\n");
					}else {
						System.out.println("------------------------------------------------------------------------");
						System.out.println("\t현재까지 진행된 설문의 횟수는:\t"+serveyCount);
						System.out.println("\n\t1.장르 순위 보기\n\t2.연령대 별 장르 순위 보기\n\t3. 장르별  추천 노래 보기");
						System.out.printf("\t");
						mindex=sc.nextInt();
						if(mindex==1) {
							int selectquery=2;
							try {
								forPrint(selectquery);
							} catch (SQLException e) {
							}
						}else if(mindex==2) {
							int selectquery=mindex;
							try {
								forPrintJDAO(selectquery);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}else if(mindex==3){
							int selectquery=mindex;
							try {
								forPrintJDAO(selectquery);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}else {
				System.out.println("\t목록에 없는 값으로 돌아가요~\n");
				continue loop1;
			}
		}
		catch(InputMismatchException e) {
			System.out.println("\t입력형식이 잘못되어 돌아갑니다.\n");
			inter();
			}
		}
		return startEnd;
	}
	
	public boolean ageTran(int age) {
		boolean agestat=true;
		if(age%10!=0) {
			System.out.println("\t잘못 입력하셨습니다 처음 안내로 돌아갑니다\n");
			agestat=false;
			return agestat;
		}
		return agestat;
	}
	public void forPrint(int age, int gender) throws SQLException {
		System.out.println("\t장르 보기 목록");
		int a=1;
		List<ServeyVO>ret=sdao.selectAll(a);
		int serveyIndex;
		for( serveyIndex=0; serveyIndex<ret.size(); serveyIndex++) {
			System.out.println("\t"+(serveyIndex+1)+".  "+ret.get(serveyIndex));
		}
		if(serveyIndex==ret.size()) {
			System.out.println("\t"+(serveyIndex+1)+"기타()\n");
		}
		serveyIndex++;
		turnTable(age, gender,serveyIndex);
		}

	public void forPrint(int a) throws SQLException{
		List<ServeyVO>ret=sdao.selectAll(a);
		int i=0;
		for(ServeyVO tmp: ret) {
			i++;
			System.out.println("\t"+i+"\t"+tmp.inString());
		}
		System.out.println("------------------------------------------------------------------------");
	}
	public void forPrintJDAO(int selectquery) throws SQLException{
		if(selectquery==2) {
		List<JoinVO>ret=jdao.selectAll();
		int i=0;
		for(JoinVO tmp: ret) {
			i++;
			System.out.println("\t"+i+"\t"+tmp.secondQueryString());
		}
		System.out.println("------------------------------------------------------------------------");
		}else if(selectquery==3) {
			List<JoinVO>ret=jdao.selectThree();
			int i=0;
			for(JoinVO tmp: ret) {
				i++;
				System.out.println("\t"+i+"\t"+tmp.threeQueryString());
			}
			System.out.println("------------------------------------------------------------------------");
		}
	}
	
	public void turnTable(int age, int gender,int i){
	System.out.println("\t장르를 선택해 주세요\n");
	System.out.printf("\t");
	int value=0;
	try {
		value=sc.nextInt();
	}catch(InputMismatchException e) {
		System.out.println("\t입력형식이 잘못되어 돌아갑니다.\n");
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
	}else if(value<i){
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
		System.out.println("\t현재 보기에 없고 즐겨듣는 장르를 특수문자 제외하여 추가해 주세요\n");
		System.out.printf("\t");
		String servey="";
		sc.nextLine();
		servey=sc.nextLine();
		songInput=true;
			try {
				if(sdao.selectServeyName(servey)) {
					sdao.insertServey(servey);
					inputServeyName=true;
				}else {
					System.out.println("\t현재 목록중 기재되어 있는 장르 입니다.\n \t처음으로 돌아갑니다.\n");
					inputServeyName=false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return inputServeyName;
	}
	
	public boolean songIn(int age,int gender, int value) {
		boolean songInComplete=true;
		System.out.println("\t선택하신 장르 중 가장 좋아하시는 음악의 제목을 넣어주세요\n");
		System.out.printf("\t");
		String songname="";
		if(songInput==false) {
			sc.nextLine();songname=sc.nextLine();
		}else if(songInput==true) {
			songname=sc.nextLine();
			songInput=false;
		}
		svo=new SongVO(songname,value);
		try {
			if(songDAO.selectOne(songname, value)) {
				songDAO.insertSong(svo);
			}else if(!songDAO.selectOne(songname, value)) {
				songDAO.updateSongCount(songname,value);
			}
		} catch (SQLException e) {
			System.out.println("\t뭔가 잘못 되었네여");
			songInComplete=false;
		}
		System.out.println("\t설문에 참여해주셔서 감사합니다\n\n");
		return songInComplete;
	}
	
	public boolean makeUser(int value,int age,int gender) throws SQLException{
		boolean s=false;
		char cGender='n';
		if(gender==1) {
			cGender='M';
		}else if(gender==2) {
			cGender='F';
		}
		uvo=new UserVO(value, age, cGender);
		if(udao.selectUser(value, age, cGender)) {
			try {
				udao.returnUserCount(uvo);
				s=udao.updateUserCount(uvo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(udao.selectUser(value, age, cGender)==false) {
			s=udao.insertUser(uvo);
		}
		return s;
	}
}
