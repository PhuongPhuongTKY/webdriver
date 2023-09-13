package webdriver.phuong.datas;

public enum SeatType {
	HardSeat("Hard seat"), SoftSeat("Soft seat"), SeatAirConditioner("Soft seat with air conditioner"),
	HardBed("Hard bed"), SoftBed("Soft bed"), BedWithAirConditioner("Soft bed with air conditioner");

	private final String value;

	private SeatType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
