package com.challenge.totvscrud.commons;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de teste para a classe CPFUtil.
 */
@SpringBootTest(classes = {CPFUtil.class})
class CPFUtilTest {
    /**
     * Testa o método isCPF da classe CPFUtil.
     */
    @Test
    @DisplayName("Deve validar CPF")
    void deveValidarCPF(){
        assertTrue(CPFUtil.isCPF("05497491103"));

        assertFalse(CPFUtil.isCPF("123456789012"));
        assertFalse(CPFUtil.isCPF("11111111111"));
        assertFalse(CPFUtil.isCPF("12345678901"));
        assertFalse(CPFUtil.isCPF(null));
        assertFalse(CPFUtil.isCPF(""));
    }
}
