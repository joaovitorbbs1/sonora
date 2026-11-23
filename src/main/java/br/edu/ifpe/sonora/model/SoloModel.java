package br.edu.ifpe.sonora.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "ARTISTA_SOLO")
@PrimaryKeyJoinColumn(name = "ID_ARTISTA")
public class SoloModel extends ArtistaModel{

    @Column(name = "NOME_CIVIL", nullable = false)
    private String nomeCivil;

    @Column(name = "DATA_NASC", nullable = false)
    private LocalDate dataNasc;

    public SoloModel() {
    }

    public SoloModel(String nomeArtistico, String nacionalidade, String biografia, int anoInicioCarreira, String nomeCivil, LocalDate dataNasc) {
        super(nomeArtistico, nacionalidade, biografia, anoInicioCarreira);
        this.nomeCivil = nomeCivil;
        this.dataNasc = dataNasc;
    }

    public String getNomeCivil() {
        return nomeCivil;
    }

    public void setNomeCivil(String nomeCivil) {
        this.nomeCivil = nomeCivil;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }
}
