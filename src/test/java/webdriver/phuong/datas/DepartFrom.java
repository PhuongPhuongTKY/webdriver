package webdriver.phuong.datas;

public class DepartFrom {
	
	public enum Depart{
		SaiGon("Sài Gòn"), PhanThiet("Phan Thiết"), NhaTrang("Nha Trang"), DaNang("Đà Nẵng"), Hue("Huế"), QuangNgai("Quảng Ngãi");	
		private final String value;
		private Depart(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
	
	
	
	
}
