export class EventModel {
  id!: number;
  name!: string;
  startTime!: string; // LocalTime will be represented as string
  endTime!: string;   // LocalTime will be represented as string
  date!: string;      // LocalDate will be represented as string
  description!: string;
  status!: string;
  meetingRoomId!: number;
  employeeId!: number;
  roomDto!: {
    floor?: string;
    status?: string;
  };
  employeeDto!:{
    firstName?:string;
    lastName?:string;
  }

  constructor(
    id: number,
    name: string,
    startTime: string,
    endTime: string,
    date: string,
    description: string,
    status: string,
    meetingRoomId: number,
    employeeId: number,
    roomDto: {
      floor?: string;
      status?: string;
    } = {}, // Default value as an empty object
    employeeDto:{
      firstName?:string;
    lastName?:string;
    }={}
  ) {
    this.id = id;
    this.name = name;
    this.startTime = startTime;
    this.endTime = endTime;
    this.date = date;
    this.description = description;
    this.status = status;
    this.meetingRoomId = meetingRoomId;
    this.employeeId = employeeId;
    this.roomDto = roomDto; // Assign the whole roomDto object
    this.employeeDto=employeeDto
  }

  setStatus(status:string):void{
    this.status=status;
  }
}
