package ru.kibia.fileserver.repository.file;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kibia.fileserver.mvc.model.file.entity.SharedFileEntity;
import java.util.List;

/**
 * Created by D.Kostin on 19.11.2016.
 */
public interface SharedFileRepository extends JpaRepository<SharedFileEntity, Long> {

    List<SharedFileEntity> findAllByUserOwnerId(long userId);

}
