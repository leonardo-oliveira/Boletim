public class Main {
    public static void main(String[] args) throws Exception {
        Config config = new Config();
        ProcessaArquivo processaArquivo = new ProcessaArquivo();
        processaArquivo.executa(config);
    }
}
