package board;

import java.util.Date;	//Date�� import��.
//DB�� ����� ���̺��� �÷����� private������ ���.
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
	
	//getter setter �̿��ؼ� ������.
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
	 The toString() �� ���ڿ��� ��ȯ�ϴ� object�� ��ǥ���� ����̴�.
	 toString() �Լ��� ��� ��ü�� ��Ÿ���� ���ڿ��� return�Ѵ�.
	 */
	@Override			//���콺 ������-> source-> toString ������ ��.
	public String toString() {
		return "BoardDataBeen [num=" + num + ", id=" + id + ", passwd=" + passwd + ", name=" + name + ", tel=" + tel
				+ ", address=" + address + ", age=" + age + ", sex=" + sex + ", join_date=" + join_date + "]";
	}
	
}
