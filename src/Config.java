import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Config {
    private String pathBase;
    private ArrayList<String> listaBins;
    private final String arquivoListaBins = "bins.conf";
    private final String arquivoPathBase = "path.conf";


    public Config () throws Exception {
        listaBins = new ArrayList<>();
        carregaLista();
        carregaPath();
    }

    private void carregaLista() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(arquivoListaBins));
        String linha;
        while (br.ready()) {
            linha = br.readLine();
            processaLinhaListaBins(linha);
        }
        br.close();
    }

    private void processaLinhaListaBins(String linha) throws Exception {
        String[] dados = linha.split(";");
        for (int i = 0; i < dados.length; i ++){
            listaBins.add(dados[i]);
            System.out.println(dados[i]);
        }
    }

    private void carregaPath() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(arquivoPathBase));
        String linha;
        while (br.ready()) {
            linha = br.readLine();
            processaLinhaPath(linha);
        }
        br.close();
    }

    private void processaLinhaPath(String linha) throws Exception {
        String[] dados = linha.split("=");
        pathBase = dados[1] + "/";
        System.out.println(pathBase);
    }

    public String getPathBase() {
        return pathBase;
    }

    public void setPathBase(String pathBase) {
        this.pathBase = pathBase;
    }

    public String getListaBins(int indice) {
        return listaBins.get(indice);
    }

    public int getSizeListaBins() {
        return listaBins.size();
    }
}
