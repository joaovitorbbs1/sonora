package br.edu.ifpe.sonora.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRIMEIRO_NOME", nullable = false, length = 20)
    private String primeiroNome;

    @Column(name = "SOBRENOME",nullable = false, length = 60)
    private String sobrenome;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "USERNAME",nullable = false, unique = true)
    private String user;

    @Column(name = "SENHA", nullable = false)
    private String senha;

    @Column(name = "DATA_NASC", nullable = false)
    private LocalDate dataNasc;

    @Column(name = "CIDADE", nullable = false)
    private String cidade;

    @Column(name = "ESTADO", nullable = false)
    private String estado;

    @Column(name = "PAIS", nullable = false)
    private String pais;

    @ElementCollection
    @CollectionTable(name = "usuario_telefone", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "TELEFONE", nullable = false)
    private Set<String> telefones = new HashSet<>();

    public UsuarioModel() {}

    public UsuarioModel(String primeiroNome, String sobrenome, String email, String user, String senha, String cidade, LocalDate dataNasc, String estado, String pais, Set<String> telefones) {
        this.primeiroNome = primeiroNome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.user = user;
        this.senha = senha;
        this.cidade = cidade;
        this.dataNasc = dataNasc;
        this.estado = estado;
        this.pais = pais;
        this.telefones = telefones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }
}
