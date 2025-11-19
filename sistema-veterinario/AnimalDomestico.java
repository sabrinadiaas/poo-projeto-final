public abstract class AnimalDomestico implements Animal{
    private String nome;
    private Integer idade;
    private Double peso;

    public abstract void exibirInfo();

    public AnimalDomestico(String nome, Integer idade, Double peso) {
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
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
