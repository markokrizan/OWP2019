package airline.dal;

import java.util.ArrayList;
import java.util.Date;

import airline.dto.ReportDTO;
import airline.dto.TotalReportDTO;

public class ReportDAO {
	
	public static ArrayList<TotalReportDTO> doGetReportAllTime(){
		return GenericDAO.getReportAllTime();
	}
	
	public static ArrayList<ReportDTO> doGetReportByAirportAllTime(){
		return GenericDAO.getReportByAirportAllTime();
	}

	public static ArrayList<ReportDTO> doGetReportByAirportSpecific(Date dateLow, Date dateHigh){
		return GenericDAO.getSpecificReportByAirport(dateLow, dateHigh);
	}

}
