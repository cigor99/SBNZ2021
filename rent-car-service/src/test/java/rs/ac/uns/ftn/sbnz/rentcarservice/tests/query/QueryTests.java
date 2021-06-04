package rs.ac.uns.ftn.sbnz.rentcarservice.tests.query;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class QueryTests {

    private KieSession kieSession;
    ArrayList<Auto> automobili;

    @Before
    public void setup(){
        Auto auto = new Auto(new Marka("tesla"), "model s", 2019, Karoserija.LIMUNZINA, TipGoriva.ELEKTRICNI, 4.5, 2.1, 1.3, 5,
                500, 0, 600, 3.5, 250, 45);

        Auto auto1 = new Auto(new Marka("ferskoda"), "oktavia", 2018, Karoserija.KARAVAN, TipGoriva.DIZEL, 6.5, 2.1, 1.3, 5,
                500, 600, 0, 3.5, 220, 15);

        Auto auto2 = new Auto(new Marka("smart"), "two", 2018, Karoserija.KUPE, TipGoriva.ELEKTRICNI, 3, 2.1, 1.3, 2,
                40, 300, 0, 3.5, 220, 15);

        Auto auto3 = new Auto(new Marka("ferarri"), "488 pista", 2018, Karoserija.KUPE, TipGoriva.BENZIN, 3, 2.1, 1.3, 2,
                40, 300, 0, 2.8, 340, 15);

        automobili = new ArrayList<Auto>();
        automobili.add(auto);
        automobili.add(auto1);
        automobili.add(auto2);
        automobili.add(auto3);

        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks
                .newKieContainer(ks.newReleaseId("rs.ac.uns.ftn.sbnz", "rent-car-kjar", "1.0.0-SNAPSHOT"));
        kieSession = kieContainer.newKieSession("rulesSession");
    }

    @Test
    public void Pretraga_auta_ime(){

        for(Auto a: this.automobili){
            kieSession.insert(a);
        }

        QueryResults results = kieSession.getQueryResults("pretrazi auto po imenu", "fer");

        assertEquals(2, results.size());
    }
}
