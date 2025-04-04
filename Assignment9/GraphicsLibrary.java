package Assignment9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Date;

class Point {
    double x;
    double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

enum ShapeType {
    CIRCLE,
    RECTANGLE,
    TRIANGLE,
    SQUARE,
    POLYGON
}

interface Shape {
    double getArea();

    double getPerimeter();

    Point getOrigin();

    boolean isPointEnclosed(Point point);
}

abstract class ShapeClass implements Shape {
    ShapeType type;
    Point origin;
    List<Integer> dimension;
}

class Circle extends ShapeClass {
    double radius ;
    Circle(Point origin, List<Integer> dimension) {
        this.origin = origin;
        this.dimension = dimension;
        this.type = ShapeType.CIRCLE;
        radius = dimension.get(0);
    }

    @Override
    public double getArea() {
        return 3.14 * radius * radius;
    }

    @Override
    public double getPerimeter() {
        double radius = dimension.get(0);
        return 2 * 3.14 * radius;
    }

    @Override
    public Point getOrigin() {
        return origin;
    }

    @Override
    public boolean isPointEnclosed(Point point) {
        double distance = Math.sqrt(Math.pow(point.x - origin.x, 2) + Math.pow(point.y - origin.y, 2));
        return distance <= radius;
    }
}

class Triangle extends ShapeClass {
    double breadth;
    double height;
    double length;

    Triangle(Point origin, List<Integer> dimension) {
        this.origin = origin;
        this.dimension = new ArrayList<>();
        this.dimension = dimension;
        this.type = ShapeType.TRIANGLE;
        breadth = dimension.get(0);
        height = dimension.get(1);
        length = dimension.get(2);
    }

    @Override
    public double getArea() {
        return 0.5 * breadth * height;
    }

    @Override
    public double getPerimeter() {
        return breadth * height * length;
    }

    @Override
    public Point getOrigin() {
        return origin;
    }

    @Override
    public boolean isPointEnclosed(Point point) {
        double x1 = origin.x , y1 = origin.y;
        double x2 = x1 + dimension.get(0) , y2 = y1;
        double x3 = x1 + dimension.get(1) , y3 = y1 + dimension.get(1);

        double Area = Math.abs(x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2) / 2.0);
        double Area1 = Math.abs(point.x*(y2-y3) + x2*(y3-point.y) + x3*(point.y-y2) / 2.0);
        double Area2 = Math.abs(x1*(point.y-y3) + point.x*(y3-y1) + x3*(y1-point.y) / 2.0);
        double Area3 = Math.abs(x1*(y2-point.y) + x2*(point.y-y1) + point.x*(y1-y2) / 2.0);

        return (Area == Area1+Area2+Area3);
    }
}

class Rectangle extends ShapeClass {
    double breadth;
    double length;

    Rectangle(Point origin, List<Integer> dimension) {
        this.origin = origin;
        this.dimension = new ArrayList<>();
        this.dimension = dimension;
        this.type = ShapeType.RECTANGLE;
        breadth = dimension.get(0);
        length = dimension.get(1);
    }

    @Override
    public double getArea() {
        return length * breadth;
    }

    @Override
    public double getPerimeter() {
        return 2 * (length + breadth);
    }

    @Override
    public Point getOrigin() {
        return origin;
    }

    @Override
    public boolean isPointEnclosed(Point point) {
        double xMin = origin.x, yMin = origin.y;
        double xMax = xMin + dimension.get(0), yMax = yMin + dimension.get(1);
        
        return (point.x >= xMin && point.x <= xMax) && (point.y >= yMin && point.y <= yMax);
    }
}

class Polygon extends ShapeClass {
    double breadth;
    double height;
    double length;

    Polygon(Point origin, List<Integer> dimension) {
        this.origin = origin;
        this.dimension = new ArrayList<>();
        this.dimension = dimension;
        this.type = ShapeType.POLYGON;
        breadth = dimension.get(0);
        height = dimension.get(1);
        length = dimension.get(2);
    }

    @Override
    public double getArea() {
        int totalSides = dimension.size();
        double side = dimension.get(0);

        return (totalSides * side * side) / (4 * Math.tan(Math.PI / totalSides));
    }

    @Override
    public double getPerimeter() {
        double perimeter = 0;
        for(int side : dimension){
            perimeter += side;
        }

        return perimeter;
    }

    @Override
    public Point getOrigin() {
        return origin;
    }

    @Override
    public boolean isPointEnclosed(Point point) {
        double radius = dimension.get(0) * 0.5;
        double distance = Math.sqrt(Math.pow(point.x - origin.x, 2) + Math.pow(point.y - origin.y, 2));
        return distance <= radius;
    }
}

class Square extends ShapeClass {
    double side;

