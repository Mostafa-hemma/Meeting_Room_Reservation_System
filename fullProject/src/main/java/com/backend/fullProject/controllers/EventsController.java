package com.backend.fullProject.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.fullProject.customExceptoins.EventOverlapException;
import com.backend.fullProject.customExceptoins.EventWrongDate;
import com.backend.fullProject.dto.EventsDto;
import com.backend.fullProject.dto.MRDto;
import com.backend.fullProject.entity.Events;
import com.backend.fullProject.entity.MeetingRoom;
import com.backend.fullProject.model.EventsResponse;
import com.backend.fullProject.service.EmployeeService;
import com.backend.fullProject.service.EventsService;
import com.backend.fullProject.service.MRService;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:4200")
public class EventsController {

	@Autowired
	private EventsService eventsService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private MRService meetingRoomService;

	@GetMapping(value = "/events")
	public ResponseEntity<?> getAll() {
		List<EventsDto> myEventsDto = new ArrayList<EventsDto>();
		List<Events> myEvents = new ArrayList<Events>();

		try {
			myEvents = eventsService.getAll();
			for (int i = 0; i < myEvents.size(); i++) {
				MRDto meetingRoomDto = new MRDto(meetingRoomService.getById(myEvents.get(i).getMeetingRoomId()));
				EventsDto eventDto = new EventsDto(myEvents.get(i));
				eventDto.setRoomDto(meetingRoomDto);

				myEventsDto.add(eventDto);
			}
			return new ResponseEntity(new EventsResponse("all Events Retrived Successfully", myEventsDto),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new EventsResponse("Something Went wrong", myEventsDto), HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping(value = "/events/country/{countryId}")
	public ResponseEntity<?> getEventsByCountryId(@PathVariable int countryId) {
		List<EventsDto> myEventsDto = new ArrayList<EventsDto>();
		List<Events> myEvents = new ArrayList<Events>();

		try {
			myEvents = eventsService.getAllEventsByCountryId(countryId);
			for (int i = 0; i < myEvents.size(); i++) {
				if (myEvents.get(i).getStatus().equals("approved")) {
					MRDto meetingRoomDto = new MRDto(meetingRoomService.getById(myEvents.get(i).getMeetingRoomId()));
					EventsDto eventDto = new EventsDto(myEvents.get(i));
					eventDto.setRoomDto(meetingRoomDto);

					myEventsDto.add(eventDto);
				} else {
					System.out.println("statussssss " + myEvents.get(i).getStatus());
				}

			}
			return new ResponseEntity(new EventsResponse("all Events Retrived Successfully", myEventsDto),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new EventsResponse("Something Went wrong", myEventsDto), HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping(value = "/events/government/{govId}")
	public ResponseEntity<?> getEventsByGovId(@PathVariable int govId) {
		List<EventsDto> myEventsDto = new ArrayList<EventsDto>();
		List<Events> myEvents = new ArrayList<Events>();

		try {
			myEvents = eventsService.getAllEventsByGovId(govId);
			for (int i = 0; i < myEvents.size(); i++) {
				if (myEvents.get(i).getStatus().equals("approved")) {
					MRDto meetingRoomDto = new MRDto(meetingRoomService.getById(myEvents.get(i).getMeetingRoomId()));
					EventsDto eventDto = new EventsDto(myEvents.get(i));
					eventDto.setRoomDto(meetingRoomDto);

					myEventsDto.add(eventDto);
				} else {
					System.out.println("statussssss " + myEvents.get(i).getStatus());
				}

			}
			return new ResponseEntity(new EventsResponse("all Events Retrived Successfully", myEventsDto),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new EventsResponse("Something Went wrong", myEventsDto), HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping(value = "/events/meetingroom/{meetingRoomId}")
	public ResponseEntity<?> getEventsByMeetingRoomId(@PathVariable int meetingRoomId) {
		List<EventsDto> myEventsDto = new ArrayList<EventsDto>();
		List<Events> myEvents = new ArrayList<Events>();

		try {
			myEvents = eventsService.getAllEventsByMeetingRoomId(meetingRoomId);
			for (int i = 0; i < myEvents.size(); i++) {
				if (myEvents.get(i).getStatus().equals("approved")) {
					MRDto meetingRoomDto = new MRDto(meetingRoomService.getById(myEvents.get(i).getMeetingRoomId()));
					EventsDto eventDto = new EventsDto(myEvents.get(i));
					eventDto.setRoomDto(meetingRoomDto);

					myEventsDto.add(eventDto);
				} else {
					System.out.println("statussssss " + myEvents.get(i).getStatus());
				}

			}
			return new ResponseEntity(new EventsResponse("all Events Retrived Successfully", myEventsDto),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new EventsResponse("Something Went wrong", myEventsDto), HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping(value = "/events/office/{officeId}")
	public ResponseEntity<?> getEventsByOfficeId(@PathVariable int officeId) {
		List<EventsDto> myEventsDto = new ArrayList<EventsDto>();
		List<Events> myEvents = new ArrayList<Events>();

		try {
			myEvents = eventsService.getAllEventsByOfficeId(officeId);
			for (int i = 0; i < myEvents.size(); i++) {
				if (myEvents.get(i).getStatus().equals("approved")) {
					MRDto meetingRoomDto = new MRDto(meetingRoomService.getById(myEvents.get(i).getMeetingRoomId()));
					EventsDto eventDto = new EventsDto(myEvents.get(i));
					eventDto.setRoomDto(meetingRoomDto);

					myEventsDto.add(eventDto);
				} else {
					System.out.println("statussssss " + myEvents.get(i).getStatus());
				}

			}
			return new ResponseEntity(new EventsResponse("all Events Retrived Successfully", myEventsDto),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new EventsResponse("Something Went wrong", myEventsDto), HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping(value = "/events/approved")
	public ResponseEntity<?> getAprovedEvents() {
		List<EventsDto> myEventsDto = new ArrayList<EventsDto>();
		List<Events> myEvents = new ArrayList<Events>();

		try {
			myEvents = eventsService.getAll();
			for (int i = 0; i < myEvents.size(); i++) {
				if (myEvents.get(i).getStatus().equals("approved") || myEvents.get(i).getStatus().equals("pending")) {
					MRDto meetingRoomDto = new MRDto(meetingRoomService.getById(myEvents.get(i).getMeetingRoomId()));
					EventsDto eventDto = new EventsDto(myEvents.get(i));
					eventDto.setRoomDto(meetingRoomDto);

					myEventsDto.add(eventDto);
				} else {
					System.out.println("statussssss " + myEvents.get(i).getStatus());
				}

			}
			return new ResponseEntity(new EventsResponse("all Events Retrived Successfully", myEventsDto),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new EventsResponse("Something Went wrong", myEventsDto), HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping(value = "/events/empId/{empId}")
	public ResponseEntity<?> getAllByEmpId(@PathVariable int empId) {
		List<EventsDto> myEventsDto = new ArrayList<EventsDto>();
		List<Events> myEvents = new ArrayList<Events>();
		try {
			myEvents = eventsService.getAllByEmpId(empId);
			for (int i = 0; i < myEvents.size(); i++) {
				MRDto meetingRoomDto = new MRDto(meetingRoomService.getById(myEvents.get(i).getMeetingRoomId()));
				EventsDto eventDto = new EventsDto(myEvents.get(i));
				eventDto.setRoomDto(meetingRoomDto);

				myEventsDto.add(eventDto);
			}
			return new ResponseEntity(
					new EventsResponse("all Events for employee id :" + empId + "Retrived Successfully", myEventsDto),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new EventsResponse("Something Went wrong", myEventsDto), HttpStatus.NOT_FOUND);

		}
	}

	@PostMapping(value = "/events")
	public ResponseEntity<?> addEvent(@RequestBody EventsDto eventsDto) {
		int empId = eventsDto.getEmployeeId();
		int meetingRoomId = eventsDto.getMeetingRoomId();
		Events myEvent = new Events();
		Events savedEvent = new Events();
		MeetingRoom meetingRoom = new MeetingRoom();
		List<EventsDto> myEventsDto = new ArrayList<EventsDto>();

		myEvent.setId(0);
		myEvent.setName(eventsDto.getName());
		myEvent.setDate(eventsDto.getDate());
		myEvent.setStartTime(eventsDto.getStartTime());
		myEvent.setDescription(eventsDto.getDescription());
		myEvent.setEndTime(eventsDto.getEndTime());
		myEvent.setStatus(eventsDto.getStatus());
		myEvent.setEmployeeId(eventsDto.getEmployeeId());
		myEvent.setMeetingRoomId(eventsDto.getMeetingRoomId());
		try {
			meetingRoom = meetingRoomService.getById(eventsDto.getMeetingRoomId());
			if (meetingRoom.getStatus().equals("opened")) {
				savedEvent = eventsService.addEvent(myEvent);
				EventsDto myEventDto = new EventsDto(savedEvent);
				myEventDto.setRoomDto(eventsDto.getRoomDto());
				myEventsDto.add(myEventDto);
				return new ResponseEntity(new EventsResponse("Event is added Successfully", myEventsDto),
						HttpStatus.OK);

			} else {
				return new ResponseEntity(new EventsResponse("this meeting room is closed"), HttpStatus.NOT_FOUND);

			}
		} catch (EventOverlapException e) {
			e.printStackTrace();
			return new ResponseEntity(new EventsResponse("there is an event at this time", myEventsDto),
					HttpStatus.NOT_FOUND);

		} catch (EventWrongDate e) {
			e.printStackTrace();
			return new ResponseEntity(new EventsResponse("Please Enter Time of Event In a right way", myEventsDto),
					HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new EventsResponse("Something Went wrong", myEventsDto), HttpStatus.NOT_FOUND);

		}
	}

	@PutMapping(value = "/events")
	public ResponseEntity<?> updateEvent(@RequestBody EventsDto eventDTO) {
		List<EventsDto> EventsList = new ArrayList<EventsDto>();
		Events updatedEvent = new Events();
		MeetingRoom meetingRoom = new MeetingRoom();

		try {
			meetingRoom = meetingRoomService.getById(eventDTO.getMeetingRoomId());

			if (meetingRoom.getStatus().equals("opened")) {
				Events myEvent = eventsService.getById(eventDTO.getId());
				myEvent.setDescription(eventDTO.getDescription());
				myEvent.setDate(eventDTO.getDate());
				myEvent.setEndTime(eventDTO.getEndTime());
				myEvent.setStartTime(eventDTO.getStartTime());
				myEvent.setStatus(eventDTO.getStatus());
				myEvent.setName(eventDTO.getName());
				myEvent.setEmployeeId(eventDTO.getEmployeeId());
				myEvent.setMeetingRoomId(eventDTO.getMeetingRoomId());
				updatedEvent = eventsService.editEvent(myEvent);
				EventsDto updatedEventDto = new EventsDto(updatedEvent);
				updatedEventDto.setRoomDto(new MRDto(meetingRoomService.getById(updatedEvent.getMeetingRoomId())));

				EventsList.add(updatedEventDto);
				return new ResponseEntity(new EventsResponse("Event is updated Successfully", EventsList),
						HttpStatus.OK);

			} else {
				return new ResponseEntity(new EventsResponse("this meeting room is closed"), HttpStatus.NOT_FOUND);

			}

		} catch (EventOverlapException e) {
			e.printStackTrace();
			return new ResponseEntity(
					new EventsResponse("there is an event at this time in this meeting Room", EventsList),
					HttpStatus.NOT_FOUND);

		} catch (EventWrongDate e) {
			e.printStackTrace();
			return new ResponseEntity(new EventsResponse("Please Enter Time of Event In a right way", EventsList),
					HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new EventsResponse("Please Select A meeting Room", EventsList),
					HttpStatus.NOT_FOUND);

		}
	}

	@DeleteMapping(value = "/events/{eventId}")
	public ResponseEntity<?> deleteEvent(@PathVariable int eventId) {
		try {
			eventsService.deleteEvent(eventId);
			return new ResponseEntity(new EventsResponse("Event is deleted Successfully"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new EventsResponse("Something Went wrong"), HttpStatus.NOT_FOUND);

		}
	}

}
