package entity;

import javax.persistence.*;

// tag pro banco de dados
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AnimalSilvestre implements Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String habitatNatural;
    private String origem;
    private String dietaPadrao;

    // Construtor hibernate
    public AnimalSilvestre() {
    }

    public AnimalSilvestre(Long id, String habitatNatural, String origem, String dietaPadrao) {
        this.id = id;
        this.habitatNatural = habitatNatural;
        this.origem = origem;
        this.dietaPadrao = dietaPadrao;
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

    public String getHabitatNatural() {
        return this.habitatNatural;
    }

    public void setHabitatNatural(String habitatNatural) {
        this.habitatNatural = habitatNatural;
    }

    public String getOrigem() {
        return this.origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDietaPadrao() {
        return this.dietaPadrao;
    }

    public void setDietaPadrao(String dietaPadrao) {
        this.dietaPadrao = dietaPadrao;
    }
}