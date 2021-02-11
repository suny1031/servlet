package el.model.vo;

public class Student {
	private String name;
	private int kor;
	private int math;
	private int eng;
	private int code;
	
	

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	public int getSum() {
		return kor + eng + math + code;
		
	}
	
	public int getAvg() {
		return getSum()/4;
		
	}
	

}
