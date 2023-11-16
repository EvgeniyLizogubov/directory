package com.github.evgenylizogubov.directory.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "tree_path")
@IdClass(TreePathId.class)
@Getter
@Setter
@NoArgsConstructor
public class TreePath implements Serializable {
    @Id
    @ManyToOne(targetEntity = Heading.class)
    @JoinColumn(name = "ancestor", nullable = false, foreignKey = @ForeignKey(name = "fk_ancestor"))
    private Heading ancestor;
    
    @Id
    @ManyToOne(targetEntity = Heading.class)
    @JoinColumn(name = "descendant", nullable = false, foreignKey = @ForeignKey(name = "fk_descendant"))
    private Heading descendant;
}
