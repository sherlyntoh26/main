package stub;

import java.util.ArrayList;
import java.util.List;

import calendrier.EventHandler;
import utils.Event;
import utils.Priority;

public class EventHandlerStub extends EventHandler {
	private List<Event> events = new ArrayList<>();
	
	public EventHandlerStub(){
		Event deleteEvent = new Event();
		deleteEvent.setId("deleted");
		deleteEvent.setTitle("deleted");
		deleteEvent.setPriority(Priority.LOW);
		events.add(deleteEvent);
		
		Event updateEvent = new Event();
		updateEvent.setId("updated");
		updateEvent.setTitle("updated");
		updateEvent.setPriority(Priority.LOW);
		events.add(updateEvent);
	}
	
	@Override
	public Event add(String identifier, Event event) {
		// TODO Auto-generated method stub
		events.add(event);
		return event;
	}

	

	@Override
	public Event remove(String identifier, Event event) {
		// TODO Auto-generated method stub
		Event deletedItem = null;
		for(Event eventItem : events){
			if(eventItem.getTitle().equals(identifier)){
				deletedItem = eventItem;
				break;
			}
		}
		events.remove(deletedItem);
		return event;
	}
	
	

	@Override
	public Event update(String identifier, Event event) {
		// TODO Auto-generated method stub
		for(Event eventItem : events){
			if(eventItem.getTitle().equals(identifier)){
				eventItem.setPriority(event.getPriority());
				break;
			}
		}
		return event;
	}

	public List<Event> getEvents() {
		return events;
	}

	@Override
	public List<Event> getAllEvents() {
		// TODO Auto-generated method stub
		return events;
	}
	
	
}
