package testone;

import java.util.List;
import java.util.Scanner;

public class MainCtrl {
	private boolean infostat=true;
	ServeyDAO sdao= new ServeyDAO();
	Scanner sc= new Scanner(System.in);
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
	public void etc() {
		System.out.println("���� ���⿡ ���� ��ܵ�� �帣�� �߰��� �ּ���");
		sc.nextLine();String servey=sc.nextLine();
		sdao.insertInfo(servey);
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
			etc();
		}else if(value!=i){
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
		}
		i++;
		turnTable(age, gender,i);
	}
	public void songIn(int age,int gender, int value) {
		System.out.println("�����Ͻ� �帣 �� ���� �����Ͻô� ������ ������ �־��ּ���");
		sc.nextLine(); String songname=sc.nextLine();
	}
}
