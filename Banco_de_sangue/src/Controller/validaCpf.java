
package Controller;

/**
 * Classe responsável por verificar se o valor de CPF informado pelo usuário é válido
 * 
 * @author - Ruan Pereira
 * @since  - 18/12/2023
 */
public class validaCpf {
    
    /**
     * Valida o número de CPF.
     *
     * @param cpf número de CPF a ser validado.
     * @return true se o CPF é válido, caso contrário false.
     */
    public static boolean isCpfValido(String cpf) {
        cpf = removeNaoNumericos(cpf);

        if (!verificaTamanhoCPF(cpf)) {
            return false;
        }

        int digitoVerificador1 = calculaDigitoVerificador(cpf, 9, 10);
        int digitoVerificador2 = calculaDigitoVerificador(cpf, 10, 11);

        return verificaDigito(cpf, 9, digitoVerificador1) && verificaDigito(cpf, 10, digitoVerificador2);
    }

    /**
     * Remove caracteres não numéricos de uma string.
     *
     * @param cpf string a ser processada.
     * @return string resultante após a remoção de caracteres não numéricos.
     */
    private static String removeNaoNumericos(String cpf) {
        return cpf.replaceAll("[^0-9]", "");
    }

    /**
     * Verifica se o CPF possui o tamanho correto (11 dígitos).
     *
     * @param cpf número de CPF a ser verificado.
     * @return true se o tamanho do CPF é válido, false caso contrário.
     */
    private static boolean verificaTamanhoCPF(String cpf) {
        return cpf.length() == 11;
    }

    /**
     * Calcula o dígito verificador do CPF.
     *
     * @param cpf     número de CPF a ser processado.
     * @param indice  índice do dígito verificador.
     * @param peso    peso pelo qual o número do CPF será multiplicado
     * @return int o dígito verificador calculado.
     */
    private static int calculaDigitoVerificador(String cpf, int indice, int peso) {
        int soma = 0;
        for (int i = 0; i < indice; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * peso;
            peso--;
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

    /**
     * Verifica se um dígito verificador de CPF é válido.
     *
     * @param cpf             número de CPF a ser verificado.
     * @param indice          índice do dígito verificador.
     * @param digitoCalculado dígito verificador calculado.
     * @return true se o dígito verificador é válido, false caso contrário.
     */
    private static boolean verificaDigito(String cpf, int indice, int digitoCalculado) {
        return Character.getNumericValue(cpf.charAt(indice)) == digitoCalculado;
    }

}
