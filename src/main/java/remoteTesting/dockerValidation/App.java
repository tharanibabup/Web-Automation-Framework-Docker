package remoteTesting.dockerValidation;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        String os = System.getProperty("os.name");
        System.out.println("Using System Property: " + os);

        if (os.contains("Mac")){
            System.out.println("It is Mac OS");
        } else if (os.contains("Window")){
            System.out.println("It is Window OS");
        }else{
            System.out.println("It is unknown OS");
        }
    }
}
