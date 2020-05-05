import model.Point;
import model.Vector;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    private static void readDataFromFiles(Vector vector, Point point, Serializer serializer) {
        try {
            File vectorfile = new File("vector.xml");
            File pointfile = new File("point.xml");
            if(vectorfile.exists())
                serializer.read(vector, vectorfile);
            else System.out.println("File not exists");
            if(pointfile.exists())
                serializer.read(point, pointfile);
            else System.out.println("File not exists");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void writeVector(Vector vector, Serializer serializer) {
        try {
            File vectorfile = new File("vector.xml");
            if(!vectorfile.exists())
                vectorfile.createNewFile();
            serializer.write(vector, vectorfile);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void writePoint(Point point, Serializer serializer) {
        try {
            File pointfile = new File("point.xml");
            if(!pointfile.exists())
                pointfile.createNewFile();
            serializer.write(point, pointfile);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void main(String [] args) {
        scanner.useLocale(Locale.US); // double delimiter is point "."

        Vector vector = new Vector();
        Point point = new Point();
        System.out.println("" +
                "Myakushka Lab 2 App." +
                "\nInput: material point, force vector, time" +
                "\nOutput: material point coordinates after the impact force vector \n");
        String command;
        // загружаем данные из файла
        Serializer serializer = new Persister();
        readDataFromFiles(vector, point, serializer);

        System.out.println("Menu\n" +
                "commands:\n" +
                "finish - close the programm\n" +
                "point - set material point coord\n" +
                "vector - set force vector\n" +
                "run - calculate result\n" +
                "print - print result\n" +
                "help - print this help info");
        do {
            System.out.println("Input command: ");
            command = scanner.nextLine();
            if(command.isEmpty()) command = scanner.nextLine();
            if(command.equals("vector")) {
                try {
                    Point endPoint = null;
                    double x, y;
                    System.out.println("Point 1: " + point.toString());
                    System.out.println("Point 2:\nx = ");
                    x = scanner.nextDouble();
                    System.out.println("y = ");
                    y = scanner.nextDouble();
                    endPoint = new Point(x, y);
                    System.out.println("Input force abs: ");
                    double force = scanner.nextDouble();
                    vector = new Vector(new Point(point), endPoint, force);
                    // запись в xml
                    writeVector(vector, serializer);
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
            else if(command.equals("point")) {
                try {
                    System.out.println("Material point:");
                    System.out.println("x: ");
                    double x = scanner.nextDouble();
                    System.out.println("y: ");
                    double y = scanner.nextDouble();
                    point = new Point(x, y);
                    // write point data into xml
                    writePoint(point, serializer);
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
            else if(command.equals("print")) {
                System.out.println(vector.toString() + "\n" + point.toString());
            }
            else if(command.equals("run")) {
                try {
                    System.out.println("Time:");
                    System.out.println("t: ");
                    double t = scanner.nextDouble();
                    point.movePoint(vector, t);
                    writePoint(point, serializer);
                    System.out.println("OK. Check result: ");
                    System.out.println(vector.toString() + "\n" + point.toString());
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
            else if(command.equals("help")) {
                System.out.println("Menu\n" +
                        "commands:\n" +
                        "finish - close the programm\n" +
                        "point - set material point coord\n" +
                        "vector - set force vector\n" +
                        "run - calculate result\n" +
                        "print - print result\n" +
                        "help - print this help info");
            }
            System.out.println("\n");
        } while(!command.equals("finish"));
        scanner.close();
    }
}
