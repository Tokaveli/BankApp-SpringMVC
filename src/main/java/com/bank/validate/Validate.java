package com.bank.validate;

/*
 * Przyk�ad wzorca projektowego Singleton
 * Jest to wersja bezpieczna w �rodowisku wielow�tkowym
 * �adowanie klasy Singleton jest synchronizowane, wi�c nie ma mo�liwo�ci stworzenia dw�ch instacji
 */
public class Validate {
	private Validate() {}
	
	private static class Singleton {
		private final static Validate instance = new Validate();
	}
	
	public static Validate getInstance() {
		return Singleton.instance;
	}
	
	public int maxTextLength = 100;
	
	public boolean isText(String string) {
		if (string == null) {
			return false;
		}
		
		String text = string.trim();

		return !text.isEmpty() && text.length() <= maxTextLength;
	}
	
	public boolean isDate(String string) {
		return string.matches("\\d{4}-\\d{2}-\\d{2}");
	}

	public boolean isNumber(String string) {
		return string.matches("^[0-9]+$");
	}
	
	public String errorText(String fieldname) {
		return "Pole " + fieldname + " nie mo�e by� puste oraz nie mo�e mie� wi�cej ni� "
			+ maxTextLength + " znak�w";
	}
	
	public String errorDate(String fieldname) {
		return "Pole " + fieldname + " musi by� dat� (format RRRR-MM-DD, gdzie R oznacza rok, M miesi�c, a D dzie�)";
	}
	
	public String errorNumber(String fieldname) {
		return "Pole " + fieldname + " nie mo�e zawiera� liter";
	}
	
	public String errorNotNumber(String fieldname) {
		return "Pole " + fieldname + " nie mo�e zawiera� cyfr";
	}
	
	public String capitalizeFirst(String string) {
		if (string.length() > 0) {
			string = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
		}
		return string;
	}
}