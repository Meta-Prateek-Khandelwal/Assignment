package Assignment3;
class Area{
    // initialization 
    private double width;
    private double height;
    private double radius;

    // using getter methods for the encapsulations
    double getWidth() {return width;}
    double getHeight() {return height;}
    double getRadius() {return radius;}

    // triangle area calculate
    double triangle(double widht, double height){
        if(widht < 0 || height < 0){
            throw new ArithmeticException("Height and width must be greater then or equal to zero");
        }
        double area = 0.5*widht*height;
        return area;
    }

    // rectangle area calculate
    double rectangle(double widht, double height){
        if(widht < 0 || height < 0){
            throw new ArithmeticException("Height and width must be greater then or equal to zero");
        }
        double area = widht*height;
        return area;
    }

    // squre area calculate
    double square(double widht){
        if(widht < 0){
            throw new ArithmeticException("width must be greater then or equal to zero");
        }
        double area = widht*widht;
        return area;
    }

    // cricle area calculate
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
        Area shape = new Area();
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