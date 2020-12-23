import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class ProcessaArquivo {

    private static int numeroLinha;
    private static ArrayList<String> listaCompleta = new ArrayList<String>();
    private static String pathBase;

    public ProcessaArquivo(){
        numeroLinha = 0;
    }

    public void executa(Config config) throws Exception {
        File arquivo = new File(config.getPathBase());
        pathBase = config.getPathBase();
        popularListaCompleta();
        while (true) {
            File[] files = arquivo.listFiles();
            for (File f : files) {
                if (f.isFile()) {
                    insereHeader();
                    int inserida = carregaDados(f.getName(), config);
                    insereTrailer(inserida);
                    moverArquivoProcessado(f);
                }
            }
        }
    }

    private void popularListaCompleta() throws Exception {
            BufferedReader br = new BufferedReader(new FileReader(pathBase  + "/completo/completo.txt"));
            String linha;
            while (br.ready()) {
                linha = br.readLine();
                listaCompleta.add(linha.substring(4,23));
            }
            System.out.println(listaCompleta.size());
            br.close();
    }

    public void moverArquivoProcessado(File file) throws Exception {
        if (file.renameTo(new File(pathBase + "/processado" , file.getName()))){
            System.out.println("Arquivo foi movido com sucesso");
        } else {
            System.out.println("Nao foi possivel mover o arquivo");
        }
    }

    private void insereHeader() throws Exception{
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(formataArquivoSaida(),false));
        Header header = new Header();
        buffWrite.append(header.toString());
        buffWrite.append("\n");
        buffWrite.close();
    }
    private void insereTrailer(int quantidade) throws Exception{
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(formataArquivoSaida(),true));
        Trailer trailer = new Trailer(quantidade);
        buffWrite.append(trailer.toString());
        buffWrite.append("\n");
        buffWrite.close();
    }
    private int carregaDados(String arquivoBoletim, Config config) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(pathBase  + arquivoBoletim));
        String linha;
        int contadorInseridas = 0;
        while (br.ready()) {
            linha = br.readLine();
            numeroLinha++;
            if (processaLinha(linha, config) == true)
                contadorInseridas++;
        }
        numeroLinha = 0;
        br.close();
        return contadorInseridas;
    }

    private boolean processaLinha(String linha, Config config) throws Exception {
        Dados d = new Dados();

        d.setRegistro(linha.substring(0,1));
        d.setCodigoAutorizacao(linha.substring(1,2));
        d.setTipoLista(linha.substring(2,3));
        d.setTipoArquivo(linha.substring(3,4));
        d.setCartao(linha.substring(4,10));
        d.setComplemento(linha.substring(10,23));
        d.setViaCartao(linha.substring(23,24));
        d.setAcao(linha.substring(24,25));

        if (filtrarDados(d, config) == true)
            return true;
        else
            return false;
    }
    private Boolean filtrarDados(Dados d, Config config) throws Exception {
        for (int i = 0; i < config.getSizeListaBins(); i++){
            String aux;
            aux = config.getListaBins(i);
            if (d.getCartao().equals(aux)){
                if(cartaoExiste(d.getCartao() + d.getComplemento())) {
                    gravaDadosDescarte(d);
                    return false;
                }else{
                    gravaDados(d);
                    gravaDadosCompleto(d);
                    return true;
                    }
                }
        }
        gravaDadosDescarte(d);
        return false;
    }

    private void gravaDadosCompleto(Dados d) throws Exception{
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(pathBase + "/completo/completo.txt" ,true));
        buffWrite.append(d.toString());
        System.out.println("gravou bb");
        buffWrite.append("\n");
        buffWrite.close();
    }

    public static void gravaDados(Dados d) throws Exception {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(formataArquivoSaida() ,true));
        buffWrite.append(d.toString());
        buffWrite.append("\n");
        buffWrite.close();
    }

    public static Boolean cartaoExiste(String cartao) throws Exception{
        for (int i = 0; i < listaCompleta.size(); i ++){
            if (listaCompleta.get(i).equals(cartao)){
                System.out.println("existe");
                return true;
            }
        }
        System.out.println("n existe");
        return false;
    }

    public static void gravaDadosDescarte(Dados d) throws Exception {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(formataArquivoDescarte() ,true));
        buffWrite.append(d.toString());
        buffWrite.append("\n");
        buffWrite.close();
    }

    private static String formataArquivoSaida (){
        String arquivoSaida;
        Calendar c = Calendar.getInstance();
        Date data = c.getTime();
        DateFormat f = DateFormat.getDateInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        arquivoSaida = pathBase + "/saida/boletimProtetor_" + sdf.format(data) + ".txt";
        return arquivoSaida;
    }

    private static String formataArquivoDescarte (){
        String arquivoSaida;
        Calendar c = Calendar.getInstance();
        Date data = c.getTime();
        DateFormat f = DateFormat.getDateInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        arquivoSaida = pathBase + "/descarte/boletimProtetor_" + sdf.format(data) + ".txt";
        return arquivoSaida;
    }

}
