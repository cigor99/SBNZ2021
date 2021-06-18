package rs.ac.uns.ftn.sbnz.rentcarservice.util;

import rs.ac.uns.ftn.sbnz.rentcarservice.dto.AutoDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AutoMapper {

    public AutoDto toDto(Auto auto) {
        Set<String> dodatnaOpremaString = new HashSet<>();
        Set<String> dodaciZaUdobnostString = new HashSet<>();
        for (DodatakZaUdobnost dzu : auto.getDodaciZaUdobnost())
            dodaciZaUdobnostString.add(dzu.getNaziv());
        for (DodatnaOprema dod : auto.getDodatnaOprema())
            dodatnaOpremaString.add(dod.getNaziv());
        return new AutoDto(
                auto.getId(),
                auto.getMarka().getNaziv(),
                auto.getModel(),
                auto.getGodiste(),
                auto.getKaroserija().name(),
                auto.getTipGoriva().name(),
                auto.getDuzina(),
                auto.getSirina(),
                auto.getVisina(),
                auto.getBrojSedista(),
                auto.getZapreminaGepeka(),
                auto.getZapreminaRezervoara(),
                auto.getDistanca(),
                auto.getUbrzanje(),
                auto.getMaksimalnaBrzina(),
                auto.getCena(),
                auto.getProsecnaOcena(),
                dodatnaOpremaString,
                dodaciZaUdobnostString,
                auto.getBodovi()
        );
    }

    public List<AutoDto> toDtoList(List<Auto> auta) {
        List<AutoDto> autoDtos = new ArrayList<>();
        for (Auto auto : auta)
            autoDtos.add(this.toDto(auto));
        return autoDtos;
    }

    public Auto toEntity(AutoDto autoDto) {
        Set<DodatakZaUdobnost> dodaciZaUdobnost = new HashSet<>();
        Set<DodatnaOprema> dodatnaOprema = new HashSet<>();
        for(String dzu: autoDto.getDodaciZaUdobnost())
            dodaciZaUdobnost.add(new DodatakZaUdobnost(dzu));
        for(String dod: autoDto.getDodatnaOprema())
            dodatnaOprema.add(new DodatnaOprema(dod));
        return new Auto(
                0,
                new Marka(autoDto.getMarka()),
                autoDto.getModel(),
                autoDto.getGodiste(),
                Karoserija.valueOf(autoDto.getKaroserija()),
                TipGoriva.valueOf(autoDto.getTipGoriva()),
                autoDto.getDuzina(),
                autoDto.getSirina(),
                autoDto.getVisina(),
                autoDto.getBrojSedista(),
                autoDto.getZapreminaGepeka(),
                autoDto.getZapreminaRezervoara(),
                autoDto.getDistanca(),
                autoDto.getUbrzanje(),
                autoDto.getMaksimalnaBrzina(),
                autoDto.getCena(),
                0,
                dodatnaOprema,
                dodaciZaUdobnost,
                new HashSet<>(),
                0
        );

    }
}