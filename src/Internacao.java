/**
 * @author Joao Antonio Stoll Coelho [ joaoantonioscoelho@gmail.com ] - Engenharia de Sotware (PUCRS)
 * 
 *         PUCRS/DELL IT Academy 2021 - Turma 14: Exercício Técnico Este projeto
 *         faz parte do processo seletivo PUCRS/DELL IT Academy
 * 
 *         Cada linha do arquivo .csv corresponde a uma internacao (exceto a
 *         primeira linha, pois sao os headers) O raciocinio foi criar um objeto
 *         Internacao para cada linha do arquivo .csv A classe Internacao.java
 *         define o objeto Internacao, os seus atributos, construtor e os
 *         metodos getters e toString() Nao criei setters pois nao seria
 *         necessario modificar os atributos Tambem foi criado um metodo
 *         getDados() para utilizar na funcao opcao3() de App.java, para exibir
 *         apenas os dados necessarios
 */

public class Internacao {
    // define os atributos de um objeto internacao
    // os atributos sao todos os headers existentes no arquivo csv
    private String dataExtracao;
    private String id;
    private String situacao;
    private String regulacao;
    private String dataSolicitacao;
    private String sexo;
    private String idade;
    private String municipioResidencia;
    private String solicitante;
    private String municipioSolicitante;
    private String codigoCid;
    private String carater;
    private String tipoInternacao;
    private String tipoLeito;
    private String dataAutorizacao;
    private String dataInternacao;
    private String dataAlta;
    private String executante;
    private int horasFila;

    // construtor => recebe todos os atributos como parametros
    public Internacao(String dataExtracao, String id, String situacao, String regulacao, String dataSolicitacao,
            String sexo, String idade, String municipioResidencia, String solicitante, String municipioSolicitante,
            String codigoCid, String carater, String tipoInternacao, String tipoLeito, String dataAutorizacao,
            String dataInternacao, String dataAlta, String executante, int horasFila) {
        this.dataExtracao = dataExtracao;
        this.id = id;
        this.situacao = situacao;
        this.regulacao = regulacao;
        this.dataSolicitacao = dataSolicitacao;
        this.sexo = sexo;
        this.idade = idade;
        this.municipioResidencia = municipioResidencia;
        this.solicitante = solicitante;
        this.municipioSolicitante = municipioSolicitante;
        this.codigoCid = codigoCid;
        this.carater = carater;
        this.tipoInternacao = tipoInternacao;
        this.tipoLeito = tipoLeito;
        this.dataAutorizacao = dataAutorizacao;
        this.dataInternacao = dataInternacao;
        this.dataAlta = dataAlta;
        this.executante = executante;
        this.horasFila = horasFila;
    }

    public String getDataExtracao() {
        return dataExtracao;
    }

    public void setDataExtracao(String dataExtracao) {
        this.dataExtracao = dataExtracao;
    }

    public String getId() {
        return id;
    }

    public String getSituacao() {
        return situacao;
    }

    public String getRegulacao() {
        return regulacao;
    }

    public String getDataSolicitacao() {
        return dataSolicitacao;
    }

    public String getSexo() {
        return sexo;
    }

    public String getIdade() {
        return idade;
    }

    public String getMunicipioResidencia() {
        return municipioResidencia;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public String getMunicipioSolicitante() {
        return municipioSolicitante;
    }

    public String getCodigoCid() {
        return codigoCid;
    }

    public String getCarater() {
        return carater;
    }

    public String getTipoInternacao() {
        return tipoInternacao;
    }

    public String getTipoLeito() {
        return tipoLeito;
    }

    public String getDataAutorizacao() {
        return dataAutorizacao;
    }

    public String getDataInternacao() {
        return dataInternacao;
    }

    public String getDataAlta() {
        return dataAlta;
    }

    public String getExecutante() {
        return executante;
    }

    public int getHorasFila() {
        return horasFila;
    }

    public String getDados() {
        return "Internacao [dataAlta=" + dataAlta + ", dataAutorizacao=" + dataAutorizacao + ", dataInternacao="
                + dataInternacao + ", executante=" + executante + ", idade=" + idade + ", municipioResidencia="
                + municipioResidencia + ", municipioSolicitante=" + municipioSolicitante + ", solicitante="
                + solicitante + "]";
    }

    @Override
    public String toString() {
        return "Internacao [carater=" + carater + ", codigoCid=" + codigoCid + ", dataAlta=" + dataAlta
                + ", dataAutorizacao=" + dataAutorizacao + ", dataExtracao=" + dataExtracao + ", dataInternacao="
                + dataInternacao + ", dataSolicitacao=" + dataSolicitacao + ", executante=" + executante
                + ", horasFila=" + horasFila + ", id=" + id + ", idade=" + idade + ", municipioResidencia="
                + municipioResidencia + ", municipioSolicitante=" + municipioSolicitante + ", regulacao=" + regulacao
                + ", sexo=" + sexo + ", situacao=" + situacao + ", solicitante=" + solicitante + ", tipoInternacao="
                + tipoInternacao + ", tipoLeito=" + tipoLeito + "]";
    }

}
