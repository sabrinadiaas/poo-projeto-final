public class TartarugaMarinha extends AnimalSilvestre {
    private Double comprimentoCasco;
    private String localEncalhe;

    public TartarugaMarinha(String habitatNatural, String origem, String dietaPadrao, 
        Double comprimentoCasco, String localEncalhe) {
        super(habitatNatural, origem, dietaPadrao);
        this.comprimentoCasco = comprimentoCasco;
        this.localEncalhe = localEncalhe;
    }

    @Override
    public void exibirInfo(){
        System.out.println("Habitat natural: " + getHabitatNatural());
        System.out.println("Origem: " + getOrigem());
        System.out.println("Dieta padrão: " + getDietaPadrao());
        System.out.println("Comprimento do casco: " + this.comprimentoCasco);
        System.out.println("Local enchalhe: " + this.localEncalhe);
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
