package praktikum;

import org.junit.Test;

import static org.junit.Assert.*;

public class BunTest {

    @Test
    public void getNameTest(){
        Bun bun = new Bun("Тестовая булка", 1034.0f);
        String actualName = bun.getName();
        assertEquals("Название булки не совпадает с ожидаемым значением", "Тестовая булка", actualName);
    }

    @Test
    public void getPriceTest(){
        Bun bun = new Bun("Вторая тестовая булка", 4536.0f);
        float actualPrice = bun.getPrice();
        assertEquals("Цена булки не совпадает с ожидаемым значением", 4536.0f, actualPrice, 0.001f);
    }

}