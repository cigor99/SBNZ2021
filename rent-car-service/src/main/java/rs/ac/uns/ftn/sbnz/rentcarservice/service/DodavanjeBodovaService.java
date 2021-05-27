package rs.ac.uns.ftn.sbnz.rentcarservice.service;

import org.apache.tomcat.util.threads.ResizableExecutor;

import rs.ac.uns.ftn.sbnz.rentcarservice.model.Auto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Budzet;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.ZahteviZaAuto;

public class DodavanjeBodovaService {
    
    public int racunajBodove(Auto auto, ZahteviZaAuto zahteviZaAuto){
        int suma = 0;

        suma += this.racunajCenu(auto, zahteviZaAuto);

        suma += this.racunajDodatnuOpremu(auto, zahteviZaAuto);

        suma += this.racunajDodatkeZaUdobnost(auto, zahteviZaAuto);

        return suma;
    }

    public int racunajCenu(Auto auto, ZahteviZaAuto zahteviZaAuto){
        int rez = 0;
        if(zahteviZaAuto.getBudzet().equals(Budzet.NISKI)){
            if(auto.getCena() <= 20)
                rez += 3;
            else if(auto.getCena()>20 && auto.getCena() < 60)
                rez += 1;
        }
        else if(zahteviZaAuto.getBudzet().equals(Budzet.SREDNJI)){
            if(auto.getCena() < 60 && auto.getCena() > 20)
                rez += 3;
            else if(auto.getCena() < 20)
                rez += 1;
        }
        else{
            if(auto.getCena() > 60)
                rez += 3;
            else if(auto.getCena() < 60 && auto.getCena() > 20)
                rez += 1;
        }
        return rez;
    }

    public int racunajDodatnuOpremu(Auto auto, ZahteviZaAuto zahteviZaAuto){
        int rez = 0;

        for(String o : zahteviZaAuto.getDodatneOpreme()){
            if(auto.getDodatnaOprema().contains(o))
                rez += 2;
        }
        return rez;
    }

    public int racunajDodatkeZaUdobnost(Auto auto, ZahteviZaAuto zahteviZaAuto){
        int rez = 0;

        for(String u : zahteviZaAuto.getDodaciZaUdobnost()){
            if(auto.getDodaciZaUdobnost().contains(u))
                rez += 2;
        }

        return rez;
    }
}
