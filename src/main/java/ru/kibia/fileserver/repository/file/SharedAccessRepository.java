package ru.kibia.fileserver.repository.file;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kibia.fileserver.rest.model.file.entity.SharedAccessEntity;
import ru.kibia.fileserver.rest.model.file.entity.SharedAccessPkEntity;

import java.util.List;

/**
 * Created by kostin on 22.11.2016.
 */
public interface SharedAccessRepository extends JpaRepository<SharedAccessEntity, SharedAccessPkEntity> {

    List<SharedAccessEntity> findAllByPkUserId(long userId);
    List<SharedAccessEntity> findAllByPkFileId(long fileId);
}
