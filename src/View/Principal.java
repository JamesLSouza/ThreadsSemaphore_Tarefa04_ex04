package View;

import java.util.concurrent.Semaphore;

import Controller.ThreadDeposito;
import Controller.ThreadSaque;

public class Principal {

	public static void main(String[] args) {
		Semaphore Deposito = new Semaphore(1);
		Semaphore Saque = new Semaphore(1);
		for (int i = 0; i < 20; i++) {
			int tipo = (int) (Math.random() * 2);
			int id = (int) (Math.random() * 10000);
			double saldo = Math.round((Math.random() * 10000) * 100) / 100;
			double Transacao = Math.round((Math.random() * 10000) * 100) / 100;
			if (tipo == 0) {
				ThreadDeposito deposito = new ThreadDeposito(id, saldo, Transacao, Deposito);
				deposito.start();
			} else if (tipo == 1) {
				ThreadSaque saque = new ThreadSaque(id, saldo, Transacao, Saque);
				saque.start();
			}
		}

	}

}
