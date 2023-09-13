package webdriver.phuong.datas;

public enum Subjects {
	Date("Depart Date"), DepartFrom("Depart Station"), Arrive("Arrive Station"), SeatType("Seat Type"),
	TicketAmount("Amount");

	private final String value;

	private Subjects(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
