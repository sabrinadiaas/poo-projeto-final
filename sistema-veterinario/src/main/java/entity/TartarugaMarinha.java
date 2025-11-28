package entity;

import javax.persistence.Entity;

// tag pro banco de dados
@Entity
public class TartarugaMarinha extends AnimalSilvestre {
    private Double comprimentoCasco;
    private String localEncalhe;

    // Construtor hibernate
    public TartarugaMarinha() {
    }

    public TartarugaMarinha(Long id, String habitatNatural, String origem, String dietaPadrao, 
        Double comprimentoCasco, String localEncalhe) {
        super(id, habitatNatural, origem, dietaPadrao);
        this.comprimentoCasco = comprimentoCasco;
        this.localEncalhe = localEncalhe;
    }

    @Override
    public void exibirInfo(){
        System.out.println("ID: " + getId());
        System.out.println("Habitat natural: " + getHabitatNatural());
        System.out.println("Origem: " + getOrigem());
        System.out.println("Dieta padrão: " + getDietaPadrao());
        System.out.println("Comprimento do casco: " + this.comprimentoCasco + " cm");
        System.out.println("Local encalhe: " + this.localEncalhe);
        System.out.println("Tipo: Tartaruga Marinha");
    }

    public Double getComprimentoCasco() {
        return this.comprimentoCasco;
    }

    public void setComprimentoCasco(Double comprimentoCasco) {
        this.comprimentoCasco = comprimentoCasco;
    }

    public String getLocalEncalhe() {
        return this.localEncalhe;
    }

    public void setLocalEncalhe(String localEncalhe) {
        this.localEncalhe = localEncalhe;
    }
}