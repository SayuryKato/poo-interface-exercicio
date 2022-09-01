package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Contract;
import entities.Installment;
import services.ContractService;
import services.PaypalService;

public class Programa {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Digite os dados do contrato");
		System.out.print("Numero: ");
		int number = sc.nextInt();
		System.out.print("Data do contrato (dd/MM/yyyy): ");
		Date date = sdf.parse(sc.next());
		System.out.print("Valor total: ");
		double totalValue = sc.nextDouble();
		
		Contract contract = new Contract(number, date, totalValue);
		
		System.out.print("Digite a qunatidade de parcelas: ");
		int n = sc.nextInt();
		
		ContractService contractService = new ContractService(new PaypalService());
		
		contractService.processContract(contract, n);
		
		System.out.println("PARCELAS:");
		for (Installment x : contract.getInstallments()) {
			System.out.println(x);
		}
		
		sc.close();
	}
}