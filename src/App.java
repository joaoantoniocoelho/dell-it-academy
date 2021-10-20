import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

/**
 * @author Joao Antonio Stoll Coelho [ joaoantonioscoelho@gmail.com ] - Engenharia de Sotware (PUCRS)
 * 
 *         PUCRS/DELL IT Academy 2021 - Turma 14: Exercício Técnico Este projeto
 *         faz parte do processo seletivo PUCRS/DELL IT Academy
 * 
 *         A classe App.java possui o metodo main que le o arquivo csv, executa
 *         o programa App.java tambem possui todos os metodos para executar a
 *         funcao que o usuario deseja
 */

public class App {

    static Scanner sc = new Scanner(System.in);

    // cria uma lista de objetos do tipo internacao (cada linha do csv corresponde a
    // uma internacao)
    static List<Internacao> listaInternacoes = new ArrayList<Internacao>();
    // instancia o calendario Gregoriano (o calendario gregoriano eh o calendario
    // que utilizamos com meses de janeiro a dezembro e dias da semana de domingo
    // ate sabado)
    static Calendar calendar = new GregorianCalendar();

    // cria 2 metodos para formatar as datas do arquivo .csv e poder converter de
    // String para Date
    // o arquivo csv utiliza 2 formatos diferents para exibir as datas, por isso
    // criei 2 SimpleDateFormat
    // sao static para nao precisar iniciar toda vez que for utilizar, visto que sao
    // um pouco "custosos"
    static SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat formataDataSemHorario = new SimpleDateFormat("yyyy-MM-dd");

    // constante com o caminho para o arquivo a ser lido
    static final String path = "assets/gerint_solicitacoes_mod.csv";

    public static void main(String[] args) {
        boolean isWorking = false;

        // tenta ler o arquivo csv
        try (BufferedReader bReader = new BufferedReader(new FileReader(path))) {
            String linhaInternacao = bReader.readLine();
            // evita que os headers sejam lidos e incluidos na lista, por isso le 2 vezes,
            // visto que a primeira linha contem apenas os headers do csv
            linhaInternacao = bReader.readLine();

            // continua lendo o arquivo ate que chegue em uma linha vazia
            while (linhaInternacao != null) {
                // cada linha do csv se torna um array e cada indice eh separado por ";"
                String[] arrayInternacao = linhaInternacao.split(";");

                // cada atributo da internacao equivale a um indice da linha
                String dataExtracao = arrayInternacao[0];
                String id = arrayInternacao[1];
                String situacao = arrayInternacao[2];
                String regulacao = arrayInternacao[3];
                String dataSolicitacao = arrayInternacao[4];
                String sexo = arrayInternacao[5];
                String idade = arrayInternacao[6];
                String municipioResidencia = arrayInternacao[7];
                String solicitante = arrayInternacao[8];
                String municipioSolicitante = arrayInternacao[9];
                String codigoCid = arrayInternacao[10];
                String carater = arrayInternacao[11];
                String tipoInternacao = arrayInternacao[12];
                String tipoLeito = arrayInternacao[13];
                String dataAutorizacao = arrayInternacao[14];
                String dataInternacao = arrayInternacao[15];
                String dataAlta = arrayInternacao[16];
                String executante = arrayInternacao[17];
                // transofrma as horas na Fila para inteiros
                int horasFila = Integer.parseInt(arrayInternacao[18]);

                // cria um objeto internacao para cada linha e informa os atributos acima no
                // construtor (ver Internacao.java)
                Internacao internacao = new Internacao(dataExtracao, id, situacao, regulacao, dataSolicitacao, sexo,
                        idade, municipioResidencia, solicitante, municipioSolicitante, codigoCid, carater,
                        tipoInternacao, tipoLeito, dataAutorizacao, dataInternacao, dataAlta, executante, horasFila);

                // adiciona os objetos internao criados na lista de internacoes
                listaInternacoes.add(internacao);
                linhaInternacao = bReader.readLine();

                // se nao houver erros, isWorking passa a ser true
                isWorking = true;
            }

        } catch (IOException e) {
            // caso o arquivo nao seja encontrado, exibe esta mensagem
            System.err.println("Arquivo não encontrado. Verifique o caminho especificado.");
        }

        // se os passos anteriores forem concluidos sem erros (ler arquivo, criar
        // objetos, preencher a lista etc), o programa inicia e chama o menu()
        if (isWorking) {
            menu();
        }
    }

