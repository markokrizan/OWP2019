package airline.dto;

import java.util.Date;

public class SearchReportDTO {
	
	private Date dateLow;
	private Date dateHigh;
	
	public SearchReportDTO() {
		
	}
	
	
	public SearchReportDTO(Date dateLow, Date dateHigh) {
		super();
		this.dateLow = dateLow;
		this.dateHigh = dateHigh;
	}


	public Date getDateLow() {
		return dateLow;
	}

	public void setDateLow(Date dateLow) {
		this.dateLow = dateLow;
	}

	public Date getDateHigh() {
		return dateHigh;
	}

	public void setDateHigh(Date dateHigh) {
		this.dateHigh = dateHigh;
	}
	
	

}
