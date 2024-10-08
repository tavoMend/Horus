package modelo;
public class Periodo {
    
    private int idPeriodo;
    private String periodo;

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        if (idPeriodo <= 0) {
            throw new IllegalArgumentException("El ID del período debe ser un número positivo.");
        }
        this.idPeriodo = idPeriodo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        if (periodo == null || periodo.trim().isEmpty()) {
            throw new IllegalArgumentException("El período no puede ser nulo o vacío.");
        }
        this.periodo = periodo;
    }
}
