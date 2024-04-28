import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.janelas.Janela;
import com.github.britooo.looca.api.group.janelas.JanelaGrupo;
import com.github.britooo.looca.api.group.processos.Processo;
import conexao.Conexao;
import maquina.Conversor;
import maquina.Limite;
import maquina.Maquina;
import maquina.Registro;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import usuario.Usuario;

import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class Teste {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        System.out.println("""
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@.===.@@@@@@@.=====-@@========== @-=========.@@@@@@@@@-===@@@@@@@@====== @====:@@@@@:====@@==========-@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@:=== @@@@@@@:======@@========== @==========.@@@@@@@@@====@@@@@@@@====== @=====@@@@@ ====@@===========@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@:=== @@@@@@@:======@@========== @==========.@@@@@@@@@====@@@@@@@@====== @=====- @@@ ====@@===========@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@:===.@@@@@@@:=====-@@====------ @====------.@@@@@@@@@====@@@@@@@@======@@=======@@@ ====@@====-------@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@:=== @@@@@@@@-====@@@====@@@@@@@@==== @@@@@@@@@@@@@@@====@@@@@@@@@====@@@=======:@@ ====@@====-@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@:=== @@@@@@@@-====@@@====@@@@@@@@====.@@@@@@@@@@@@@@@====@@@@@@@@@====@@@======== @ ====@@====-@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@:===.@@@@@@@@-====@@@=========-@@==========@@@@@@@@@@====@@@@@@@@@====@@@========-@ ====@@==========@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@:===.@@@@@@@@-====@@@==========@@==========@@@@@@@@@@====@@@@@@@@@====@@@====:====- ====@@==========@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@:===.@@@@@@@@-====@@@==========@@==========@@@@@@@@@@====@@@@@@@@@====@@@====:-==== ====@@==========@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@:===.@@@@@@@@-====@@@=========-@@=========-@@@@@@@@@@====@@@@@@@@@====@@@====:@-========@@=========-@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@:===.@@@@@@@@-====@@@====@@@@@@@@==== @@@@@@@@@@@@@@@====@@@@@@@@@====@@@====-@@========@@====-@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@:===.@@@@@@@@-====@@@====@@@@@@@@==== @@@@@@@@@@@@@@@====@@@@@@@@@====@@@====:@@-=======@@====-@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@:===-=====-@ -===- @@====@@@@@@@@==========.@@@@@@@@@==========@@.===--@@====:@@@:======@@====-=====-@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@:=========-@.=====-@@====@@@@@@@@==========.@@@@@@@@@==========@@====== @====:@@@@======@@===========@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@:=========-@:======@@====@@@@@@@@==========.@@@@@@@@@==========@@====== @====:@@@@ =====@@===========@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@.=========-@:======@@====@@@@@@@@==========.@@@@@@@@@==========@@======-@====:@@@@@-====@@===========@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@ ..........@:......@@:...@@@@@@@@.......... @@@@@@@@@..........@@......@@ ... @@@@@@....@@:..........@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                --------------------------------------------------
                    Bem vindo(a) a Life Line <3
                --------------------------------------------------
                """);


        Integer numeroEscolhido;
        do{

            System.out.println("""
                    *------------------------------------*
                    | Digite o que deseja realizar!      |
                    |                                    |
                    | 0 - Encerrar App                   |
                    | 1 - Login                          |
                    | 2 - Cadastro                       |
                    | 3 - Visualizar dados sem login     |
                    *------------------------------------* 
                    """);
            numeroEscolhido = leitor.nextInt();

            switch(numeroEscolhido) {
                case 1:
                    Boolean usuarioLogado = false;
                    Usuario usuario = login();

                    do {
                        if (usuario.getIdUsuario() != null) {
                            usuarioLogado = true;
                        } else {
                            usuario = login();
                        }
                    } while (!usuarioLogado); // Login bem sucedido

                    maquina(usuario); // Pos login
                    break;
                case 2:
                    cadastro();
                    break;
                case 3:
                    maquinaAtual();
                    break;
                default:
                    System.out.println("Valor inválido!");
            }
        }while(numeroEscolhido!=0);

    }


    private static Usuario login() {
        Scanner leitor = new Scanner(System.in);
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
                |Digite a sua senha:                 |
                *------------------------------------*
                """
        );
        String senha = leitor.next();
        return new Usuario(email, senha);
    }
    private static void cadastro(){
        Scanner leitor = new Scanner(System.in);
        String nome;
        String email;
        String senha;
        String endereco;
        String telefone;
        String cargo = "";
        String cpf;
        System.out.printf("""
                *------------------------------------*
                |      Cadastro - Life Line          |
                *------------------------------------*
                |Digite o seu nome:                  |
                *------------------------------------*
                """);
        nome = leitor.nextLine();
        System.out.printf("""
                *------------------------------------*
                |Digite seu email:                   |
                *------------------------------------*
                """
        );
        email = leitor.nextLine();
        System.out.printf("""
                *------------------------------------*
                |Digite a sua senha:                 |
                *------------------------------------*
                """
        );
        senha = leitor.nextLine();
        System.out.printf("""
                *------------------------------------*
                |Digite seu endereco:                |
                *------------------------------------*
                """
        );
        endereco = leitor.nextLine();
        System.out.printf("""
                *------------------------------------*
                |Digite seu telefone:                |
                *------------------------------------*
                """
        );
        telefone = leitor.nextLine();
        System.out.printf("""
                *------------------------------------*
                |Digite seu CPF:                     |
                *------------------------------------*
                """
        );
        cpf = leitor.nextLine();
        System.out.printf("""
                *------------------------------------*
                |Qual seu cargo:                     |
                *------------------------------------*
                |1 - Profissional da Saúde:          |
                |2 - Profissional de TI:             |
                *------------------------------------*
                """
        );
        int opcao = leitor.nextInt();
        if (opcao == 1){
            cargo = "Saúde";
        }else if (opcao == 2){
            cargo = "TI";
        }

        Usuario user = new Usuario();
        user.cadastro(nome,email,senha, endereco,telefone,cargo,cpf);
        System.out.println("Cadastro realizado");
    };
    private static void maquina(Usuario usuario) {
        Maquina maquinaUsuario = new Maquina();

        if (!maquinaUsuario.verificarMaquina(usuario.getIdUsuario())) { // Caso maquina não foi identificada
            maquinaUsuario.cadastrarNomeMaquina(usuario.getIdUsuario());
            maquinaUsuario.cadastrarMaquina(usuario.getIdUsuario());
        }
        Limite limite = new Limite(maquinaUsuario.getIdMaquina());
        while (true) {
            Registro registro = new Registro();
            registro.inserirRegistros(maquinaUsuario.getIdMaquina(), limite);
        }
    };

    private static void maquinaAtual (){
        Looca looca = new Looca();
        Timer coletaDados = new Timer();

        coletaDados.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Double consumoDisco = Conversor.converterDoubleDoisDecimais(
                        Conversor.formatarBytes(
                                looca.getGrupoDeDiscos().getVolumes().get(0).getTotal() -
                                        looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel()
                        )
                );

                System.out.println("""
                    *------------------------------------*
                    |           Dados Coletados          |
                    *------------------------------------*
                    |Nome máquina: %s                    
                    |Tempo de atividade: %s              
                    |Consumo da CPU: %.2f                
                    |Consumo da RAM: %.2f                
                    |Consumo da Disco: %.2f              
                    *------------------------------------*
                    """.formatted(
                        looca.getRede().getParametros().getHostName(),
                        formatarTempo(looca.getSistema().getTempoDeAtividade()),
                        looca.getProcessador().getUso(),
                        Conversor.converterDoubleDoisDecimais(
                                Conversor.formatarBytes(looca.getMemoria().getEmUso())
                        ),
                        consumoDisco
                ));
            }
        }, 0, 5000); // Agendado para iniciar imediatamente e repetir a cada 5000 ms (5 segundos)
    }

    public static String formatarTempo(long segundosTotal) {
        long segundos = segundosTotal % 60;
        long minutosTotal = segundosTotal / 60;
        long minutos = minutosTotal % 60;
        long horasTotal = minutosTotal / 60;
        long horas = horasTotal % 24;
        long dias = horasTotal / 24;

        return String.format("%d dias, %d h, %d m e %d s", dias, horas, minutos, segundos);
    }
}



