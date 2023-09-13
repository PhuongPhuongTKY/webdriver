package webdriver.phuong.datas;

public enum Station {
	SaiGon("Sài Gòn"), PhanThiet("Phan Thiết"), NhaTrang("Nha Trang"), DaNang("Đà Nẵng"), Hue("Huế"),
	QuangNgai("Quảng Ngãi");

	private String value;

	private Station(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
