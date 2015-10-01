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
		
		Event viewEvent = new Event();
		viewEvent.setId("viewDetails");
		viewEvent.setTitle("viewDetails");
		viewEvent.setPriority(Priority.HIGH);
		events.add(viewEvent);
	}
	
	@Override
	public Event add(String identifier, Event eventDetails) {
		// TODO Auto-generated method stub
		events.add(eventDetails);
		return eventDetails;
	}

	

	@Override
	public Event remove(String identifier, Event eventDetails) {
		// TODO Auto-generated method stub
		Event deletedItem = null;
		for(Event eventItem : events){
			if(eventItem.getTitle().equals(identifier)){
				deletedItem = eventItem;
				break;
			}
		}
		events.remove(deletedItem);
		return eventDetails;
	}
	
	

	@Override
	public Event view(String identifier) {
		// TODO Auto-generated method stub
		Event viewEvent = null;
		for(Event event : events){
			if(event.getTitle().equals(identifier)){
				viewEvent = event;
				break;
			}
		}
		return viewEvent;
	}

	@Override
	public Event update(String identifier, Event eventDetails) {
		// TODO Auto-generated method stub
		for(Event eventItem : events){
			if(eventItem.getTitle().equals(identifier)){
				eventItem.setPriority(eventDetails.getPriority());
				break;
			}
		}
		return eventDetails;
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
