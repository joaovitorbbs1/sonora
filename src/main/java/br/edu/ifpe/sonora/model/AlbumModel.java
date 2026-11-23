package br.edu.ifpe.sonora.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="album")
public class AlbumModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @ManyToMany
    @JoinTable(name = "ARTISTA_PARTICIPA_ALBUM",
            joinColumns = @JoinColumn(name = "ID_ALBUM"),
            inverseJoinColumns = @JoinColumn(name = "ID_ARTISTA")
    )
    private Set<ArtistaModel> artista = new HashSet<>();

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EdicaoModel> edicoes = new ArrayList<>();

    public AlbumModel(){}

    public AlbumModel(Genero genero, String titulo) {
        this.genero = genero;
        this.titulo = titulo;
    }

    public void adicionarArtista(ArtistaModel artista) {
        this.artista.add(artista);
        artista.getAlbum().add(this);
    }

    public Long getId() {
        return id;
    }

    public Set<ArtistaModel> getArtistas() {
        return artista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}
