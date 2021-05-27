package rs.ac.uns.ftn.sbnz.rentcarservice.model;

public enum Budzet {
    NISKI, SREDNJI, VISOK;

    public static Budzet StringToBudzet(String budzetString){
        switch (budzetString){
            case "NISKI":
                return Budzet.NISKI;
            case "SREDNJI":
                return Budzet.SREDNJI;
            case "VISOK":
                return Budzet.VISOK;
            default:
                return null;
        }
    }
}
