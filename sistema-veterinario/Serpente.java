public class Serpente extends AnimalSilvestre{
    private Double comprimento;

    public Serpente(Long id, String habitatNatural, String origem, String dietaPadrao, 
        Double comprimento) {
        super(id, habitatNatural, origem, dietaPadrao);
        this.comprimento = comprimento;
    }

    @Override
    public void exibirInfo(){
        System.out.println("Habitat natural: " + getHabitatNatural());
        System.out.println("Origem: " + getOrigem());
        System.out.println("Dieta padrão: " + getDietaPadrao());
        System.out.println("Comprimento: " + this.comprimento);
    }

    public Double getComprimento() {
        return this.comprimento;
    }

    public void setComprimento(Double comprimento) {
        this.comprimento = comprimento;
    }
}