package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static praktikum.IngredientType.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;
    private final static double DELTA = 0.001f;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        Bun bun = new Bun("Тестовая булка", 1500.0f);
        burger.setBuns(bun);
        assertEquals("Булка установлена неверно", bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        Ingredient ingredient1 = new Ingredient(SAUCE, "Тестовый соус", 500.0f);
        Ingredient ingredient2 = new Ingredient(FILLING, "Тестовая начинка", 900.0f);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        // Проверяем, что в бургере 2 ингредиента
        assertEquals("Количество ингредиентов не верно", 2, burger.ingredients.size());

        // Проверяем, что первый ингредиент - это наш тестовый соус
        assertEquals("Первый ингредиент неверный", ingredient1, burger.ingredients.get(0));

        // Проверяем, что второй ингредиент - это наша тестовая начинка
        assertEquals("Второй ингредиент неверный", ingredient2, burger.ingredients.get(1));
    }

    @Test
    public void removeIngredientTest() {
        Ingredient ingredient1 = new Ingredient(SAUCE, "Тестовый соус", 500.0f);

        burger.addIngredient(ingredient1);
        assertEquals("Количество ингредиентов после добавления неверно", 1, burger.ingredients.size());
        burger.removeIngredient(0);
        assertEquals("Количество ингредиентов после удаления не верно", 0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientTest() {
        Ingredient ingredient1 = new Ingredient(SAUCE, "Тестовый соус", 500.0f);
        Ingredient ingredient2 = new Ingredient(FILLING, "Тестовая начинка", 900.0f);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        assertEquals("Первый игредиент должен быть на второй позиции", ingredient2, burger.ingredients.get(0));
        assertEquals("Первый игредиент должен быть на первой позиции", ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void getPriceTest() {
        when(mockBun.getPrice()).thenReturn(2389.0f);
        when(mockIngredient1.getPrice()).thenReturn(567.0f);
        when(mockIngredient2.getPrice()).thenReturn(880.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        float expectedPrice = (mockBun.getPrice() * 2) + mockIngredient1.getPrice() + mockIngredient2.getPrice();
        assertEquals("Цена бургера неверна", expectedPrice, burger.getPrice(), DELTA);
    }


    @Test
    public void getReceiptTest() {
        // Настраиваем моки для содержимого бургера
        when(mockBun.getName()).thenReturn("Тестовая булка");
        when(mockBun.getPrice()).thenReturn(1000.0f);
        when(mockIngredient1.getType()).thenReturn(SAUCE);
        when(mockIngredient1.getName()).thenReturn("Тестовый соус");
        when(mockIngredient1.getPrice()).thenReturn(500.0f);
        when(mockIngredient2.getType()).thenReturn(FILLING);
        when(mockIngredient2.getName()).thenReturn("Тестовая начинка");
        when(mockIngredient2.getPrice()).thenReturn(500.0f);

        // Собираем бургер
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        // Ожидаемый чек
        float expectedPrice = (mockBun.getPrice() * 2) + mockIngredient1.getPrice() + mockIngredient2.getPrice();
        String expectedReceipt = String.format(
                "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                mockBun.getName(),
                mockIngredient1.getType().toString().toLowerCase(), mockIngredient1.getName(),
                mockIngredient2.getType().toString().toLowerCase(), mockIngredient2.getName(),
                mockBun.getName(),
                expectedPrice
        );

        // Фактический чек
        String actualReceipt = burger.getReceipt();

        // Проверяем, что чеки совпали
        assertEquals("Чек неверен", expectedReceipt, actualReceipt);
    }
}