package ir.nas.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@SuperBuilder
@MappedSuperclass
public abstract class BaseModel<ID>
{
    @Column(name = "created_at")
    @CreationTimestamp(source = SourceType.DB)
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    @UpdateTimestamp(source = SourceType.DB)
    private LocalDateTime updateAt;

    public abstract ID getId();

    // @PrePersist
    // private void prePersiste()
    // {
    //     if (this.createdAt == null)
    //         this.createdAt = LocalDateTime.now();

    //     if (this.updateAt == null)
    //         this.updateAt = LocalDateTime.now();
    // }

    // @PreUpdate
    // private void preUpdate()
    // {
    //     if (this.updateAt == null)
    //         this.updateAt = LocalDateTime.now();
    // }
}
