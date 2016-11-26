package ru.kibia.fileserver.mvc.model.file.converter;

import ru.kibia.fileserver.mvc.model.file.bean.SharedFile;
import ru.kibia.fileserver.mvc.model.file.entity.SharedAccessEntity;
import ru.kibia.fileserver.mvc.model.file.entity.SharedAccessPkEntity;
import ru.kibia.fileserver.mvc.model.file.entity.SharedFileEntity;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by D.Kostin on 26.11.2016.
 */
public class SharedFileConverter {

    public static SharedFile entityToBean(SharedFileEntity entity, List<SharedAccessEntity> accessEntities) {
        Set<Long> accessSet = new HashSet<>();
        for(SharedAccessEntity accessEntity : accessEntities) {
            if(accessEntity.getPk().getFileId() == entity.getId()) {
                accessSet.add(accessEntity.getPk().getUserId());
            } else {
                throw new IllegalArgumentException("Wrong access " + accessEntity.getPk() + " for file " + entity);
            }
        }

        return new SharedFile(entity.getId(), new File(entity.getPath()), entity.getUserOwnerId(), accessSet);
    }

    public static SharedFileEntity beanToEntity(SharedFile bean) {
        return new SharedFileEntity(bean.getFile(), bean.getOwnerUserId());
    }

    public static List<SharedAccessEntity> beanToEntities(long fileId, Set<Long> usersId) {
        List<SharedAccessEntity> accessEntities = new ArrayList<>();

        for(Long userId : usersId) {
            accessEntities.add(new SharedAccessEntity(new SharedAccessPkEntity(fileId, userId)));
        }

        return accessEntities;
    }
}
