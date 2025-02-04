package main;

import java.util.Scanner;
import java.util.ArrayList;

class Factory {
	String name;
	ArrayList<Machine> machines;

	Factory(String name_) {
		name = name_;

		machines = new ArrayList<Machine>();
	}

	void addMachine(Machine machine) {
		machines.add(machine);
	}

	ArrayList<Machine> getMachines() {
		return machines;
	}

	String getName() {
		return name;
	}
}

class Machine {
	String type;
	String model;
	Worker operator;

	Machine(String type_, String model_, Worker operator_) {
		type = type_;
		model = model_;
		operator = operator_;
	}

	String getMachineDetails() {
		return String.format("Koneen tiedot: %s, %s\nTyöntekijän tiedot: %s, %s\n", type, model, operator.name, operator.role);
	}
}

class Worker {
	String name;
	String role;

	Worker(String name_, String role_) {
		name = name_;
		role = role_;
	}

	String getWorkerName() {
		return name;
	}
	String getWorkerRole() {
		return role;
	}
}

/**
* Actual implementation
**/
class Impl {
	Scanner in;

	Factory factory;

	Impl(String[] args) {
		in = new Scanner(System.in);

		System.out.println("Anna tehtaalle nimi:");

		factory = new Factory(in.nextLine());
	}

	void close() {
		in.close();

		System.out.println("Kiitos ohjelman käytöstä.");
	}

	void lisaaKone() {
		System.out.println("Anna koneen tyyppi:");

		String type = in.nextLine();

		System.out.println("Anna koneen malli:");

		String model = in.nextLine();

		System.out.println("Anna työntekijän nimi:");

		String name = in.nextLine();

		System.out.println("Anna työntekijän ammattinimike:");

		String role = in.nextLine();

		Worker worker = new Worker(name, role);

		Machine machine = new Machine(type, model, worker);

		factory.addMachine(machine);
	}

	void listaaKaikkiKoneet() {
		System.out.println(String.format("Tehtaasta %s löytyy seuraavat koneet:", factory.getName()));

		for (Machine m: factory.getMachines()) {
			System.out.println(m.getMachineDetails());
		}
	}

	boolean run() {
		System.out.println("1) Lisää kone");
		System.out.println("2) Listaa kaikki koneet");

		System.out.println("0) Lopeta ohjelma");

		int selection = Integer.parseInt(in.nextLine());

		if (selection == 0) {
			return false;

		} else if (selection == 1) {
			lisaaKone();

		} else if (selection == 2) {
			listaaKaikkiKoneet();

		} else {
			System.out.println("Tuntematon valinta, yritä uudestaan.");
		}

		return true;
	}
}

/**
* Impl runner, do not edit.
*/
public class App {
	public static void main(String[] args) {
		Impl impl = new Impl(args);

		while (impl.run()) {}

		impl.close();
	}
}
