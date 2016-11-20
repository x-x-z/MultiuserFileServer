package ru.kibia.fileserver.repository.file;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kibia.fileserver.mvc.model.entity.SharedFile;

/**
 * Created by D.Kostin on 19.11.2016.
 */
public interface SharedFileRepository extends JpaRepository<SharedFile, Long> {

}
