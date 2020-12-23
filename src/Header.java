import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Header {
    private String registro;
    private String banco;
    private String data;
    private String tipo;
    private String sequencia;
    private String filler;

    public Header() {
        Calendar c = Calendar.getInstance();
        Date data = c.getTime();
        DateFormat f = DateFormat.getDateInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        this.registro = "0";
        this.banco = "0898";
        this.data = sdf.format(data);
        this.tipo = "GLB";
        this.sequencia = "0001";
        this.filler = "                            ";
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public String getSequencia() {
        return sequencia;
    }

    public void setSequencia(String sequencia) {
        this.sequencia = sequencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    @Override
    public String toString() {
        return this.registro +  this.banco +
                this.data + this.tipo +
                this.sequencia + this.filler;
    }

}
