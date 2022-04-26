import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestFactorial {

    @ParameterizedTest
    @MethodSource("simpleValues")
    public void factorialCalculateTest_SimpleValues(int x, BigDecimal expected) {

        //when:
        BigDecimal result = Factorial.calculate(x);

        //then:
        assertEquals(expected, result);
    }

    // Проверка работы на простых параметрах
    private static Stream<Arguments> simpleValues() {
        return Stream.of(
                // Проверяем простые значение
                Arguments.of(5, new BigDecimal(120)),
                Arguments.of(10, new BigDecimal(3628800)),
                Arguments.of(1, BigDecimal.ONE),
                // Факториал 0 равен 1
                Arguments.of(0, BigDecimal.ONE),
                // Максимальное значение, которое можно получить в пределах Long
                Arguments.of(20, new BigDecimal(2432902008176640000L))
        );
    }

    // Выбрасывается исключение при отрицательном параметре
    @Test
    public void factorialCalculateTest_ExpectedException() {

        //given:
        int x = -1;
        var expected = ArithmeticException.class;

        //when:
        Executable action = () -> Factorial.calculate(x);

        //then:
        assertThrows(expected, action);
    }

    // Не выбрасываются исключения при больших значениях факториала
    @ParameterizedTest
    @MethodSource("bigValues")
    public void factorialCalculateTest_DoesNotThrow(int x) {

        //when:
        Executable action = () -> Factorial.calculate(x);

        //then:
        assertDoesNotThrow(action);
    }

    private static Stream<Arguments> bigValues() {
        return Stream.of(
                // Проверяем большие значения
                Arguments.of(100),
                Arguments.of(1000),
                Arguments.of(10000),
                Arguments.of(100000)
        );
    }
}