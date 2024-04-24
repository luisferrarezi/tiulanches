package br.com.fiap.tiulanches.core.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class CpfValidatorTest {

    CpfValidator cpfValidator;
    
    @Test
    void cpfIsValidoTest(){
        cpfValidator = new CpfValidator();        
        assertTrue(cpfValidator.isValid("50579411001", null));
        assertTrue(cpfValidator.isValid("51864800020", null));
    }
    
    @Test
    void cpfComLetraTest(){
        testaCpfInvalido("505794110A1");        
    }

    @Test
    void cpfTamanhoErradoTest(){
        testaCpfInvalido("5057941101");
    }

    @Test
    void cpfTamanhoErrado0Test(){
        testaCpfInvalido("00000000000");
    }
    
    @Test
    void cpfTamanhoErrado1Test(){
        testaCpfInvalido("11111111111");
    }
    
    @Test
    void cpfTamanhoErrado2Test(){
        testaCpfInvalido("22222222222");
    }
    
    @Test
    void cpfTamanhoErrado3Test(){
        testaCpfInvalido("33333333333");
    }
    
    @Test
    void cpfTamanhoErrado4Test(){
        testaCpfInvalido("44444444444");
    }
    
    @Test
    void cpfTamanhoErrado5Test(){
        testaCpfInvalido("55555555555");
    }
    
    @Test
    void cpfTamanhoErrado6Test(){
        testaCpfInvalido("66666666666");
    }
    
    @Test
    void cpfTamanhoErrado7Test(){
        testaCpfInvalido("77777777777");
    }

    @Test
    void cpfTamanhoErrado8Test(){
        testaCpfInvalido("88888888888");
    }

    @Test
    void cpfTamanhoErrado9Test(){
        testaCpfInvalido("99999999999");
    }

    void testaCpfInvalido(String cpf){
        cpfValidator = new CpfValidator();
        assertFalse(cpfValidator.isValid(cpf, null));
    }
}
