package airline.service;

import java.util.ArrayList;
import java.util.Date;

import airline.dal.ReportDAO;
import airline.dto.ReportDTO;
import airline.dto.TotalReportDTO;

public class ReportService {
	
	public static ArrayList<TotalReportDTO> doGetReportAllTime(){
		return ReportDAO.doGetReportAllTime();
	}
	
	public static ArrayList<ReportDTO> doGetReportByAirportAllTime(){
		return ReportDAO.doGetReportByAirportAllTime();
	}

	public static ArrayList<ReportDTO> doGetReportByAirportSpecific(Date dateLow, Date dateHigh){
		return ReportDAO.doGetReportByAirportSpecific(dateLow, dateHigh);
	}
}
