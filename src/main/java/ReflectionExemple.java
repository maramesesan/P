import java.lang.reflect.Field;

public class ReflectionExemple {
    /**
     * Retrieves and prints the properties of an object using reflection.
     * @param object The object whose properties need to be retrieved.
     */
    public static void retrieveProperties(Object object) {

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(object);
                System.out.println(field.getName() + "=" + value);

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }
}
