package by.gvu.resource.repository;

import by.gvu.resource.model.data.Mp3FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Mp3FileRepository extends JpaRepository<Mp3FileModel, Long> {
}
