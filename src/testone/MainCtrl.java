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
			System.out.println("���� ��������");
			System.out.println("1. ���� ����");
			System.out.println("2. ���� ���� ��Ȳ");
			try {
				index=sc.nextInt();
				if(index==1) {
					System.out.println("������ ������ �ּż� �����մϴ�!");
					System.out.println();
					System.out.println("�ڽ��� ���� �븦 �Է����ּ���\n Ex)20�� �̸� 20�� �Է� ��Ź�帳�ϴ�.");
					int age=sc.nextInt();
					if(mistat=ageTran(age)) {
						System.out.println("������ �Է����ּ���\n 1. ���� 2. ����");
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
								System.out.println("���� �� ���� ��ȣ�� �Է��ϼ̽��ϴ�.\n ù ȭ������ ���ư��ϴ�.�Ϸη�\n ");
								break;
							}
							break;
					}
				}else if(index==2) {
					int mindex=0;
					System.out.println("1.�帣 ���� ����");
					System.out.println("2.���� �뺰 �帣 ���� ����");
					System.out.println("3. �帣��  ��õ �뷡 ����");
					mindex=sc.nextInt();
					if(mindex==1) {
						int selectquery=2;
						try {
							forPrint(selectquery);
						} catch (SQLException e) {
							}
					}else if(mindex==2) {
						}else {
						System.out.println("��Ͽ� ���� ������ ���ư���~\n");
							//continue loop1;
						}
				}else {
						System.out.println("��Ͽ� ���� ������ ���ư���~\n");
						continue loop1;
				}
		}
		catch(InputMismatchException e) {
			System.out.println("�Է������� �߸��Ǿ� ���ư��ϴ�.\n");
			inter();
		}
		}
	}
	
	
	
	public boolean ageTran(int age) {
		boolean agestat=true;
		if(age%10!=0) {
			System.out.println("�߸� �Է��ϼ̽��ϴ� ó�� �ȳ��� ���ư��ϴ�\n");
			agestat=false;
			return agestat;
		}
		return agestat;
	}
	public void forPrint(int age, int gender) throws SQLException {
		System.out.println("�帣 ���� ���");
		int a=1;
		List<ServeyVO>ret=sdao.selectAll(a);
		int serveyIndex;
		for( serveyIndex=0; serveyIndex<ret.size(); serveyIndex++) {
			System.out.println(serveyIndex+1+".  "+ret.get(serveyIndex));
		}
		if(serveyIndex==ret.size()) {
			System.out.println(serveyIndex+1+"��Ÿ()");
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
	System.out.println("�帣�� ����ּ���");
	int value=0;
	try {
		value=sc.nextInt();
	}catch(InputMismatchException e) {
		System.out.println("�Է������� �߸��Ǿ� ���ư��ϴ�.\n");
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
		System.out.println("���� ���⿡ ���� ��ܵ�� �帣�� Ư������ �����Ͽ� �߰��� �ּ���\n");
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
					System.out.println("���� ����� ����Ǿ� �ִ� �帣 �Դϴ�.\n ó������ ���ư��ϴ�.\n");
					inputServeyName=false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return inputServeyName;
	}
	
	public boolean songIn(int age,int gender, int value) {
		boolean songInComplete=true;
		System.out.println("�����Ͻ� �帣 �� ���� �����Ͻô� ������ ������ �־��ּ���");
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
			System.out.println("���� �߸� �Ǿ��׿�");
			songInComplete=false;
		}
		System.out.println("������ �������ּż� �����մϴ�\n");
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
