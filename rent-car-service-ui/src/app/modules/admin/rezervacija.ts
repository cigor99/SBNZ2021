export interface Rezervacija {
	id: number;
	pocetakRezervacije: string;
	krajRezervacije: string;
	status: string;
	brojDana: number;
	iznos: number;
	autoId: number;
	autoMarka: string;
	autoModel: string;
	korisnikEmail: string;
}

export class RezervacijaCollection implements Iterator<Rezervacija> {
	private pointer = 0;

	constructor(public components: Rezervacija[]) {}

	public next(): IteratorResult<Rezervacija> {
		if (this.pointer < this.components.length) {
			return {
				done: false,
				value: this.components[this.pointer++],
			};
		} else {
			return {
				done: true,
				value: null,
			};
		}
	}
}
