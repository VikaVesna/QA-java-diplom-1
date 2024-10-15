package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BunTest {

    private Bun bun;
    private final String name;
    private final float price;
    private final static double DELTA = 0.001f;

    public BunTest(String name, float price){
        this.name = name;
        this.price = price;
    }

    @Before
    public void setUp(){
        bun = new Bun(name, price);
    }

    @Parameterized.Parameters
    public static Object[][] parameters() {
        return new Object[][]{
                {"Тестовая булка", 999.0f},
                {"Вторая тестовая булка", 1388.0f},
                {"Тестовая булка", 0.0f},
                {null, 999.0f},
                {"Тестовая булка", -999.0f},
        };
    }

    @Test
    public void getNameTest(){
        assertEquals("Название булки не совпадает с ожидаемым значением", name, bun.getName());
    }

    @Test
    public void getPriceTest(){
        assertEquals("Цена булки не совпадает с ожидаемым значением", price, bun.getPrice(), DELTA);
    }
}