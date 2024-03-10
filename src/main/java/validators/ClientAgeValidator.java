package validators;

import model.Client;

/**
 * The ClientAgeValidator class is responsible for validating the age of a client.
 */
public class ClientAgeValidator {

    private static final int MIN_AGE = 7;
    private static final int MAX_AGE = 30;

    public void validate(Client t) {

        if (t.isAge() < MIN_AGE || t.isAge() > MAX_AGE) {
            throw new IllegalArgumentException("The Student Age limit is not respected!");
        }

    }

}
