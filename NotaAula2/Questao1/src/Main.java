import java.util.Scanner;

class Conta {
    String nomeTitular;
    double saldoConta;
    int cpfTitular;

    public Conta(String nomeTitular, int saldoConta, int cpfTitular) {
        this.nomeTitular = nomeTitular;
        this.saldoConta = saldoConta;
        this.cpfTitular = cpfTitular;
    }

    public void chequeEspecial() {
        this.saldoConta = 1000;
    }

    public void depositar(double valor) {
        saldoConta += valor;
        System.out.println("Depositado " + valor);
    }

    public void sacar(double valor) {
        if (saldoConta >= valor) {
            saldoConta -= valor;
            System.out.println("Saque de " + valor + " realizado");
        } else {
            System.out.println("Saldo insuficiente para o saque!");
        }
    }

    public void exibirDadosConta() {
        System.out.println("Nome do titular da conta: " + nomeTitular);
        System.out.println("CPF do titular da conta:" + cpfTitular);
        System.out.println("Saldo dísponivel na conta: " + saldoConta);

    }


}

class ContaCorrente extends Conta {
    private double chequeEspecial = 1000;

    public ContaCorrente(String nomeTitular, int saldoConta, int cpfTitular) {
        super(nomeTitular, saldoConta, cpfTitular);
    }

    public void usarChequeEspecial() {
        System.out.println("Usado cheque especial de valor: R$" + chequeEspecial);
        saldoConta += chequeEspecial;
        System.out.println("Saldo da conta atual: " + saldoConta);
    }
}

class ContaPoupanca extends Conta {
    private double selic;

    public ContaPoupanca(String nomeTitular, int saldoConta, int cpfTitular, double selic) {
        super(nomeTitular, saldoConta, cpfTitular);
        this.selic = selic;
    }

    public void calcularRendimento() {
        double rendimento;

        if (selic > 8.5) {
            rendimento = 0.005 * saldoConta;
        } else {
            rendimento = 0.007 * selic * saldoConta;
        }
        saldoConta += rendimento;
        System.out.println("Rendimento calculado: R$" + rendimento);
        System.out.println("Saldo atual com rendimento: R$" + saldoConta);
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o nome do titular");
        String nomeTitular = sc.nextLine();
        System.out.println("Digite o CPF do titular");
        int cpfTitular = sc.nextInt();

        System.out.println("Selecione o tipo de conta");
        System.out.println("1 - Conta Poupança");
        System.out.println("2 - Conta Corrente");
        int opcaoConta = sc.nextInt();

        Conta conta = null;

        if (opcaoConta == 1) {
            conta = new ContaCorrente(nomeTitular, 0, cpfTitular);
        } else if (opcaoConta == 2) {
            System.out.println("Digite a taxa selic");
            double selic = sc.nextDouble();
            conta = new ContaPoupanca(nomeTitular, 0, cpfTitular, selic);
        }
        int opcao;
        do {
            System.out.println("O que você deseja fazer?");
            System.out.println("1 - Depositar");
            System.out.println("2 - Sacar");
            if (conta instanceof ContaCorrente) {
                System.out.println("3 - Usar cheque especial");
            } else if (conta instanceof ContaPoupanca) {
                System.out.println("3 - Calcular taxa de rendimento");
            }
            System.out.println("4 - Exibir dados da conta");
            System.out.println("5 - Sair");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o valor para depósito: ");
                    double deposito = sc.nextDouble();
                    conta.depositar(deposito);
                    break;
                case 2:
                    System.out.println("Digite o valor para saque: ");
                    double saque = sc.nextDouble();
                    conta.sacar(saque);
                    break;
                case 3:
                    if (conta instanceof ContaCorrente) {
                        ((ContaCorrente) conta).usarChequeEspecial();
                    } else if (conta instanceof ContaPoupanca) {
                        ((ContaPoupanca) conta).calcularRendimento();
                    }
                    break;
                case 4:
                    conta.exibirDadosConta();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcão escolhida inexistente");
            }
        } while (opcao != 5);
        sc.close();


    }
}