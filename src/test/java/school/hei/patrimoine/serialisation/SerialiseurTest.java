package school.hei.patrimoine.serialisation;

import org.junit.jupiter.api.Test;
import school.hei.patrimoine.Patrimoine;
import school.hei.patrimoine.Personne;
import school.hei.patrimoine.possession.Argent;
import school.hei.patrimoine.possession.FluxArgent;

import java.time.Instant;
import java.util.Set;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SerialiseurTest {

  @Test
  void serialise_et_deserialise() {
    var ilo = new Personne("Ilo");
    var au13mai24 = Instant.parse("2024-05-13T00:00:00.00Z");
    var financeur = new Argent("Espèces", au13mai24, 600_000);
    var trainDeVie = new FluxArgent(
        "Vie courante",
        -100_000,
        au13mai24.minus(100, DAYS),
        au13mai24.plus(100, DAYS),
        financeur, 15);
    var patrimoineIloAu13mai24 = new Patrimoine(
        ilo,
        au13mai24,
        Set.of(financeur, trainDeVie));

    var serialiseur = new Serialiseur<Patrimoine>();
    var serialisé = serialiseur.serialise(patrimoineIloAu13mai24);

    assertEquals(patrimoineIloAu13mai24, serialiseur.deserialise(serialisé));
  }
}