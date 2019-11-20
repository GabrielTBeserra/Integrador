package br.unaerp.integrador.STATICS;

import java.util.InputMismatchException;

public class ValidadorCPF {
	public static boolean isCPF(String CPF) {
		CPF = removerMascara(CPF);
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11)) {
			return (false);
		}
		char digito10, digito11;
		int soma;
		int resultado;
		int numero;
		int peso;

		try {
			soma = 0;
			peso = 10;

			for (int i = 0; i < 9; i++) {
				numero = (int) (CPF.charAt(i) - 48);
				soma = soma + (numero * peso);
				peso = peso - 1;
			}

			resultado = 11 - (soma % 11);

			if ((resultado == 10) || (resultado == 11)) {
				digito10 = '0';
			} else {
				digito10 = (char) (resultado + 48);
			}

			soma = 0;
			peso = 11;

			for (int i = 0; i < 10; i++) {
				numero = (int) (CPF.charAt(i) - 48);
				soma = soma + (numero * peso);
				peso = peso - 1;
			}

			resultado = 11 - (soma % 11);

			if ((resultado == 10) || (resultado == 11)) {
				digito11 = '0';
			} else {
				digito11 = (char) (resultado + 48);
			}

			if ((digito10 == CPF.charAt(9)) && (digito11 == CPF.charAt(10))) {
				return (true);
			} else {
				return (false);
			}

		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	private static String removerMascara(String str) {
		try {
			return str.replaceAll("\\D", "");
		} catch (Exception e) {
			return str;
		}
	}
}
