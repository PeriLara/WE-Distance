public class LengthVectorException extends Exception{

	/** lance une exception si les vecteurs n'ont pas la même longueur */
	public LengthVectorException(String message){
		super(message);
	}
}
