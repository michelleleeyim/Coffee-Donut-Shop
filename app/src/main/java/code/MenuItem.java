package code;

import java.io.Serializable;

/**
 * A superclass and abstract class of all menu items including donut and coffee.
 * @author Stephanie Lin, Hyeseo Lee
 */
public abstract class MenuItem implements Serializable {
    public String name;
    public int quantity;

    /**
     * constructor method that holds all the attributes of MenuItem objects.
     * @param name represents the name of the menu item.
     * @param quantity represents the number of the menu item ordered.
     */
    public MenuItem( String name, int quantity){
        this.name = name;
        this.quantity = quantity;
    }

    /**
     * getter method for the menu item's name.
     * @return String representation of the menu item's name.
     */
    public String getName(){
        return name;
    }

    /**
     * abstract method implemented in all child classes to calculate the menu item's price.
     * @return double of the menu item's price.
     */
    public abstract double itemPrice();

    /**
     * getter method for the number of the menu item ordered.
     * @return int representation of the number of the menu item ordered.
     */
    public abstract int getQuantity();

    /**
     * abstract method implemented in all child class to create a String representation.
     * @return String representation of the ordered menu item.
     */
    public abstract String toString();

    /**
     * setter method for the number of menu item ordered.
     * @param newQuantity the new number of menu item ordered that will be replacing the current quantity.
     */
    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

}