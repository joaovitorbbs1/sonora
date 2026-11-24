package br.edu.ifpe.sonora.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ColecaoAlbumID implements Serializable {

    @Column(name = "ID_ALBUM")
    private Long albumID;

    @Column(name = "ID_COLECAO")
    private Long colecaoID;

    public ColecaoAlbumID() {
    }

    public ColecaoAlbumID(Long albumID, Long colecaoID) {
        this.albumID = albumID;
        this.colecaoID = colecaoID;
    }

    public Long getAlbumID() {
        return albumID;
    }

    public Long getColecaoID() {
        return colecaoID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ColecaoAlbumID that)) return false;
        return Objects.equals(albumID, that.albumID) &&
                Objects.equals(colecaoID, that.colecaoID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumID, colecaoID);
    }


}
