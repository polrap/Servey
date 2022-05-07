package testone;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainCtrl {
	ServeyDAO sdao= new ServeyDAO();
	SongDAO songDAO= new SongDAO();
	UserDAO udao=new UserDAO();
	Scanner sc= new Scanner(System.in);
	SongVO svo;
	UserVO uvo;
	boolean infostat=true;
	boolean strstate=true;
	boolean strstatetwo=true;
	public void inter() {
		loop1:while(infostat) {
		boolean mistat;
			System.out.println("���� ��������");
			System.out.println("1. ���� ����");
			System.out.println("2. ���� ���� ��Ȳ");
			int index=sc.nextInt();
			if(index==1) {
				System.out.println("������ ������ �ּż� �����մϴ�!");
				System.out.println();
				System.out.println("�ڽ��� ���� �븦 �Է����ּ���\n Ex)20�� �̸� 20�� �Է� ��Ź�帳�ϴ�.");
				int age=sc.nextInt();
				if(mistat=ageTran(age)) {
					System.out.println("������ �Է����ּ���\n 1. ���� 2. ����");
					int gender=sc.nextInt();
					while(mistat) {
						forPrint(age, gender);
						continue loop1;
					}
				}
			}else if(index==2) {
				System.out.println("1.�帣 ���� ����");
				System.out.println("2.���� �뺰 �帣 ���� ����");
				System.out.println("3. �帣��  ��õ �뷡 ����");
				int mindex=sc.nextInt();
				if(mindex==1) {
					int selectquery=2;
					forPrint(selectquery);
				}
			}
		}

	}
	public void etc(int age, int gender, int value) {
		System.out.println("���� ���⿡ ���� ��ܵ�� �帣�� Ư������ �����Ͽ� �߰��� �ּ���\n");
		String servey="";
		if(strstate==true) {
			sc.nextLine();servey=sc.nextLine();
			strstate=false;
		}else if(strstate==false) {
			servey=sc.nextLine();
		}
		if(sdao.selectServeyName(servey)) {
			sdao.insertInfo(servey);
			value=sdao.lastServey_Code(); 
			makeUser( value, age, gender);
			//songIn(age, gender,value );
		}else {
			System.out.println("���� ����� ����Ǿ� �ִ� �帣 �Դϴ�.\n ó������ ���ư��ϴ�.");
		}
		
	}
	public boolean ageTran(int age) {
		boolean agestat=true;
		if(age%10!=0) {
			System.out.println("�߸� �Է��ϼ̽��ϴ� ó�� �ȳ��� ���ư��ϴ�");
			agestat=false;
			return agestat;
		}
		return agestat;
	}
	public void turnTable(int age, int gender,int i) {
		System.out.println("�帣�� ����ּ���");
		int value=0;
		try {
			value= sc.nextInt();
		}catch(InputMismatchException e) {
			
		}
		if(value==i) {
			etc(age, gender, value);
			songIn(age, gender,value );
		}else if(value!=i){
			sdao.updateInfo(value);
			makeUser( value, age, gender);
			songIn( age,  gender, value);
		}
	}
	public void forPrint(int age, int gender) {
		int a=1;
		List<ServeyVO>ret=sdao.selectAll(a);
		int i;
		for( i=0; i<ret.size(); i++) {
			System.out.println(i+1+".  "+ret.get(i));
		}
		if(i==ret.size()) {
			System.out.println(i+1+"��Ÿ()");
			System.out.println();
		}
		i++;
		turnTable(age, gender,i);
	}
	public void forPrint(int a) {
		List<ServeyVO>ret=sdao.selectAll(a);
		int i=0;
		for(ServeyVO tmp: ret) {
			i++;
			System.out.println(i+"\t"+tmp.inString());
		}
	}
	public void songIn(int age,int gender, int value) {
		System.out.println("�����Ͻ� �帣 �� ���� �����Ͻô� ������ ������ �־��ּ���");
		String songname="";
		if(strstatetwo==true) {
			sc.nextLine();songname=sc.nextLine();
			strstatetwo=false;
		}
		else if(strstatetwo==false) {
			songname=sc.nextLine();
		}
		svo=new SongVO(songname,value );
		if(songDAO.selectOne(songname, value)) {
			songDAO.insertSong(svo);
		}else if(!songDAO.selectOne(songname, value)) {
			songDAO.updateSongCount(songname,value);
		}
		
		System.out.println("������ �������ּż� �����մϴ�");
		System.out.println();
	}
	public void makeUser(int value,int age,int gender) {
		char cGender='n';
		if(gender==1) {
			cGender='M';
		}else if(gender==2) {
			cGender='F';
		}
		uvo=new UserVO(value, age, cGender);
		udao.insertUser(uvo);
	}
	
}
