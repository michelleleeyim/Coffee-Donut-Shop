package code;

/**
 * Coffee subclass of MenuItem, holds the coffee attributes
 * @author Stephanie Lin, Hyeseo Lee
 */
public class Coffee extends MenuItem {

    private String cupSize;
    private String addInString;
    private String[] addIns;
    private double price;
    private double addInPrice = 0.30;
    private double SHORT_PRICE=1.89;
    private double TALL_PRICE = 2.29;
    private double GRANDE_PRICE = 2.69;
    private double VENTI_PRICE=3.09;
    private double NONE = 0;
    private int numOfAddIns = 5;

    /**
     * Coffee constructor that is assigning each component of the Coffee class.
     * @param name represents the MenuItem, in this case Coffee.
     * @param cupSize represents the coffee's cup size in short, tall, grande, or venti.
     * @param addIns represents the user's selected add-ins.
     * @param quantity represents the number of coffees ordered.
     */
    public Coffee(String name, String cupSize, String[] addIns, int quantity){
        super(name, quantity);
        this.cupSize = cupSize;
        this.addIns = addIns;
        this.addInString = addInToString(addIns);
        if(cupSize.equals("Short")){
            this.price = SHORT_PRICE;
        }else if(cupSize.equals("Tall")){
            this.price = TALL_PRICE;
        }else if(cupSize.equals("Grande")){
            this.price = GRANDE_PRICE;
        }else{
            this.price = VENTI_PRICE;
        }

        if (addIns != null) {
            for(String item: addIns){
                if (item != null) {
                    this.price +=addInPrice;
                }
            }
        }
    }

    /**
     * getter method for quantity of coffee ordered.
     * @return int value quantity of coffees ordered.
     */
    @Override
    public int getQuantity() { return this.quantity;}

    /**
     * getter method for the coffee cup size.
     * @return String representation of the coffee cup size.
     */
    public String getCupSize() {
        return this.cupSize;
    }

    /**
     * getter method for the user's selection of add ins.
     * @return an array of the user's selection of add ins.
     */
    public String[] getAddIns() {
        return this.addIns;
    }

    /**
     * getter method for the user's selection of add ins.
     * @return String representation of user's selection of add ins.
     */
    public String getAddInString() {
        return this.addInString;
    }

    /**
     * comparison method for Coffee object's add ins.
     * @param coffee Coffee object that will be compared with.
     * @return boolean value, true if the add ins match with the current add ins
     * of current Coffee object, false otherwise.
     */
    public boolean compareAddIns(Coffee coffee) {
        String[] newAddIns = coffee.getAddIns();
        String[] currAddIns = this.addIns;
        for (int i = 0; i < numOfAddIns; i++) {
            if (newAddIns[i] != currAddIns[i]) {
                return false;
            }
        }
        return false;
    }

    /**
     * calculates the item's price with the number of items ordered.
     * @return double of the item's price.
     */
    @Override
    public double itemPrice(){
        return this.price * (double) this.quantity;
    }

    /**
     * toString method that converts the coffee's cup size, add ins, and
     * quantity into one string.
     * @return String representtion of the coffee's cup size, add ins, and quantity.
     */
    @Override
    public String toString() {
        String returnString= this.getCupSize() + " coffee with: ";
        for (int i = 0; i < numOfAddIns; i++) {
            if (addIns[i] != null) {
                returnString += addIns[i] + ", ";
            }
        }
        returnString += "quantity: " + Integer.toString(this.quantity);
        return returnString;
    }

    /**
     * Converts an array of the user's add ins to a String representation.
     * @param addIns is the String array that holds the user's selection of add ins.
     * @return String representation of the user's selection of add ins.
     */
    public String addInToString(String[] addIns) {
        String returnString = "";
        for (int i = 0; i < numOfAddIns; i++) {
            if (addIns[i] != null) {
                returnString += addIns[i] + " ";
            }
        }
        return returnString;
    }



}