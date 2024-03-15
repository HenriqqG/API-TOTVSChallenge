package com.challenge.totvscrud.commons;

import org.springframework.util.ObjectUtils;

/**
 * Classe utilitária para validar números de telefone brasileiros.
 */
public class TelefoneUtil {

    /**
     * Valida o número de telefone fornecido.
     *
     * @param tel O número de telefone a ser validado.
     * @throws IllegalArgumentException se o número de telefone estiver vazio, contiver caracteres não distintos
     *                                  ou não tiver um comprimento de 9 dígitos.
     */
    public static void isTelefoneValido(String tel){
        if(ObjectUtils.isEmpty(tel) || caracteresNaoDistintos(tel) || (tel.length() != 9)){
            throw new IllegalArgumentException(String.format("Número de telefone inválido: %s", tel));
        }
    }

    /**
     * Verifica se o número de telefone fornecido contém caracteres não distintos.
     *
     * @param tel O número de telefone a ser verificado.
     * @return {@code true} se o número de telefone contiver caracteres não distintos, {@code false} caso contrário.
     */
    private static boolean caracteresNaoDistintos(String tel){
        return (tel.equals("000000000") || tel.equals("111111111") || tel.equals("222222222") || tel.equals("333333333") || tel.equals("444444444")
                || tel.equals("555555555") || tel.equals("666666666") || tel.equals("777777777") || tel.equals("888888888") || tel.equals("999999999"));
    }
}
