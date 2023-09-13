package webdriver.phuong.datas;

public enum PriceSeatTypeDNSG {
	HardSeat("310000"), SoftSeat("335000"), SeatAirConditioner("360000"), HardBed("410000"), SoftBed("460000"),
	BedWithAirConditioner("510000");

	private final String value;

	private PriceSeatTypeDNSG(String i) {
		this.value = i;
	}

	public String getValue() {
		return this.value;
	}

}
