package test;

//다음 클래스 정의에서 PersonalInfo의 clone 메소드 호출 시
//깊은 복사가 이뤄지도록 clone 메소드를 오버라이딩 하시오.

class Business implements Cloneable{
	private String company;
	private String work;
	
	public Business(String company, String work) {
		this.company = company;
		this.work = work;
	}
	
	public void showBusinessInfo() {
		System.out.println("회사 :"+company);
		System.out.println("업무 :"+work);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}	
}
	
class PersonalInfo implements Cloneable{
	private String name;
	private int age;
	private Business bz;
	
	public PersonalInfo(String name, int age, String company, String work) {
		this.name = name;
		this.age = age;
		bz = new Business(company, work);
	}
	
	public void ChangeInfo(String name, int age, String company, String work) {
		this.name = name;
		this.age = age;
		bz = new Business(company, work);
	}
	
	public void showPersonalInfo() {
		System.out.println("이름 :"+name);
		System.out.println("나이 :"+age);
		bz.showBusinessInfo();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
				
		PersonalInfo copy = (PersonalInfo)super.clone();
		copy.name = name;
		copy.age = age;
		return copy;
	}
}

public class DeepCopy {
	public static void main(String[] args) {
		PersonalInfo per = new PersonalInfo("래리 페이지", 49, "Google", "CEO");
		PersonalInfo cpy;
		
		try {
			cpy = (PersonalInfo)per.clone();
			per.ChangeInfo("정재환", 37, "MicroSoft", "CTO");
			cpy.showPersonalInfo();
			System.out.println("-----------------");
			per.showPersonalInfo();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
