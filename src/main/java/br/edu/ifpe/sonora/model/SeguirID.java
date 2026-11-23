package br.edu.ifpe.sonora.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SeguirID implements Serializable {

    @Column(name = "SEGUIDOR_ID")
    private Long seguidorID;

    @Column(name = "SEGUIDO_ID")
    private Long seguidoID;

    public SeguirID() {
    }

    public SeguirID(Long seguidorID, Long seguidoID) {
        this.seguidorID = seguidorID;
        this.seguidoID = seguidoID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof SeguirID that)) return false;
        return Objects.equals(seguidorID, that.seguidorID) &&
                Objects.equals(seguidoID, that.seguidoID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seguidoID, seguidorID);
    }
}
