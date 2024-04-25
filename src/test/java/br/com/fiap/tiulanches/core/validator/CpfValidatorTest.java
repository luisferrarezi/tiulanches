package br.com.fiap.tiulanches.core.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class CpfValidatorTest {

    CpfValidator cpfValidator;
    String[] cpfsInvalidos = {"505794110A1", "5057941101", "00000000000", "11111111111", "22222222222", "33333333333", 
                              "44444444444", "55555555555", "66666666666", "77777777777", "88888888888", "99999999999"};
    
    @Test
    void cpfIsValidoTest(){
        cpfValidator = new CpfValidator();        
        assertTrue(cpfValidator.isValid("50579411001", null));
        assertTrue(cpfValidator.isValid("51864800020", null));
    }

    @Test
    void testaCpfInvalido(){
        cpfValidator = new CpfValidator();

        for (String cpf : cpfsInvalidos) {
            assertFalse(cpfValidator.isValid(cpf, null));    
        }
    }
}
