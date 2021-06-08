package rs.ac.uns.ftn.sbnz.rentcarservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.rentcarservice.exception.NepostojeciObjekatException;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Auto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Korisnik;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.Rezervacija;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.StatusRezervacije;
import rs.ac.uns.ftn.sbnz.rentcarservice.repository.RezervacijaRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.util.List;

@Service
public class RezervacijaService {

    @Autowired
    private RezervacijaRepository rezervacijaRepository;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private AutoService autoService;

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    Environment env;

    List<Rezervacija> findAllByKorisnikId(Integer id){
        return  rezervacijaRepository.findAllByKorisnikId(id);
    }

    public Rezervacija rezervisiAuto(Rezervacija rezervacija) {
        return rezervacijaRepository.save(rezervacija);
    }

    public Rezervacija findById(Integer rezervacijaId) {
        return this.rezervacijaRepository.findById(rezervacijaId).orElse(null);
    }

    public void odbijRezervaciju(Integer rezervacijaId) throws NepostojeciObjekatException, MessagingException {
        Rezervacija rezervacija = rezervacijaRepository.findById(rezervacijaId).orElse(null);
        if(rezervacija == null)
            throw new NepostojeciObjekatException("rezervacija", rezervacijaId.toString());
        rezervacija.setStatus(StatusRezervacije.ODBIJENA);
        Rezervacija odbijena = rezervacijaRepository.save(rezervacija);

        mailSender.setUsername(env.getProperty("spring.mail.username"));
        mailSender.setPassword(env.getProperty("spring.mail.password"));

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper email = new MimeMessageHelper(mimeMessage, "utf-8");
        email.setTo(rezervacija.getKorisnik().getEmail());
        email.setSubject("Odobrena rezervacija");

        String message = String.format("Vaša rezervacija za auto marke: {}, model: {} je odobrena", odbijena.getAuto().getMarka().getNaziv(), odbijena.getAuto().getModel());
        email.setText(message);
        mailSender.send(mimeMessage);
    }

    public void odobriRezervaciju(Integer rezervacijaId) throws NepostojeciObjekatException, MessagingException {
        Rezervacija rezervacija = rezervacijaRepository.findById(rezervacijaId).orElse(null);
        if(rezervacija == null)
            throw new NepostojeciObjekatException("rezervacija", rezervacijaId.toString());
        rezervacija.setStatus(StatusRezervacije.PRIHVACENA);
        Rezervacija odobrena = rezervacijaRepository.save(rezervacija);

        mailSender.setUsername(env.getProperty("spring.mail.username"));
        mailSender.setPassword(env.getProperty("spring.mail.password"));

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper email = new MimeMessageHelper(mimeMessage, "utf-8");
        email.setTo(rezervacija.getKorisnik().getEmail());
        email.setSubject("Odobrena rezervacija");

        String message = String.format("Vaša rezervacija za auto marke: {}, model: {} je odobrena", odobrena.getAuto().getMarka().getNaziv(), odobrena.getAuto().getModel());
        email.setText(message);
        mailSender.send(mimeMessage);
    }
}
