import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CalculatorTest {

    // initializing Instance
    private Calculator calculatorUnderTest;

    private static Instant starteAt;
    @BeforeEach
    public void iniCalculator(){
        System.out.println("Aufruf vor dem Test");
        calculatorUnderTest = new Calculator();
    }

    @BeforeEach
    public void initStartingTime(){
        System.out.println("Aufruf vor jedem Test");
        starteAt = Instant.now();
    }

    @Test
    void testAddTwoPositiveNumbers(){
        // Arrange
        int a = 2;
        int b = 3;

        // Act
        int summe = calculatorUnderTest.add(a,b);

        // Assert
        assertEquals(5,summe);

        //fail("not yet implemented");
    }


    @Test
    void testMultiplyTwoNumbers(){

        // Arrange
        int a = 9;
        int b = 9;

        // Act
        int multiple = calculatorUnderTest.multiply(a,b);

        //Assert
        assertEquals(81,multiple);
    }


    @AfterEach
    public void underfCalculator(){
        System.out.println("Aufruf nach jedem Test");
        calculatorUnderTest = null;
    }


   @AfterAll
    public static void showTestDuration(){
        System.out.println("Nach Aufruf aller Teste ");
        final Instant endeAt = Instant.now();
        final long duration = Duration.between(starteAt, endeAt).toMillis();
        System.out.println(MessageFormat.format("Dauer des Aufruf: {0} ms", duration));
    }

    @ParameterizedTest(name = "{0} x 0 muss gleich 0 sein")
    @ValueSource(ints = {1,2,42,1011,5089})
    void multiply_shouldReturnNull_ofZeroWiithMultipleIntegers(int args){
        // Arrange

        //Act
        int actualResult = calculatorUnderTest.multiply(args, 0);

        // Assert
        assertEquals(0,actualResult);
    }

    @ParameterizedTest(name = "{0} + {1} should equal to {2}")
    @CsvSource({ "1,1,2", "2,3,5", "42,57,99" })
    public void add_shouldReturnTheSum_ofMultipleIntegers(int arg1, int arg2, int expectResult) {
        // Arrange -- Tout est prêt !

        // Act
        int actualResult = calculatorUnderTest.add(arg1, arg2);

        // Assert
       // assertEquals(expectResult, actualResult);
        assertThat(actualResult).isEqualTo(expectResult);
    }

    @Test
    public void listDigits_shouldReturnsTheListOfDigits_ofPositiveInteger(){
        // Given
        final int number = 95897;

        // When
        Set<Integer> actualDigit = calculatorUnderTest.digitsSet(number);

        // Then
        // final Set<Integer> exceptedDigits = Stream.of(5,7,8,9).collect(Collectors.toSet());
        // assertEquals(exceptedDigits, actualDigit);
        assertThat(actualDigit).containsExactlyInAnyOrder(9,5,8,7);
    }

    @Test
    public void listDigits_shouldReturnsTheListOfDigits_ofNegativeInteger(){
        // Given
        final int number = -124432;
        final Set<Integer> actualDigit = calculatorUnderTest.digitsSet(number);
        assertThat(actualDigit).containsExactlyInAnyOrder(1,2,3,4);
    }

    @Test
    public void listDigits_shouldReturnsTheListOfDigits_ofZero(){
        final int number = 0;
        final Set<Integer> actualDigit = calculatorUnderTest.digitsSet(number);
        assertThat(actualDigit).containsExactly(0);
    }

}
