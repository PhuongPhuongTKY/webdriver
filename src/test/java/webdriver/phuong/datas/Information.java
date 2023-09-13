package webdriver.phuong.datas;

public class Information {
	public enum bookTicketForm{
		Date("Depart Date"), DepartFrom("Depart Station"), Arrive("Arrive Station"), SeatType("Seat Type"), TicketAmount("Amount");	
		private final String value;
		private bookTicketForm(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
}
