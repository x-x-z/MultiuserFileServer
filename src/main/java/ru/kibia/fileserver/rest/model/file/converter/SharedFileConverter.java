package ru.kibia.fileserver.rest.model.file.converter;

import ru.kibia.fileserver.rest.model.file.bean.SharedAccess;
import ru.kibia.fileserver.rest.model.file.bean.SharedAccessRight;
import ru.kibia.fileserver.rest.model.file.bean.SharedFile;
import ru.kibia.fileserver.rest.model.file.entity.SharedAccessEntity;
import ru.kibia.fileserver.rest.model.file.entity.SharedAccessPkEntity;
import ru.kibia.fileserver.rest.model.file.entity.SharedFileEntity;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by D.Kostin on 26.11.2016.
 */
public class SharedFileConverter {

    public static SharedFile entityToBean(SharedFileEntity entity, List<SharedAccessEntity> accessEntities) {
        Set<SharedAccess> accessSet = new HashSet<>();

        for(SharedAccessEntity accessEntity : accessEntities) {
            if(accessEntity.getPk().getFileId() == entity.getId()) {
                accessSet.add(new SharedAccess(
                        accessEntity.getPk().getUserId(),
                        SharedAccessRight.valueOf(accessEntity.getRightId())));
            } else {
                throw new IllegalArgumentException("Wrong access " + accessEntity.getPk() + " for file " + entity);
            }
        }

        return new SharedFile(entity.getId(),
                            new File(entity.getPath()),
                            entity.getUserOwnerId(),
                            accessSet);
    }

    public static SharedFileEntity beanToEntity(SharedFile bean) {
        return new SharedFileEntity(bean.getFile(), bean.getOwnerUserId());
    }

    public static Set<SharedAccessEntity> beanToEntities(long fileId, Set<SharedAccess> accesses) {
        Set<SharedAccessEntity> accessEntities = new HashSet<>();

        for(SharedAccess access : accesses) {
            accessEntities.add(
                    new SharedAccessEntity(
                            new SharedAccessPkEntity(fileId, access.getUserId()),
                            access.getRight().getId())
                    );
        }

        return accessEntities;
    }
}
