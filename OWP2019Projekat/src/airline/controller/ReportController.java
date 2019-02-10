package airline.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import airline.controller.ControllerUtil.GenericUriMeaning;
import airline.dto.MessageDTO;
import airline.dto.ReportDTO;
import airline.dto.SearchReportDTO;
import airline.dto.TotalReportDTO;
import airline.service.ReportService;


public class ReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		GenericUriMeaning uriMeaning = ControllerUtil.genericChecker(uri);
		switch(uriMeaning) {
		case REPORT_TOTAL_ALL_TIME:
			doGetReportAllTime(request, response);
			break;
		case REPORT_BY_AIRPORT_ALL_TIME:
			doGetReportByAirportAllTime(request, response);
		case ERROR:
			MessageDTO message = new MessageDTO("error", "uri error");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);	
			break;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		String uri = request.getRequestURI();
		GenericUriMeaning uriMeaning = ControllerUtil.genericChecker(uri);
		switch(uriMeaning) {
		case REPORT_BY_AIRPORT_SPECIFIC:
			BufferedReader reader = request.getReader();
			mapper = new ObjectMapper();
			SearchReportDTO srDTO = mapper.readValue(reader, SearchReportDTO.class);
			doGetReportByAirportSpecific(srDTO, request, response);
			break;
		case ERROR:
			MessageDTO message = new MessageDTO("error", "uri error");
			mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);	
			break;
		}
	}
	
	
	protected void doGetReportAllTime (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<TotalReportDTO> reports = ReportService.doGetReportAllTime();
		if(reports != null) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(reports);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);	
		}else {
			MessageDTO message = new MessageDTO("error", "processing_error");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);	
		}
	}
	
	protected void doGetReportByAirportAllTime (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ReportDTO> reports = ReportService.doGetReportByAirportAllTime();
		if(reports != null) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(reports);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);	
		}else {
			MessageDTO message = new MessageDTO("error", "processing_error");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);	
		}
	}

	protected void doGetReportByAirportSpecific (SearchReportDTO srDTO, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ReportDTO> reports = ReportService.doGetReportByAirportSpecific(srDTO.getDateLow(), srDTO.getDateHigh());
		if(reports != null) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(reports);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);	
		}else {
			MessageDTO message = new MessageDTO("error", "processing_error");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);	
		}
	}
	
	

}
