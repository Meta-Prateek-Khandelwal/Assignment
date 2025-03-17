package Assignment3;
class Shape{
    private double width;
    private double height;
    private double radius;

    // using getter methods for the encapsulations
    double getWidth() {return width;}
    double getHeight() {return height;}
    double getRadius() {return radius;}

    /**
     * triangle area calculate
     * @param the first is width
     * @param the secound is height
     * @return double vlaue area
     */
    double triangle(double widht, double height){
        if(widht < 0 || height < 0){
            throw new ArithmeticException("Height and width must be greater then or equal to zero");
        }
        double area = 0.5*widht*height;
        return area;
    }

    /**
     * rectangle area calculate
     * @param the first is width
     * @param the secound is height
     * @return double vlaue area
     */
    double rectangle(double widht, double height){
        if(widht < 0 || height < 0){
            throw new ArithmeticException("Height and width must be greater then or equal to zero");
        }
        double area = widht*height;
        return area;
    }

    /**
     * square area calculate
     * @param the first is width
     * @param the secound is height
     * @return double vlaue area
     */
    double square(double widht){
        if(widht < 0){
            throw new ArithmeticException("width must be greater then or equal to zero");
        }
        double area = widht*widht;
        return area;
    }

    /**
     * cricle area calculate
     * @param the first is width
     * @param the secound is height
     * @return double vlaue area
     */
    double circle(double radius){
        if(radius < 0){
            throw new ArithmeticException("radius must be greater then or equal to zero");
        }
        double area = 3.14*radius*radius;
        return area;
    }
}

public class AreaCal{
    public static void main(String[] args){
        Shape shape = new Shape();
        try{
            System.out.println("Triangle Area: "+shape.triangle(5,-1));
        }catch(ArithmeticException e){
            System.out.println("Exception: "+ e.getMessage());
        }
        try{
            System.out.println("Rectangle Area: "+shape.rectangle(5,4));
        }catch(ArithmeticException e){
            System.out.println("Exception: "+ e.getMessage());
        }
        try{
            System.out.println("Square Area: "+shape.square(-5));
        }catch(ArithmeticException e){
            System.out.println("Exception: "+ e.getMessage());
        }
        try{
            System.out.println("Circle Area: "+shape.circle(7));
        }catch(ArithmeticException e){
            System.out.println("Exception: "+ e.getMessage());
        }
    }
}