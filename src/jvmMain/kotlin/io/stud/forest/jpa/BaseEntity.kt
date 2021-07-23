package io.stud.forest.jpa

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.domain.Persistable
import java.time.Instant
import java.util.*
import javax.persistence.*

@MappedSuperclass
//@Suppress("NO_NOARG_CONSTRUCTOR_IN_SUPERCLASS")
abstract class BaseEntity: CommonBaseEntity(UUID.randomUUID()) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}

@MappedSuperclass
//@Suppress("NO_NOARG_CONSTRUCTOR_IN_SUPERCLASS")
abstract class FixedIdEntity():
    CommonBaseEntity(UUID.randomUUID()),
    Persistable<UUID> {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private val id = uuid

    override fun getId(): UUID = id
    override fun isNew(): Boolean = true

    override fun toString(): String {
        return "BaseEntity(id=$id, version=$version, createdAt=$createdAt, updatedAt=$updatedAt, isNew=$isNew)"
    }
}

@MappedSuperclass
@Suppress("NO_NOARG_CONSTRUCTOR_IN_SUPERCLASS")
sealed class CommonBaseEntity(val uuid: UUID){
    @Version
    var version: Int = 0

    @field:CreationTimestamp
    val createdAt: Instant? = null

    @field:UpdateTimestamp
    val updatedAt: Instant? = null

    override fun hashCode(): Int = uuid.hashCode()
    override fun equals(other: Any?): Boolean {
        return (this === other || other is CommonBaseEntity)
                && (uuid == other.uuid)
    }
}

//TODO: make Embeddable version

@Embeddable
class Audit{
    //    @field:CreationTimestamp
    var createdOn: Instant? = null

    //    @field:UpdateTimestamp
    var updatedOn: Instant? = null

    @PrePersist
    fun prePersist(){
        createdOn = Instant.now()
    }

    @PreUpdate
    fun preUpdate(){
        updatedOn = Instant.now()
    }
}