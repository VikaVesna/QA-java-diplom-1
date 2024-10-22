package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType type;
    private final String name;
    private final float price;
    private Ingredient ingredient;
    private final static double DELTA = 0.001f;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Before
    public void setUp(){
        ingredient = new Ingredient(type, name, price);
    }

    @Parameterized.Parameters
    public static Object[][] parameters() {
            return new Object[][]{
                    {SAUCE, "Тестовый соус", 999.0f},
                    {FILLING, "Тестовая начинка", 1388.0f},
            };
    }

    @Test
    public void getPriceTest(){
        assertEquals("Цена ингредиента не совпадает с ожидаемым значением", price, ingredient.getPrice(), DELTA);
    }

    @Test
    public void getNameTest(){
        assertEquals("Название ингредиента не совпадает с ожидаемым значением", name, ingredient.getName());
    }

    @Test
    public void getTypeTest(){
        assertEquals("Тип ингредиента не совпадает с ожидаемым значением", type, ingredient.getType());
    }
}