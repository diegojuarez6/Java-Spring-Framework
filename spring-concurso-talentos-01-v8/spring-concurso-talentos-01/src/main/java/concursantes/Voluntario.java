package concursantes;

import org.springframework.stereotype.Component;

@Component
public class Voluntario implements Pensador {
	private String pensamientos;

	public void pensarEnAlgo(String pensamientos) {
		System.out.println("Ejecutando método pensar en Algo ");
		this.pensamientos = pensamientos;
	}

	public String getPensamientos() {
		return this.pensamientos;
	}
}