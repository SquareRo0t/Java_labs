package labb3.modell;

public class Gång {

	// TODO: Lägg till tillståndsvariabler för att hålla parametrarna till
	// konstruktorn.
	
	private Rum från;
	private Väderstreck riktningUtUrFrån;
	private Rum till;
	private Väderstreck riktningInITill;

	public Gång(Rum från, Väderstreck riktningUtUrFrån, Rum till,
			Väderstreck riktningInITill) {
		
		// TODO: Tilldela tillståndsvariablerna parametervärdena.
		this.från = från;
		this.riktningUtUrFrån = riktningUtUrFrån;
		this.till = till;
		this.riktningInITill = riktningInITill;
	}

	// TODO: Lägg till instansmetoder som returnerar tillståndsvariablernas
	// värden.
	public Rum från() {
		return från;
	}
	
	public Väderstreck riktningUtUrFrån() {
		return riktningUtUrFrån;
	}
	
	public Rum till() {
		return till;
	}
	
	public Väderstreck riktningInITill() {
		return riktningInITill;
	}
}
