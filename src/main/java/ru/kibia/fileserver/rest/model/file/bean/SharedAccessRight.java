package ru.kibia.fileserver.rest.model.file.bean;

/**
 * Created by D.Kostin on 05.12.2016.
 */
public enum SharedAccessRight {
    READ (1),
    READ_WRITE (2),
    UNKNOWN (-1);

    private int id;

    SharedAccessRight(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static SharedAccessRight valueOf(int id) {
        for(SharedAccessRight right : values()) {
            if(right.id == id) {
                return right;
            }
        }

        return UNKNOWN;
    }
}
