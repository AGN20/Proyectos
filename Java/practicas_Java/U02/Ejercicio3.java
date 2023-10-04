import java.util.Scanner; // importamos el Scanner
public class Ejercicio3 {
	
	public static void main (String[] args){
		Scanner teclado = new Scanner (System.in); //creamos el Scanner
		// creamos las variables
		string username;
		string password;
		string validaclave= "";
		// Pedimos al usuario que nos diga los datos que ocuparan estan variables
		System.out.print("User: ");
		username = teclado.nextLine();
		System.out.print("Password: ");
		password = teclado.nextLine();
		//Indicamos que si en username nos introducen admin (de cualquier manera) nos dara un mensaje de error
		boolean admin = (username.equalsIgnoreCase("admin"));
		validaclave = (admin == true)? "El usuario \"admin\" no se puede utilizar \n":"";
		System.out.println( validaclave );
		// Indicamos que si username inicia con un numero nos dara error
		boolean numero = username.matches("[0-9].*");
		validaclave = (numero == true)? "El nombre del usuario debe comenzar por letra \n":"";
		System.out.println( validaclave );
		// Indicamos que si password debe tener un minimo de 8 caracteres
		int Cantidad = password.length();
		validaclave = (Cantidad < 8)? "Password debe tener una minimo de 8 caracteres \n":"";
		System.out.println( validaclave );
		// Indicamos que password debe tener minimo una letra mayuscula
		string minusculas = (password.toLowerCase());
		validaclave = (minusculas == password)? "El password debe tener una letra mayuscula \n":"";
		System.out.println( validaclave );
		// Indicamos que password debe tener minimo una letra minuscula
		string mayusculas = (password.toUpperCase());
		validaclave = (mayusculas == password)? "El password debe tener una letra minuscula \n":"";
		System.out.println( validaclave );
		// Indicamos que password deve tener al menos uno de estos simbolos ., _, @  
		string punto = ".";
		string barra = "_";
		string arroba = "@";
		
		int caracteres = password.indexOf(punto) & password.indexOf(barra) & password.indexOf(arroba);
		validaclave = (caracteres == -1)? "El password debe tener al menos un simbolo \"._@\"":"";
		System.out.println( validaclave );
		
		
	}
}