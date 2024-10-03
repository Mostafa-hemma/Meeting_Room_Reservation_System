package com.backend.fullProject.service;

import java.util.List;

import com.backend.fullProject.entity.Events;

public interface EventsService {
	
	public List<Events> getAll() throws Exception;
	public List<Events> getAllByEmpId(int empId) throws Exception;

	public Events getById(int id)throws Exception;
	public Events addEvent (Events event)throws Exception;
	public Events editEvent (Events updatedEvent)throws Exception;
	public void deleteEvent(int id)throws Exception;
	public List<Events> getPendingEvents(int empId) throws Exception;
	public void deleteByRoomId(int roomId) throws Exception;
	public List<Events> getAllEventsByCountryId(int countryId)throws Exception;
	public List<Events> getAllEventsByGovId(int govId)throws Exception;
	public List<Events> getAllEventsByOfficeId(int officeId)throws Exception;
	public List<Events> getAllEventsByMeetingRoomId(int meetingRoomId)throws Exception;

}
