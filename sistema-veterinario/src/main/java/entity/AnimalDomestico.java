package entity;

import javax.persistence.*;

// tag pro banco de dados
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AnimalDomestico implements Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nome;
    private Integer idade;
    private Double peso;

    //Construtor hibernate
    public AnimalDomestico() {
    }

    public AnimalDomestico(Long id, String nome, Integer idade, Double peso) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
    }

    @Override
    public abstract void exibirInfo();

    // Getters e Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return this.idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getPeso() {
        return this.peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
}