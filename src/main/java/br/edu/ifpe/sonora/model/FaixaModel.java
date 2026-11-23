package br.edu.ifpe.sonora.model;


import jakarta.persistence.*;

@Entity
@Table(name = "FAIXA")
public class FaixaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITULO", nullable = false)
    private String titulo;

    @Column(name = "ORDEM", nullable = false)
    private int ordem;

    @Column(name = "DURACAO", nullable = false)
    private int duracao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "EDICAO_ALBUM_ID", referencedColumnName = "ID_ALBUM", nullable = false),
            @JoinColumn(name = "EDICAO_NUMERO_EDICAO", referencedColumnName = "NUMERO_EDICAO", nullable = false)
    })
    private EdicaoModel edicao;

    public FaixaModel() {
    }

    public FaixaModel(String titulo, int ordem, int duracao, EdicaoModel edicao) {
        this.titulo = titulo;
        this.ordem = ordem;
        this.duracao = duracao;
        this.edicao = edicao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public EdicaoModel getEdicao() {
        return edicao;
    }

    public void setEdicao(EdicaoModel edicao) {
        this.edicao = edicao;
    }
}
