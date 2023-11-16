package com.github.evgenylizogubov.directory.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class TreePathId implements Serializable {
    private int ancestor;
    private int descendant;
}
