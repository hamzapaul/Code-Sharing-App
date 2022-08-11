package platform.repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.models.Code;

import java.util.List;
import java.util.UUID;

@Repository
public interface CodeRepo extends CrudRepository<Code, UUID> {

    @Query(value = "SELECT * FROM CODE \n" +
            "WHERE IS_TIME_RESTRICTED = FALSE AND IS_VIEWS_RESTRICTED  = FALSE\n" +  //todo
            "ORDER BY TIMESTAMP DESC\n" +
            "LIMIT 10", nativeQuery = true)
    List<Code> findLatest();
}
