package br.edu.ifpe.sonora.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EDICAO")
public class EdicaoModel {

    @EmbeddedId
    private EdicaoID edicaoID;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("albumId")
    @JoinColumn(name = "ID_ALBUM")
    private AlbumModel album;

    @Enumerated(EnumType.STRING)
    @Column(name = "FORMATO", nullable = false, length = 30)
    private Formatos formato;

    @Column(name = "GRAVADORA", nullable = false, length = 60)
    private String gravadora;

    @Column(name = "DATA_LANCAMENTO", nullable = false)
    private LocalDate dataLancamento;

    @OneToMany(mappedBy = "edicao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FaixaModel> faixas = new ArrayList<>();

    public EdicaoModel() {}

    public EdicaoModel(AlbumModel album, Long numeroEdicao, Formatos formato, String gravadora, LocalDate dataLancamento) {
        this.album = album;
        this.edicaoID = new EdicaoID(album.getId(), numeroEdicao);
        this.formato = formato;
        this.gravadora = gravadora;
        this.dataLancamento = dataLancamento;
    }

    public EdicaoID getEdicaoID() {
        return edicaoID;
    }

    public void setEdicaoID(EdicaoID edicaoID) {
        this.edicaoID = edicaoID;
    }

    public AlbumModel getAlbum() {
        return album;
    }

    public void setAlbum(AlbumModel album) {
        this.album = album;
    }

    public Formatos getFormato() {
        return formato;
    }

    public void setFormato(Formatos formato) {
        this.formato = formato;
    }

    public String getGravadora() {
        return gravadora;
    }

    public void setGravadora(String gravadora) {
        this.gravadora = gravadora;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
}
