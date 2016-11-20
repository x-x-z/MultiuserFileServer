package ru.kibia.fileserver.mvc.model.entity;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;

@Entity
@Table(name = "SHARED_FILES", uniqueConstraints = { @UniqueConstraint( columnNames = { "path", "userId" } ) })
public class SharedFile implements Serializable {

    private static final long serialVersionUID = 8999225423023905892L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="shared_file_id_seq")
    @SequenceGenerator(name="shared_file_id_seq", sequenceName="shared_files_sequence")
    private long fileId;

    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false)
    private String path;

    private long size;

    @ManyToOne
    @JoinTable(name="SHARED_ACCESS")
    private User userSlave;

    public SharedFile() {
    }

    public SharedFile(File file, User user) {
        this.path = file.getAbsolutePath();
        this.size = file.length();
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format(
                "SharedFile[fileId=%d, userId='%s', path='%s', size = %d]",
                fileId, user.getUserId(), path, size);
    }
}