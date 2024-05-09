package put.io.testing.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FailureOrErrorTest {

    @Test
    public void test1(){
        assertEquals(5,4);
    }

    @Test
    public void test2(){
        throw new RuntimeException("arbitrary exception");
    }

    @Test
    public void test3(){
        try{
            assertEquals(1,2);
        } catch (AssertionError e){
            e.printStackTrace();
        }
    }

}
