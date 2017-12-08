
public class Product {

	private String name;
	private int phoneNumber;
	private int sid;
	
	public Product(){
		this.name="";
		this.phoneNumber=0;
		this.sid=0;
	}
	public Product( String name,int phoneNumber,int sid){
		this.name="";
		this.phoneNumber=phoneNumber;
		this.sid=sid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getsid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	
}
