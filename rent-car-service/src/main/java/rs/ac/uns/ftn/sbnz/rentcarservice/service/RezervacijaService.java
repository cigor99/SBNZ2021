package rs.ac.uns.ftn.sbnz.rentcarservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.rentcarservice.dto.RezervacijaDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.exception.NepostojeciObjekatException;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.*;
import rs.ac.uns.ftn.sbnz.rentcarservice.repository.RezervacijaRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RezervacijaService {

    @Autowired
    private RezervacijaRepository rezervacijaRepository;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private AutoService autoService;

//    @Autowired
//    private JavaMailSenderImpl mailSender;

    @Autowired
    private KnowledgeService knowledgeService;

    @Autowired
    private AdministratorService administratorService;


    @Autowired
    Environment env;

    List<Rezervacija> findAllByKorisnikId(Integer id){
        return  rezervacijaRepository.findAllByKorisnikId(id);
    }

    public Rezervacija rezervisiAuto(RezervacijaDto rezervacijaDto, Korisnik ulogovani) throws NepostojeciObjekatException, MessagingException {
        Korisnik korisnik = korisnikService.findOneByEmail(ulogovani.getEmail());
        Auto auto = autoService.findOneById(rezervacijaDto.getAutoId());
        if(auto == null)
            throw new NepostojeciObjekatException("auto", String.valueOf(rezervacijaDto.getAutoId()));

        long daniIzmedju = ChronoUnit.DAYS.between(rezervacijaDto.getPocetakRezervacije(), rezervacijaDto.getKrajRezervacije());
        int brojDana = Math.toIntExact(daniIzmedju);
        Rezervacija rezervacija = new Rezervacija(
                0,
                rezervacijaDto.getPocetakRezervacije(),
                rezervacijaDto.getKrajRezervacije(),
                StatusRezervacije.KREIRANA,
                brojDana,
                0,
                brojDana * auto.getCena(),
                auto,
                korisnik);

        knowledgeService.getRulesSession().insert(korisnik);
        knowledgeService.getRulesSession().getAgenda().getAgendaGroup("status").setFocus();
        knowledgeService.getRulesSession().fireAllRules();
        knowledgeService.releaseRulesSession();

        knowledgeService.getRulesSession().insert(korisnik);
        knowledgeService.getRulesSession().insert(rezervacija);
        knowledgeService.getRulesSession().getAgenda().getAgendaGroup("popusti").setFocus();
        knowledgeService.getRulesSession().fireAllRules();
        knowledgeService.releaseRulesSession();

        Rezervacija kreirana = rezervacijaRepository.save(rezervacija);

        RezervacijaEvent rezervacijaEvent = new RezervacijaEvent(rezervacija.getKorisnik().getEmail());
        knowledgeService.getEventsSession().insert(rezervacijaEvent);
        knowledgeService.getEventsSession().fireAllRules();

        return kreirana;
    }

    public Rezervacija findById(Integer rezervacijaId) {
        return this.rezervacijaRepository.findById(rezervacijaId).orElse(null);
    }

    public void odbijRezervaciju(Integer rezervacijaId, Administrator ulogovani) throws NepostojeciObjekatException, MessagingException {
        Rezervacija rezervacija = rezervacijaRepository.findById(rezervacijaId).orElse(null);
        if(rezervacija == null)
            throw new NepostojeciObjekatException("rezervacija", rezervacijaId.toString());
        rezervacija.setStatus(StatusRezervacije.ODBIJENA);

        Rezervacija odbijena = rezervacijaRepository.save(rezervacija);
    }

    public void odobriRezervaciju(Integer rezervacijaId, Administrator ulogovani) throws NepostojeciObjekatException, MessagingException {
        Administrator administrator = administratorService.findOneByEmail(ulogovani.getEmail());
        Rezervacija rezervacija = rezervacijaRepository.findById(rezervacijaId).orElse(null);
        if(rezervacija == null)
            throw new NepostojeciObjekatException("rezervacija", rezervacijaId.toString());
        rezervacija.setStatus(StatusRezervacije.PRIHVACENA);
        Rezervacija odobrena = rezervacijaRepository.save(rezervacija);
        administrator.getOdobreneRezervacije().add(odobrena);
        administratorService.updateAdministrator(administrator);

    }

    public List<Rezervacija> findAll() {
        return this.rezervacijaRepository.findAll();
    }
}
