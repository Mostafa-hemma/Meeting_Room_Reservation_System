export class meetingRoomModel{
    id!:number
    floor!:string
    status!:string
    employeeId!:number[]
    officeId!:number
    officeName!:string
    constructor(id:number,floor:string,status:string,employeeId:number[]=[],officeId:number,officeName:string){
        this.id=id
        this.floor=floor
        this.status=status
        this.employeeId=employeeId
        this.officeId=officeId
        this.officeName=officeName
    }
}