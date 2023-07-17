package database;

public class DbException extends RuntimeException {
    // Exceção personalizada
    public DbException(String message){
        super(message);
    }
}
