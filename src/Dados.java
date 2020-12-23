public class Dados {
    private String registro;
    private String codigoAutorizacao;
    private String tipoLista;
    private String tipoArquivo;
    private String cartao;
    private String complemento;
    private String viaCartao;
    private String acao;

    public Dados() {
        this.registro = "";
        this.codigoAutorizacao = "";
        this.tipoLista = "";
        this.tipoArquivo = "";
        this.cartao = "";
        this.viaCartao = "";
        this.acao = "";
        this.complemento = "";
    }

    public Dados(String registro, String codigoAutorizacao, String tipoLista, String tipoArquivo, String cartao, String complemento) {
        this.registro = registro;
        this.codigoAutorizacao = codigoAutorizacao;
        this.tipoLista = tipoLista;
        this.tipoArquivo = tipoArquivo;
        this.cartao = cartao;
        this.complemento = complemento;
        this.viaCartao = "";
        this.acao = "";
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getCodigoAutorizacao() {
        return codigoAutorizacao;
    }

    public void setCodigoAutorizacao(String codigoAutorizacao) {
        this.codigoAutorizacao = codigoAutorizacao;
    }

    public String getTipoLista() {
        return tipoLista;
    }

    public void setTipoLista(String tipoLista) {
        this.tipoLista = tipoLista;
    }

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public void setViaCartao(String viaCartao) {
        this.viaCartao = viaCartao;
    }
    public String getViaCartao() {
        return viaCartao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }
    public String getAcao() {
        return acao;
    }

    @Override
    public String toString() {
        return this.registro +  this.codigoAutorizacao +
                this.tipoLista + this.tipoArquivo +
               this.cartao + this.complemento + this.viaCartao + this.acao;
    }



}
