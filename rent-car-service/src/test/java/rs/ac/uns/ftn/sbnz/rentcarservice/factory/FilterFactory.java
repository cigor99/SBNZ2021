package rs.ac.uns.ftn.sbnz.rentcarservice.factory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rs.ac.uns.ftn.sbnz.rentcarservice.dto.*;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.*;

@Configuration
public class FilterFactory {
    
    @Bean
    public KorisnickiUnosDto napraviEkoloskiKorisnickiUnos(){
        KorisnickiUnosDto unos = new KorisnickiUnosDto();
		unos.setEkoloskaVoznja(true);
		unos.setBudzet("VISOK");
		Set<String> dodatnaOprema = new HashSet<>();
		unos.setDodatnaOprema(dodatnaOprema);

		Set<String> dodaciZaUdobnost = new HashSet<String>();
		// dodaciZaUdobnost.add("drzaci za case");
		unos.setDodaciZaUdobnost(dodaciZaUdobnost);

        return unos;
    }

    @Bean
    public KorisnickiUnosDto napraviDodatnaOpremaUnos(){
        KorisnickiUnosDto unos = new KorisnickiUnosDto();
		unos.setBudzet("VISOK");
		Set<String> dodatnaOprema = new HashSet<>();
		dodatnaOprema.add("grejaci sedista");
		dodatnaOprema.add("automatski menjac");
		unos.setDodatnaOprema(dodatnaOprema);

		Set<String> dodaciZaUdobnost = new HashSet<String>();
		// dodaciZaUdobnost.add("drzaci za case");
		unos.setDodaciZaUdobnost(dodaciZaUdobnost);

        return unos;
    }

    public KorisnickiUnosDto napraviOpremaZaUdobnost(){
        KorisnickiUnosDto unos = new KorisnickiUnosDto();
		unos.setBudzet("VISOK");
		Set<String> dodatnaOprema = new HashSet<>();
		unos.setDodatnaOprema(dodatnaOprema);

		Set<String> dodaciZaUdobnost = new HashSet<String>();
		dodaciZaUdobnost.add("drzaci za case");
		unos.setDodaciZaUdobnost(dodaciZaUdobnost);

        return unos;
    }

    public KorisnickiUnosDto korisnickiUnosPutniciIBudzet(){
        KorisnickiUnosDto unos = new KorisnickiUnosDto();
		unos.setBudzet("VISOK");
        unos.setBrojPutnika(4);
        unos.setBudzet("SREDNJI");
		Set<String> dodatnaOprema = new HashSet<>();
		unos.setDodatnaOprema(dodatnaOprema);

		Set<String> dodaciZaUdobnost = new HashSet<String>();
		unos.setDodaciZaUdobnost(dodaciZaUdobnost);

        return unos;
    }

    public KorisnickiUnosDto korisnickiUnosSvrha(String svrha){
        KorisnickiUnosDto unos = new KorisnickiUnosDto();
        unos.setSvrha(svrha);
		unos.setBudzet("VISOK");
        unos.setBrojPutnika(4);
        unos.setBudzet("SREDNJI");
		Set<String> dodatnaOprema = new HashSet<>();
		unos.setDodatnaOprema(dodatnaOprema);

		Set<String> dodaciZaUdobnost = new HashSet<String>();
		unos.setDodaciZaUdobnost(dodaciZaUdobnost);

        return unos;
    }
    

    public List<Auto> getAllAutomobili(){
        Auto auto = new Auto(new Marka("tesla"), "model s", 2019, Karoserija.LIMUNZINA, TipGoriva.ELEKTRICNI, 4.5, 2.1, 1.3, 5,
                500, 0, 600, 3.5, 250, 45);

        Auto auto1 = new Auto(new Marka("skoda"), "oktavia", 2018, Karoserija.KARAVAN, TipGoriva.DIZEL, 6.5, 2.1, 1.3, 5,
                500, 600, 0, 3.5, 220, 15);

        Auto auto2 = new Auto(new Marka("smart"), "two", 2018, Karoserija.KUPE, TipGoriva.ELEKTRICNI, 3, 2.1, 1.3, 2,
                40, 300, 0, 3.5, 220, 15);

        Auto auto3 = new Auto(new Marka("ferarri"), "488 pista", 2018, Karoserija.KUPE, TipGoriva.BENZIN, 3, 2.1, 1.3, 2,
            40, 300, 0, 2.8, 340, 15);

        HashSet<DodatnaOprema> dodatnaOprema = new HashSet<>();
        dodatnaOprema.add(new DodatnaOprema("grejaci sedista"));
        dodatnaOprema.add(new DodatnaOprema("automatski menjac"));
        HashSet<DodatakZaUdobnost> udobnost = new HashSet<>();
        udobnost.add(new DodatakZaUdobnost("drzaci za case"));

        HashSet<DodatnaOprema> dodatnaOprema2 = new HashSet<>();
        dodatnaOprema2.add(new DodatnaOprema("grejaci sedista"));
        HashSet<DodatakZaUdobnost> udobnost2 = new HashSet<>();
        udobnost2.add(new DodatakZaUdobnost("drzaci za case"));

        auto.setDodatnaOprema(dodatnaOprema);
        auto.setDodaciZaUdobnost(udobnost);

        auto1.setDodaciZaUdobnost(udobnost2);
        auto1.setDodatnaOprema(dodatnaOprema2);

        auto2.setDodatnaOprema(dodatnaOprema);
        auto2.setDodaciZaUdobnost(udobnost);

        dodatnaOprema.add(new DodatnaOprema("sportska sedista"));
        auto3.setDodatnaOprema(dodatnaOprema);

        ArrayList<Auto> automobili = new ArrayList<Auto>();
        automobili.add(auto);
        automobili.add(auto1);
        automobili.add(auto2);
        automobili.add(auto3);
        return automobili;
    }

}
