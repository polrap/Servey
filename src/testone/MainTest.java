package testone;

import java.util.List;
import java.util.Scanner;

public class MainTest {
	public static void main(String[] args) {
		MainCtrl mc= new MainCtrl();
		Scanner sc= new Scanner(System.in);
		boolean infostat=true;
		boolean mistat=true;
		int age;
		int gender;
		loop1:while(infostat) {
			mc.inter();
			age=sc.nextInt();
			if(mistat=mc.ageTran(age)) {
				System.out.println("성별을 입력해주세요\n 1. 남성 2. 여성");
				gender=sc.nextInt();
				while(mistat) {
					mc.forPrint(age, gender);
					continue loop1;
				}
			}
		}
		
		
		
		
	}
}
