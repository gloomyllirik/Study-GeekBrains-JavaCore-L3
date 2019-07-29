import org.junit.*;

public class Lesson06Test {
    private Lesson06 lesson06;

    @Before
    public void startTest(){
        lesson06 = new Lesson06();
    }

    @Test
    public void testcutMass(){
        Assert.assertArrayEquals(new Integer[] {5,6,7,8,9}, Lesson06.cutMass(new Integer[] {0,1,2,3,4,5,6,7,8,9}));
        Assert.assertArrayEquals(new Integer[] {5}, Lesson06.cutMass(new Integer[] {4,1,2,3,4,1,2,3,4,5}));
        Assert.assertArrayEquals(new Integer[] {50,60,70,80}, Lesson06.cutMass(new Integer[] {10,20,30,4,50,60,70,80}));
        Assert.assertArrayEquals(new Integer[] {3,2,1,0}, Lesson06.cutMass(new Integer[] {4,3,2,1,0}));
    }

    @Test (expected = RuntimeException.class)
    public void checkException() {
        Lesson06.cutMass(new Integer[] {0,1,2,3,5,6,7,8,9});
    }

    @Test
    public void testcheckMass(){
        Assert.assertFalse(Lesson06.checkMass(new Integer[] {5,6,7,8,9}));
        Assert.assertFalse(Lesson06.checkMass(new Integer[] {2,3,5,6,7,8,9}));
        Assert.assertFalse(Lesson06.checkMass(new Integer[] {10,20,30,40,50,60,70,80,90}));
        Assert.assertFalse(Lesson06.checkMass(new Integer[] {2,3,5,6}));
    }

}
