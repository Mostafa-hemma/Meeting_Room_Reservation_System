package com.backend.fullProject.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.backend.fullProject.customExceptoins.StatusException;
import com.backend.fullProject.dto.EmployeeDto;
import com.backend.fullProject.dto.MRDto;
import com.backend.fullProject.entity.Employee;
import com.backend.fullProject.entity.MeetingRoom;
import com.backend.fullProject.model.EmployeeResponse;
import com.backend.fullProject.model.EventsResponse;
import com.backend.fullProject.model.MRResponse;
import com.backend.fullProject.service.MRService;
import com.backend.fullProject.service.OfficeService;

@RestController
@RequestMapping(value="/api")
@CrossOrigin
public class MeetingRoomController {
	
	@Autowired
	private MRService mrService;
	
	@Autowired
	private OfficeService officeService;
	
	@GetMapping(value="/meeting-room")
	public ResponseEntity<?> getAll(){
		List<MRDto> mrDtos=new ArrayList<MRDto>();
		List<MeetingRoom> meetingRooms=new ArrayList<MeetingRoom>();
		String officeName;
		MRDto meetingRoomDto;
		try {
			meetingRooms=mrService.getAll();
			for(int i=0;i<meetingRooms.size();i++) {
				officeName=officeService.getById(meetingRooms.get(i).getOfficeId()).getName();
				 meetingRoomDto=new MRDto(meetingRooms.get(i));
				 meetingRoomDto.setOfficeName(officeName);
				mrDtos.add(meetingRoomDto);
				
			}
			return new ResponseEntity (new MRResponse("all meeting rooms retrived successfully", mrDtos),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity (new MRResponse("something Went wrong", mrDtos),HttpStatus.NOT_FOUND);

		}
	}
	
	@GetMapping(value="/meeting-room/empId/{empId}")
	public ResponseEntity<?> getAllMeetingRoomsByEmpId(@PathVariable int empId){
		List<MRDto> mrDtos=new ArrayList<MRDto>();
		List<MeetingRoom> meetingRooms=new ArrayList<MeetingRoom>();
		String officeName;
		MRDto meetingRoomDto;
		try {
			meetingRooms=mrService.getAllMeetingRoomsByEmpId(empId);
			
			for(int i=0;i<meetingRooms.size();i++) {
				officeName=officeService.getById(meetingRooms.get(i).getOfficeId()).getName();
				 meetingRoomDto=new MRDto(meetingRooms.get(i));
				 meetingRoomDto.setOfficeName(officeName);
				mrDtos.add(meetingRoomDto);
				
			}
			return new ResponseEntity (new MRResponse("all meeting rooms retrived successfully", mrDtos),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity (new MRResponse("something Went wrong", mrDtos),HttpStatus.NOT_FOUND);

		}
	}
	
	@GetMapping(value="/meeting-room/{id}")
	public ResponseEntity<?> getAll(@PathVariable int id){
		List<MRDto> mrDtos=new ArrayList<MRDto>();
		MeetingRoom meetingRoom=new MeetingRoom();
		try {
			meetingRoom=mrService.getById(id);
			mrDtos.add(new MRDto(meetingRoom));
			
			return new ResponseEntity (new MRResponse("meeting room retrived successfully", mrDtos),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity (new MRResponse("something Went wrong", mrDtos),HttpStatus.NOT_FOUND);

		}
	}
	

	
	@PostMapping(value="/meeting-room")
	public ResponseEntity<?> addMeetingRoom(@RequestBody MRDto newMR){
		//int empId=newMR.getEmployeeId();
		List<Integer> empIds=new ArrayList<Integer>();
//		String officeName=newMR.getOfficeName();
		empIds=newMR.getEmployeeId();
		List<MRDto> meetingRoomsDto=new ArrayList<MRDto>();
		MeetingRoom savedMeetingRoom=new MeetingRoom();

		MeetingRoom meetingRoom=new MeetingRoom();
		
		meetingRoom.setId(0);
		meetingRoom.setFloor(newMR.getFloor());
		meetingRoom.setStatus(newMR.getStatus());
		meetingRoom.setOfficeId(newMR.getOfficeId());
	
		try {
			savedMeetingRoom=mrService.addMeetingRoom(empIds, meetingRoom);
			System.out.println("******* "+savedMeetingRoom.toString());
			MRDto mrDtoooo=new MRDto(savedMeetingRoom);
			mrDtoooo.setOfficeName(newMR.getOfficeName());
			meetingRoomsDto.add(mrDtoooo);
			return new ResponseEntity (new MRResponse("meeting rooms added successfully", meetingRoomsDto),HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity (new MRResponse("something Went wrong", meetingRoomsDto),HttpStatus.NOT_FOUND);

		}
		
		
	}
	
	@PutMapping(value="/meeting-room/setStatus")
	public ResponseEntity<?> updateMeetingRoomStatus(@RequestBody MRDto meetingRoomDto){
	
		 MeetingRoom savedMeetingRoom=new MeetingRoom();
		 List<MRDto> meetingRooms=new ArrayList<MRDto>();		 
		 try {
			 MeetingRoom meetingRoom=mrService.getById(meetingRoomDto.getId());
			 meetingRoom.setStatus(meetingRoomDto.getStatus());

			savedMeetingRoom=mrService.updateMeetingRoom(meetingRoom);
			meetingRooms.add(new MRDto(meetingRoom));
			return new ResponseEntity (new MRResponse("meeting room updated successfully", meetingRooms),HttpStatus.OK);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return new ResponseEntity (new MRResponse("something Went wrong", meetingRooms),HttpStatus.NOT_FOUND);

			}
		
	}
	
	@PutMapping(value="/meeting-room")
	public ResponseEntity<?> updateMeetingRoom(@RequestBody MRDto myMeetingRoomDto){
		MeetingRoom myMeetingRoom=new MeetingRoom();
		MeetingRoom updatedMeetingRoom=new MeetingRoom();

		List<MRDto> myMeetingRoomDtos=new ArrayList<MRDto>();
		try {
			myMeetingRoom=mrService.getById(myMeetingRoomDto.getId());
			myMeetingRoom.setFloor(myMeetingRoomDto.getFloor());
			myMeetingRoom.setStatus(myMeetingRoomDto.getStatus());
			myMeetingRoom.setOfficeId(myMeetingRoomDto.getOfficeId());
			updatedMeetingRoom=mrService.updateMeetingRoom(myMeetingRoom);
			
			 MRDto meetingRoomDto=new MRDto(updatedMeetingRoom);
			myMeetingRoomDtos.add(meetingRoomDto);
			return new ResponseEntity (new MRResponse(" Meeting Room updated Successfully",myMeetingRoomDtos), HttpStatus.OK);

		}catch (StatusException e) {
			e.printStackTrace();
			return new ResponseEntity (new MRResponse("You should enter a status for meeting room",new ArrayList<MRDto>()), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity (new MRResponse("Meeting Room couldnt update "), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping(value="/meeting-room/{roomId}")
	public ResponseEntity<?> deleteEvent(@PathVariable int roomId)
	{
		try {
			mrService.deleteMeetingRoom(roomId);
			return new ResponseEntity(new MRResponse("Meeting Room is deleted Successfully"),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new MRResponse("Something Went wrong"),HttpStatus.NOT_FOUND);

		}
	}


}
