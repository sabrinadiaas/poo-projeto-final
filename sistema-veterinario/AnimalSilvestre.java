public abstract class AnimalSilvestre implements Animal{
    private String habitatNatural;
    private String origem;
    private String dietaPadrao;

    public abstract void exibirInfo();

    public AnimalSilvestre(String habitatNatural, String origem, String dietaPadrao) {
        this.habitatNatural = habitatNatural;
        this.origem = origem;
        this.dietaPadrao = dietaPadrao;
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
