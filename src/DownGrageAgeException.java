public class DownGrageAgeException extends RuntimeException {
    private String exceptionMessage;
    private Human causer;
    private int currentAge;
    private int newAge;

    public DownGrageAgeException(String exceptionMessage, Human causer, int currentAge, int newAge) {
        this.exceptionMessage = exceptionMessage;
        this.causer = causer;
        this.currentAge = currentAge;
        this.newAge = newAge;
    }

    public String getMessage () {
        return (exceptionMessage + "\n" +
                "Causer is: " + causer + "\n" +
                "Current age is: " + currentAge + "\n" +
                "New planning age is: " + newAge);
    }
}
