package stub;

public class CalenderTime{
	private Boolean avalibility;
	private String task;
	
	public CalenderTime(){}
	public CalenderTime(Boolean input){
		this.avalibility=input;
	}
	public void setTime(Boolean input, String info){
		setTimeSlot(input);
		setTask(info);
	}
	public void setTimeSlot(Boolean input){
		this.avalibility = input;
	}
	public void setTask(String input){
		this.task = input;
	}
	public String getTask(){
		return task;
	}
	public Boolean getTimeSlot(){
		return avalibility;
	}
	public void clear(){
		avalibility=false;
		task="";
	}
}