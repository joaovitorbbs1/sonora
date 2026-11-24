package br.edu.ifpe.sonora.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "COLECAO_CONTEM_ALBUM")
public class ColecaoAlbumModel {

    @EmbeddedId
    private ColecaoAlbumID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_COLECAO")
    @MapsId("colecaoID")
    private ColecaoModel colecao;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("albumID")
    @JoinColumn(name = "ID_ALBUM")
    private AlbumModel album;

    @Column(name = "DATA_INCLUSAO", nullable = false)
    private LocalDate dataInclusao;

    public ColecaoAlbumModel() {
    }

    public ColecaoAlbumModel(ColecaoAlbumID id, ColecaoModel colecao, AlbumModel album, LocalDate dataInclusao) {
        this.id = id;
        this.colecao = colecao;
        this.album = album;
        this.dataInclusao = dataInclusao;
    }



    public ColecaoAlbumID getId() {
        return id;
    }

    public void setId(ColecaoAlbumID id) {
        this.id = id;
    }

    public ColecaoModel getColecao() {
        return colecao;
    }

    public void setColecao(ColecaoModel colecao) {
        this.colecao = colecao;
    }

    public AlbumModel getAlbum() {
        return album;
    }

    public void setAlbum(AlbumModel album) {
        this.album = album;
    }

    public LocalDate getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDate dataInclusao) {
        this.dataInclusao = dataInclusao;
    }
}
