package var;

public class Apple {
    
    private double price;
    
    public static void main(String[] args) {
        Apple fuji = new Apple();
        fuji.setPrice(3.99);
        Apple gala = new Apple();
        gala.setPrice(4.99);
        var p1 = fuji.getPrice();
        var p2 = gala.getPrice();
        
        var total = p1 + p2;
        
        System.out.println(total);
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
