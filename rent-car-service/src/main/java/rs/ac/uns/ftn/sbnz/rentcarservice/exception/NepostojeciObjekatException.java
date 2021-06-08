package rs.ac.uns.ftn.sbnz.rentcarservice.exception;

public class NepostojeciObjekatException extends Exception {

    private static final long serialVersionUID = 1L;

    public NepostojeciObjekatException(String type, String fieldName) {
        super(String.format("%s sa id %s ne postoji.", type, fieldName));

    }
}
