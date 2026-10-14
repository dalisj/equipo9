
public class Main {

    private static HospitalEmergency crearListaDePrueba() {
        HospitalEmergency lista = new HospitalEmergency();
        lista.InsertarPorPrioridad(new Paciente("HC-001", "Juan", 2, 30));
        lista.InsertarPorPrioridad(new Paciente("HC-002", "Maria", 5, 10));
        lista.InsertarPorPrioridad(new Paciente("HC-003", "Pedro", 1, 60));
        lista.InsertarPorPrioridad(new Paciente("HC-004", "Ana", 4, 15));
        return lista;
    }

    public static void main(String[] args) {

        System.out.println("=== CASOS POSITIVOS ===");

        
        System.out.println("Caso 1: insertar pacientes con prioridades 2, 5, 1, 4");
        HospitalEmergency lista = new HospitalEmergency();
        lista.InsertarPorPrioridad(new Paciente("HC-001", "Juan", 2, 30));
        lista.InsertarPorPrioridad(new Paciente("HC-002", "Maria", 5, 10));
        lista.InsertarPorPrioridad(new Paciente("HC-003", "Pedro", 1, 60));
        lista.InsertarPorPrioridad(new Paciente("HC-004", "Ana", 4, 15));
        System.out.println(" Recorrido hacia adelante (siguiente):");
        lista.mostrar();         
        System.out.println(" Recorrido hacia atrás (anterior):");
        lista.mostrarInverso();   

        
        System.out.println("Caso 2: verificar orden de atención");
        HospitalEmergency paraAtender = crearListaDePrueba();
        int turno = 1;
        Paciente atendido = paraAtender.AtenderSiguientePaciente();
        while (atendido != null) {
            System.out.println("  Turno " + turno + ": " + atendido.nombre + " (prioridad " + atendido.nivelPrioridad + ")");
            turno++;
            atendido = paraAtender.AtenderSiguientePaciente();
        }
        
        System.out.println(" Lista después de atender a todos:");
        paraAtender.mostrar();    

        
        System.out.println("Caso 3: verificar tiempos de espera (límite 25 min)");
        HospitalEmergency paraCriticos = crearListaDePrueba();
        System.out.println("  Críticos: " + paraCriticos.PacientesEnEsperaCritica(25));   

        
        System.out.println("Caso 4: reordenamiento por tiempo de espera");
        HospitalEmergency paraReordenar = crearListaDePrueba();
        paraReordenar.ReordenarPorTiempoEspera();
        System.out.println(" Recorrido hacia adelante (siguiente):");
        paraReordenar.mostrar();          
        System.out.println(" Recorrido hacia atrás (anterior):");
        paraReordenar.mostrarInverso();   

        System.out.println("\n=== CASOS NEGATIVOS ===");
        HospitalEmergency vacia = new HospitalEmergency();

        
        System.out.println("Caso negativo 1: insertar un paciente null");
        try {
            vacia.InsertarPorPrioridad(null);
        } catch (IllegalArgumentException e) {
            System.out.println("  Error controlado: " + e.getMessage());
        }

        
        System.out.println("Caso negativo 2: paciente con prioridad inválida (7)");
        try {
            vacia.InsertarPorPrioridad(new Paciente("HC-005", "Carlos", 7, 15));
        } catch (IllegalArgumentException e) {
            System.out.println("  Error controlado: " + e.getMessage());
        }

       
        System.out.println("Caso negativo 3: atender siguiente paciente con la lista vacía");
        Paciente resultado = vacia.AtenderSiguientePaciente();
        System.out.println("  Resultado: " + (resultado == null ? "no hay pacientes para atender" : resultado.nombre));
    }
}
