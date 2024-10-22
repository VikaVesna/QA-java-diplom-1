package praktikum;

import org.junit.Test;

import static org.junit.Assert.*;

public class IngredientTypeTest {

    @Test
    public void valueOfSauce() {
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }

    @Test
    public void valueOfFilling() {
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}