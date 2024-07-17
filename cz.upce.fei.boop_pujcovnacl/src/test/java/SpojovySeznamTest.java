import cz.upce.fei.boop.pujcovna.kolekce.KolekceException;
import cz.upce.fei.boop.pujcovna.kolekce.SpojovySeznam;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 *
 * @author karel@simerda.cz
 */
public class SpojovySeznamTest {

    /**
     * Testovací třída pro ověření implementace třídy SpojovySeznam
     */
    private static class TestClass {

        int a;

        public TestClass(int a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return "T" + a;
        }

    }
    /***
     * Sada instancí testovací třídy pro ověření implementace třídy SpojovySeznam
     */
    private final TestClass T1 = new TestClass(1);
    private final TestClass T2 = new TestClass(2);
    private final TestClass T3 = new TestClass(3);
    private final TestClass T4 = new TestClass(4);
    private final TestClass T5 = new TestClass(5);
    private final TestClass T6 = new TestClass(6);
    private final TestClass T7 = new TestClass(7);
    private final TestClass T8 = new TestClass(8);
    private final TestClass T9 = new TestClass(9);
  

    public SpojovySeznamTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

// TODO Každou veřejnou metodu třídy SpojovySeznam ověřte alespoň jedním testem  
// TODO Dosáhněte 100% pokrytí třídy SpojovySeznam tímto testem    
    
// Ukázka jednoduchého testu
    //<editor-fold defaultstate="collapsed" desc="Vkladani prvniho prvku">
    @Test
    public void test_01_01_VlozPrvni() {
        try {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPrvni(T1);
            TestClass[] expected = {T1};
            TestClass[] result = {instance.dejPrvni()};
            assertEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }
// Ukázka testu s vícenásobnou kontrolou stavu testované instance
    @Test
    public void test_01_02_VlozPrvni() {
        try {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPrvni(T1);
            instance.vlozPrvni(T2);
            TestClass[] result = {instance.dejPrvni(), instance.dejPosledni()};
            TestClass[] expected = {T2, T1};
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }

//  Ukázka testu s vyvoláním výjimky   
    @Test(expected = NullPointerException.class)
    public void test_01_04_VlozPrvni() throws KolekceException {
        SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
        instance.vlozPrvni(null);
        fail();
    }
    //</editor-fold>


    //<editor-fold defaultstate="collapsed" desc="Vkladani posledniho prvku">
    @Test
    public void test_01_01_VlozPosledni() {
        try {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPosledni(T1);
            TestClass[] result = {instance.dejPrvni(), instance.dejPosledni()};
            TestClass[] expected = {T1, T1};
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }
    @Test(expected = NullPointerException.class)
    public void test_01_03_VlozPosledni() throws KolekceException {
        SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
        instance.vlozPosledni(null);
        fail();
    }
    @Test
    public void test_01_02_VlozPosledni() {
        try {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPosledni(T1);
            instance.vlozPosledni(T2);
            TestClass[] result = {instance.dejPrvni(), instance.dejPosledni()};
            TestClass[] expected = {T1, T2};
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Nastaveni prvniho prvku">
    @Test
    public void test_03_01_NastavPrvni() {
        try {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPrvni(T1);
            instance.vlozPrvni(T2);
            instance.vlozPosledni(T3);
            instance.nastavPrvni();
            TestClass result = instance.dejAktualni();
            TestClass expected = T2;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }
    @Test(expected = KolekceException.class)
    public void test_03_02_NastavPrvni() throws KolekceException {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.nastavPrvni();
            fail();

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Nastaveni posledniho prvku">
    @Test
    public void test_04_01_NastavPosledni() {
        try {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPrvni(T1);
            instance.vlozPrvni(T2);
            instance.vlozPosledni(T3);
            instance.nastavPosledni();
            TestClass result = instance.dejAktualni();
            TestClass expected = T3;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }
    @Test(expected = KolekceException.class)
    public void test_04_02_NastavPosledni() throws KolekceException {
        SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
        instance.nastavPosledni();
        fail();

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Kontrola metody dalsi">
    @Test
    public void test_05_01_KontrolaDalsi() {
        try {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPrvni(T1);
            instance.vlozPrvni(T2);
            instance.vlozPrvni(T3);
            instance.vlozPosledni(T4);
            instance.nastavPrvni();
            instance.dalsi();
            TestClass result = instance.dejAktualni();
            TestClass expected = T2;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }
    @Test(expected = KolekceException.class)
    public void test_05_02_KontrolaDalsi() throws KolekceException {
        SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
        instance.dalsi();
        fail();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Kontrola metody JeDalsi ">
    @Test
    public void test_06_01_KontrolaJeDalsi() {
        try {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPrvni(T1);
            instance.vlozPosledni(T4);
            instance.nastavPrvni();
            boolean result = instance.jeDalsi();
            boolean expected = true;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }

    @Test
    public void test_06_02_KontrolaJeDalsi() {
        try {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPrvni(T1);
            instance.nastavPrvni();
            boolean result = instance.jeDalsi();
            boolean expected = false;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }
    @Test
    public void test_06_03_KontrolaJeDalsi() {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            boolean result = instance.jeDalsi();
            boolean expected = false;
            assertEquals(expected, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Vkladani za aktualniho prvku">
    @Test
    public void test_07_01_VlozZaAktualni() {
        try {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPosledni(T1);
            instance.nastavPosledni();
            instance.vlozZaAktualni(T2);
            TestClass result = instance.dejPosledni();
            TestClass expected = T2;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }
    @Test
    public void test_07_02_VlozZaAktualni() {
        try {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPrvni(T3);
            instance.vlozPrvni(T1);
            instance.nastavPrvni();
            instance.vlozZaAktualni(T2);
            instance.dejPrvni();
            TestClass result = instance.dejZaAktualnim();
            TestClass expected = T2;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }
    @Test(expected = NullPointerException.class)
    public void test_07_03_VlozZaAktualni() throws KolekceException {
        SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
        instance.vlozZaAktualni(null);
        fail();
    }
    @Test(expected = KolekceException.class)
    public void test_07_04_VlozZaAktualni() throws KolekceException {
        SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
        instance.vlozZaAktualni(T1);
        fail();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Kontrola metody jePrazdny, Zrus a Size">
    @Test
    public void test_08_01_KontrolajePrazdny() {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            boolean result = instance.jePrazdny();
            boolean expected = true;
            assertEquals(expected, result);
    }
    @Test
    public void test_08_02_KontrolajePrazdny() {
        SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
        instance.vlozPrvni(T1);
        boolean result = instance.jePrazdny();
        boolean expected = false;
        assertEquals(expected, result);
    }
    @Test
    public void test_08_03_KontrolajePrazdnyAndZrus() {
        SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
        instance.vlozPrvni(T9);
        instance.vlozPrvni(T8);
        instance.vlozPrvni(T7);
        instance.vlozPrvni(T6);
        instance.vlozPrvni(T5);
        instance.vlozPrvni(T4);
        instance.vlozPrvni(T3);
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T1);
        instance.zrus();
        boolean result = instance.jePrazdny();
        boolean expected = true;
        assertEquals(expected, result);
    }

    @Test
    public void test_08_03_KontrolajePrazdnyAndSize() {
        SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
        instance.vlozPrvni(T1);
        int result = instance.size();
        int expected = 1;
        assertEquals(expected, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Kontrola metody dejPrvni">
    @Test
    public void test_09_01_KontrolaDejPrvni() {
        try {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPrvni(T1);
            TestClass result = instance.dejPrvni();
            TestClass expected = T1;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }
    @Test(expected = KolekceException.class)
    public void test_09_02_KontrolaDejPrvni() throws KolekceException {
        SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
        instance.dejPrvni();
        fail();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Kontrola metody dejPosledni">
    @Test
    public void test_10_01_KontrolaDejPosldeni() {
        try {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPrvni(T1);
            instance.vlozPosledni(T8);
            TestClass result = instance.dejPosledni();
            TestClass expected = T8;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }
    @Test(expected = KolekceException.class)
    public void test_10_02_KontrolaDejPosldeni() throws KolekceException {
        SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
        instance.dejPosledni();
        fail();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Kontrola metody dejAktualni">
    @Test
    public void test_11_01_KontrolaDejAktualni() {
        try {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPrvni(T1);
            instance.vlozPosledni(T2);
            instance.vlozPosledni(T3);
            instance.nastavPrvni();
            instance.dalsi();
            TestClass result = instance.dejAktualni();
            TestClass expected = T2;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }
    @Test(expected = KolekceException.class)
    public void test_11_02_KontrolaDejAktualni() throws KolekceException {
        SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
        instance.dejAktualni();
        fail();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Kontrola metody dejZaAktualnim">
    @Test
    public void test_12_01_KontrolaDejZaAktualnim() {
        try {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPrvni(T1);
            instance.vlozPosledni(T2);
            instance.vlozPosledni(T3);
            instance.vlozPosledni(T4);
            instance.nastavPrvni();
            instance.dalsi();
            TestClass result = instance.dejZaAktualnim();
            TestClass expected = T3;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }
    @Test(expected = KolekceException.class)
    public void test_12_02_KontrolaDejZaAktualnim() throws KolekceException {
        SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
        instance.dejZaAktualnim();
        fail();
    }
    @Test(expected = KolekceException.class)
    public void test_12_03_KontrolaDejZaAktualnim() throws KolekceException {

            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPrvni(T1);
            instance.nastavPrvni();
            instance.dejZaAktualnim();
            fail();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Odebrani prvniho prvku">
    @Test
    public void test_13_01_OdeberPrvni() {
        try {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPrvni(T2);
            instance.vlozPrvni(T1);
            instance.vlozPosledni(T3);
            instance.odeberPrvni();
            instance.nastavPrvni();
            TestClass result = instance.dejAktualni();
            TestClass expected = T2;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }
    @Test(expected = KolekceException.class)
    public void test_13_02_OdeberPrvni() throws KolekceException {
        SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
        instance.odeberPrvni();
        fail();
    }
    @Test(expected = KolekceException.class)
    public void test_13_03_OdeberPrvni() throws KolekceException {
        SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
        instance.vlozPrvni(T1);
        instance.nastavPrvni();
        instance.odeberPrvni();
        TestClass result = instance.dejAktualni();
        TestClass expected = null;
        assertEquals(expected, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Odebrani posledniho prvku">
    @Test
    public void test_14_01_OdeberPosledni() {
        try {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPrvni(T2);
            instance.vlozPrvni(T1);
            instance.vlozPosledni(T3);
            instance.vlozPosledni(T4);
            instance.odeberPosledni();
            instance.nastavPosledni();
            TestClass result = instance.dejAktualni();
            TestClass expected = T3;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }
    @Test(expected = KolekceException.class)
    public void test_14_02_OdeberPosledni() throws KolekceException {
        SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
        instance.odeberPosledni();
        fail();
    }
    @Test(expected = KolekceException.class)
    public void test_14_03_OdeberPosledni() throws KolekceException {
        SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
        instance.vlozPosledni(T1);
        instance.nastavPosledni();
        instance.odeberPosledni();
        TestClass result = instance.dejAktualni();
        TestClass expected = null;
        assertEquals(expected, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Odebrani aktualniho prvku">
    @Test
    public void test_15_01_OdeberAktualni() {
        try {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPosledni(T1);
            instance.vlozPosledni(T2);
            instance.nastavPosledni();
            instance.odeberAktualni();
            TestClass result = instance.dejPosledni();
            TestClass expected = T1;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }
    @Test(expected = KolekceException.class)
    public void test_15_02_OdeberAktualni() throws KolekceException {
        SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
        instance.odeberAktualni();
        fail();
    }
    @Test(expected = KolekceException.class)
    public void test_15_03_OdeberAktualni() throws KolekceException {
        SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
        instance.vlozPrvni(T1);
        instance.nastavPrvni();
        instance.odeberAktualni();
        TestClass result = instance.dejAktualni();
        TestClass expected = null;
        assertEquals(expected, result);
    }
    @Test
    public void test_15_04_OdeberAktualni() {
        try {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPosledni(T1);
            instance.vlozPosledni(T2);
            instance.vlozPosledni(T3);
            instance.vlozPosledni(T4);
            instance.nastavPrvni();
            instance.dalsi();
            instance.dalsi();
            instance.odeberAktualni();
            TestClass result = instance.dejPosledni();
            TestClass expected = T4;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Odebrani za aktualnim prvkem">
    @Test
    public void test_16_01_OdeberZaAktualnim() {
        try {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPrvni(T2);
            instance.vlozPrvni(T1);
            instance.vlozPosledni(T3);
            instance.vlozPosledni(T4);
            instance.nastavPrvni();
            instance.odeberZaAktualnim();
            TestClass result = instance.dejZaAktualnim();
            TestClass expected = T3;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }
    @Test(expected = KolekceException.class)
    public void test_16_02_OdeberZaAktualnim() throws KolekceException {
        SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
        instance.odeberZaAktualnim();
        fail();
    }
    @Test(expected = KolekceException.class)
    public void test_16_03_OdeberZaAktualnim() throws KolekceException {
        SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
        instance.vlozPosledni(T1);
        instance.nastavPosledni();
        instance.odeberZaAktualnim();
        TestClass result = instance.dejAktualni();
        TestClass expected = null;
        assertEquals(expected, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Testovani Iteratora">
    @Test
    public void test_17_01_Iterator() {
        try {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPrvni(T2);
            instance.vlozPrvni(T1);
            instance.nastavPrvni();
            boolean result = instance.iterator().hasNext();
            boolean expected = true;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }
    @Test
    public void test_17_02_Iterator() {
        try {
            SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
            instance.vlozPrvni(T3);
            instance.vlozPrvni(T2);
            instance.vlozPrvni(T1);
            instance.nastavPrvni();
            TestClass result = instance.iterator().next();
            TestClass expected = T1;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }
    @Test(expected = NoSuchElementException.class)
    public void test_17_03_Iterator() throws NoSuchElementException {
        SpojovySeznam<TestClass> instance = new SpojovySeznam<>();
        instance.iterator().next();
        fail();
    }
    //</editor-fold>
}
