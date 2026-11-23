package br.edu.ifpe.sonora.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ARTISTA")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ArtistaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ARTISTA")
    private Long id;

    @Column(name = "NOME_ARTISTICO", nullable = false, length = 50)
    private String nomeArtistico;

    @Column(name = "NACIONALIDADE", nullable = false, length = 30)
    private String nacionalidade;

    @Column(name = "BIOGRAFIA", columnDefinition = "TEXT")
    private String biografia;

    @Column(name = "ANO_INICIO_CARREIRA", nullable = false)
    private int anoInicioCarreira;

    @ManyToMany(mappedBy = "artista")
    private Set<AlbumModel> album = new HashSet<>();

    public ArtistaModel(){}

    public ArtistaModel(String nomeArtistico, String nacionalidade, String biografia, int anoInicioCarreira) {
        this.nomeArtistico = nomeArtistico;
        this.nacionalidade = nacionalidade;
        this.biografia = biografia;
        this.anoInicioCarreira = anoInicioCarreira;
    }

    public Set<AlbumModel> getAlbum() {
        return album;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeArtistico() {
        return nomeArtistico;
    }

    public void setNomeArtistico(String nomeArtistico) {
        this.nomeArtistico = nomeArtistico;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public int getAnoInicioCarreira() {
        return anoInicioCarreira;
    }

    public void setAnoInicioCarreira(int anoInicioCarreira) {
        this.anoInicioCarreira = anoInicioCarreira;
    }
}
