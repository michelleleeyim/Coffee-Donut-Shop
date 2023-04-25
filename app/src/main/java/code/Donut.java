package code;

/**
 * Donut subclass of MenuItem, holds the donut attributes.
 * @author Stephanie Lin, Hyeseo Lee
 */
public class Donut extends MenuItem{
    private String type;
    private String flavor;
    private double price;
    private double YEAST_PRICE=1.59;
    private double CAKE_PRICE = 1.79;
    private double DONUT_HOLE_PRICE = 0.39;

    /**
     * Donut constructor that is assigning each component of the Donut class.
     * @param name represents the MenuItem, in this case Donut.
     * @param type represents the type of Donut in Yeast, Cake, or Donut Hole.
     * @param flavor represents the different flavors associated with each type of Donut.
     * @param quantity represents the number of Donuts ordered.
     */
    public Donut(String name, String type, String flavor, int quantity){
        super(type + " " + name + ", " + flavor + " flavor", quantity);
        this.type = type;
        this.flavor = flavor;
        if(type.equals("Yeast")){
            this.price = YEAST_PRICE;
        }else if(type.equals("Cake")){
            this.price = CAKE_PRICE;
        }else{
            this.price = DONUT_HOLE_PRICE;
        }
    }

    /**
     * getter method for the type of donut.
     * @return String representation of the type of donut.
     */
    public String getType() {
        return type;
    }

    /**
     * getter method for the type of flavor associated with the type of donut.
     * @return String representation of the flavor or the donut.
     */
    public String getFlavor() {
        return flavor;
    }

    /**
     * calculates the menu item's price.
     * @return double representation of the menu item's price given the quantity ordered.
     */
    @Override
    public double itemPrice() {
        return price*quantity;
    }

    /**
     * getter method for the number of menu items ordered.
     * @return int representation of the number of menu items ordered.
     */
    @Override
    public int getQuantity() { return quantity;}

    /**
     * toString method that converts the donut's type, flavor, and
     * quantity into one string.
     * @return String representtion of the donut's type, flavpr, and quantity.
     */
    @Override
    public String toString() {
        String returnString= this.getType() + " donut, " + this.getFlavor()
                + " flavor, quantity: "+ Integer.toString(this.quantity);
        return returnString;
    }

}