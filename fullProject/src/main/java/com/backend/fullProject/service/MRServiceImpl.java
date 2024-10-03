package com.backend.fullProject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.fullProject.customExceptoins.StatusException;
import com.backend.fullProject.dao.MeetingRoomDao;
import com.backend.fullProject.entity.Employee;
import com.backend.fullProject.entity.Events;
import com.backend.fullProject.entity.MeetingRoom;
import com.backend.fullProject.entity.Office;
@Service
public class MRServiceImpl implements MRService {
	boolean flag=false;
	
	@Autowired
	private MeetingRoomDao mrDao;
	
	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EventsService eventService;

	@Override
	public List<MeetingRoom> getAll() throws Exception {
		// TODO Auto-generated method stub
		
		return mrDao.findAll();
	}

	@Override
	public MeetingRoom getById(int id) throws Exception {
	
		return mrDao.findById(id).orElseThrow(()->new Exception());
	}

	@Override
	public MeetingRoom addMeetingRoom(List<Integer> empIds, MeetingRoom newMR) throws Exception {
		List<Employee>listEmp=new ArrayList<Employee>();
//		Office myOffice=officeService.getOfficeByName(officeName);
//		System.out.println("ssssss "+empIds.size());
		for(int i=0;i<empIds.size();i++) {
			Employee myEmployee=employeeService.getById(empIds.get(i));
			myEmployee.getMeetingRooms().add(newMR);
			//employeeService.updateEmployee(myEmployee);
			listEmp.add(myEmployee);
			//myEmployee=null;

		}
		newMR.setEmployees(listEmp);
		//System.out.println(myEmployee.toString());
//		newMR.setOffice(myOffice);
		//newMR.getEmployees().add(myEmployee);
		
//		myOffice.getMeetingRooms().add(newMR);
		return mrDao.save(newMR);

	}

	@Override
	public MeetingRoom updateMeetingRoom(MeetingRoom newMR) throws Exception {
//		if(newMR.getStatus().equals("closed")||newMR.getStatus().equals("opened")) {
//			flag=true;
//		}
		if(newMR.getStatus()!=null) {
			return mrDao.save(newMR);

		}else {
			throw new StatusException("you should enter a meeting room status");
		}
	}

	@Override
	public List<MeetingRoom> getAllByOfficeId(int officeId) throws Exception {
		return mrDao.findByOfficeId(officeId);
	}

	@Override
	public List<MeetingRoom> getAllMeetingRoomsByEmpId(int empId) throws Exception {
		// TODO Auto-generated method stub
		return mrDao.findMeetingRoomsByEmployeeId(empId);
	}

	@Override
	 public void deleteMeetingRoom(int meetingRoomId) throws Exception {
        MeetingRoom meetingRoom = mrDao.findById(meetingRoomId)
            .orElseThrow(() -> new Exception("MeetingRoom not found"));

        // Remove associations with employees
        for (Employee employee : meetingRoom.getEmployees()) {
            employee.getMeetingRooms().remove(meetingRoom);
            employeeService.updateEmployee(employee);
        }
        List<Events> myEvents=eventService.getAllEventsByMeetingRoomId(meetingRoomId);
        for(int i=0;i<myEvents.size();i++) {
        	eventService.deleteEvent(myEvents.get(i).getId());
        }
        // Now delete the meeting room
        mrDao.delete(meetingRoom);
    }



}
