
package Controller;

import java.util.HashMap;

/**
 * Classe responsável por tratar ou formatar informações
 * 
 * @author - Ruan Pereira
 * @since  - 18/12/2023
 */
public class trataDados {
    
    /**
     * Adiciona caracteres na string informada
     * 
     * @param originalString
     * @param caracteres
     * @param posicao
     * @return String
     */
    public static String adicionaCaracteres(String originalString, String caracteres, int posicao) {
        String primeiraParteString = originalString.substring(0, posicao);
        String segundaParteString = caracteres;
        String terceiraParteString = originalString.substring(posicao);
        
        return primeiraParteString + segundaParteString + terceiraParteString;
    }
    
    /**
     * Verifica o formato e os valores da data e retorna se a data informada é válida
     * 
     * @param data
     * @return String isDataValida
     */
    public String isDataValida(String data) {
        String situacaoData = "";
        if (this.isFormatoDataValido(data)) {
            if (!this.isValoresDataValidos(data)) {
                situacaoData = "Valores inválidos para a data!";
            }
        } else {
            situacaoData = "Formato de data inválido! Use a seguinte formatação: 00/00/0000";
        }
        
        return situacaoData;
    }
     
    /**
     * Verifica se o formato da data informada pelo usuário é válido
     * 
     * @param data
     * @return boolean isFormatoValido
     */
    public boolean isFormatoDataValido(String data) {
        String primeiraParteData = data.substring(2, 3);
        String segundaParteData = data.substring(5, 6);
        int comprimentoData = data.length();
        
        boolean isFormatoValido = ("/".equals(primeiraParteData) && "/".equals(segundaParteData));
        isFormatoValido = (comprimentoData == 10);
        
        return isFormatoValido;
    }
    
    /**
     * Extrai os valores de dia, mes e ano da String de data e os retorna como inteiro em um array associativo
     * 
     * @param data
     * @return HashMap<String, Integer> mapaData
     */
    private HashMap<String, Integer> extraiValoresData(String data) {
        HashMap<String, Integer> mapaData = new HashMap<>();
        Integer valorDia = Integer.parseInt(data.substring(0, 2));
        Integer valorMes = Integer.parseInt(data.substring(3, 5));
        Integer valorAno = Integer.parseInt(data.substring(6, data.length()));
        
        mapaData.put("dia", valorDia);
        mapaData.put("mes", valorMes);
        mapaData.put("ano", valorAno);
        
        return mapaData;
    }
    
    /**
     * Verifica se os valores da data são válidos
     * 
     * @param data
     * @return boolean isDataValida
     */
    public boolean isValoresDataValidos(String data) {
        HashMap<String, Integer> mapaData = this.extraiValoresData(data);
        boolean isDiaValido = (mapaData.get("dia") > 0 && mapaData.get("dia") <= 31);
        boolean isMesValido = (mapaData.get("mes") > 0 && mapaData.get("mes") <= 12);
        boolean isAnoValido = (mapaData.get("ano") > 2000);
        boolean isDataValida = false;
        
        if (isDiaValido && isMesValido && isAnoValido) {
            isDataValida = true;
        }
        
        return isDataValida;
    }
    
    /**
     * Verifica se o valor inserido para o telefone é válido
     * 
     * @param telefone
     * @return boolean isTelefoneValido
     */
    public boolean isTelefoneValido(String telefone) {
        boolean isTelefoneValido = false;
        if (telefone.length() <= 14) {
            if (this.verificaCaracteresTelefone(telefone) && this.verificaFormatacaoTelefone(telefone)) {
                isTelefoneValido = true;
            }
        }
        
        return isTelefoneValido;
    }
    
    /**
     * Verifica se os caracteres nas posições correspondentes a números são realmente números
     * 
     * @param telefone
     * @return boolean isCaracteresValidos
     */
    private boolean verificaCaracteresTelefone(String telefone) {
        boolean isCaracteresValidos = true;
        String telefoneLimpo = telefone.substring(1, 3) + telefone.substring(5, telefone.length());

        for (int i = 0; i < telefoneLimpo.length(); i++) {
            if (!Character.isDigit(telefoneLimpo.charAt(i))) {
                isCaracteresValidos = false;
                break;
            }
        }

        return isCaracteresValidos;
    }
    
    /**
     * Verifica se a formatação usada pelo usuario na inserção do telefone é válida
     * 
     * @param telefone
     * @return boolean isFormatoValido
     */
    private boolean verificaFormatacaoTelefone(String telefone) {
        String caracteresEspeciais = telefone.substring(0, 1) + telefone.substring(3, 5);
        boolean isFormatoValido = false;
        
        if (caracteresEspeciais.equals("() ")) {
            isFormatoValido = true;
        }

        return isFormatoValido;
    }
}
