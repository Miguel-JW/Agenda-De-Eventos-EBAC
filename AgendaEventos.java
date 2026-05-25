import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

// ── Enum DiaDaSemana ───────────────────────────────────────
enum DiaDaSemana {
    SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO, DOMINGO
}

// ── Classe Evento ──────────────────────────────────────────
class Evento {
    private String        nome;
    private LocalDateTime dataHora;
    private DiaDaSemana   diaDaSemana;

    // Formatador padrão para exibição
    private static final DateTimeFormatter FORMATO =
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // ── Construtor ─────────────────────────────────────────
    public Evento(String nome, LocalDateTime dataHora, DiaDaSemana diaDaSemana) {
        this.nome        = nome;
        this.dataHora    = dataHora;
        this.diaDaSemana = diaDaSemana;
    }

    // ── Getters ────────────────────────────────────────────
    public String        getNome()        { return nome; }
    public LocalDateTime getDataHora()    { return dataHora; }
    public DiaDaSemana   getDiaDaSemana() { return diaDaSemana; }

    // ── Adiciona dias ao evento ────────────────────────────
    public void adicionarDias(int dias) {
        this.dataHora = dataHora.plusDays(dias);
    }

    // ── Exibe detalhes do evento ───────────────────────────
    public void exibirEvento() {
        System.out.println("  Nome:        " + nome);
        System.out.println("  Data e Hora: " + dataHora.format(FORMATO));
        System.out.println("  Dia:         " + diaDaSemana);
    }

    // ── Converte e exibe fusos horários ────────────────────
    public void exibirFusos() {
        ZoneId zoneSP  = ZoneId.of("America/Sao_Paulo");
        ZoneId zoneGMT = ZoneId.of("GMT");

        ZonedDateTime horaSP  = dataHora.atZone(zoneSP);
        ZonedDateTime horaGMT = horaSP.withZoneSameInstant(zoneGMT);

        DateTimeFormatter formatoFuso =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm z");

        System.out.println("  🇧🇷 São Paulo (BRT): " + horaSP.format(formatoFuso));
        System.out.println("  🌍 GMT:              " + horaGMT.format(formatoFuso));
    }
}

// ── Classe principal ───────────────────────────────────────
public class AgendaEventos {
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("       📅  Agenda de Eventos           ");
        System.out.println("╚══════════════════════════════════════╝");

        // Cria evento com a data atual
        LocalDateTime agora = LocalDateTime.now();
        Evento evento = new Evento("Workshop de Java", agora, DiaDaSemana.SEGUNDA);

        // ── Exibe evento original ──────────────────────────
        System.out.println("\n── Evento Original ───────────────────");
        evento.exibirEvento();

        // ── Adiciona 5 dias ────────────────────────────────
        evento.adicionarDias(5);
        System.out.println("\n── Após adicionar 5 dias ─────────────");
        evento.exibirEvento();

        // ── Exibe fusos horários ───────────────────────────
        System.out.println("\n── Fusos Horários ────────────────────");
        evento.exibirFusos();

        System.out.println("\n╚══════════════════════════════════════╝");
    }
}
