package de.benevolo;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/event")
public class EventResource {

    @Inject
    EventRepository eventRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Event postEvent(Event event)
    {
        eventRepository.persist(event);
        return event;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> events()
    {

        return eventRepository.listAll();
    }
    @DELETE
    @Path("/{id}")
    @Transactional
    public void deleteEvent(@PathParam("id") Long id) {

        Event event = eventRepository.findById(id);
        if(event != null) {

            eventRepository.delete(event);
        } else {

            throw new NotFoundException("Event with ID " + id + " not found");
        }

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Event updateEvent(@PathParam("id") Long id, Event updateEvent) {
        Event event = eventRepository.findById(id);
        if (event == null) {
            throw new NotFoundException("Event with ID " + id + " not found");
        }

         event.setName(updateEvent.getName());
         event.setDescription(updateEvent.getDescription());
         event.setDate(updateEvent.getDate());

        eventRepository.persist(event);

        return event;
    }

}
