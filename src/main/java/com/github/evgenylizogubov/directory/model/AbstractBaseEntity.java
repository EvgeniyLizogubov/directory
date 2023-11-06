package com.github.evgenylizogubov.directory.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public abstract class AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        
        return id != null && id.equals(that.id);
    }
    
    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }
}
