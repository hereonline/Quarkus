package de.benevolo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped

public class EventRepository implements PanacheRepository<Event>
{
    // quarkus machts selber
}
