package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    Map<String, Integer> groceryInventory = new HashMap<>();
    String productName;
    int productQuantity;


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setup(){
        System.setOut(new PrintStream(outContent));
        groceryInventory.put("Apple",50);
        groceryInventory.put("Eggs",20);
        groceryInventory.put("Milk",20);
        groceryInventory.put("Bread",5);
    }
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }


    @Test
    void addProduct(){
        Main.addProduct(groceryInventory,"Banana",30);
        assertTrue(groceryInventory.containsKey("Banana"));
        Integer actual = groceryInventory.get("Banana");
        assertEquals(30,actual);
    }

    @Test
    void invalidAddProduct(){
        Main.addProduct(groceryInventory,"Mango",0);
        assertFalse(groceryInventory.containsKey("Mango"));
        String expected = "Quantity must be positive." + System.lineSeparator();
        assertEquals(expected,outContent.toString());
    }
    @Test
    void addExistingProduct(){
        Main.addProduct(groceryInventory,"Milk",50);
        assertTrue(groceryInventory.containsKey("Milk"));
        Integer actual = groceryInventory.get("Milk");
        assertEquals(50,actual);
    }


    @Test
    void checkProduct(){
        Main.checkProduct(groceryInventory,"Milk");
        String expected = "Milk is in stock: 20" + System.lineSeparator() + System.lineSeparator();
        assertEquals(expected,outContent.toString());
    }

    @Test
    void invalidCheckProduct(){
        Main.removeProduct(groceryInventory,"Ice Cream");
        String expected = "Ice Cream is not in stock."  + System.lineSeparator();
        assertEquals(expected,outContent.toString());
    }


    @Test
    void updateStock(){
        int newQuantity = 25;
        Main.updateStock(groceryInventory,"Bread",newQuantity);
        Integer actual = groceryInventory.get("Bread");
        assertEquals(newQuantity,groceryInventory.get("Bread"));
    }

    @Test
    void invalidUpdateStock(){
        int newQuantity = 25;
        Main.updateStock(groceryInventory,"Tofu",newQuantity);
        String expected = "Tofu is not in stock."  + System.lineSeparator();
        assertEquals(expected,outContent.toString());
    }



    @Test
    void removeProduct(){
        Main.removeProduct(groceryInventory,"Eggs");
        assertFalse(groceryInventory.containsKey("Eggs"));
        String expected = "Product removed."  + System.lineSeparator();
        assertEquals(expected,outContent.toString());

    }

    @Test
    void invalidRemoveProduct(){
        Main.removeProduct(groceryInventory,"Pizza");
        String expected = "Pizza is not in stock." + System.lineSeparator();
        assertEquals(expected,outContent.toString());
    }

}