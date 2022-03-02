package u10pp;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigInteger;

import org.junit.jupiter.api.*;

public class RecursiveMathTests {

    private static final float EPSILON = 0.0001f;

    @Test 
    public void pow_positive_returnsCorrectValues() {
        assertAll(
            () -> assertEquals(Math.pow(13, 0), RecursiveMath.pow(13, 0), EPSILON),
            () -> assertEquals(Math.pow(1, 100), RecursiveMath.pow(1, 100), EPSILON),
            () -> assertEquals(Math.pow(2, 20), RecursiveMath.pow(2, 20), EPSILON),
            () -> assertEquals(Math.pow(123, 3), RecursiveMath.pow(123, 3), EPSILON),
            () -> assertEquals(Math.pow(12300, 7), RecursiveMath.pow(12300, 7), EPSILON),
            () -> assertEquals(Math.pow(12300.5, 7), RecursiveMath.pow(12300.5, 7), 1E20),
            () -> assertEquals(Math.pow(0.5, 5), RecursiveMath.pow(0.5, 5), EPSILON)
        );
    }

    @Test
    public void pow_negative_returnsCorrectValue() {
        assertAll(
            () -> assertEquals(Math.pow(-13, 0), RecursiveMath.pow(-13, 0), EPSILON),
            () -> assertEquals(Math.pow(-0.1, 7), RecursiveMath.pow(-0.1, 7), EPSILON),
            () -> assertEquals(Math.pow(-1, 5), RecursiveMath.pow(-1, 5), EPSILON),
            () -> assertEquals(Math.pow(-6, 34), RecursiveMath.pow(-6, 34), EPSILON),
            () -> assertEquals(Math.pow(-60, 14), RecursiveMath.pow(-60, 14), 1E10),
            () -> assertEquals(Math.pow(-60.2, 14), RecursiveMath.pow(-60.2, 14), 1E12)
        );
    }

    @Test
    public void fibonacci_small_returnsCorrectValues() {
        assertAll(
            () -> assertEquals(1, RecursiveMath.getFibonacciNumber(1)),
            () -> assertEquals(1, RecursiveMath.getFibonacciNumber(2)),
            () -> assertEquals(2, RecursiveMath.getFibonacciNumber(3)),
            () -> assertEquals(3, RecursiveMath.getFibonacciNumber(4)),
            () -> assertEquals(5, RecursiveMath.getFibonacciNumber(5)),
            () -> assertEquals(8, RecursiveMath.getFibonacciNumber(6)),
            () -> assertEquals(13, RecursiveMath.getFibonacciNumber(7))
        );
    }

    @Test
    public void fibonacci_large_returnsCorrectValues() {
        assertAll(            
            () -> assertEquals(55, RecursiveMath.getFibonacciNumber(10)),
            () -> assertEquals(6765, RecursiveMath.getFibonacciNumber(20)),
            () -> assertEquals(1346269, RecursiveMath.getFibonacciNumber(31)),
            () -> assertEquals(39088169, RecursiveMath.getFibonacciNumber(38))
        );
    }

    @Test
    public void factorial_small_returnsCorrectValues() {
        assertAll(
            () -> assertEquals(new BigInteger("1"), RecursiveMath.getFactorial(0)),
            () -> assertEquals(new BigInteger("1"), RecursiveMath.getFactorial(1)),
            () -> assertEquals(new BigInteger("2"), RecursiveMath.getFactorial(2)),
            () -> assertEquals(new BigInteger("6"), RecursiveMath.getFactorial(3)),
            () -> assertEquals(new BigInteger("24"), RecursiveMath.getFactorial(4)),
            () -> assertEquals(new BigInteger("120"), RecursiveMath.getFactorial(5))
        );
    }

    @Test
    public void factorial_large_returnsCorrectValues() {
        assertAll(
            () -> assertEquals(new BigInteger("3628800"), RecursiveMath.getFactorial(10)),
            () -> assertEquals(new BigInteger("2432902008176640000"), RecursiveMath.getFactorial(20)),
            () -> assertEquals(new BigInteger("933262154439441526816992388562667004907159682643816214685929638952175999932299156089414639761565182862536979208272237582511852109168640000000000000000000000"), 
                                                RecursiveMath.getFactorial(99)),
            () -> assertEquals(new BigInteger("435172281340659532239765050545636993575468063628292108941273414883732058750474383616856659441158282324690256052248800585497232469751038011950955548393204012072891513546726428975527098844367121296463396955934730296403643003140294715558489644622417358821886036965350971794858868549192367459329133116028460516363488437668131355469914235110539787843671863303910932048449686777752709503221004604721613368541189130323367315049586961269365213977467720193942467125261288080706106995967568022993462361952081731835853596164764008570502140764670004834827255718644707393768049565788953813012573930480156843049073035506677797518912561931901376001538475098339170741200657739145755123383418576602550423299677109519815811839383774940261486462980956459652502145883136586478067403847913644047202903836133413116332748734828264131331688237387188427610235295214192901814720999253396678184054548932879464688588843450136858910997334826566262905351119130295324414532245241332525626244475880294046620799957205841356135002522606810510518193914851139483351269250810026072540042818339776049254311345720711181473164691212101023648437971857259188906065450242212851806677086580193419164740123583365461900233401278040993999425126303980140198635645023284075448733071596985734992598504499439446079387738344876985907550876207959968702880446578636284117257674705202225103032741239304859332061895407389398859170154907882499448035828080465550397268451197988616490351885704002175278088571927166965615916438862395718198582738488279235962958499409025334383970325095374238808795711537761370584393028362001681904598892654317906884650344400075315228892930409037180692468402299318641892761089282434905419996020614454347909124454486077162189932752739035192595468938633750007320883945021019646952838533990286788176928033460691918084878596958984382521827853692065698059121332086792893549094940493231611363855095871725755591031728475475185658473748464143720213169794965448124500617263914005067131078123152434607952693401801745580747510516030730493977620834112462832160251412946757915716553199086681682287727557090911581221103180914702421637404836147851117421227557043081061808741860495700611429160132494645628572460362305950897758309758643427628653302931737158431234418499967145302618342991501862786978579876335969151834964243691601920000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),
                                                RecursiveMath.getFactorial(987))
        );
    }
}
