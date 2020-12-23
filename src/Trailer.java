public class Trailer {

    private String registro;
    private String quantidade;
    private String filler;

    public Trailer(int quantidade) {
        this.registro = "9";
        this.quantidade = String.format("%08d", quantidade);
        this.filler = "                                       ";
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = String.format("%08d", quantidade);
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    @Override
    public String toString() {
        return this.registro +  this.quantidade +
                this.filler;
    }
}
