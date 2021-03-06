package ch.skunky.skunklaw.repository;

import ch.skunky.skunklaw.model.law.LawClient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * https://www.baeldung.com/spring-data-repositories
 * https://www.baeldung.com/spring-data-rest-relationships
 *
 * one to many mappings:
 * https://vladmihalcea.com/jpa-hibernate-synchronize-bidirectional-entity-associations/
 * https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
 */

@Repository
@Transactional
public interface ClientRepository extends CrudRepository<LawClient, Long> {

    List<LawClient> findClientByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
    List<LawClient> findAllByOrderByLastNameAscFirstNameAsc();

}