    Square(Point origin, List<Integer> dimension) {
        this.origin = origin;
        this.dimension = new ArrayList<>();
        this.dimension = dimension;
        this.type = ShapeType.SQUARE;
        side = dimension.get(0);
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public double getPerimeter() {
        return 4 * side;
    }

    @Override
    public Point getOrigin() {
        return origin;
    }

    @Override
    public boolean isPointEnclosed(Point point) {
        double xMin = origin.x, yMin = origin.y;
        double xMax = xMin + dimension.get(0), yMax = yMin + dimension.get(1);
        
        return (point.x >= xMin && point.x <= xMax) && (point.y >= yMin && point.y <= yMax);
    }
}

enum SortCriteria{
    AREA,
    PARIMETER,
    TIMESTAMP,
    ORIGIN
}

class Screen {
    private final List<ShapeClass> listShape;
    private final HashMap<ShapeClass, Long> shapeTimestamps;

    Screen() {
        listShape = new ArrayList<>();
        shapeTimestamps = new HashMap<>();
    }

    void addShape(ShapeClass shape) {
        listShape.add(shape);
        shapeTimestamps.put(shape, System.currentTimeMillis());
    }

    void delete(ShapeClass shape) {
        listShape.remove(shape);
        shapeTimestamps.remove(shape);
    }

    void deleteAll(ShapeType shapeType) {
        for (int i = 0; i < listShape.size(); i++) {
            if (listShape.get(i).type == shapeType) {
                listShape.remove(i);
                shapeTimestamps.remove(i);
                i--;
            }
        }
    }

    List<ShapeClass> getSortedShapes(SortCriteria criteria){
        List<ShapeClass> sortedShapes = new ArrayList<>(listShape);
        listShape.sort(getComparator(criteria));
        return sortedShapes;
    }

    
    private Comparator<ShapeClass> getComparator(SortCriteria criteria) {
        switch(criteria){
            case AREA : return Comparator.comparingDouble(ShapeClass::getArea);
            case PARIMETER: return Comparator.comparingDouble(ShapeClass::getPerimeter);
            case TIMESTAMP: return Comparator.comparingLong(shape -> shapeTimestamps.getOrDefault(shape, 0L));
            case ORIGIN: return Comparator.comparingDouble(shape ->
                Math.sqrt(Math.pow(shape.getOrigin().x,2) + Math.pow(shape.getOrigin().y,2))
            );
            default:
                throw new IllegalArgumentException("Invalid sort criteria");
        }
    }

    void displaySorted(SortCriteria criteria){
        List<ShapeClass> sortedShape = getSortedShapes(criteria);

        for(ShapeClass shape: sortedShape){
            System.out.println("Shape Type: "+shape.type);
            System.out.println("Area: "+shape.getArea());
            System.out.println("Shape Type: "+shape.getPerimeter());
            System.out.println("Shape Type: "+
                Math.sqrt(Math.pow(shape.getOrigin().x,2) + Math.pow(shape.getOrigin().y,2)));
            System.out.println("Added TimeStamp: "+ new Date(shapeTimestamps.get(shape)));
            System.out.println();
        }
    } 
    
    List<ShapeClass> getShapesEncolsingPoint(Point p){
        List<ShapeClass> encolsingShape = new ArrayList<>();

        for(ShapeClass shape: listShape){
            if(shape.isPointEnclosed(p)){
                encolsingShape.add(shape);
            }
        }

        return encolsingShape;
    }

    void display() {
        if(listShape.isEmpty()){
            System.out.println("No Shapes on the screen.");
            return ;
        }

        for (Shape shape : listShape) {
            System.out.println("Area: " + shape.getArea());
            System.out.println("Perimeter: " + shape.getPerimeter());
            System.out.println("Origin Point X coordinate = " + shape.getOrigin().x+" and Y coordinate = "+shape.getOrigin().y);
            System.out.println("Point inside and boundries of the shape: "+ shape.isPointEnclosed(new Point(31, 41)));
            System.out.println();
        }
    }
}

class ShapeFactory {

    ShapeClass createShape(ShapeType shapeType, Point origin, List<Integer> dimension) {

        switch(shapeType){
            case CIRCLE:
            return new Circle(origin, dimension);

            case RECTANGLE:
            return new Rectangle(origin, dimension);

            case TRIANGLE:
            return new Triangle(origin, dimension);

            case POLYGON:
            return new Polygon(origin, dimension);

            case SQUARE:
            return new Square(origin, dimension);

            default:
            return null;
        }
    }
}

class GraphicsLibrary {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();

        ShapeClass circle = factory.createShape(ShapeType.CIRCLE, new Point(30, 40), Arrays.asList(4));
        
        ShapeClass rectangle1 = factory.createShape(ShapeType.RECTANGLE, new Point(40, 40), Arrays.asList(20,40));
        ShapeClass rectangle2 = factory.createShape(ShapeType.RECTANGLE, new Point(30, 40), Arrays.asList(30,40));
        ShapeClass rectangle3 = factory.createShape(ShapeType.RECTANGLE, new Point(40, 50), Arrays.asList(40,40));
        Screen screen = new Screen();
        
        screen.addShape(circle);
        screen.addShape(rectangle1);
        screen.addShape(rectangle2);
        screen.addShape(rectangle3);
        
        // screen.delete(rectangle2);
        // screen.displaySorted(SortCriteria.AREA);


        screen.deleteAll(ShapeType.RECTANGLE);
        
        screen.display();
    }
}