    // metodo que executa o menu
    public static void menu() {
        System.out.println("\n1. [Consultar média de idade dos pacientes]");
        System.out.println("2. [Consultar internações por ano]");
        System.out.println("3. [Consultar hospitais]");
        System.out.println("4. [Calcular tempo de internação]");
        System.out.println("5. [Determinar tempos de espera na fila]");
        System.out.println("6. [Terminar o programa]\n");

        int opcao;
        // chama a funcao opcaoMenu() que recebe a opcao digitada pelo usuario e
        // converte para inteiro
        opcao = opcaoMenu();

        // executa a funcao conforme a opcao escolhida pelo usuario
        switch (opcao) {
            case 1:
                opcao1();
                break;
            case 2:
                opcao2();
                break;
            case 3:
                opcao3();
                break;
            case 4:
                opcao4();
                break;
            case 5:
                opcao5();
                break;
            case 6:
                opcao6();
                break;
            default:
                System.out.println("ATENÇÃO: Digite uma opção válida (de 1 até 6)");
                menu();
                break;
        }
        // faz com que o programa continue executando ate que o usuario informe que
        // deseja sair
        menu();
    }

    // le a opcao digitada pelo usuario
    public static int opcaoMenu() {
        int opcao = 0;
        try {
            // recebe a opcao do usuario (String) e converte para Inteiro
            opcao = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            // se o numero for invalido, avisa o usuario e reinicia o menu()
            System.out.println("ATENÇÃO: Digite uma opção válida (de 1 até 6)");
            menu();
            opcao = opcaoMenu();
        }
        return opcao;
    }

    public static void opcao1() {
        System.out.println("Digite o  nome do município residencial:");
        // evita que de erro caso o usuario digite em letras minusculas
        String municipioResidencial = sc.nextLine().toUpperCase();
        int totalPacientes = 0;
        int totalMasc = 0;
        int totalFem = 0;

        float somaIdades = 0;
        float somaMasc = 0;
        float somaFem = 0;

        float mediaTotal = 0;
        float mediaMasc = 0;
        float mediaFem = 0;

        boolean isValid = false;
        for (Internacao i : listaInternacoes) {
            if (i.getMunicipioResidencia().equals(municipioResidencial)) {
                // se encontrar internacao com o municipio informado, isValid passa a ser true
                isValid = true;
                try {
                    // adiciona 1 ao total de pacientes do municipio e adiciona a "idade" na soma
                    // total de idades do municipio
                    totalPacientes++;
                    float idade = Float.parseFloat(i.getIdade());
                    somaIdades += idade;
                } catch (NumberFormatException e) {
                    continue;
                }
                if (i.getSexo().equals("MASCULINO")) {
                        // adiciona 1 ao total de pacientes do sexo masculino do municipio e adiciona a
                        // "idade" na soma de idades
                        totalMasc++;
                        float idade = Float.parseFloat(i.getIdade());
                        somaMasc += idade;
                } else if (i.getSexo().equals("FEMININO")) {
                        // adiciona 1 ao total de pacientes do sexo feminino do municipio e adiciona a
                        // "idade" na soma de idades
                        totalFem++;
                        float idade = Float.parseFloat(i.getIdade());
                        somaFem += idade;
                } else{
                    continue;
                }
            }
        }
        // se for valido, faz os calculos de media (soma de idades divido pela
        // quantidade total de pacientes)
        if (isValid) {
            mediaTotal = somaIdades / totalPacientes;
            mediaMasc = somaMasc / totalMasc;
            mediaFem = somaFem / totalFem;

            System.out.println("\nA. Número total de pacientes de " + municipioResidencial + ": " + totalPacientes);
            System.out.println("B. Média de idade de todos os pacientes [SEXO MASCULINO]: " + mediaMasc);
            System.out.println("C. Média de idade de todos os pacientes [SEXO FEMININO]: " + mediaFem);
            System.out.println("D. Média de idade de todos os pacientes [TOTAL]: " + mediaTotal);

            // se nao for encontrado caso com o municipio especificado, alerta o usuario e
            // reinicia a funcao
        } else {
            System.out.println("\nERRO: Município não encontrado. Verifique.\n");
            opcao1();
        }
    }

