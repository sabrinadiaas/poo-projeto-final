package entity;

import javax.persistence.Entity;

// tag pro banco de dados
@Entity
public class Gato extends AnimalDomestico {
    private String pelagem;

    // Construtor hibernate
    public Gato() {
    }

    public Gato(Long id, String nome, Integer idade, Double peso, String pelagem) {
        super(id, nome, idade, peso);
        this.pelagem = pelagem;
    }

    @Override 
    public void exibirInfo() {
        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("Peso: " + getPeso());
        System.out.println("Pelagem: " + this.pelagem);
        System.out.println("Tipo: Gato");
    }

    public String getPelagem() {
        return this.pelagem;
    }

    public void setPelagem(String pelagem) {
        this.pelagem = pelagem;
    }
}