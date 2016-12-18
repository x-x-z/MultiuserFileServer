package ru.kibia.fileserver.facade.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kibia.fileserver.rest.model.file.bean.SharedFile;
import ru.kibia.fileserver.rest.model.file.converter.SharedFileConverter;
import ru.kibia.fileserver.rest.model.file.entity.SharedAccessEntity;
import ru.kibia.fileserver.rest.model.file.entity.SharedFileEntity;
import ru.kibia.fileserver.repository.file.SharedAccessRepository;
import ru.kibia.fileserver.repository.file.SharedFileRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by D.Kostin on 26.11.2016.
 */
@Component
public class SharedFileFacade {

    @Autowired
    private SharedFileRepository fileRepository;

    @Autowired
    private SharedAccessRepository accessRepository;

    @Transactional
    public void save(SharedFile sharedFile) {
        SharedFileEntity entity = SharedFileConverter.beanToEntity(sharedFile);
        fileRepository.save(entity);
        accessRepository.save(SharedFileConverter.beanToEntities(entity.getId(), sharedFile.getAccesses()));
    }

    @Transactional(readOnly = true)
    public List<SharedFile> getAllUserFiles(long userId) {
        List<SharedFileEntity> entityList = fileRepository.findAllByUserOwnerId(userId);
        List<SharedFile> beanList = new ArrayList<>();

        for(SharedFileEntity entity : entityList) {
            beanList.add(
                    SharedFileConverter.entityToBean(
                            entity,
                            accessRepository.findAllByPkFileId(entity.getId())
                    )
            );
        }

        return beanList;
    }

    @Transactional(readOnly = true)
    public List<SharedFile> getAllAvailableFiles(long userId) {
        List<SharedAccessEntity> accessEntities = accessRepository.findAllByPkUserId(userId);
        List<SharedFile> beanList = new ArrayList<>();

        for(SharedAccessEntity entity : accessEntities) {
            beanList.add(
                    SharedFileConverter.entityToBean(
                            fileRepository.getOne(entity.getPk().getFileId()),
                            accessRepository.findAllByPkFileId(entity.getPk().getFileId())
                    )
            );
        }

        return beanList;
    }

    public void flush() {
        fileRepository.flush();
    }
}
