package entity;

import javax.persistence.Entity;

// tag pro banco de dados
@Entity
public class Cachorro extends AnimalDomestico {
    private String raca;
    private String porte;

    // Construtor hibernate
    public Cachorro() {
    }

    public Cachorro(Long id, String nome, Integer idade, Double peso, String raca, String porte) {
        super(id, nome, idade, peso);
        this.raca = raca;
        this.porte = porte;
    }

    @Override
    public void exibirInfo(){
        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("Peso: " + getPeso());
        System.out.println("Raça: " + this.raca);
        System.out.println("Porte: " + this.porte);
        System.out.println("Tipo: Cachorro");
    }

    public String getRaca() {
        return this.raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getPorte() {
        return this.porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }
}