package ru.kibia.fileserver.repository.file;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kibia.fileserver.mvc.model.file.entity.SharedAccessEntity;
import ru.kibia.fileserver.mvc.model.file.entity.SharedAccessPkEntity;

/**
 * Created by kostin on 22.11.2016.
 */
public interface SharedAccessRepository extends JpaRepository<SharedAccessEntity, SharedAccessPkEntity> {
}
