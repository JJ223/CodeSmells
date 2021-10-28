/**
 * @author Miguel Goulao
 *
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

/**
 * A classe Tests especifica um conjunto de testes implementado recorrendo 'a ferramenta 
 * JUnit. Estes testes usam como input os ficheiros de teste do Mooshak, gerando, como
 * output, o resultado esperado na execucao desses testes.
 * A classe esta implementada para os testes do problema do ContactBook, a usar na primeira
 * aula pratica de POO. No entanto, a sua adaptacao para os restantes problemas a realizar 
 * ao longo do semestre 'e trivial.
 * Para poder usar esta classe tem de incluir no seu ambiente de execucao a biblioteca JUnit 4.
 * Veja como o pode fazer na primeira aula pratica do semestre!
 */
public class RunAllTests {
    /**
     * Use as linhas que se seguem para especificar os testes que vai realizar.
     * Por exemplo, o resultado esperado para o teste
     * 1_in_base.txt 'e 1_out_base.txt . Nao tem de fazer mais nada no resto da classe.
     * Basta configurar esta sequencia de testes!
     */

    /**
     * Testa os comandos help e exit.
     */
    @Test public void test01() { test("1_in_test.txt","1_out_test.txt"); }
    /**
     * Testa o comando new com a opcao open.
     */
    @Test public void test02() { test("2_in_test.txt","2_out_test.txt"); }

    private static final File BASE = new File("tests");

    private PrintStream consoleStream;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        consoleStream = System.out;
        System.setOut(new PrintStream(outContent));
    }

    public void test(String intput, String output) {
        test(new File(BASE, intput), new File(BASE, output));
    }

    public void test(File input, File output) {
        consoleStream.println("Testing!");
        consoleStream.println("Input: " + input.getAbsolutePath());
        consoleStream.println("Output: " + output.getAbsolutePath());

        String fullInput = "", fullOutput = "";
        try {
            fullInput = new String(Files.readAllBytes(input.toPath()));
            fullOutput = new String(Files.readAllBytes(output.toPath()));
            consoleStream.println("INPUT ============");
            consoleStream.println(new String(fullInput));
            consoleStream.println("OUTPUT ESPERADO =============");
            consoleStream.println(new String(fullOutput));
            consoleStream.println("OUTPUT =============");
        } catch(Exception e) {
            e.printStackTrace();
            fail("Erro a ler o ficheiro");
        }

        try {
            Locale.setDefault(Locale.US);
            System.setIn(new FileInputStream(input));
            Class<?> mainClass = Class.forName("Main");
            mainClass.getMethod("main", String[].class).invoke(null, new Object[] { new String[0] });
        } catch (Exception e) {
            e.printStackTrace();
            fail("Erro no programa");
        } finally {
            byte[] outPrintBytes = outContent.toByteArray();
            consoleStream.println(new String(outPrintBytes));

            assertEquals(removeCarriages(fullOutput), removeCarriages(new String(outContent.toByteArray())));
        }
    }

    private static String removeCarriages(String s) {
        return s.replaceAll("\r\n", "\n");
    }

}