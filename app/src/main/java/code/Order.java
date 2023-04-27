package code;

import java.io.Serializable;
import java.util.*;

/**
 * Order class that holds items added to the order cart and information about them, including price.
 * @author Stephanie Lin, Hyeseo Lee
 */
public class Order implements Serializable {
    private MenuItem[] Order;
    private int InitialCapacity = 1;
    private int size;
    private int EMPTY = 0;
    private int GrowthRate = 1;
    private double totalPrice;
    private int orderNumber;
    private double tax = 0.06625;
    private int Error = -1;

    /**
     * Order constructor to create Order object
     */
    public Order() {
        this.Order = new MenuItem[InitialCapacity];
        this.size = EMPTY;
        this.totalPrice = EMPTY;
    }

    /**
     * Void method to grow the array of MenuItems in Order object
     */
    private void grow() {
        MenuItem[] oldOrder = this.Order;
        this.Order = new MenuItem[this.size + GrowthRate];
        for (int i = 0; i < this.size; i++) {
            this.Order[i] = oldOrder[i];
        }
    }

    /**
     * Searches for the MenuItem in Order object, returns index if present.
     * @param item the MenuItem being searched in Order
     * @return Integer index of MenuItem if the item is present.
     * Integer -1 to represent that the item is not in array if not.
     */
    public int findItem(MenuItem item) {
        for (int i = 0; i < size; i++) {
            MenuItem currentItem = Order[i];
            if (currentItem instanceof Coffee && item instanceof Coffee) {
                if ((((Coffee) currentItem).compareAddIns((Coffee) item) == true)) {
                    if ((((Coffee) currentItem).getCupSize().equals((((Coffee) item).getCupSize())))) {
                        return i;
                    }
                }
            } else if (currentItem instanceof Donut && item instanceof Donut) {
                if (((Donut) currentItem).getType().equals(((Donut) item).
                        getType()) &&
                        (((Donut) currentItem).getFlavor().equals(((Donut) item).
                                getFlavor()))) {
                    return i;
                }
            }
        }
        return Error;
    }

    /**
     * Returns the MenuItem in MenuItem array of Order if present.
     * @param item the MenuItem being searched to be returned.
     * @return MenuItem that was inputted if present; null if not.
     */
    public MenuItem returnItem(MenuItem item) {
        int index = findItem(item);
        if (index == Error) {
            return null;
        } else {
            return Order[index];
        }
    }

    /**
     * Adds a MenuItem to the MenuItem array in Order class.
     * @param item the MenuItem being added to the array.
     * @return true once the object was successfully added.
     */
    public boolean add(MenuItem item) {
        MenuItem currentItem = returnItem(item);
        if (currentItem != null) {
            currentItem.setQuantity(currentItem.getQuantity()
                    + item.getQuantity());
        } else {
            if (size == Order.length) {
                grow();
            }
            Order[size] = item;
            size++;
        }
        totalPrice += item.itemPrice();
        return true;
    }

    /**
     * Object to return the specified MenuItem from the array in Order object.
     * @param item the MenuItem to be removed from the array in Order object.
     * @return false if the object was not present in array.
     * true if the object was present and has been successfully removed.
     */
    public boolean remove(MenuItem item) {
        MenuItem currentItem = returnItem(item);
        if (currentItem == null) {
            return false;
        } else {
            if (currentItem.getQuantity() > item.getQuantity()) {
                currentItem.setQuantity(currentItem.getQuantity() -
                        item.getQuantity());
            } else if (currentItem.getQuantity() == item.getQuantity()) {
                int index = findItem(item);
                for (int i = index; i < size - 1; i++) {
                    Order[i] = Order[i + 1];
                }
                Order[size - 1] = null;
                size--;
            } else if (currentItem.getQuantity() < item.getQuantity()) {
                return false;
            }
            totalPrice -= item.itemPrice();
            return true;
        }
    }

    /**
     * Returns the total price of all items in Order object.
     * @return double value of total price of all items in order.
     */
    public double getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * Creates a string representation of the Order to show its content.
     * @return String representation of the Order object.
     */
    public String toString() {
        String returnString = "";
        for (int i = 0; i < size; i++) {
            returnString += Order[i].toString() + "\n";
        }
        return returnString;
    }


    /**
     * Creates an ArrayList of MenuItems from the array in Order.
     * @return ArrayList<MenuItems> from items in MenuItem array of Order.
     */
    public ArrayList<MenuItem> OrderList() {
        ArrayList<MenuItem> OrderList = new ArrayList<MenuItem>(Arrays.asList(Order));
        return OrderList;
    }

    /**
     * Getter method for the order number of object.
     * @return Integer representation of the order number.
     */
    public int getOrderNumber() {
        return this.orderNumber;
    }

    /**
     * Setter method to change the order number of the object.
     * @param number Integer to change the order number to.
     */
    public void setOrderNumber(int number) {
        this.orderNumber = number;
    }

    /**
     * Method to compare to instances of Order objects and see if they equal.
     * @param order the Order object to compare this Order object to.
     * @return true if the two objects are the same; false otherwise.
     */
    public boolean equals(Order order) {
        if (orderNumber == order.getOrderNumber()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Calculates the total price of the order with tax.
     * @return Double representation of total price with tax.
     */
    public double priceWithTax() {
        double salesTax = totalPrice * tax;
        return totalPrice + salesTax;
    }


}