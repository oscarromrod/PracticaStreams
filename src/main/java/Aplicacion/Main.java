package Aplicacion;

import Models.Registro;

import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private List<Registro> registros;

    public Main() {
        this.registros = new ArrayList<>();
        cargarDatosEjemplo();
    }

    private void cargarDatosEjemplo() {
        for (int i = 0; i < 100; i++) {
            registros.add(new Registro(LocalDateTime.now().minusMinutes(1), Math.random() * (50 - (-10) + 1) + (-10), Math.random() * 100));
        }
    }

    //1. Filtrar los registros de temperatura que sean mayores a 25 grados, la humedad sea menor a 70 y la
    //fecha sea anterior a la fecha actual, y mostrarlos.
    public List<Registro> mayor25Menor70(){
        return registros.stream()
                .filter(r -> r.getTemperatura() > 25.0)
                .filter(r -> r.getHumedad() < 70.0)
                .filter(r -> r.getFechaHora().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());

    }

    //2. Encontrar el registro con la temperatura más alta y mostrar el registro completo.
    public Optional<Registro> temperaturaMasAlta(){
        return registros.stream()
                .max(Comparator.comparingDouble(Registro::getTemperatura));
    }

    //3. Obtener una lista con las fechas/horas de todas las tomas de datos.
    public List<LocalDateTime> listaFechasHoras(){
        return registros.stream()
                .map(Registro::getFechaHora)
                .collect(Collectors.toList());
    }

    //4. Incrementar en 5 unidades la humedad de todos los registros y mostrar las temperaturas,
    //humedades y fechas/horas actualizadas.
    public List<Registro> incrementar5(){
        return registros.stream()
                .map(r -> new Registro(r.getFechaHora(), r.getTemperatura(), r.getHumedad() + 5))
                .collect(Collectors.toList());
    }

    //5. Encontrar el registro con la temperatura más baja que tenga una humedad mayor a 80 y mostrar la
    //temperatura, humedad y fecha.
    public Optional<Registro> teperaturaMinima(){
        return registros.stream()
                .filter(registro -> registro.getHumedad() > 80.0)
                .min(Comparator.comparingDouble(Registro::getTemperatura));
    }

    //6. Verificar si algún registro tiene una temperatura mayor a 30 grados, humedad mayor a 90 y la fecha
    //es de hoy. Mostrar un mensaje indicando si hay algún registro que cumple esta condición o no.
    public Boolean verificar(){
        return registros.stream()
                .anyMatch(r -> r.getTemperatura() > 30 && r.getHumedad() > 90 && r.getFechaHora().equals(LocalDateTime.now()));
    }

    //7. Muestra 10 registros saltándote los 5 primeros
    public List<Registro> saltar5primeros(){
        return registros.stream()
                .skip(5)
                .limit(10)
                .collect(Collectors.toList());
    }

    //8. Muestra los registros ordenados por fecha (sorted(Comparator))
    public List<Registro> fechaOrdenada(){
        return registros.stream()
                .sorted(Comparator.comparing(Registro::getFechaHora).reversed())
                .collect(Collectors.toList());
    }

    //9. Cuenta los registros que tengan temperatura mayor a 35 grados (count()).
    public Long contarTemperatura(){
        return registros.stream()
                .filter(registro -> registro.getTemperatura() > 35.0)
                .count();
    }

    //10. Calcular la temperatura promedio de todos los registros (transformarlo en Stream<Double> y
    //llamar a average()).
    public OptionalDouble promedio(){
        return registros.stream()
                .mapToDouble(Registro::getTemperatura)
                .average();
    }

    static void main() {
       Main app = new Main();

        IO.println("================ CONSULTA 1 ================");
        app.mayor25Menor70().forEach(IO::println);

        IO.println("================ CONSULTA 2 ================");
        app.temperaturaMasAlta().ifPresent(IO::println);

        IO.println("================ CONSULTA 3 ================");
        app.listaFechasHoras().forEach(IO::println);

        IO.println("================ CONSULTA 4 ================");
        app.incrementar5().forEach(IO::println);

        IO.println("================ CONSULTA 5 ================");
        app.teperaturaMinima().ifPresent(IO::println);

        IO.println("================ CONSULTA 6 ================");
        if (app.verificar()) {
            IO.println("Encontrado");
        } else {
            IO.println("No encontrado");
        }

        IO.println("================ CONSULTA 7 ================");
        app.saltar5primeros().forEach(IO::println);

        IO.println("================ CONSULTA 8 ================");
        app.fechaOrdenada().forEach(IO::println);

        IO.println("================ CONSULTA 9 ================");
        IO.println(app.contarTemperatura());

        IO.println("================ CONSULTA 10 ================");
        if (app.promedio().isPresent()){
            IO.println("La temperatura poromedio es: " + app.promedio().getAsDouble());
        } else {
            IO.println("No hay datos");
        }
    }



}
