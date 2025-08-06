import java.time.LocalTime;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AV3NATDISCRETA {

    // Constantes do sistema
    private static final double TAXA_INICIAL = 10.00;
    private static final int TOLERANCIA_MINUTOS = 15;
    private static final int PERIODO_INICIAL_MINUTOS = 180;
    private static final int INTERVALO_COBRANCA_MINUTOS = 20;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==== SISTEMA DE ESTACIONAMENTO ====\n");

        // 1. Identificação do estado pela placa
        processarPlaca(scanner);

        // 2. Cálculo do estacionamento
        processarEstacionamento(scanner);

        // 3. Análise combinatória (automática)
        exibirAnaliseCombinatoria();

        scanner.close();
    }

    private static void processarPlaca(Scanner scanner) {
        while (true) {
            System.out.println("Digite a placa no padrão Mercosul (ex: ABC1D23):");
            String placa = scanner.nextLine().toUpperCase().trim();

            if (validarFormatoPlaca(placa)) {
                String estado = identificarEstado(placa);
                System.out.println("\nEstado identificado: " + estado);
                System.out.println("----------------------------------");
                break;
            } else {
                System.out.println("Formato inválido! Use o padrão LLLNLNN (3 letras, 1 número, 1 letra, 2 números)");
            }
        }
    }

    private static void processarEstacionamento(Scanner scanner) {
        LocalTime entrada = lerHorario(scanner, "ENTRADA (HH:MM)");
        LocalTime saida = lerHorario(scanner, "SAÍDA (HH:MM)");

        if (saida.isBefore(entrada)) {
            System.out.println("AVISO: A saída ocorreu no dia seguinte!");
        }

        double valor = calcularValorEstacionamento(entrada, saida);
        System.out.printf("\nValor total: R$ %.2f%n", valor);
        System.out.println("----------------------------------");
    }

    private static LocalTime lerHorario(Scanner scanner, String tipo) {
        while (true) {
            try {
                System.out.println("Horário de " + tipo + ":");
                return LocalTime.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido! Use HH:MM (ex: 08:30)");
            }
        }
    }

    public static String identificarEstado(String placa) {
        String prefixo = placa.substring(0, 3);

        // Paraíba
        if (prefixo.equals("MMN") || prefixo.equals("MOW") || prefixo.equals("NPR") ||
                prefixo.equals("NQK") || prefixo.equals("OET") || prefixo.equals("OFH") ||
                prefixo.equals("OFX") || prefixo.equals("OGG") || prefixo.equals("OXO") ||
                prefixo.equals("QFA") || prefixo.equals("QFZ") || prefixo.equals("QSA") ||
                prefixo.equals("QSM") || prefixo.equals("RLQ") || prefixo.equals("RMC")) {
            return "PARAÍBA";
        }
        // Rio de Janeiro
        else if (prefixo.equals("KMF") || prefixo.equals("LVE") || prefixo.equals("RIO") ||
                prefixo.equals("RIP") || prefixo.equals("RKV")) {
            return "RIO DE JANEIRO";
        }
        // Piauí
        else if (prefixo.equals("LVF") || prefixo.equals("LWQ") || prefixo.equals("ODU") ||
                prefixo.equals("NHU") || prefixo.equals("NIX") || prefixo.equals("OEI") ||
                prefixo.equals("OUA") || prefixo.equals("OUE") || prefixo.equals("OVW") ||
                prefixo.equals("OVY") || prefixo.equals("PIA") || prefixo.equals("PIZ") ||
                prefixo.equals("QRN") || prefixo.equals("QRZ") || prefixo.equals("RSG") ||
                prefixo.equals("RST")) {

            return "PIAUÍ";
        }

        return "OUTRO ESTADO (não é Paraíba, Rio de Janeiro ou Piauí)";
    }

    public static boolean validarFormatoPlaca(String placa) {
        return placa.matches("^[A-Z]{3}\\d[A-Z]\\d{2}$");
    }

    public static double calcularValorEstacionamento(LocalTime entrada, LocalTime saida) {
        long minutos = Duration.between(entrada, saida).toMinutes();

        if (minutos <= TOLERANCIA_MINUTOS) {
            return 0.0;
        }

        if (minutos <= PERIODO_INICIAL_MINUTOS) {
            return TAXA_INICIAL;
        }

        long minutosExcedentes = minutos - PERIODO_INICIAL_MINUTOS;
        long intervalos = (minutosExcedentes + INTERVALO_COBRANCA_MINUTOS - 1) / INTERVALO_COBRANCA_MINUTOS;

        double valorPorIntervalo = 2.0 + calcularM();
        System.out.printf("\nTaxa adicional: R$%.2f a cada 20 minutos após as 3 horas iniciais%n", valorPorIntervalo);
        return TAXA_INICIAL + (intervalos * valorPorIntervalo);
    }

    private static double calcularM() {
        int[] digitosMatriculas = {8, 1, 4, 4};

        int soma = 0;
        for (int digito : digitosMatriculas) {
            soma += digito;
        }
        double m = soma / 100.0;
        System.out.printf("\nValor de m calculado: %.2f (Soma dos dígitos %d, %d, %d, %d, dividido por 100)%n",
                m, digitosMatriculas[0], digitosMatriculas[1], digitosMatriculas[2], digitosMatriculas[3]);
        return m;
    }

    private static void exibirAnaliseCombinatoria() {
        int totalPorEstado = 26 * 10 * 26 * 100;

        System.out.println("\n==== ANÁLISE COMBINATÓRIA ====");
        System.out.println("Total de placas possíveis por estado (formato LLLNLNN): " + totalPorEstado);
        System.out.println("Total para os 3 estados: " + (totalPorEstado * 3));
        System.out.println("----------------------------------");
    }
}
