package com.backend.fullProject.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.fullProject.dto.EmployeeDto;
import com.backend.fullProject.dto.EventsDto;
import com.backend.fullProject.dto.MRDto;
import com.backend.fullProject.entity.Employee;
import com.backend.fullProject.entity.Events;
import com.backend.fullProject.entity.Government;
import com.backend.fullProject.entity.MeetingRoom;
import com.backend.fullProject.entity.Office;
import com.backend.fullProject.model.EventsResponse;
import com.backend.fullProject.model.MRResponse;
import com.backend.fullProject.service.CountryService;
import com.backend.fullProject.service.EmployeeService;
import com.backend.fullProject.service.EventsService;
import com.backend.fullProject.service.GovernmentService;
import com.backend.fullProject.service.MRService;
import com.backend.fullProject.service.OfficeService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CustomControllers {

	@Autowired
	private MRService mrService;

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EventsService eventsService;
	
	@Autowired
	private OfficeService officeService ;
	
	@Autowired
	private GovernmentService govService ;
	
	@Autowired
	private CountryService countryService ;


	@GetMapping(value = "/meeting-room/getname/{meetingRoomId}/{employeeId}")
	public ResponseEntity<?> getMeetingRoomNameById(@PathVariable("meetingRoomId") int meetingRoomId,
			@PathVariable("employeeId") int employeeId) {
		MeetingRoom meetingRoom = new MeetingRoom();
		Employee myEmployee = new Employee();

		Map<String, String> response = new HashMap<>();

		String mrName = "";
		String employeeName = "";
		String officeName = "";	
		String govName = "";	
		String countryName = "";
		int officeId;
		Office myOffice;
		Government myGov;
		
		try {
			meetingRoom = mrService.getById(meetingRoomId);
			myEmployee = employeeService.getById(employeeId);
			mrName = meetingRoom.getFloor();
			
			officeId=myEmployee.getOfficeId();
			myOffice=officeService.getById(officeId);
			officeName=myOffice.getName();
			
			myGov=govService.getById(myOffice.getGovernmentId());
			govName=myGov.getName();
			countryName=countryService.getById(myGov.getCountryId()).getName();
			
			
			employeeName = myEmployee.getFirstName() + " " + myEmployee.getLastName();
			response.put("meetingRoomName", mrName);
			response.put("employeeName", employeeName);
			response.put("officeName", officeName);
			response.put("govName", govName);
			response.put("countryName", countryName);

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@GetMapping(value = "/meeting-room/officeId/{id}")
	public ResponseEntity<?> getAllByOfficeId(@PathVariable int id) {
		List<MRDto> meetingRoomsDto = new ArrayList<MRDto>();
		List<MeetingRoom> meetingRooms = new ArrayList<MeetingRoom>();
		try {
			meetingRooms = mrService.getAllByOfficeId(id);
			for (int i = 0; i < meetingRooms.size(); i++) {
				if(meetingRooms.get(i).getStatus().equals("opened")) {
					meetingRoomsDto.add(new MRDto(meetingRooms.get(i)));

				}
			}
			return new ResponseEntity(new MRResponse("all meeting rooms retrived successfully", meetingRoomsDto),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new MRResponse("something Went wrong", meetingRoomsDto), HttpStatus.NOT_FOUND);

		}

	}

	@GetMapping(value = "/events/pending/{empId}")
	public ResponseEntity<?> getAllPendingEventsWithEmpId(@PathVariable int empId) {
		List<Events> pendingEvents = new ArrayList<Events>();
		List<EventsDto> eventsDto=new ArrayList<EventsDto>();
		Employee myEmp=new Employee();

		try {
			pendingEvents = eventsService.getPendingEvents(empId);
			
			myEmp=employeeService.getById(empId);

				for(int i=0;i<pendingEvents.size();i++) {
					EventsDto myEventDto=new EventsDto(pendingEvents.get(i));
					
					myEmp=employeeService.getById(pendingEvents.get(i).getEmployeeId());
					MeetingRoom myMeetingRoom=new MeetingRoom();
					myMeetingRoom=mrService.getById(pendingEvents.get(i).getMeetingRoomId());
					myEventDto.setRoomDto(new MRDto(myMeetingRoom));
					myEventDto.setEmployeeDto(new EmployeeDto(myEmp));
					eventsDto.add(myEventDto);
				}
				
				return new ResponseEntity (new EventsResponse("all pending events is retrived successfully", eventsDto),HttpStatus.OK);

			
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity (new EventsResponse("something  went wrong", eventsDto),HttpStatus.NOT_FOUND);

		}
	}


	


}
