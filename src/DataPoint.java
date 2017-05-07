import java.time.LocalDate;

interface DataPoint {    // Interfact is abstract class except it can only have abstract methods (everything is abstract, nothing has body)
    LocalDate getDate();    // Don't need type of class b/c interface makes every method inherently abstract
}