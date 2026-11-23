package br.edu.ifpe.sonora.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "COLECAO")
public class ColecaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME", nullable = false, length = 40)
    private String nome;

    @Column(name = "DESCRICAO", columnDefinition = "text")
    private String descricao;

    @Column(name = "VISIBILIDADE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Visibilidade visibilidade;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDate dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private UsuarioModel usuario;

    public ColecaoModel(){}

    public ColecaoModel(Long id, String nome, String descricao,
                        Visibilidade visibilidade, LocalDate dataCriacao, UsuarioModel usuario) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.visibilidade = visibilidade;
        this.dataCriacao = dataCriacao;
        this.usuario = usuario;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Visibilidade getVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(Visibilidade visibilidade) {
        this.visibilidade = visibilidade;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
}
