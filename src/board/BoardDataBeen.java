package board;

import java.util.Date;	//Date를 import함.
//DB에 등록한 테이블의 컬럼들을 private변수로 등록.
public class BoardDataBeen {		
	private int num;
	private String id;
	private String passwd;
	private String name;
	private String tel;
	private String address;
	private String age;
	private String sex;
	private Date join_date;
	
	//getter setter 이용해서 가져옴.
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getJoin_date() {
		return join_date;
	}
	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}
	/*
	 The toString() 은 문자열을 반환하는 object의 대표적인 방법이다.
	 toString() 함수는 어떠한 객체를 나타내는 문자열을 return한다.
	 */
	@Override			//마우스 오른쪽-> source-> toString 누르면 됨.
	public String toString() {
		return "BoardDataBeen [num=" + num + ", id=" + id + ", passwd=" + passwd + ", name=" + name + ", tel=" + tel
				+ ", address=" + address + ", age=" + age + ", sex=" + sex + ", join_date=" + join_date + "]";
	}
	
}
