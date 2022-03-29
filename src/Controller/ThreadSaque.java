package Controller;

import java.util.concurrent.Semaphore;

public class ThreadSaque extends Thread {
	private int idConta;
	private double saldo;
	private double Transacao;
	private Semaphore SemSaque;

	public ThreadSaque(int idConta, double saldoConta, double valorTransacao, Semaphore SemSaque) {
		this.idConta = idConta;
		this.saldo = saldoConta;
		this.Transacao = valorTransacao;
		this.SemSaque = SemSaque;
	}

	@Override
	public void run() {
		try {
			SemSaque.acquire();
			debitar();
			sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			SemSaque.release();
		}
	}

	public void debitar() {
		System.out.format("Conta %d - Saldo Anterior: R$ %.2f Saque no valor de: R$ %.2f Novo saldo: R$ %.2f%n",idConta, saldo, Transacao, saldo + Transacao);
		this.saldo += Transacao;
	}
}