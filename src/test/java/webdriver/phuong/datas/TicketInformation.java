package webdriver.phuong.datas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TicketInformation {
	private String date;
	private String departFrom;
	private String arriveAt;
	private String seatType;
	private String ticketAmount;

	public TicketInformation(String departFrom, String arriveAt, String seatType, int ticketAmount) {
		super();
		this.departFrom = departFrom;
		this.arriveAt = arriveAt;
		this.seatType = seatType;
		this.ticketAmount = String.valueOf(ticketAmount);
		this.date = this.dateFmt();
	}

	public String dateFmt() {
		LocalDate now = LocalDate.now();
		LocalDate result = now.plusDays(7L);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("M/dd/yyyy");
		String dateStr = result.format(fmt);
		return dateStr;
	}

	public String getDepartFrom() {
		return departFrom;
	}

	public void setDepartFrom(String departFrom) {
		this.departFrom = departFrom;
	}

	public String getArriveAt() {
		return arriveAt;
	}

	public void setArriveAt(String arriveAt) {
		this.arriveAt = arriveAt;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getTicketAmount() {
		return ticketAmount;
	}

	public void setTicketAmount(String ticketAmount) {
		this.ticketAmount = ticketAmount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
