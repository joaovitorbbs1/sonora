package br.edu.ifpe.sonora.model;

import jakarta.persistence.*;

@Entity
@Table(name = "BANDA")
@PrimaryKeyJoinColumn(name = "ID_ARTISTA")
public class BandaModel extends ArtistaModel {

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_ATIVIDADE", nullable = false)
    private Status statusAtividade;

    public BandaModel() {}

    public BandaModel(String nomeArtistico, String nacionalidade, String biografia,
                      int anoInicioCarreira, Status statusAtividade) {
        super(nomeArtistico, nacionalidade, biografia, anoInicioCarreira);
        this.statusAtividade = statusAtividade;
    }

    public Status getStatusAtividade() {
        return statusAtividade;
    }

    public void setStatusAtividade(Status statusAtividade) {
        this.statusAtividade = statusAtividade;
    }
}
