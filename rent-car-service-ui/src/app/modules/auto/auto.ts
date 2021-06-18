export interface Auto{
  marka: string;
  model: string;
  godiste: number;
  karoserija: number;
  tipGoriva: number;
  duzina?: number,
  sirina?: number,
  visina?: number,
  brojSedista: number;
  zapreminaGepeka: number;
  zapreminaRezervoara: number;
  distanca?: number,
  ubrzanje: number,
  maksimalnaBrzina: number,
  cena: number,
  dodatnaOprema: string[],
  dodaciZaUdobnost: string[]
}

export interface AddAuto{
  marka: string;
  model: string;
  godiste: number;
  karoserija: number;
  tipGoriva: number;
  duzina?: number,
  sirina?: number,
  visina?: number,
  brojSedista: number;
  zapreminaGepeka: number;
  zapreminaRezervoara: number;
  distanca?: number;
  ubrzanje: number;
  maksimalnaBrzina: number;
  cena: number;
  dodatnaOprema: string[],
  dodaciZaUdobnost: string[]
}
