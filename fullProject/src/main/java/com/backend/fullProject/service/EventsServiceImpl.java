package com.backend.fullProject.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.fullProject.customExceptoins.EventOverlapException;
import com.backend.fullProject.customExceptoins.EventWrongDate;
import com.backend.fullProject.dao.EventsDao;
import com.backend.fullProject.entity.Events;
@Service
public class EventsServiceImpl implements EventsService {
	
	@Autowired
	private EventsDao eventsDao;
	@Autowired
	private MRService mrService;
	@Autowired
	private EmployeeService empService;
	
	
	@Override
	public List<Events> getAll() throws Exception {
//		// TODO Auto-generated method stub
//		//mapper map from model events to eventdto
//		List<Integer> meetingIds=eventsDao.findAll().stream().map(Events::getMeetingRoomId).distinct().collect(Collectors.toList());
//		 //meetin room service to return rooms
//		List<MeetingRoom>rooms=new ArrayList();
		
		return eventsDao.findAll();
		
		
	}

	@Override
	public Events getById(int id) throws Exception {
		// TODO Auto-generated method stub
		return eventsDao.findById(id).orElseThrow(()->new Exception());
	}
	  public boolean isOverlapping(LocalDate date, LocalTime startTime, LocalTime endTime,int meetingRoomId) {
	        List<Events> eventsOnDate = eventsDao.findByDateAndMeetingRoomId(date,meetingRoomId);

	        for (Events event : eventsOnDate) {
	            if ((startTime.isBefore(event.getEndTime()) && endTime.isAfter(event.getStartTime()))|| ((startTime.equals(event.getStartTime()))||(endTime.equals(event.getEndTime())))
	            		
	            		) {
	                return true; // Overlapping
	            }
	        }

	        return false; // No overlap
	    }
 public boolean isOverlappingForEdit(int id,LocalDate date, LocalTime startTime, LocalTime endTime,int meetingRoomId) {
	        List<Events> eventsOnDate = eventsDao.findByDateAndMeetingRoomId(date,meetingRoomId);

	        for (Events event : eventsOnDate) {
	        	if(event.getId()==id)
	        	{
	        		continue;
	        	}
	            if ((startTime.isBefore(event.getEndTime()) && endTime.isAfter(event.getStartTime()))|| ((startTime.equals(event.getStartTime()))||(endTime.equals(event.getEndTime())))
	            		) {
	                return true; // Overlapping
	            }
	        }

	        return false; // No overlap
	    }

	@Override
	public Events addEvent( Events event) throws Exception {
		
//		Employee myEmployee=empService.getById(empId);
//		MeetingRoom myMeetingRoom=mrService.getById(meetingRoomId);
//		
//		myEmployee.getEvents().add(event);
//		myMeetingRoom.getEvents().add(event);
//		
//		event.setEmployee(myEmployee);
//		event.setMeetingRoom(myMeetingRoom);
		  if (isOverlapping(event.getDate(), event.getStartTime(), event.getEndTime(),event.getMeetingRoomId())) {
	            throw new EventOverlapException("Event times overlap with an existing event.");
	        }
		if((event.getStartTime().isAfter(event.getEndTime()))){
            throw new EventWrongDate("Please Enter Time of Event In a right way");

		}
		
		return eventsDao.save(event);
	}

	@Override
	public Events editEvent(Events updatedEvent) throws Exception {
	if	(isOverlappingForEdit(updatedEvent.getId(),updatedEvent.getDate(), updatedEvent.getStartTime(), updatedEvent.getEndTime(),updatedEvent.getMeetingRoomId())){       
		throw new EventOverlapException("Event times overlap with an existing event.");
	        }
		if((updatedEvent.getStartTime().isAfter(updatedEvent.getEndTime()))){
          throw new EventWrongDate("Please Enter Time of Event In a right way");

		}		
		
		return eventsDao.save(updatedEvent);
	}

	@Override
	public void deleteEvent(int id) throws Exception {
		eventsDao.deleteById(id);
	}

	@Override
	public List<Events> getAllByEmpId(int empId) throws Exception {
		// TODO Auto-generated method stub
		return eventsDao.findByEmployeeId(empId);
	}

	@Override
	public List<Events> getPendingEvents(int empId) throws Exception {
		return eventsDao.findPendingEventsByEmployeeId(empId);
	}

	@Override
	public List<Events> getAllEventsByCountryId(int countryId) throws Exception {
		
		return eventsDao.findAllByCountryId(countryId);
	}

	@Override
	public List<Events> getAllEventsByGovId(int govId) throws Exception {
		// TODO Auto-generated method stub
		return eventsDao.findAllByGovId(govId);
	}

	@Override
	public List<Events> getAllEventsByOfficeId(int officeId) throws Exception {
		// TODO Auto-generated method stub
		return eventsDao.findAllByOfficeId(officeId);
	}

	@Override
	public List<Events> getAllEventsByMeetingRoomId(int meetingRoomId) throws Exception {
		// TODO Auto-generated method stub
		return eventsDao.findByMeetingRoomId(meetingRoomId);
	}

	@Override
	public void deleteByRoomId(int roomId) throws Exception {
		eventsDao.deleteAllByMeetingRoomId(roomId);
	}
	
	

}
