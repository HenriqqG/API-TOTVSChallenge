package com.challenge.totvscrud.commons;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe de teste para a classe TelefoneUtil.
 */
@SpringBootTest(classes = {TelefoneUtil.class})
class TelefoneUtilTest {
    /**
     * Testa o método isTelefoneValido da classe TelefoneUtil.
     */
    @Test
    @DisplayName("Deve validar Telefone")
    void deveValidarTelefone(){
        assertThrows(IllegalArgumentException.class, () -> TelefoneUtil.isTelefoneValido("1234567890"));
        assertThrows(IllegalArgumentException.class, () -> TelefoneUtil.isTelefoneValido("111111111"));
        assertThrows(IllegalArgumentException.class, () -> TelefoneUtil.isTelefoneValido(""));

        assertDoesNotThrow(() -> TelefoneUtil.isTelefoneValido("123456789"));
    }
}
