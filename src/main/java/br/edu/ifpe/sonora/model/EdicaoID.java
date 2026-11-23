package br.edu.ifpe.sonora.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EdicaoID implements Serializable {

    @Column(name = "ID_ALBUM")
    private Long albumId;

    @Column(name = "NUMERO_EDICAO")
    private Long numeroEdicao;

    public EdicaoID() {
    }

    public EdicaoID(Long albumId, Long numeroEdicao) {
        this.albumId = albumId;
        this.numeroEdicao = numeroEdicao;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public Long getNumeroEdicao() {
        return numeroEdicao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EdicaoID that)) return false;
        return Objects.equals(albumId, that.albumId) && Objects.equals(numeroEdicao, that.numeroEdicao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumId, numeroEdicao);
    }
}
