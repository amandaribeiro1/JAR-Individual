import maquina.Maquina;
import maquina.Registro;
import usuario.Usuario;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Integer opcao;
        do {
            System.out.printf("""
                    *------------------------------------*
                    | Olá, Seja bem-vindo(a) a Life Line!|
                    *------------------------------------*
                    | Digite o que deseja realizar!      |
                    |                                    |
                    | 0 - Encerrar App                   |
                    | 1 - Login                          |
                    *------------------------------------* 
                    """);
            opcao = leitor.nextInt();

            if (opcao == 0) {
                System.out.printf("Encerrando...");
            } else if (opcao == 1) {
            System.out.printf("""
                    *------------------------------------*
                    |        Login - Life Line           |
                    *------------------------------------*
                    |Digite o seu email:                 |
                    *------------------------------------*
                    """
            );
            String email = leitor.next();
                System.out.printf("""
                    *------------------------------------*
                    |Digite o sua senha:                 |
                    *------------------------------------*
                    """
                );
            String senha = leitor.next();

            Usuario usuario = new Usuario(email, senha);
            Maquina maquinaUsuario = new Maquina();
            Scanner sc2 = new Scanner(System.in);

            if (!maquinaUsuario.verificarMaquina(usuario.getIdUsuario())) {
                System.out.printf(
                    """
                    *------------------------------------*
                    |Digite o nome para a maquina:       |
                    *------------------------------------*
                     """
                );
                String nomeMaquina = sc2.nextLine();

                if (nomeMaquina.length() > 1) {
                    maquinaUsuario.cadastrarMaquina(nomeMaquina, usuario.getIdUsuario());
                }
            } else {
                while (true) {
                    Registro registro = new Registro();
                    registro.inserirRegistros(usuario.getIdUsuario(), maquinaUsuario.getMacAddress());
                }
            }
            } else {
                System.out.println("Digite uma opção válida!");
            }
        } while (opcao != 1 && opcao != 0);
    }
}