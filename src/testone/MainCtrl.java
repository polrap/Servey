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
			System.out.println("���� ��������");
			System.out.println("1. ���� ����");
			System.out.println("2. ���� ���� ��Ȳ");
			if(sc.nextInt()==1) {
				System.out.println("������ ������ �ּż� �����մϴ�!");
				System.out.println();
				System.out.println("�ڽ��� ���� �븦 �Է����ּ���\n Ex)20�� �̸� 20�� �Է� ��Ź�帳�ϴ�.");
			}
	}
	public void etc(int age, int gender, int value) {
		System.out.println("���� ���⿡ ���� ��ܵ�� �帣�� �߰��� �ּ���");
		sc.nextLine();String servey=sc.nextLine();
		sdao.insertInfo(servey);
		value=sdao.lastServey_Code(); 
		songIn(age, gender,value );
	}
	public boolean ageTran(int age) {
		boolean agestat=true;
		if(age/10==0) {
			System.out.println("�߸� �Է��ϼ̽��ϴ� ó�� �ȳ��� ���ư��ϴ�");
			agestat=false;
			return agestat;
		}
		return agestat;
	}
	public void turnTable(int age, int gender,int i) {
		System.out.println("�帣�� ����ּ���");
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
			System.out.println(i+1+"��Ÿ()");
			System.out.println();
		}
		i++;
		turnTable(age, gender,i);
	}
	public void songIn(int age,int gender, int value) {
		System.out.println("�����Ͻ� �帣 �� ���� �����Ͻô� ������ ������ �־��ּ���");
		String songname=sc.nextLine();
		svo=new SongVO(songname,value );
		if(songDAO.selectOne(songname, value)) {
			songDAO.insertSong(svo);
		}else if(!songDAO.selectOne(songname, value)) {
			songDAO.updateSongCount(songname,value);
		}
		System.out.println("������ �������ּż� �����մϴ�");
		System.out.println();
	}
	
}
