package ru.kibia.fileserver.mvc.model.file.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by kostin on 22.11.2016.
 */
@Entity
@Table(name = "SHARED_ACCESS")
public class SharedAccessEntity {

    @EmbeddedId
    private SharedAccessPkEntity pk;

    public SharedAccessEntity() {
    }

    public SharedAccessEntity(SharedAccessPkEntity pk) {
        this.pk = pk;
    }

    public SharedAccessPkEntity getPk() {
        return pk;
    }

    @Override
    public String toString() {
        return "SharedAccessEntity{" +
                "pk=" + pk +
                '}';
    }
}
