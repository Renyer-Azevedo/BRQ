package com.brq.santander.cadeiasocietaria.util;

public class Utils {
	
	public static Boolean valorSeguroBoolean(String valor) {
		
		return valor != null && !valor.isEmpty() && valor.equalsIgnoreCase("s");

	}
	
	public static String valorSeguroString(String valor) {
		return valor != null ? valor : "";
	}
	
	public static Integer valorSeguroInteger(Integer valor) {
		return valor != null ? valor : 0;
	}
	
	public static Double valorSeguroDouble(Double valor) {
		return valor != null ? valor : 0D;
	}
	
	public static Float valorSeguroFloat(Float valor) {
		return valor != null ? valor : 0F;
	}

}
