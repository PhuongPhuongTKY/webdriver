package webdriver.phuong.datas;

public class ArriveAt {
	
	public enum  Arrive {
		PhanThiet("Phan Thiết"), NhaTrang("Nha Trang"), DaNang("Đà Nẵng"), Hue("Huế"), QuangNgai("Quảng Ngãi"), SaiGon("Sài Gòn");
		private final String value;
		private Arrive(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}	
	}

}
