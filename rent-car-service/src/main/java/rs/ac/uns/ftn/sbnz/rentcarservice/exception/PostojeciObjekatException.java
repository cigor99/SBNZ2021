package rs.ac.uns.ftn.sbnz.rentcarservice.exception;

public class PostojeciObjekatException extends Exception {

	private static final long serialVersionUID = 1L;

	public PostojeciObjekatException(String type, String fieldName) {
		super(String.format("%s sa imenom %s vec postoji.", type, fieldName));
	}
}