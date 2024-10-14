package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class IngredientParameterizedTest {

    private final IngredientType type;
    private final String name;
    private final float price;
    private final Ingredient ingredient;

    public IngredientParameterizedTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.ingredient = new Ingredient(type, name, price);
    }

    @Parameterized.Parameters
    public static Object[][] parameters() {
            return new Object[][]{
                    {SAUCE, "Первый тестовый ингредиент", 999.0f},
                    {FILLING, "Второй тестовый ингредиент", 1388.0f},
                    {SAUCE, "Третий тестовый ингредиент", 555.0f},
                    {FILLING, "Четвертый тестовый ингредиент", 555.0f},
            };
    }

    @Test
    public void getPriceTest(){
        assertEquals("Цена ингредиента не совпадает с ожидаемым значением", price, ingredient.getPrice(), 0.001f);
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