    public static void opcao2() {
        System.out.println("Digite o  nome do município residencial:");
        // evita que de erro caso o usuario digite em letras minusculas
        String municipioResidencial = sc.nextLine().toUpperCase();

        boolean isValid = false;

        int total2018 = 0;
        int total2019 = 0;
        int total2020 = 0;
        int total2021 = 0;

        // verifica se existe internacao no municipio residencial informado, quando
        // encontra o primeiro, isValid passa a ser true e encerra o laco
        for (Internacao i : listaInternacoes) {
            if (i.getMunicipioResidencia().equals(municipioResidencial)) {
                isValid = true;
                break;
            }
        }
        // se for valido, executa o codigo abaixo
        if (isValid) {
            for (Internacao i : listaInternacoes) {
                if (i.getMunicipioResidencia().equals(municipioResidencial)) {
                    String data = i.getDataInternacao();
                    try {
                        // converte a data da internacao de String para Date, para podermos utilizar o
                        // calendar.get() e verificar o ano
                        Date dataToDate = formataData.parse(data);
                        calendar.setTime(dataToDate);

                        // extrai apenas o ano da data de internacao
                        int ano = calendar.get(Calendar.YEAR);

                        // verifica o ano e adiciona ao total de casos daquele ano (totalANO)
                        switch (ano) {
                            case 2018:
                                total2018++;
                                break;

                            case 2019:
                                total2019++;
                                break;

                            case 2020:
                                total2020++;
                                break;

                            case 2021:
                                total2021++;

                                // caso o ano nao seja 2018, 2019, 2020 ou 2021, apenas continua executando o
                                // laco
                            default:
                                continue;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("\nQuantidade de pacientes que foram internados em 2018: " + total2018);
            System.out.println("Quantidade de pacientes que foram internados em 2019: " + total2019);
            System.out.println("Quantidade de pacientes que foram internados em 2020: " + total2020);
            System.out.println("Quantidade de pacientes que foram internados em 2021: " + total2021);

            // se nao for encontrado caso com o municipio especificado, alerta o usuario e
            // reinicia a funcao
        } else {
            System.out.println("\nERRO: Município não encontrado. Verifique.\n");
            opcao2();
        }
    }

    public static void opcao3() {
        System.out.println("Digite o  nome do executante:");
        // evita que de erro caso o usuario digite em letras minusculas
        String executante = sc.nextLine().toUpperCase();

        boolean isValid = false;

        // verifica se existe internacao com o executante informado, quando encontra o
        // primeiro, isValid passa a ser true e encerra o laco
        for (Internacao i : listaInternacoes) {
            if (i.getExecutante().equals(executante)) {
                isValid = true;
                break;
            }
        }
        // se for valido, analisa todas as internacoes da lista e imprime os dados
        // daquelas que possuem o executante igual ao informado
        if (isValid) {
            for (Internacao i : listaInternacoes) {
                if (i.getExecutante().equals(executante)) {
                    System.out.println("\n" + i.getDados());
                }
            }

            // se nao for encontrado caso com o executante especificado, alerta o usuario e
            // reinicia a funcao
        } else {
            System.out.println("\nERRO: Executante inexistente. Verifique.\n");
            opcao3();
        }

    }

    public static void opcao4() {
        System.out.println("Digite o  nome do solicitante:");
        // evita que de erro caso o usuario digite em letras minusculas
        String solicitante = sc.nextLine().toUpperCase();

        boolean isValid = false;
        int cont = 1;

        // verifica se existe internacao com o executante informado, quando encontra o
        // primeiro, isValid passa a ser true e encerra o laco
        for (Internacao i : listaInternacoes) {
            if (i.getSolicitante().equals(solicitante)) {
                isValid = true;
                break;
            }
        }

        // se for valido, executa
        if (isValid) {
            // analisa as internacoes na lista
            for (Internacao i : listaInternacoes) {
                // caso o solicitante da internacao seja igual ao informado, armazena as datas
                // de solicitacao e alta em string
                if (i.getSolicitante().equals(solicitante)) {
                    String dataSolicitacao = i.getDataSolicitacao();
                    String dataAlta = i.getDataAlta();
                    try {
                        // converte as Strings das datas em Dates (utiliza os SimpleDateFormat criados
                        // no inicio do codigo)
                        Date dataSolicitacaoToDate = formataDataSemHorario.parse(dataSolicitacao);
                        Date dataAltaToDate = formataData.parse(dataAlta);
                        calendar.setTime(dataAltaToDate);
                        calendar.setTime(dataSolicitacaoToDate);

                        // calcula a diferenca entre as 2 datas e depois converte para dias com o
                        // toDays()
                        long diasInternacao = Duration
                                .between(dataSolicitacaoToDate.toInstant(), dataAltaToDate.toInstant()).toDays();

                        // printa o paciente, o hospital executante e os dias de internacao
                        System.out.println("\nPACIENTE " + cont + ":\n" + "\n" + "A. " + i
                                + "\nB. [Hospital executante: " + i.getExecutante() + "]" + "\nC. [Dias de internação: "
                                + diasInternacao + " dias.]");

                        // contador do numero total de pacientes
                        cont++;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            // se nao for encontrado caso com o solicitante especificado, alerta o usuario e
            // reinicia a funcao
        } else {
            System.out.println("\nERRO: Solicitante inexistente. Verifique.\n");
            opcao4();
        }
    }

    public static void opcao5() {
        // cria arraylist para armazenar inteiros
        List<Integer> listaHorasFila = new ArrayList<Integer>();

        // para cada internacao na lista, adiciona a horas_na_fila no ArrayList
        for (Internacao i : listaInternacoes) {
            int horasNaFila = i.getHorasFila();

            // evita horas negativas, pois nao faz sentido um paciente ficar tempo negativo
            // na fila
            if (horasNaFila >= 0) {
                listaHorasFila.add(horasNaFila);
            }
        }

        // ordena a lista utilizando o Collections.sort()
        // o Collections.sort() ordena a lista em ordem crescente, por isso utilizei o
        // Collections.reverseOrder() a fim de ordenar em ordem decrescente
        Collections.sort(listaHorasFila, Collections.reverseOrder());

        // printa as 5 primeiras posicoes (que serao as maiores)
        // poderia ter utilizado um for, mas quis fazer assim para poder escrever de um
        // jeito mais "bonito"
        System.out.println("Maior tempo de espera: " + listaHorasFila.get(0));
        System.out.println("Segundo maior tempo de espera: " + listaHorasFila.get(1));
        System.out.println("Terceiro maior tempo de espera: " + listaHorasFila.get(2));
        System.out.println("Quarto maior tempo de espera: " + listaHorasFila.get(3));
        System.out.println("Quinto maior tempo de espera: " + listaHorasFila.get(4));
    }

    public static void opcao6() {
        // fecha o programa
        System.out.println("[ Saindo ]");
        System.exit(0);
    }
}