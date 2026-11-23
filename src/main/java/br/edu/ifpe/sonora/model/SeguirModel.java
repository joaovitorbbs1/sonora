package br.edu.ifpe.sonora.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "SEGUE")
public class SeguirModel {

    @EmbeddedId
    private SeguirID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("seguidorID")
    @JoinColumn(name = "SEGUIDOR_ID")
    private UsuarioModel seguidor;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("seguidoID")
    @JoinColumn(name = "SEGUIDO_ID")
    private UsuarioModel seguido;

    @Column(name = "DATA_INICIO", nullable = false)
    private LocalDate dataInicio;

    public SeguirModel(){}

    public SeguirModel(SeguirID id, UsuarioModel seguidor, UsuarioModel seguido, LocalDate dataInicio) {
        this.id = id;
        this.seguidor = seguidor;
        this.seguido = seguido;
        this.dataInicio = dataInicio;
    }

    public SeguirID getId() {
        return id;
    }

    public void setId(SeguirID id) {
        this.id = id;
    }

    public UsuarioModel getSeguidor() {
        return seguidor;
    }

    public void setSeguidor(UsuarioModel seguidor) {
        this.seguidor = seguidor;
    }

    public UsuarioModel getSeguido() {
        return seguido;
    }

    public void setSeguido(UsuarioModel seguido) {
        this.seguido = seguido;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }
}
