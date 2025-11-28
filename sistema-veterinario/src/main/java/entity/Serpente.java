package entity;

import javax.persistence.Entity;

// tag pro banco de dados
@Entity
public class Serpente extends AnimalSilvestre {
    private Double comprimento;

    // Construtor hibernate
    public Serpente() {
    }

    public Serpente(Long id, String habitatNatural, String origem, String dietaPadrao, 
        Double comprimento) {
        super(id, habitatNatural, origem, dietaPadrao);
        this.comprimento = comprimento;
    }

    @Override
    public void exibirInfo(){
        System.out.println("ID: " + getId());
        System.out.println("Habitat natural: " + getHabitatNatural());
        System.out.println("Origem: " + getOrigem());
        System.out.println("Dieta padrão: " + getDietaPadrao());
        System.out.println("Comprimento: " + this.comprimento + " cm");
        System.out.println("Tipo: Serpente");
    }

    public Double getComprimento() {
        return this.comprimento;
    }

    public void setComprimento(Double comprimento) {
        this.comprimento = comprimento;
    }
}